package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Role;
import entity.User;
import entityManager.RoleDB;
import entityManager.UserDB;
import utils.DBUtil;
import utils.SendMail;

@WebServlet({ "/UserController", "/admin" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		System.out.println("check action UserController Servlet: " + action);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");

		if (user != null && user.getRole().getRoleId() == 6) {
			if (action != null && (action.equals("get-page-add-account"))) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("add-account.jsp");
				dispatcher.forward(request, response);
			} else if (action != null && action.equals("get-page-edit-account")) {
				getPageEditDeleteUser(request, response, "edit-account");
			} else if (action != null && action.equals("get-page-delete-account")) {
				getPageEditDeleteUser(request, response, "delete-account");
			} else if (action != null && action.equals("get-page-manage-account")) {
				getAllUsers(request, response);
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

	private void getPageEditDeleteUser(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			EntityManager entityManager = DBUtil.getEntityManager();
			entityManager.clear();

			UserDB userDB = new UserDB(entityManager);
			User user = userDB.getUserById(userId);

			RoleDB roleDB = new RoleDB(entityManager);
			List<Role> roles = roleDB.getAllRoles();

			request.setAttribute("user", user);
			request.setAttribute("roles", roles);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url + ".jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	private void getAllUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		EntityManager entityManager = DBUtil.getEntityManager();
		entityManager.clear();

		UserDB userDB = new UserDB(entityManager);
		List<User> users = userDB.getAllUsers();

		request.setAttribute("users", users);
		RequestDispatcher rd = request.getRequestDispatcher("/manage-account.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		System.out.println("check action UserController Servlet: " + action);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");

		if (user != null && user.getRole().getRoleId() == 6) {
			if (action != null && (action.equals("add-account"))) {
				addUser(request, response);
			} else if (action != null && action.equals("edit-account")) {
				editUser(request, response);
			} else if (action != null && action.equals("delete-account")) {
				deleteUser(request, response);
			} else {
				getAllUsers(request, response);
			}
		} else if (user == null || user.getRole().getRoleId() == 3) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void editUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String fullname = request.getParameter("fullname");
			String idenId = request.getParameter("idenId");
			int age = Integer.parseInt(request.getParameter("age"));
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			Date dob = Date.valueOf(request.getParameter("dob"));
			int roleId = Integer.parseInt(request.getParameter("roleId"));

			System.out.println("check utf-8 from jsp: " + username);
			if (username != null && !email.isBlank()) {
				EntityManager entityManager = DBUtil.getEntityManager();
				entityManager.clear();
				RoleDB roleDB = new RoleDB(entityManager);
				Role role = roleDB.getRoleById(roleId);

				UserDB userDB = new UserDB(entityManager);
				User user = userDB.getUserById(userId);
				user.setEmail(email);
				user.setUsername(username);
				user.setFullname(fullname);
				user.setIdentificationId(idenId);
				user.setAge(age);
				user.setAddress(address);
				user.setPhone(phone);
				user.setDob(dob);
				user.setRole(role);
				if (userDB.updateUser(user)) {
					getAllUsers(request, response);
				}
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			EntityManager entityManager = DBUtil.getEntityManager();
			entityManager.clear();

			UserDB userDB = new UserDB(entityManager);
			User user = userDB.getUserById(userId);
			if (userDB.deleteUser(user)) {
				getAllUsers(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String fullname = request.getParameter("fullname");
			String idenId = request.getParameter("idenId");
			int age = Integer.parseInt(request.getParameter("age"));
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			Date dob = Date.valueOf(request.getParameter("dob"));
			int roleId = Integer.parseInt(request.getParameter("roleId"));
			System.out.println("check utf-8 from jsp: " + username);

			// Check the input fields
			if (email.trim() != null && username.trim() != null && password.trim() != null) {
				EntityManager entityManager = DBUtil.getEntityManager();
				UserDB userDb = new UserDB(entityManager);
				RoleDB roleDB = new RoleDB(entityManager);
				Role role = roleDB.getRoleById(roleId);

				// Check if the email has already existed
				if (userDb.getUserByEmail(email) == null) {
					User newUser = new User();

					newUser.setUsername(username);
					newUser.setPass(password);
					newUser.setFullname(fullname);
					newUser.setIdentificationId(idenId);
					newUser.setAge(age);
					newUser.setAddress(address);
					newUser.setPhone(phone);
					newUser.setDob(dob);
					newUser.setEmail(email);
					// 1- Not activated, 2-User, 3 - Subscriber, 4-Writer, 5-Editor, 6-Admin
					newUser.setRole(role);

					if (userDb.addUser(newUser)) {
						getAllUsers(request, response);
					}
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
					dispatcher.forward(request, response);
				}
			}
		
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
