package entity;

import java.io.Serializable;
import java.util.List;

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
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy="parent", fetch = FetchType.EAGER)
    private List<Category> children;

	@Column(name = "description")
	private String description;
	
	public String getDescription() {
		return description;
	}
	@ManyToOne(optional=true)
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
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public List<Category> getChildren() {
		return children;
	}
	public void setChildren(List<Category> children) {
		this.children = children;
	}

   
}
