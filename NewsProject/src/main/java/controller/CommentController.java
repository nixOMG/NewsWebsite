package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Article;
import entity.Comment;
import entity.User;
import entityManager.ArticleDB;
import entityManager.CommentDB;
import utils.DBUtil;

/**
 * Servlet implementation class CommentController
 */
@WebServlet({ "/CommentController", "/comment" })
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		System.out.println("check action Chapter servlet: " + action);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if (user != null) {
			System.out.println("writer: " + user.getUsername());
			request.setAttribute("writerId", user.getUserId());
			if (action != null && action.equals("add-comment")) {
				handleAddComment(request, response);
			} else if (action != null && action.equals("edit-comment")) {
				handleEditComment(request, response);
			} else if (action != null && action.equals("delete-comment")) {
				handleDeleteComment(request, response);
			} 
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void handleDeleteComment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int commentId = Integer.parseInt(request.getParameter("commentId"));

        try {
        	EntityManager entityManager = DBUtil.getEntityManager();
            CommentDB commentDB = new CommentDB(entityManager);

            Comment comment=commentDB.getCommentById(commentId);
            int articleId = comment.getArticle().getArticleId();
            commentDB.deleteComment(comment);

            response.sendRedirect("article?action=view-article&articleId=" + articleId);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error deleting comment.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
		
	}

	private void handleEditComment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int commentId = Integer.parseInt(request.getParameter("commentId"));
        String content = request.getParameter("content");

        try {
        	EntityManager entityManager = DBUtil.getEntityManager();
            CommentDB commentDB = new CommentDB(entityManager);
            Comment comment = commentDB.getCommentById(commentId);
            comment.setContent(content);
            comment.setCommentTime(new Timestamp(System.currentTimeMillis()));

            commentDB.updateComment(comment);

            response.sendRedirect("article?action=view-article&articleId=" + comment.getArticle().getArticleId());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error adding comment.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
		
	}

	private void handleAddComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int articleId = Integer.parseInt(request.getParameter("articleId"));
        String content = request.getParameter("content");

        try {
        	EntityManager entityManager = DBUtil.getEntityManager();
            ArticleDB articleDB = new ArticleDB(entityManager);
            CommentDB commentDB = new CommentDB(entityManager);

            Article article = articleDB.getArticleById(articleId);
            Comment comment = new Comment();
            comment.setArticle(article);
            comment.setCommentor(user);
            comment.setContent(content);
            comment.setCommentTime(new Timestamp(System.currentTimeMillis()));

            commentDB.addComment(comment);

            response.sendRedirect("article?action=view-article&articleId=" + articleId);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error adding comment.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
		
	}

}
