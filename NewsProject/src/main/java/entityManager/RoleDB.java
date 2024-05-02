package entityManager;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import entity.Role;

public class RoleDB {
	private static final long serialVersionUID = 1L;
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
}
