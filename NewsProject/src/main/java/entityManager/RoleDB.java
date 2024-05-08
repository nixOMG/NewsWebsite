package entityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entity.Role;

public class RoleDB {
	private final EntityManager entityManager;
	
	public RoleDB(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	public Role getRoleById(int roleId) {

	    try {
	        return entityManager.find(Role.class, roleId);
	    } catch (NoResultException e) {
	        return null;
	    }
	}
	
	public List<Role> getAllRoles() {
    	try {
    		TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r", Role.class);
            return query.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
        
    }
}
