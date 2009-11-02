package controllers;
import play.mvc.*;
import play.*;
import play.libs.OpenID.*;
import play.libs.OpenID;
import models.User;

public class BaseController extends Controller {
	
	@Before
	static void addDefaults() {
	    renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
	    renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
	}
	
	@Before(unless={"index", "logout", "clearSession", "login", "authenticate", "show", "listTagged"})
	static void checkAuthenticated() {
	    if(!session.contains("user.id")) {
	        login();
	    }
	}
	
	public static void login() {
		Logger.info("Entering Login");
	    render();
	}
	
	public static void logout() {
		Logger.info("Entering Logout");
		render("Application/logout.html");
	}
	
	public static void clearSession() {
		Logger.info("Cleating session:");
		session.put("user.id", ""); 
        session.put("user.email", "");
        session.put("user.displayName", "");
		flash.success("You have successfully logged out.");
		Application.index();
	}
	
	public static void authenticate(String claimID) { 
		Logger.info("Entering authenticate");
		Logger.info("OpenID.isAuthenticationResponse(): " + OpenID.isAuthenticationResponse());
	    if(OpenID.isAuthenticationResponse()) { 
	    	
	        // Retrieve the verified id 
	        UserInfo userInfo = OpenID.getVerifiedID(); 
	        Logger.info("OpenID UserInfo: " + userInfo);
	        if(userInfo == null) { 
	              flash.put("error", "Oops. Authentication has failed"); 
	              Application.index(); 
	        } else { 
	              session.put("user.id", userInfo.id); 
	              session.put("user.email", userInfo.extensions.get("email"));
	              Logger.info("UserInfo: " + userInfo);
	              User user = User.find("byEmail",userInfo.extensions.get("email")).first();
	              if(user == null) {
	            	  user = User.findOrCreateByEmail(userInfo.extensions.get("email"));
	            	  user = user.save();
	            	  Profile.update();
	              }
	              
	              Logger.info("User saved? " + user);
	              session.put("user.displayName", user.displayName);
	              Logger.info("User displayName: " + user.displayName);
	              Application.index(); 
	        } 
	    } else { 
	        // Verify the id 
	        if(!OpenID.id(claimID).required("email", 
	"http://axschema.org/contact/email").verify()) { 
	              flash.put("error", "Oops. Cannot contact google"); 
	              Application.index(); 
	        } 
	    }
	}    
}
