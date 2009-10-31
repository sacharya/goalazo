
import play.*;
import play.jobs.*;
import play.test.*;
 
import models.*;
 
@OnApplicationStart
public class Bootstrap extends Job {
 
    public void doJob() {
        // Check if the database is empty
       // if(User.count() == 0) {
        	new User("bob@gmail.com", "Passw0rd", "Bob", "Crazy", "crazybob").save();
        	User bob = User.find("byEmail", "bob@gmail.com").first();
        	System.out.println("User: " + bob);
        	
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();

        	
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	new Post(bob, "who is th PM of Nepal?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	
        	
        	
        	
        //}
    }
 
}