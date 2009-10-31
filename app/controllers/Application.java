package controllers;

import java.util.List;

import models.Post;
import play.mvc.*;

public class Application extends Controller {

    public static void index() {
    	List<Post> posts = Post.find(
                "order by createdDate desc"
            ).from(0).fetch(10);
    	System.out.println("Posts: " + posts.size());
        render(posts);
    }

}