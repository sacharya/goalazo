package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
@Entity
public class User extends Model {
 
    public String firstName;
    public String lastName;
    
    public String displayName;
    public String password;
    
    public String email;
    
    public Date createdDate;
    public Date lastModifiedDate;
    
    public Date lastVisitedDate;
    
    
    public User(String email, String password, String firstName, String lastName, String displayName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
    }
    
    public static User connect(String email, String password) {
    	return find("byEmailAndPassword", email, password).first();
    }
 
}