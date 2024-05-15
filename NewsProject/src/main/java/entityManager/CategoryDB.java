package entityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entity.Category;

public class CategoryDB {
	private final EntityManager entityManager;

	public CategoryDB(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public boolean addCategory(Category category) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(category);
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

	public boolean updateCategory(Category category) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.merge(category);
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

	public boolean deleteCategory(Category category) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.remove(category);
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

	public Category getCategoryById(int categoryId) {
		return entityManager.find(Category.class, categoryId);
	}
	
	public List<Integer> getCategoryByUserId(int userId){
	    try {
	        TypedQuery<Integer> query = entityManager.createQuery("SELECT c.categoryId FROM Category c WHERE c.user.userId= :userId", Integer.class);
	        query.setParameter("userId", userId);
	        return query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}


	public List<Category> getAllCategories() {
		try {
			TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c", Category.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public List<Category> getChildCategories() {
	    // Create a TypedQuery to get a list of all child categories
	    TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c WHERE c.parent.categoryId IS NOT NULL", Category.class);

	    // Execute the query and return the result list
	    return query.getResultList();
	}
	
	public List<Category> getParentCategories() {
	    // Create a TypedQuery to get a list of all child categories
	    TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c WHERE c.parent IS NULL", Category.class);

	    // Execute the query and return the result list
	    return query.getResultList();
	}
	
	public void removeRelationShip(Category category) {
	    EntityTransaction transaction = entityManager.getTransaction();
	    try {
	        transaction.begin();

	        // If the category has a parent, reassign its children to the parent
	        if (category.getParent() != null) {
	            Category parent = category.getParent();
	            
	            // Remove the category from its parent's children list
	            parent.getChildren().remove(category);
	            
	            // Reassign each child of the category to the category's parent
	            for (Category child : category.getChildren()) {
	                child.setParent(parent);
	                parent.getChildren().add(child);
	            }
	        } else {
	            // If the category has no parent, just set the parent of its children to null
	            for (Category child : category.getChildren()) {
	                child.setParent(null);
	            }
	        }

	        // Clear the children list of the category to maintain consistency
	        category.getChildren().clear();

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	}



}
