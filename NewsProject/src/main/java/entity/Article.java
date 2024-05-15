package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;

import java.sql.Timestamp;
import java.util.List;

@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id")
	private int articleId; 

	@Column(name = "title")
	private String title;

	@Column(columnDefinition = "TEXT", name="article_content")
	private String content;

	@Column(name = "publishTime")
	private Timestamp publishTime;

	@Column(name = "status")
	private String status;

	@ManyToOne
	@JoinColumn(name = "writer_id")
	private User writer; //has role writer

	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
	private List<Image> images;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cover_image_id", referencedColumnName = "image_id")
	private Image coverImage;

	@ManyToMany
	@JoinTable(
	    name = "article_tag",
	    joinColumns = @JoinColumn(name = "article_id"),
	    inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	private List<Tag> tags;

	@Column(name = "views")
	private Long views;

	@OneToMany(mappedBy="article")
	private List<Comment> comments;
	
    public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}

    
	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return writer;
	}

	public void setUser(User writer) {
		this.writer = writer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Image getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(Image coverImage) {
		this.coverImage = coverImage;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
    
    
}