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
    
    @ManyToMany(cascade=CascadeType.PERSIST)
    public Set<Tag> tags;
    
    public Post(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdDate = new Date();
        
        this.comments = new ArrayList<Comment>();
        this.tags = new TreeSet<Tag>();
    }
    
    public Post addComment(User author, String content) {
        Comment newComment = new Comment(this, author, content).save();
        this.comments.add(newComment);
        return this;
    }
    
    public Post tagItWith(String name) {
    	Tag newTag = Tag.findOrCreateByName(name);
        this.tags.add(newTag);
        return this;
    }
    
    public static List<Post> findTaggedWith(String tag) {
        return Post.find(
            "select distinct p from Post p join p.tags as t where t.name = ?", tag
        ).fetch();
    }
    
    public String toString() {
        return this.title;
    }
 
}