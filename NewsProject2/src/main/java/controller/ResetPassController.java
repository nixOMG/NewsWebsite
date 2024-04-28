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
import entityManager.UserDB;

/**
 * Servlet implementation class ResetPassController
 */
@WebServlet("/resetPass")
public class ResetPassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPassController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	EntityManager entityManager = DBUtil.getEntityManager();
        String token = request.getParameter("token");
        System.out.println("Check doGet token: "+token);
        UserDB userDb=new UserDB(entityManager);
        User user = userDb.getUserByLink(token);
        
		if (user != null) {
        	request.getSession().setAttribute("token", token);
            response.sendRedirect("newPassword.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
        System.out.println("check action: " + action);
		if(action != null && action.equals("handle-change-pass")) {
	        handleChangePass(request, response);
		}
	}
    private void handleChangePass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	EntityManager entityManager = DBUtil.getEntityManager();
        String token = request.getParameter("token");
        System.out.println("check token:" + token);
        
        UserDB userDb=new UserDB(entityManager);
        User user = userDb.getUserByLink(token);

        if (user != null) {
        	String newPassword = request.getParameter("password");
        	user.setPass(newPassword);
        	user.setVerifylink(null);
        	userDb.updateUser(user);
            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
		
    }

}
