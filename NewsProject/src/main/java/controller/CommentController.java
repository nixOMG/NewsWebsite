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

import entity.Comment;
import entity.User;
import entityManager.CommentDB;
import utils.DBUtil;

@WebServlet({ "/CommentController", "/comments" })
public class CommentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        System.out.println("check action CommentController Servlet: " + action);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");

        if (user != null && user.getRole().getRoleId() == 6) {
            if (action != null && action.equals("delete-comment")) {
                deleteComment(request, response);
            } else if (action != null && action.equals("get-page-manage-comments")) {
                getAllComments(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                dispatcher.forward(request, response);
            }
        } else if (user == null || (user.getRole().getRoleId() >= 1 && user.getRole().getRoleId() < 6)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        System.out.println("check action CommentController Servlet: " + action);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");

        if (user != null && user.getRole().getRoleId() == 6) {
            if (action != null && action.equals("delete-comment")) {
                deleteComment(request, response);
            } else {
                getAllComments(request, response);
            }
        } else if (user == null || (user.getRole().getRoleId() >= 1 && user.getRole().getRoleId() < 6)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void getAllComments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        EntityManager entityManager = DBUtil.getEntityManager();
        entityManager.clear();

        CommentDB commentDB = new CommentDB(entityManager);
        List<Comment> comments = commentDB.getAllComments();

        request.setAttribute("comments", comments);
        RequestDispatcher rd = request.getRequestDispatcher("/manage-comments.jsp");
        rd.forward(request, response);
    }

    private void deleteComment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            int commentId = Integer.parseInt(request.getParameter("commentId"));
            EntityManager entityManager = DBUtil.getEntityManager();
            entityManager.clear();

            CommentDB commentDB = new CommentDB(entityManager);
            Comment comment = commentDB.getCommentById(commentId);

            if (comment != null && commentDB.deleteComment(comment)) {
                getAllComments(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
			e.getStackTrace();
        }
    }
}
