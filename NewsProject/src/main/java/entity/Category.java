package entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Category
 *
 */
@Entity
public class Category implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Category() {
		super();
	}
	
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;
    
    public int getCategoryId() {
        return categoryId;
    }
	@Column(name = "description")
	private String description;
	
	public String getDescription() {
		return description;
	}
	@ManyToOne
    @JoinColumn(name="user_id")
    private User user;
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

   
}