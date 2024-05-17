package entityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entity.Comment;
import entity.User;
import entity.Comment;

public class CommentDB {
	private EntityManager entityManager;

    public CommentDB(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Comment> getCommentsByArticleId(int articleId) {
        TypedQuery<Comment> query = entityManager.createQuery(
            "SELECT c FROM Comment c WHERE c.article.articleId = :articleId",
            Comment.class);
        query.setParameter("articleId", articleId);
        return query.getResultList();
    }

    public boolean addComment(Comment comment) {
    	EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(comment);
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
    
    public boolean updateComment(Comment comment) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(comment);
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

    public boolean deleteComment(Comment comment) {
		EntityTransaction transaction=entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.remove(comment);
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
    
    public Comment getCommentById(int commentId) {
        return entityManager.find(Comment.class, commentId);
    }

	public List<Comment> getAllComments() {
		try {
    		TypedQuery<Comment> query = entityManager.createQuery("SELECT u FROM Comment u", Comment.class);
            return query.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
    
}
