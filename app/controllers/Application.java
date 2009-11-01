package controllers;

import java.util.List;

import models.Post;
import models.User;
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
	
	@Before(unless={"index", "logout", "login", "authenticate", "show", "listTagged"})
	static void checkAuthenticated() {
	    if(!session.contains("user")) {
	        login();
	    }
	}
	
	public static void login() {
	    render();
	}
	
	public static void logout() {
		render("Application/logout.html");
	}
	
	public static void clearSession() {
		flash.success("You have successfully logged out.");
		render();
	}
	
	public static void authenticate(String claimID) { 
	    if(OpenID.isAuthenticationResponse()) { 
	        // Retrieve the verified id 
	        UserInfo user = OpenID.getVerifiedID(); 
	        if(user == null) { 
	              flash.put("error", "Oops. Authentication has failed"); 
	              index(); 
	        } else { 
	              session.put("user.id", user.id); 
	              session.put("user.email", user.extensions.get("email")); 
	              index(); 
	        } 
	    } else { 
	        // Verify the id 
	        if(!OpenID.id(claimID).required("email", 
	"http://axschema.org/contact/email").verify()) { 
	              flash.put("error", "Oops. Cannot contact google"); 
	              index(); 
	        } 
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
    	String displayName = "crazybob";
    	User user = new User(displayName);
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
    	String displayName = "crazybob";
    	User user = new User(displayName);
    	user = user.save();
        post.addComment(user, content);
        flash.success("Thanks for posting %s", user.displayName);
        show(postId);
    }
    
    public static void listTagged(String tag) {
        List<Post> posts = Post.findTaggedWith(tag);
        render(tag, posts);
    }

}