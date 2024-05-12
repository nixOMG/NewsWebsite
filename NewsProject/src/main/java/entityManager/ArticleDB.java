package entityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entity.Article;

public class ArticleDB {
	private final EntityManager entityManager;
	
	public ArticleDB(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	
    public boolean addArticle(Article article) {
    	EntityTransaction transaction=entityManager.getTransaction();
    	try {    		
    		transaction.begin();
    		entityManager.persist(article);
    		transaction.commit();
    		return true;
    	}
    	catch(Exception e) {
    		if (transaction.isActive()) {
                transaction.rollback();
            }
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public boolean editArticle(Article article) {
    	EntityTransaction transaction=entityManager.getTransaction();
    	try {
    		transaction.begin();
    		entityManager.merge(article);
    		transaction.commit();
    		return true;
    	}
    	catch(Exception e) {
    		if (transaction.isActive()) {
    			transaction.rollback();
    		}
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public boolean deleteArticle(Article article) {
    	EntityTransaction transaction=entityManager.getTransaction();
    	try {
    		transaction.begin();
    		entityManager.remove(article);
    		transaction.commit();
    		return true;
    	}
    	catch(Exception e) {
    		if (transaction.isActive()) {
    			transaction.rollback();
    		}
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public Article getArticleById(int articleId) {
    	try {
    		TypedQuery<Article> query=entityManager.createQuery("SELECT ch FROM Article ch WHERE ch.articleId=:articleId", Article.class);
    		query.setParameter("articleId", articleId);
    		return query.getSingleResult();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public List<Article> getArticlesByCategoryId(int categoryId) {
    	try {
    		TypedQuery<Article> query=entityManager.createQuery("SELECT ch FROM Article ch WHERE ch.category.categoryId=:categoryId", Article.class);
    		query.setParameter("categoryId", categoryId);
    		return query.getResultList();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public List<Article> getArticlesByTagId(int tagId) {
    	try {
    		TypedQuery<Article> query=entityManager.createQuery("SELECT a FROM Article a JOIN a.tags t WHERE t.tagId = :tagId", Article.class);
    		query.setParameter("tagId", tagId);
    		return query.getResultList();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public List<Article> getArticlesByWriterId(int writerId) {
    	try {
    		TypedQuery<Article> query=entityManager.createQuery("SELECT ch FROM Article ch WHERE ch.writer.userId=:writerId", Article.class);
    		query.setParameter("writerId", writerId);
    		return query.getResultList();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    public Long countAllArticlesByWriterId(int writerId) {
    	try {
    		TypedQuery<Long> query=entityManager.createQuery("SELECT COUNT(ch) FROM Article ch WHERE ch.writer.userId= :writerId", Long.class);
    		query.setParameter("writerId", writerId);
    		return query.getSingleResult();
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
    public List<Article> getArticlesPaginatedByWriterId(int pageNum, int pageSize, int writerId){
    	try {
    		TypedQuery<Article> query=entityManager.createQuery("SELECT ch FROM Article ch WHERE ch.writer.userId=:writerId", Article.class);
    		query.setParameter("writerId", writerId);
    		query.setFirstResult((pageNum-1)*pageSize).setMaxResults(pageSize);
    		return query.getResultList();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
}
