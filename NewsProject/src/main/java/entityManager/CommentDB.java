package entityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entity.Category;
import entity.Comment;

public class CommentDB {
	private final EntityManager entityManager;

	public CommentDB(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public boolean deleteComment(Comment comment) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.remove(comment);
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

	public Comment getCommentById(int commentId) {
		return entityManager.find(Comment.class, commentId);
	}

	public List<Comment> getAllComments() {
		try {
			TypedQuery<Comment> query = entityManager.createQuery("SELECT c FROM Comment c", Comment.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}