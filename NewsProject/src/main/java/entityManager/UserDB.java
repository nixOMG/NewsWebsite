package entityManager;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.User;

public class UserDB {
	private final EntityManager entityManager;

    public UserDB(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean addUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
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

    
    
    public boolean updateUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(user);
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

    
    public List<User> getAllUsers() {
    	try {
    		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
            return query.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
        
    }
    
    public boolean getUserStatus(String email) {
    	try {
            TypedQuery<User> query = entityManager.createQuery("SELECT u.status FROM User u WHERE u.email=:email", User.class);
            query.setParameter("email", email);
            if(query.getResultList() != null) {
            	return true;
            }
            else {
            	return false;
            }
    	}
    	catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserById(int userId) {
        return entityManager.find(User.class, userId);
    }

    public User login(String email, String password) {
        try {
            Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.pass = :password");
            query.setParameter("email", email);
            query.setParameter("password", password);

            List<User> resultList = query.getResultList();

            if (resultList != null && !resultList.isEmpty()) {
                return resultList.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public User getUserByEmail(String email) {
        try {
            Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email");
            query.setParameter("email", email);

            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            // Cant find user with that email
            return null;
        }
    }
    
    public List<User> getUsersByRoleId(int roleId) {
        try {
            TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.role.roleId = :roleId",
                User.class
            );
            query.setParameter("roleId", roleId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    public List<User> getUsersPaged(int pageNumber, int pageSize, int roleId) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.role.roleId = :roleId", User.class);
        query.setParameter("roleId", roleId);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
    public Long getUsersCount() {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(u) FROM User u", Long.class);

        return query.getSingleResult();
    }
    
    public User getUserByLink(String link) {
        try {
            Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.verifylink = :link");
            query.setParameter("link", link);

            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public void updateUserLink(User user, String link) {
        try {
            entityManager.getTransaction().begin();

            user.setVerifylink(link);

            entityManager.merge(user);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
    public void updateUserPass(User user, String pass) {
        try {
            entityManager.getTransaction().begin();

            user.setPass(pass);

            entityManager.merge(user);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
