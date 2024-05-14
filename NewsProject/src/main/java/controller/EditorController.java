package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Article;
import entity.Category;
import entity.Tag;
import entity.User;
import entityManager.ArticleDB;
import entityManager.CategoryDB;
import entityManager.TagDB;
import utils.DBUtil;

/**
 * Servlet implementation class EditorController
 */
@WebServlet({ "/EditorController", "/editor-manage-articles" })
@MultipartConfig
public class EditorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditorController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		System.out.println("check action Chapter servlet: " + action);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if (user != null) {
			System.out.println("editor: " + user.getUsername());
			request.setAttribute("editorId", user.getUserId());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

		if (user != null && user.getRole().getRoleId() == 5) {
			if (action != null && action.equals("get-detail-article-page")) {
				getDetailArticlePage(request, response);
			} else if (action!=null && action.equals("get-articles-by-status")) {
				String status = request.getParameter("status");
				getArticlesByStatus(request, response, status);
			}
			else {
				getPendingArticles(request, response);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void getArticlesByStatus(HttpServletRequest request, HttpServletResponse response, String status) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int editorId = (int) request.getAttribute("editorId");
		System.out.println(editorId);
		System.out.println(status);
		try {

			EntityManager entityManager = null;

			try {
				entityManager = DBUtil.getEntityManager();

				ArticleDB articleDB = new ArticleDB(entityManager);
				CategoryDB categoryDB = new CategoryDB(entityManager);

				List<Integer> categoryIds = categoryDB.getCategoryByUserId(editorId);
				List<Article> articles=new ArrayList<>();

				if (!"all".equals(status)) {
					articles= articleDB.getArticlesByCategoryAndStatus(categoryIds, status);
				}
				else if ("all".equals(status)) {
					articles = articleDB.getArticlesByCategories(categoryIds);
				}
				request.setAttribute("status", status);
				request.setAttribute("editorId", editorId);
				request.setAttribute("articles", articles);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/manage-article-editor.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (entityManager != null && entityManager.isOpen()) {
					entityManager.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void getDetailArticlePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int editorId = (int) request.getAttribute("editorId");
		System.out.println("get page view chapter check editorId: " + editorId);
		request.setAttribute("editorId", editorId);
		try {
			int articleId = Integer.parseInt(request.getParameter("articleId"));

			EntityManager entityManager = DBUtil.getEntityManager();

			ArticleDB articleDB = new ArticleDB(entityManager);
			Article article = articleDB.getArticleById(articleId);

			CategoryDB categoryDB = new CategoryDB(entityManager);
			List<Category> categories = categoryDB.getAllCategories();

			TagDB tagDB = new TagDB(entityManager);
			List<Tag> tags = tagDB.getAllTags();
			entityManager.close();

			if (article != null && categories != null && tags != null) {
				request.setAttribute("article", article);
				request.setAttribute("categories", categories);
				request.setAttribute("tags", tags);

				RequestDispatcher dispatcher = request.getRequestDispatcher("view-article-editor.jsp");
				dispatcher.forward(request, response);
			} else {
				System.out.println("Articles or categories or tags variable is null!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void getPendingArticles(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int editorId = (int) request.getAttribute("editorId");
		System.out.println(editorId);
		try {

			EntityManager entityManager = null;

			try {
				entityManager = DBUtil.getEntityManager();

				ArticleDB articleDB = new ArticleDB(entityManager);
				CategoryDB categoryDB = new CategoryDB(entityManager);

				List<Integer> categoryIds = categoryDB.getCategoryByUserId(editorId);

				List<Article> articles = articleDB.getArticlesByCategories(categoryIds);

				request.setAttribute("editorId", editorId);
				request.setAttribute("articles", articles);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/manage-article-editor.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (entityManager != null && entityManager.isOpen()) {
					entityManager.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println("Check action doPost article: " + action);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");

		if (user != null) {
			System.out.println("writer: " + user.getUsername());
			int editorId = user.getUserId();
			System.out.println("doPost check editorId: " + editorId);
			request.setAttribute("editorId", user.getUserId());
		} else {
			response.sendRedirect(request.getContextPath() + "login.jsp");
		}

		if (user != null && (user.getRole().getRoleId() == 5)) {
			if (action != null && action.equals("approve-article-editor")) {
				handleApproveArticle(request, response);
			} else if (action != null && action.equals("decline-article-editor")) {
				handleDeclineArticle(request, response);
			} else if (action != null && action.equals("delete-article-editor")) {
				handleDeleteArticle(request, response);
			} else {
				getPendingArticles(request, response);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void handleDeleteArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		int editorId = (int) request.getAttribute("editorId");
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		
		System.out.println("Check bookId delete " + editorId);
		
		EntityManager entityManager = DBUtil.getEntityManager();
		
		
		ArticleDB articleDB = new ArticleDB(entityManager);
		Article article = articleDB.getArticleById(articleId);
		
		boolean isDeleted = articleDB.deleteArticle(article);
		System.out.print(isDeleted);
		request.setAttribute("editorId", editorId);
		String jsonResponse = "{ \"success\": " + isDeleted + " }";

		// Set the response content type to JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// Send the JSON response
		PrintWriter out = response.getWriter();
		out.print(jsonResponse);
		out.flush();
	}

	private void handleDeclineArticle(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int editorId = (int) request.getAttribute("editorId");
		System.out.println("Check editorId edit " + editorId);

		EntityManager entityManager = DBUtil.getEntityManager();
		try {
			int articleId = Integer.parseInt(request.getParameter("articleId"));

			ArticleDB articleDB = new ArticleDB(entityManager);
			Article article = articleDB.getArticleById(articleId);
			article.setStatus("Declined");

			if (articleDB.updateArticle(article)) {
				request.setAttribute("editorId", editorId);
				response.sendRedirect(request.getContextPath() + "/editor-manage-articles");
			} else {
				System.out.println("Cant finish the process approve article because 1 of the variables is null!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

	}

	private void handleApproveArticle(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int editorId = (int) request.getAttribute("editorId");
		System.out.println("Check editorId edit " + editorId);

		EntityManager entityManager = DBUtil.getEntityManager();
		try {
			int articleId = Integer.parseInt(request.getParameter("articleId"));

			ArticleDB articleDB = new ArticleDB(entityManager);
			Article article = articleDB.getArticleById(articleId);
			article.setStatus("Approved");

			if (articleDB.updateArticle(article)) {
				request.setAttribute("editorId", editorId);
				response.sendRedirect(request.getContextPath() + "/editor-manage-articles");
			} else {
				System.out.println("Cant finish the process approve article because 1 of the variables is null!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

	}

}
