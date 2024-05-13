package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Role {
	private int roleId;
    private String description;
    private List<User> users;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	@OneToMany(mappedBy = "role")
    public List<User> getUsers() {
        return users;
    }

	public void setUsers(List<User> users) {
		this.users = users;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
