package controller;

import java.io.IOException;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.User;

import entityManager.UserDB;
import utils.DBUtil;


@WebServlet({ "/UserController", "/manage-user-info" })
@MultipartConfig
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		System.out.println("check action UserController servlet: " + action);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		System.out.println("user: " + user.getUsername());

		request.setAttribute("userId", user.getUserId());

		if (user != null && (user.getRole().getRoleId() >= 2 && user.getRole().getRoleId() <= 5)) {
			if (action != null && action.equals("get-page-edit-user-info")) {
				getPageEditUserInfo(request, response);
			} else {
				getUserInfoPage(request, response);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
	}
		
	private void getPageEditUserInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int userId = (int) request.getAttribute("userId");
		System.out.println("get page edit info check userId: " + userId);
		request.setAttribute("userId", userId);
		try {
			EntityManager entityManager = DBUtil.getEntityManager();

			UserDB userDB = new UserDB(entityManager);
			User user = userDB.getUserById(userId);

			entityManager.close();

			if (user != null) {
				request.setAttribute("user", user);
				RequestDispatcher dispatcher = request.getRequestDispatcher("edit-user-info.jsp");
				dispatcher.forward(request, response);
			} else {
				System.out.println("User variable is null!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
	}
	private void getUserInfoPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int userId = (int) request.getAttribute("userId");
		System.out.println(userId);
		try {
			EntityManager entityManager = null;
			try {
				entityManager = DBUtil.getEntityManager();
				UserDB userDB = new UserDB(entityManager);
				User user = userDB.getUserById(userId);
				request.setAttribute("userId", userId);
				request.setAttribute("user", user);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/manage-user-info.jsp");
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String action = request.getParameter("action");
        System.out.println("check action UserController Servlet: " + action);
        
        HttpSession session = request.getSession();        
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null && (user.getRole().getRoleId() == 2 || user.getRole().getRoleId() == 3 || user.getRole().getRoleId() == 4 || user.getRole().getRoleId() == 5 || user.getRole().getRoleId() == 6)) {
            if (action != null && action.equals("edit-user-info")) {
                handleEditUserInfo(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }
	
	private void handleEditUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		EntityManager entityManager = DBUtil.getEntityManager();
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			String username = request.getParameter("username");
		    String fullname= request.getParameter("fullname");
		    String idenId= request.getParameter("idenId");
		    int age= Integer.parseInt(request.getParameter("age"));
		    String address= request.getParameter("address");
		    String phone= request.getParameter("phone");
		    String bankaccount= request.getParameter("bankaccount");
		    Date dob = Date.valueOf(request.getParameter("dob"));
		    
		    System.out.println("check utf-8 from jsp: " + username);
			    
			UserDB userDB=new UserDB(entityManager);
			User user=userDB.getUserById(userId);
			
			if (username != null && fullname != null && idenId != null
					&& address != null && phone != null && bankaccount != null && dob !=null) {
				user.setUsername(username);
				user.setFullname(fullname);
				user.setIdentificationId(idenId);
				user.setAge(age);
				user.setAddress(address);
				user.setPhone(phone);
				user.setBankAccount(bankaccount);
				user.setDob(dob);
				
				if (userDB.updateUser(user)) {
					request.setAttribute("userId", userId);
					response.sendRedirect(request.getContextPath() + "/manage-user-info");
				}
			} else {
				System.out.println("Cant finish the process edit user info because 1 of the variables is null!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
}