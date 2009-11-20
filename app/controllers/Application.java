package controllers;

import java.util.List;

import models.Post;
import models.User;
import models.Tag;
import play.mvc.*;
import play.*;
import play.libs.OpenID.*;
import play.libs.OpenID;

import play.data.validation.*;

public class Application extends Controller {
	
	@Before
	static void addDefaults() {
	    renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
	    renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
	}
	
	@Before(unless={"index", "logout", "clearSession", "login", "authenticate", "show", "listTagged"})
	static void checkAuthenticated() {
	    if(!session.contains("user.id")) {
	        Auth.login();
	    }
	}
	
	
    public static void index() {
    	List<Post> posts = Post.find(
                "order by createdDate desc"
            ).from(0).fetch(10);
    	System.out.println("Posts: " + posts.size());
        render(posts);
    }
    
    public static void show(Long id) {
        Post post = Post.findById(id);
        Logger.info("Post for id %s", post.toString());
        Logger.info("Tags for the post: " + post.tags.toString());
        Logger.info("Comments for the post: " + post.comments.toString());
        render(post);
    }
    
    public Post previous() {
        //return Post.find("createdDate < ? order by createdDate desc", createdDate).first();
    	return null;
    }
     
    public Post next() {
        //return Post.find("createdDate > ? order by createdDate asc", createdDate).first();
    	return null;
    }
    
    public static void create() {
    	render();
    }
    
    public static void savePost(@Required String title, @Required String content, String tags) {
    	if(Validation.hasErrors()) {
    		render("Application/create.html");
    	}
    	User user = User.find("byEmail",session.get("user.email")).first();
    	user = user.save();
    	Post post = new Post(user, title, content).save();
    	String[] tagsArray = tags.split(",");
        for(String tag: tagsArray) {
        	Logger.info("Tag: " + tag);
        	post.tagItWith(tag);
        }
        post.save();
        flash.success("Thanks for posting %s", user.displayName);
    	show(post.id);
    }
    
    public static void postComment(Long postId, @Required String content) {
        Post post = Post.findById(postId);
    	if (Validation.hasErrors()) {
            render("Application/show.html", post);
        }
    	User user = User.find("byEmail",session.get("user.email")).first();
    	user = user.save();
        post.addComment(user, content);
        flash.success("Thanks for posting %s", user.displayName);
        show(postId);
    }
    
    public static void listTagged(String tag) {
        List<Post> posts = Post.findTaggedWith(tag);
        render(tag, posts);
    }
    
    public static void tags() {
    	List<Tag> tags = Tag.find().from(0).fetch(10);
    	System.out.println("Tags: " + tags.size());
        render("Application/listTagged.html", tags);
    }

}