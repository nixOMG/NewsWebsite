package entityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entity.Article;
import entity.Category;

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
	
	

	public List<Article> getArticlesPagedByStatus(int pageNumber, int pageSize, String status) {
		TypedQuery<Article> query = entityManager.createQuery("SELECT a FROM Article a WHERE a.status = :status",
				Article.class);
		query.setParameter("status", status);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
	public List<Article> getArticlesPagedByWriterId(int pageNumber, int pageSize, int writerId) {
		TypedQuery<Article> query = entityManager.createQuery("SELECT a FROM Article a WHERE a.writer.userId = :writerId",
				Article.class);
		query.setParameter("writerId", writerId);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	public List<Article> getArticlesByCategoryId(int categoryId) {
		TypedQuery<Article> query = entityManager.createQuery("SELECT a FROM Article a WHERE a.category.categoryId = :categoryId",
				Article.class);
		query.setParameter("categoryId", categoryId);
		return query.getResultList();
	}
	
	
	
	public Long getArticlesCount() {
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(a) FROM Article a", Long.class);

		return query.getSingleResult();
	}
	public Long countAllArticlesByWriterId(int writerId) {
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(a) FROM Article a WHERE a.writer.userId= :writerId", Long.class);
		query.setParameter("writerId", writerId);
		return query.getSingleResult();
	}

}