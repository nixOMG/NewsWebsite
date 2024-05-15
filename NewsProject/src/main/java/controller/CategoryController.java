package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import entityManager.CategoryDB;
import utils.DBUtil;

@WebServlet({ "/CategoryController", "/manage-category" })
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryController() {
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
		if (user != null) {
			System.out.println("writer: " + user.getUsername());
			request.setAttribute("writerId", user.getUserId());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		if (user != null && (user.getRole().getRoleId() == 5 || user.getRole().getRoleId() == 6)) {
			if (action != null && action.equals("get-page-add-category")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("add-parent-category.jsp");
				dispatcher.forward(request, response);
			} else if (action != null && action.equals("get-page-add-child-category")) {
				getPageAddChildCategory(request, response);
			} else if (action != null && action.equals("get-page-edit-category")) {
				getPageEditCategory(request, response);
			} else if (action != null && action.equals("get-page-edit-child-category")) {
				getPageEditChildCategory(request, response);
			} else {
				getAllCategories(request, response);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void getPageEditChildCategory(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			int childCategoryId = Integer.parseInt(request.getParameter("childCategoryId"));
			EntityManager entityManager = DBUtil.getEntityManager();

			CategoryDB categoryDB = new CategoryDB(entityManager);
			Category category = categoryDB.getCategoryById(childCategoryId);
			List<Category> parentCategories=categoryDB.getParentCategories();
			
			request.setAttribute("parentCategories", parentCategories);
			request.setAttribute("category", category);
			entityManager.close();

			RequestDispatcher dispatcher = request.getRequestDispatcher("edit-child-category.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void getPageAddChildCategory(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			int parentCategoryId = Integer.parseInt(request.getParameter("parentCategoryId"));
			EntityManager entityManager = DBUtil.getEntityManager();

			CategoryDB categoryDB = new CategoryDB(entityManager);
			Category category = categoryDB.getCategoryById(parentCategoryId);
			request.setAttribute("category", category);
			entityManager.close();

			RequestDispatcher dispatcher = request.getRequestDispatcher("add-child-category.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void getAllCategories(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			EntityManager entityManager = null;

			try {
				entityManager = DBUtil.getEntityManager();
				CategoryDB categoryDB = new CategoryDB(entityManager);
				List<Category> categories = categoryDB.getParentCategories();
				System.out.println("Categories: " + categories);

				request.setAttribute("categories", categories);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/manage-category.jsp");
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

	private void getPageEditCategory(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			EntityManager entityManager = DBUtil.getEntityManager();

			CategoryDB categoryDB = new CategoryDB(entityManager);
			Category category = categoryDB.getCategoryById(categoryId);
			request.setAttribute("category", category);
			entityManager.close();

			RequestDispatcher dispatcher = request.getRequestDispatcher("edit-category.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
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
		System.out.println("check action Chapter servlet: " + action);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if (user != null) {
			System.out.println("writer: " + user.getUsername());
			request.setAttribute("writerId", user.getUserId());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

		if (user != null && (user.getRole().getRoleId() == 5 || user.getRole().getRoleId() == 6)) {
			if (action != null && action.equals("add-category")) {
				handleAddCategory(request, response);
			} else if (action != null && action.equals("add-child-category")) {
				handleAddChildCategory(request, response);
			}
			else if (action != null && action.equals("edit-category")) {
				handleEditCategory(request, response);
			}  else if (action != null && action.equals("edit-child-category")) {
				handleEditChildCategory(request, response);
			}  
			else if (action != null && action.equals("delete-category")) {
				handleDeleteCategory(request, response);
			} else {
				getAllCategories(request, response);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void handleEditChildCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String categoryDescription = request.getParameter("description");
		int childCategoryId=Integer.parseInt(request.getParameter("childCategoryId"));
		int parentCategoryId=Integer.parseInt(request.getParameter("parent"));
		try {
			EntityManager entityManager = DBUtil.getEntityManager();

			CategoryDB categoryDB = new CategoryDB(entityManager);
			Category parentCategory=categoryDB.getCategoryById(parentCategoryId);
			Category category = categoryDB.getCategoryById(childCategoryId);
			
			category.setDescription(categoryDescription);
			category.setParent(parentCategory);

			categoryDB.updateCategory(category);
			
			response.sendRedirect(request.getContextPath() + "/manage-category");

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	private void handleAddChildCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String categoryDescription = request.getParameter("description");
		int parentCategoryId=Integer.parseInt(request.getParameter("parentCategoryId"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if (user != null) {
			System.out.println("check user: " + user.getUsername());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		try {
			EntityManager entityManager = DBUtil.getEntityManager();

			CategoryDB categoryDB = new CategoryDB(entityManager);
			Category parentCategory=categoryDB.getCategoryById(parentCategoryId);
			Category category = new Category();
			
			category.setDescription(categoryDescription);
			category.setParent(parentCategory);
			category.setUser(user);

			categoryDB.addCategory(category);
			response.sendRedirect(request.getContextPath() + "/manage-category");

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	private void handleDeleteCategory(HttpServletRequest request, HttpServletResponse response)
	        throws IOException, ServletException {
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");

	    int categoryId = Integer.parseInt(request.getParameter("categoryId"));
	    EntityManager entityManager = DBUtil.getEntityManager();
	    CategoryDB categoryDB = new CategoryDB(entityManager);
	    Category category = categoryDB.getCategoryById(categoryId);

	    try {
	        
	    	categoryDB.removeRelationShip(category);
	    	category.setParent(null);
	        categoryDB.updateCategory(category);
	        boolean isDeleted = categoryDB.deleteCategory(category);

	        System.out.print(isDeleted);
	        String jsonResponse = "{ \"success\": " + isDeleted + " }";

	        // Set the response content type to JSON
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        // Send the JSON response
	        PrintWriter out = response.getWriter();
	        out.print(jsonResponse);
	        out.flush();
	    } catch (Exception e) {
	    	System.out.println("There are articles that related to this category!");
	        throw e;
	    }
	}




	private void handleEditCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String categoryDescription = request.getParameter("description");
		try {
			EntityManager entityManager = DBUtil.getEntityManager();

			CategoryDB categoryDB = new CategoryDB(entityManager);
			Category category = new Category();
			category.setDescription(categoryDescription);

			categoryDB.addCategory(category);
			response.sendRedirect(request.getContextPath() + "/manage-category");

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void handleAddCategory(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String categoryDescription = request.getParameter("description");
		try {
			EntityManager entityManager = DBUtil.getEntityManager();

			CategoryDB categoryDB = new CategoryDB(entityManager);
			Category category = new Category();
			category.setDescription(categoryDescription);

			categoryDB.addCategory(category);
			response.sendRedirect(request.getContextPath() + "/manage-category");

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}

	}

}
