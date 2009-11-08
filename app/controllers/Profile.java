package controllers;

import models.User;
import play.mvc.*;
import play.*;
import services.*;

public class Profile extends Controller {
    public static void index(Long id) {
    	Logger.info("Entering Index");
    	User user = null;
    	if(id == null) {
    		user = User.find("byEmail",session.get("user.email")).first();
    	} else {
    		user = User.findById(id);
    	}
        Logger.info("User: "+ user);
        String email = user.email;
        String hash = MD5Util.md5Hex(email);
        Logger.info("Hash of the email is: " + hash);
        render(user, hash);
    }
    
    public static void update() {
    	Logger.info("Entering Update");
    	Logger.info("Email: "+session.get("user.email"));
        User user = User.find("byEmail",session.get("user.email")).first();
        Logger.info("User: "+ user);
        String email = user.email;
        String hash = MD5Util.md5Hex(email);
        Logger.info("Hash of the email is: " + hash);
        render(user, hash);
    }
    
    public static void save(String displayName, String realName, String website) {
    	User user = User.find("byEmail",session.get("user.email")).first();
    	user.displayName = displayName;
    	user.realName = realName;
    	user.website = website;
    	user = user.save();
    	session.put("user.displayName", user.displayName);
    	flash.success("Successfully updated.");
    	Profile.index(user.id);
    }
}
