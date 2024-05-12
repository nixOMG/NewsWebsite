package entity;
import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Image implements Serializable {

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
    
}
