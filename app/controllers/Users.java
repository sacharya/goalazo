package controllers;
 
import play.*;
import play.mvc.*;

import java.util.List;
import models.User;

public class Users extends Controller {
	public static void index() {
    	List<User> users = User.find(
                "order by createdDate desc"
            ).from(0).fetch(10);
    	System.out.println("Users: " + users.size());
        render(users);
    }
}
