package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import entity.Article;
import entity.Category;
import entity.Image;
import entity.Tag;
import entity.User;
import entityManager.ArticleDB;
import entityManager.CategoryDB;
import entityManager.TagDB;
import utils.DBUtil;

@WebServlet({ "/ArticleController", "/manage-articles" })
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		System.out.println("check action Chapter servlet: " + action);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		System.out.println("writer: " + user.getUsername());

		request.setAttribute("writerId", user.getUserId());

		if (user != null && user.getRole().getRoleId() == 4) {
			if (action != null && action.equals("get-page-add-article")) {
				getPageAddArticle(request, response);
			} else if (action != null && action.equals("get-page-edit-article")) {
				getPageEditArticle(request, response);
			} else if (action != null && action.equals("get-page-delete-article")) {
				getPageDeleteArticle(request, response);
			} else {
				getArticlesPaginated(request, response);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void getPageAddArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int writerId = (int) request.getAttribute("writerId");
		System.out.println("get page add article check writerId: " + writerId);
		request.setAttribute("writerId", writerId);
		try {
			EntityManager entityManager = DBUtil.getEntityManager();

			CategoryDB categoryDB = new CategoryDB(entityManager);
			List<Category> categories = categoryDB.getAllCategories();
			request.setAttribute("categories", categories);

			TagDB tagDB = new TagDB(entityManager);
			List<Tag> tags = tagDB.getAllTags();
			entityManager.close();
			request.setAttribute("tags", tags);

			RequestDispatcher dispatcher = request.getRequestDispatcher("add-article.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void getPageEditArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int writerId = (int) request.getAttribute("writerId");
		System.out.println("get page edit chapter check writerId: " + writerId);
		request.setAttribute("writerId", writerId);
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

				RequestDispatcher dispatcher = request.getRequestDispatcher("edit-article.jsp");
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

	private void getPageDeleteArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int writerId = (int) request.getAttribute("writerId");
		System.out.println("get page delete chapter check writerId: " + writerId);
		request.setAttribute("writerId", writerId);
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

				RequestDispatcher dispatcher = request.getRequestDispatcher("delete-article.jsp");
				dispatcher.forward(request, response);
			} else {
				System.out.println("Articles or categories or tags variable is null here in Delete!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void getArticlesPaginated(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int writerId = (int) request.getAttribute("writerId");
		System.out.println(writerId);
		try {
			int pageNumber = 1;
			int pageSize = 10;
			if (request.getParameter("page") != null) {
				pageNumber = Integer.parseInt(request.getParameter("page"));
			}

			EntityManager entityManager = null;

			try {
				entityManager = DBUtil.getEntityManager();
				ArticleDB articleDB = new ArticleDB(entityManager);
				List<Article> articles = articleDB.getArticlesPagedByWriterId(pageNumber, pageSize, writerId);

				Long totalArticles = articleDB.countAllArticlesByWriterId(writerId);

				int totalPages = (int) Math.ceil((double) totalArticles / pageSize);
				request.setAttribute("writerId", writerId);
				request.setAttribute("articles", articles);
				request.setAttribute("pageNumber", pageNumber);
				request.setAttribute("totalPages", totalPages);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/manage-article.jsp");
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		System.out.println("admin: " + user.getUsername());
		System.out.println("Check action doPost article: " + action);
		int writerId = Integer.parseInt(request.getParameter("writerId"));
		System.out.println("doPost check writerId: " + writerId);
		request.setAttribute("writerId", writerId);
		if (user != null && (user.getRole().getRoleId() == 4)) {
			if (action != null && action.equals("add-article")) {
				handleAddArticle(request, response);
			} else if (action != null && action.equals("edit-article")) {
				handleEditArticle(request, response);
			} else if (action != null && action.equals("delete-article")) {
				handleDeleteArticle(request, response);
			} else {
				getArticlesPaginated(request, response);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
	}

	private String getSubmittedFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
			}
		}
		return null;
	}

	private String saveImageToDirectory(InputStream imageInputStream) throws IOException {
		String uploadDirectoryPath = getServletContext().getRealPath("/assets/img/articleImg/");
		String fileName = uploadDirectoryPath+UUID.randomUUID().toString() + ".jpg";
		Path dirPath = Paths.get(uploadDirectoryPath);

		// Create directory if it doesn't exist
		if (!Files.exists(dirPath)) {
			Files.createDirectories(dirPath);
		}

		String filePath = dirPath.resolve(fileName).toString();
		Files.copy(imageInputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

		return fileName;
	}

//	private void deleteOldImage(String oldImageFileName) {
//		String uploadDirectoryPath = getServletContext().getRealPath("/assets/img/articleImg/");
//
//		String oldImagePath = Paths.get(uploadDirectoryPath, oldImageFileName).toString();
//
//		File oldImageFile = new File(oldImagePath);
//		if (oldImageFile.exists()) {
//			oldImageFile.delete();
//		}
//	}

	private void handleAddArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int writerId = Integer.parseInt(request.getParameter("writerId"));
		System.out.println("Check writerId add " + writerId);
		EntityManager entityManager = DBUtil.getEntityManager();
		try {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			String[] tagIds = request.getParameterValues("tagIds");

			Collection<Part> imageParts = request.getParts().stream().filter(part -> "image".equals(part.getName()))
					.collect(Collectors.toList());

			CategoryDB categoryDB = new CategoryDB(entityManager);
			Category category = categoryDB.getCategoryById(categoryId);

			List<Tag> tags = new ArrayList<>();
			if (tagIds != null && tagIds.length > 0) {
				for (String tagId : tagIds) {
					try {
						int tagIdInt = Integer.parseInt(tagId);
						TagDB tagDB = new TagDB(entityManager);
						Tag tag = tagDB.getTagById(tagIdInt);
						if (tag != null) {
							tags.add(tag);
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}

			ArticleDB articleDB = new ArticleDB(entityManager);
			Article article = new Article();
			article.setTitle(title);
			article.setContent(content);
			article.setCategory(category);
			article.setTags(tags);

			for (Part imagePart : imageParts) {
				InputStream imageInputStream = imagePart.getInputStream();

				if (imageInputStream.available() > 0) {
					String newImageFileName = saveImageToDirectory(imageInputStream);
					Image image = new Image();
					image.setArticle(article);
					image.setImagePath(newImageFileName);
					article.getImages().add(image);
				}
			}

			if (articleDB.addArticle(article)) {
				request.setAttribute("writerId", writerId);
				getArticlesPaginated(request, response);
			} else {
				System.out.println("Cant finish the process adding new article because 1 of the variables is null!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	private void handleEditArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int writerId = Integer.parseInt(request.getParameter("writerId"));
		System.out.println("Check writerId edit " + writerId);
		EntityManager entityManager = DBUtil.getEntityManager();
		try {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			String[] tagIds = request.getParameterValues("tagIds");
			int articleId = Integer.parseInt(request.getParameter("articleId"));

			CategoryDB categoryDB = new CategoryDB(entityManager);
			Category category = categoryDB.getCategoryById(categoryId);

			List<Tag> tags = new ArrayList<>();
			if (tagIds != null && tagIds.length > 0) {
				for (String tagId : tagIds) {
					try {
						int tagIdInt = Integer.parseInt(tagId);
						TagDB tagDB = new TagDB(entityManager);
						Tag tag = tagDB.getTagById(tagIdInt);
						if (tag != null) {
							tags.add(tag);
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}

			ArticleDB articleDB = new ArticleDB(entityManager);
			Article article = articleDB.getArticleById(articleId);
			if (title != null && content != null && category != null && tags != null) {
				article.setTitle(title);
				article.setContent(content);
				article.setCategory(category);
				article.setTags(tags);

				Collection<Part> imageParts = request.getParts().stream().filter(part -> "image".equals(part.getName()))
						.collect(Collectors.toList());

				// Process each image part
				for (Part imagePart : imageParts) {
					InputStream imageInputStream = imagePart.getInputStream();

					if (imageInputStream.available() > 0) {
						// Save new image to directory and get its file name
						String newImageFileName = saveImageToDirectory(imageInputStream);

						// Create new Image entity and set its properties
						Image image = new Image();
						image.setArticle(article);
						image.setImagePath(newImageFileName);

						// Add new Image entity to article's images
						article.getImages().add(image);
					}
				}

				if (articleDB.updateArticle(article)) {
					request.setAttribute("writerId", writerId);
					getArticlesPaginated(request, response);
				}
			} else {
				System.out.println("Cant finish the process adding new chapter because 1 of the variables is null!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	private void handleDeleteArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int writerId = Integer.parseInt(request.getParameter("writerId"));
		System.out.println("Check bookId delete " + writerId);
		EntityManager entityManager = DBUtil.getEntityManager();
		try {
			int articleId = Integer.parseInt(request.getParameter("articleId"));
			ArticleDB articleDB = new ArticleDB(entityManager);
			Article article = articleDB.getArticleById(articleId);

			if (articleDB.deleteArticle(article)) {
				request.setAttribute("bookId", writerId);
				getArticlesPaginated(request, response);
			} else {
				System.out.println("Cant finish the process deleting new chapter because 1 of the variables is null!");
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