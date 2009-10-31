package controllers;

import java.util.List;

import models.Post;
import play.mvc.*;
import play.*;

public class Application extends Controller {

	@Before
	static void addDefaults() {
	    //renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
	    renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
	}
	
    public static void index() {
    	List<Post> posts = Post.find(
                "order by createdDate desc"
            ).from(0).fetch(10);
    	System.out.println("Posts: " + posts.size());
        render(posts);
    }

}