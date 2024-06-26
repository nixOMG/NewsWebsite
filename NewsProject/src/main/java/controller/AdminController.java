package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import entity.Comment;
import entity.Role;
import entity.User;
import entityManager.ArticleDB;
import entityManager.CategoryDB;
import entityManager.CommentDB;
import entityManager.RoleDB;
import entityManager.UserDB;
import utils.DBUtil;
import utils.SendMail;

@WebServlet({ "/AdminController", "/admin" })

public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		System.out.println("check action AdminController Servlet: " + action);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		String role = request.getParameter("role");

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
			} else if (action != null && action.equals("get-page-manage-comment")) {
				
				getAllComments(request, response);
			} else if (action != null && action.equals("get-accounts-by-role")) {
				
				getAccountsByRole(request, response,role);
			}

			else if (action != null && action.equals("get-page-assign-category")) {
				getAllEditor(request, response);
			} else if (action != null && action.equals("assign")) {
				getPageAssignCategory(request, response, "assign");
			}

			else {
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

	private void getPageAssignCategory(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			EntityManager entityManager = DBUtil.getEntityManager();
			entityManager.clear();

			UserDB userDB = new UserDB(entityManager);
			User user = userDB.getUserById(userId);

			CategoryDB categoryDB = new CategoryDB(entityManager);
			List<Category> Categories = categoryDB.getAllCategories();

			request.setAttribute("user", user);
			request.setAttribute("Categories", Categories);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url + ".jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	private void getAllEditor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			EntityManager entityManager = DBUtil.getEntityManager();
			entityManager.clear();

			UserDB userDB = new UserDB(entityManager);
			List<User> users = userDB.getUsersByRoleId(5); // Assuming roleId 5 is for editors

			CategoryDB categoryDB = new CategoryDB(entityManager);
			List<Category> categories = categoryDB.getAllCategories();

			Map<User, List<Category>> userCategoriesMap = new HashMap<>();
			for (User user : users) {
				List<Category> userCategories = categories.stream()
						.filter(category -> category.getUser().getUserId() == user.getUserId())
						.collect(Collectors.toList());
				userCategoriesMap.put(user, userCategories);
			}

			request.setAttribute("userCategoriesMap", userCategoriesMap);
			RequestDispatcher dispatcher = request.getRequestDispatcher("assign-category.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void assignCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));

			int categoryId = Integer.parseInt(request.getParameter("categoryId"));

			EntityManager entityManager = DBUtil.getEntityManager();
			UserDB userDB = new UserDB(entityManager);
			CategoryDB categoryDB = new CategoryDB(entityManager);

			User user = userDB.getUserById(userId);

			Category category = categoryDB.getCategoryById(categoryId);

			List<Category> categories = categoryDB.getAllCategories();
			request.setAttribute("users", user);
			request.setAttribute("categories", categories);

			if (user != null && category != null) {
				category.setUser(user);

				if (categoryDB.updateCategory(category)) {
					getAllEditor(request, response);
					return;
				}
			} else
				response.sendRedirect("error.jsp");

		} catch (Exception e) {
			e.printStackTrace();

			response.sendRedirect("error.jsp");
		}
	}

	private void getAllComments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager entityManager = DBUtil.getEntityManager();
		CommentDB commentDB = new CommentDB(entityManager);

		try {
			List<Comment> comments = commentDB.getAllComments();
			request.setAttribute("comments", comments);
			RequestDispatcher dispatcher = request.getRequestDispatcher("manage-comment.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error retrieving comments.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
	}
	private void viewCommentOnArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    int articleId = Integer.parseInt(request.getParameter("articleId"));
	   
	    response.sendRedirect("article?action=view-article&articleId=" + articleId);
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

            Comment comment = commentDB.getCommentById(commentId);

            commentDB.deleteComment(comment);

            
            
            response.sendRedirect("admin?action=get-page-manage-comment");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error deleting comment.");
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
	 private void getAccountsByRole(HttpServletRequest request, HttpServletResponse response, String role) throws IOException {
	        request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");

	        try {
	            EntityManager entityManager = null;

	            try {
	                entityManager = DBUtil.getEntityManager();

	                UserDB userDB = new UserDB(entityManager);

	                List<User> users = new ArrayList<>();

	                if (!"all".equals(role)) {
	                    users = userDB.getUsersByRole(role);
	                } else {
	                    users = userDB.getAllUsers(); // Assuming you have a method to get all users
	                }

	                int userCount = users.size();

	                request.setAttribute("role", role);
	                request.setAttribute("users", users);
	                request.setAttribute("userCount", userCount);

	                RequestDispatcher dispatcher = request.getRequestDispatcher("/manage-account.jsp");
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

	 
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		System.out.println("check action AdminController Servlet: " + action);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");

		if (user != null && user.getRole().getRoleId() == 6) {
			if (action != null && (action.equals("add-account"))) {
				addUser(request, response);
			} else if (action != null && action.equals("edit-account")) {
				editUser(request, response);
			} else if (action != null && action.equals("delete-account")) {
				deleteUser(request, response);

			} else if (action != null && action.equals("assign")) {
				assignCategory(request, response);

			} else if (action != null && action.equals("delete-comment")) {
				handleDeleteComment(request, response);
			}else if (action != null && action.equals("view-comment")) {
				viewCommentOnArticle(request, response);
			}

			else if (action != null && action.equals("manage-comment")) {
				getAllComments(request, response);
			} 
			
			
			else {
				getAllEditor(request, response);
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

		// Extract parameters from the request
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
		if (email != null && !email.trim().isEmpty() && username != null && !username.trim().isEmpty()
				&& password != null && !password.trim().isEmpty()) {

			EntityManager entityManager = DBUtil.getEntityManager();
			UserDB userDb = new UserDB(entityManager);
			RoleDB roleDB = new RoleDB(entityManager);

			// Check if the email has already existed
			if (userDb.getUserByEmail(email) == null) {
				User newUser = new User();

				Role role = roleDB.getRoleById(roleId);

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

				boolean registrationSuccess = userDb.addUser(newUser);

				if (registrationSuccess) {
					List<User> users=userDb.getAllUsers();
					request.setAttribute("users", users);
					RequestDispatcher dispatcher = request.getRequestDispatcher("manage-account.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("errorMessage2", "Register failed, please try again.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("add-account.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				request.getSession().setAttribute("form", "admin");
				request.setAttribute("errorMessage2",
						"Your email address has already been used during a previous registration process, "
								+ "please try with another email or click the forgot password link below.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("add-account.jsp");
				dispatcher.forward(request, response);
			}

			if (entityManager.getEntityManagerFactory().isOpen()) {
				entityManager.close();
			}
			request.removeAttribute("errorMessage2");
		}
	}

}