package entityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entity.Category;
import entity.Role;
import entity.Tag;

public class TagDB {
	private final EntityManager entityManager;
	
	public TagDB(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	public List<Tag> getAllTags(){
		entityManager.clear();
		try {
			TypedQuery<Tag> query=entityManager.createQuery("SELECT t FROM Tag t", Tag.class);
			return query.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public Tag getTagById(int tagId) {
		EntityTransaction transaction=entityManager.getTransaction();
		entityManager.clear();
		try {
			transaction.begin();
			Tag tag=entityManager.find(Tag.class, tagId);
			transaction.commit();
			return tag;
		}
		catch(Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
    }
}
