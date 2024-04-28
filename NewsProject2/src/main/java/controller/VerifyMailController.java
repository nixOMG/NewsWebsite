package controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import utils.DBUtil;
import entityManager.RoleDB;
import entityManager.UserDB;

/**
 * Servlet implementation class VerifyMailController
 */
@WebServlet("/verify")
public class VerifyMailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyMailController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unused")
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String link = request.getParameter("link");
        EntityManager entityManager = DBUtil.getEntityManager();
        UserDB userDb = new UserDB(entityManager);

        // Look up the user in the database using the link
        User user = userDb.getUserByLink(link);
        RoleDB roleEM=new RoleDB(entityManager);
        user.setRole(roleEM.getRoleById(5));
        if (user != null) {
        	user.setVerifylink(null);
        	userDb.updateUser(user);
        	// Redirect to a success page
            response.sendRedirect(request.getContextPath() + "/success.jsp");
        } else {
            // Redirect to an error page
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

}
