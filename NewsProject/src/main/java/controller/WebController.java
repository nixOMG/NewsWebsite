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

import entity.Article;
import entity.Category;
import entity.User;
import utils.DBUtil;
import utils.SendMail;
import entityManager.UserDB;
import entityManager.ArticleDB;
import entityManager.CategoryDB;
import entityManager.RoleDB;

@WebServlet({ "/home", "/login", "/register", "/forgot-pass" })
public class WebController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WebController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println("check action: " + action);
		String servletPath = request.getServletPath();

		if (servletPath.equals("/login") || servletPath.equals("/register")
				|| action != null && action.equals("sign-in")) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else if (servletPath.equals("/forgot-pass")) {
			response.sendRedirect(request.getContextPath() + "/resetPass.jsp");
		} else if (action != null && action.equals("sign-out")) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate(); // Delete all information in session
			}
			response.sendRedirect(request.getContextPath() + "/home");
		} else if ((servletPath.equals("/gallery") || servletPath.equals("/gallery.jsp")) && action != null
				&& action.equals("gallery")) {
			response.sendRedirect(request.getContextPath() + "/gallery");
		} else if (action != null && action.equals("read")) {
			response.sendRedirect(request.getContextPath() + "/read.jsp");
		} else if (action != null && action.equals("faq")) {
			response.sendRedirect(request.getContextPath() + "/faq.jsp");
		} else if (servletPath == null || servletPath.equals("/home") || servletPath.equals("/index.jsp")
				|| (action == null)) {
			EntityManager entityManager = DBUtil.getEntityManager();
			UserDB userDB = new UserDB(entityManager);
			List<User> users = userDB.getAllUsers();
			for (User user : users) {
				entityManager.refresh(user);
			}
			CategoryDB categoryDB = new CategoryDB(entityManager);
			List<Category> categories = categoryDB.getAllCategories();
			for (Category category : categories) {
				entityManager.refresh(category);
			}
			
			ArticleDB articleDB=new ArticleDB(entityManager);
			List<Article> articles=articleDB.getArticlesByStatus("approved");
			List<Article> lastestArticles=articleDB.getTop10ArticlesSortedByTime("approved");
			
			
			request.setAttribute("lastestArticles", lastestArticles);
			request.setAttribute("articles", articles);
			request.setAttribute("categories", categories);

			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/error404.html");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println("check action: " + action);
		if (action != null && action.equals("handle-login")) {
			handleLogin(request, response);
		}
		if (action != null && action.equals("handle-register")) {
			handleRegister(request, response);
		}
		if (action != null && action.equals("handle-forgot-pass")) {
			handleForgotPass(request, response);
		}
	}

	private void handleForgotPass(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");

		if (email.trim() != null) {
			EntityManager entityManager = DBUtil.getEntityManager();
			UserDB userDb = new UserDB(entityManager);
			User reader = userDb.getUserByEmail(email);

			if (reader != null) {

				if (userDb.getUserStatus(email)) {
					SendMail emailUtil = new SendMail();
					boolean isEmailSent = emailUtil.SendResetPasswordMail(reader, request);

					if (isEmailSent) {
						response.sendRedirect("verify.jsp");
					} else {
						request.setAttribute("errorMessage2", "Failed to send verification email, please try again.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
						dispatcher.forward(request, response);
					}
				} else {
					request.setAttribute("errorMessage2", "Change password failed, please try again.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				request.getSession().setAttribute("form", "register");
				request.setAttribute("errorMessage2", "Your email hasn't been registered, please sign up!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}

			if (entityManager.getEntityManagerFactory().isOpen()) {
				entityManager.close();
			}
			request.removeAttribute("errorMessage2");
		}
	}

	private void handleRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String idenId = request.getParameter("idenId");
		int age = Integer.parseInt(request.getParameter("age"));
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		Date dob = Date.valueOf(request.getParameter("dob"));
		System.out.println("check utf-8 from jsp: " + username);

		// Check the input fields
		if (email.trim() != null && username.trim() != null && password.trim() != null) {
			EntityManager entityManager = DBUtil.getEntityManager();
			UserDB userDb = new UserDB(entityManager);
			RoleDB roleEM = new RoleDB(entityManager);
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
				newUser.setRole(roleEM.getRoleById(0));

				boolean registrationSuccess = userDb.addUser(newUser);

				if (registrationSuccess) {
					SendMail emailUtil = new SendMail();

					boolean isEmailSent = emailUtil.SendVerifyMail(newUser, request);

					if (isEmailSent) {
						response.sendRedirect("verify.jsp");
					} else {
						request.setAttribute("errorMessage2", "Failed to send verification email, please try again.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
						dispatcher.forward(request, response);
					}
				} else {
					request.setAttribute("errorMessage2", "Register failed, please try again.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				request.getSession().setAttribute("form", "register");
				request.setAttribute("errorMessage2",
						"Your email address has already been used during a previous registration process,"
								+ "please try with other email or click the forgot password link below");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}

			if (entityManager.getEntityManagerFactory().isOpen()) {
				entityManager.close();
			}
			request.removeAttribute("errorMessage2");
		}
	}

	private void handleLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		EntityManager entityManager = DBUtil.getEntityManager();

		entityManager.clear();

		try {
			if (email.trim() != null && password.trim() != null) {

				UserDB userDb = new UserDB(entityManager);
				User loggedInUser = userDb.login(email, password);

				if (loggedInUser != null) {
					System.out.println("Đăng nhập thành công! User: " + loggedInUser.getUsername());

					// Save reader's information into session
					HttpSession session = request.getSession();
					session.setAttribute("loggedInUser", loggedInUser);

					// Check roleID to direct web pages
					int roleId = loggedInUser.getRole().getRoleId();
					System.out.println("Check roleId: " + roleId);
					if (roleId > 1 && roleId <= 6) {
						response.sendRedirect(request.getContextPath() + "/home");
					} else {
						request.setAttribute("errorMessage", "Login failed, please try again.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
						dispatcher.forward(request, response);
					}
				} else {
					System.out.println("Login failed.");
					request.setAttribute("errorMessage", "Please check your email/password and try again.");
					request.getSession().setAttribute("form", "login");
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (entityManager.getEntityManagerFactory().isOpen()) {
				entityManager.close();
			}
		}

	}

}
