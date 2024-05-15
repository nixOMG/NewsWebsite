package controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Article;
import entity.Category;
import entity.Favourite;
import entity.User;
import entityManager.ArticleDB;
import entityManager.FavouriteDB;
import utils.DBUtil;


/**
 * Servlet implementation class ArticleController
 */
@WebServlet({ "/ArticleController", "/article" })
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		System.out.println("check action Chapter servlet: " + action);	
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
		if (action != null && action.equals("view-article")) {
			viewArticle(request, response);
		} else if (action != null && action.equals("search-article")){
			searchArticle(request, response);
		} else if (user!=null && action != null && action.equals("add-to-favourite")) {
			handleAddFavourite(request, response);
		} else if (user!=null && action != null && action.equals("remove-favourite")){
			handleRemoveFavourite(request, response);
		} 
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
		
			
	}

    private void searchArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String searchTerm = request.getParameter("searchTerm");
        
        EntityManager entityManager = DBUtil.getEntityManager();
        ArticleDB articleDB = new ArticleDB(entityManager);
        
        List<Article> searchResults = articleDB.searchArticle(searchTerm);
        
        // Set the search results in the request attributes
        request.setAttribute("searchResults", searchResults);
        
        // Forward to a search results page
        RequestDispatcher rd = request.getRequestDispatcher("/search-results.jsp");
        rd.forward(request, response);
    }

	private void viewArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");

	    int articleId = Integer.parseInt(request.getParameter("articleId"));
	    EntityManager entityManager = DBUtil.getEntityManager();
	    try {
	        HttpSession session = request.getSession();
	        User user = (User) session.getAttribute("loggedInUser");

	        ArticleDB articleDB = new ArticleDB(entityManager);            
	        Article article = articleDB.getArticleById(articleId);
	        FavouriteDB favouriteDB = new FavouriteDB(entityManager);
	        boolean isFavourite = favouriteDB.isFavourite(user, articleId);

	        // Get the related articles
	        Category category = article.getCategory();
	        List<Article> relatedArticles = articleDB.getArticlesByRelatedCategory(category, articleId);
	        List<Article> sortedArticlesByView = articleDB.getArticlesSortedByViews();

	        request.setAttribute("isFavourite", isFavourite);
	        request.setAttribute("article", article);
	        request.setAttribute("relatedArticles", relatedArticles); // Set related articles in request
	        request.setAttribute("sortedArticlesByView", sortedArticlesByView); 
	        
	        RequestDispatcher rd = request.getRequestDispatcher("/single_page.jsp");
	        rd.forward(request, response);
	    } catch (ServletException | IOException e) {
	        e.printStackTrace();
	        // Forward to an error page or handle the error appropriately
	        RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
	        dispatcher.forward(request, response);
	    }
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void handleRemoveFavourite(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");

        try {
        	if (user!=null) {
        		EntityManager entityManager = DBUtil.getEntityManager();
                FavouriteDB favouriteDB = new FavouriteDB(entityManager);
                favouriteDB.removeFavourite(user, articleId);
                response.sendRedirect(request.getHeader("referer"));
        	}
        	else {
        		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
        	}
            

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

	private void handleAddFavourite(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int articleId=Integer.parseInt(request.getParameter("articleId"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		try {
			if (user!=null) {
				EntityManager entityManager = DBUtil.getEntityManager();

				ArticleDB articleDB=new ArticleDB(entityManager);
				FavouriteDB favouriteDB=new FavouriteDB(entityManager);
				Article article = articleDB.getArticleById(articleId);				
				Favourite favourite=new Favourite();
				
				favourite.setArticle(article);
				favourite.setUser(user);
				
				favouriteDB.addFavourite(favourite);
				response.sendRedirect(request.getHeader("referer"));
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
