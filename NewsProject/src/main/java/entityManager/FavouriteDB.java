package entityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entity.Article;
import entity.Favourite;
import entity.User;
import entity.Favourite;

public class FavouriteDB {
	private final EntityManager entityManager;

	public FavouriteDB(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Favourite getFavouriteById(int favId) {

		try {
			return entityManager.find(Favourite.class, favId);
		} catch (NoResultException e) {
			return null;
		}
	}
	public boolean addFavourite(Favourite fav) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(fav);
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

    
    
    public boolean updateFavourite(Favourite fav) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(fav);
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

    public boolean deleteFavourite(Favourite fav) {
		EntityTransaction transaction=entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.remove(fav);
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
	public List<Favourite> getAllFavourites() {
		try {
			TypedQuery<Favourite> query = entityManager.createQuery("SELECT r FROM Favourite r", Favourite.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean removeFavourite(User user, int articleId) {
        entityManager.getTransaction().begin();
        TypedQuery<Favourite> query = entityManager.createQuery("DELETE FROM Favourite f WHERE f.user = :user AND f.article.articleId = :articleId", Favourite.class);
        query.setParameter("user", user);
        query.setParameter("articleId", articleId);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        return true;
    }
	
	public boolean isFavourite(User user, int articleId) {
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(f) FROM Favourite f WHERE f.user = :user AND f.article.articleId = :articleId", Long.class);
        query.setParameter("user", user);
        query.setParameter("articleId", articleId);
        long count = (long) query.getSingleResult();
        return count > 0;
    }
	
	public List<Article> getFavoriteArticlesByUser(User user) {
	    TypedQuery<Article> query = entityManager.createQuery(
	        "SELECT f.article FROM Favourite f WHERE f.user = :user",
	        Article.class);
	    query.setParameter("user", user);
	    return query.getResultList();
	}

}
