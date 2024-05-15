package entity;
import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Image implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="image_id")
	private int imageId;

	@Column(name="image_path")
	private String imagePath;

	@Column(name="image_caption")
	private String imageCaption;

	@ManyToOne
	@JoinColumn(name="article_id")
	private Article article;

	@OneToOne(mappedBy = "coverImage")
	private Article coverArticle;


	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageCaption() {
		return imageCaption;
	}

	public void setImageCaption(String imageCaption) {
		this.imageCaption = imageCaption;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Article getCoverArticle() {
		return coverArticle;
	}

	public void setCoverArticle(Article coverArticle) {
		this.coverArticle = coverArticle;
	}
    
}