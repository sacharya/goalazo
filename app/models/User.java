package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
@Entity
public class User extends Model {
 
    public String realName;
    
    public String displayName;
    public String password;
    
    public String email;
    
    public String website;
    
    public Date createdDate;
    public Date lastModifiedDate;
    
    public Date lastVisitedDate;
    
    public User(String email) {
    	this.email = email;
    	this.createdDate = new Date();
    	this.lastModifiedDate = new Date();
    	this.lastVisitedDate = new Date();
    	this.displayName = "unknown";
    }
    
    public User(String email, String password, String realName, String displayName) {
        this.email = email;
        this.password = password;
        this.realName = realName;
        this.displayName = displayName;
    }
    
    public static User findOrCreateByEmail(String email) {
    	 User user = User.find("byEmail", email).first();
         if(user == null) {
             user = new User(email);
         }
         return user;
    }
    
    public String toString() {
        return this.displayName;
    }
 
}