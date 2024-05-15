package entityManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entity.Article;
import entity.Category;
import entity.User;

public class ArticleDB {
	private final EntityManager entityManager;

	public ArticleDB(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public boolean addArticle(Article article) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(article);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateArticle(Article article) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.merge(article);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteArticle(Article article) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.remove(article);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	public List<Article> getAllArticles() {
		try {
			TypedQuery<Article> query = entityManager.createQuery("SELECT a FROM Article a", Article.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Article getArticleById(int articleId) {
		return entityManager.find(Article.class, articleId);
	}

	public List<Article> getArticlesPaged(int pageNumber, int pageSize) {
		TypedQuery<Article> query = entityManager.createQuery("SELECT a FROM Article a", Article.class);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
	public List<Article> getArticlesByStatus(String status) {

		TypedQuery<Article> query = entityManager.createQuery(
				"SELECT a FROM Article a WHERE a.status = :status",
				Article.class);
		query.setParameter("status", status);
		return query.getResultList();
	}
	
	public List<Article> getTop10ArticlesSortedByTime(String status) {
        TypedQuery<Article> query = entityManager.createQuery(
                "SELECT a FROM Article a WHERE a.status = :status ORDER BY a.publishTime DESC",
                Article.class);
        query.setParameter("status", status);
        query.setMaxResults(10);
        return query.getResultList();
    }
	
	public List<Article> getArticlesByRelatedCategory(Category category, int currentArticleId) {
	    List<Article> relatedArticles = new ArrayList<>();

	    // Check if the category has a parent category
	    if (category.getParent() != null) {
	        Category parentCategory = category.getParent();

	        // Find articles in categories with the same parent category, excluding the current article
	        TypedQuery<Article> query = entityManager.createQuery(
	            "SELECT a FROM Article a WHERE a.category.parent = :parentCategory AND a.articleId != :currentArticleId",
	            Article.class);
	        query.setParameter("parentCategory", parentCategory);
	        query.setParameter("currentArticleId", currentArticleId);
	        
	        relatedArticles = query.getResultList();
	    }

	    return relatedArticles;
	}
	public List<Article> getArticlesSortedByViews() {
	    TypedQuery<Article> query = entityManager.createQuery(
	        "SELECT a FROM Article a ORDER BY a.views DESC",
	        Article.class);
	    return query.getResultList();
	}





	public List<Article> getArticlesByCategoryAndStatus(List<Integer> categoryIds, String status) {
		if (categoryIds == null) {
			System.out.println("User has no categories!");
			return new ArrayList<>();
		}

		TypedQuery<Article> query = entityManager.createQuery(
				"SELECT a FROM Article a WHERE a.status = :status AND a.category.categoryId IN :categoryIds",
				Article.class);
		query.setParameter("status", status);
		query.setParameter("categoryIds", categoryIds);
		return query.getResultList();
	}

	public List<Article> getArticlesByCategories(List<Integer> categoryIds) {
		if (categoryIds == null) {
			System.out.println("User has no categories!");
			return new ArrayList<>();
		}

		TypedQuery<Article> query = entityManager
				.createQuery("SELECT a FROM Article a WHERE a.category.categoryId IN :categoryIds", Article.class);
		query.setParameter("categoryIds", categoryIds);
		return query.getResultList();
	}

	public List<Article> getArticlesPagedByWriterId(int pageNumber, int pageSize, int writerId) {
		TypedQuery<Article> query = entityManager
				.createQuery("SELECT a FROM Article a WHERE a.writer.userId = :writerId", Article.class);
		query.setParameter("writerId", writerId);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	public List<Article> getArticlesByCategoryId(int categoryId) {
		TypedQuery<Article> query = entityManager
				.createQuery("SELECT a FROM Article a WHERE a.category.categoryId = :categoryId", Article.class);
		query.setParameter("categoryId", categoryId);
		return query.getResultList();
	}

	public Long getArticlesCount() {
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(a) FROM Article a", Long.class);

		return query.getSingleResult();
	}

	public Long countAllArticlesByWriterId(int writerId) {
		TypedQuery<Long> query = entityManager
				.createQuery("SELECT COUNT(a) FROM Article a WHERE a.writer.userId= :writerId", Long.class);
		query.setParameter("writerId", writerId);
		return query.getSingleResult();
	}

	public List<Article> searchArticle(String searchTerm) {
	    TypedQuery<Article> query = entityManager.createQuery(
	        "SELECT a FROM Article a WHERE LOWER(a.title) LIKE :searchTerm OR LOWER(a.content) LIKE :searchTerm",
	        Article.class);
	    query.setParameter("searchTerm", "%" + searchTerm.toLowerCase() + "%");
	    
	    return query.getResultList();
	}


}
