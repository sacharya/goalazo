package models;

import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
@Entity
public class Post extends Model {
 
    public String title;
    public Date createdDate;
    
    @Lob
    public String content;
    
    @ManyToOne
    public User user;
    
    @OneToMany(mappedBy="post", cascade=CascadeType.ALL)
    public List<Comment> comments;
    
    public Post(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdDate = new Date();
        
        this.comments = new ArrayList<Comment>();
    }
    
    public Post addComment(User author, String content) {
        Comment newComment = new Comment(this, author, content).save();
        this.comments.add(newComment);
        return this;
    }
 
}