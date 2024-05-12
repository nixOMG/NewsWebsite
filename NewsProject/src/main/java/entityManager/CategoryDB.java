package entityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entity.Category;
import entity.Role;

public class CategoryDB {
	private final EntityManager entityManager;
	
	public CategoryDB(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	public List<Category> getAllCategories(){
		entityManager.clear();
		try {
			TypedQuery<Category> query=entityManager.createQuery("SELECT c FROM Category c", Category.class);
			return query.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public Category getCategoryById(int categoryId) {
		EntityTransaction transaction=entityManager.getTransaction();
		entityManager.clear();
		try {
			transaction.begin();
			Category category=entityManager.find(Category.class, categoryId);
			transaction.commit();
			return category;
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
