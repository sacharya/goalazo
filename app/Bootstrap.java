
import play.*;
import play.jobs.*;
import play.test.*;
 
import models.*;
 
@OnApplicationStart
public class Bootstrap extends Job {
 
    public void doJob() {
        // Check if the database is empty
       // if(User.count() == 0) {
        	new User("bob@gmail.com", "Passw0rd", "Bob Crazy", "crazybob").save();
        	User bob = User.find("byEmail", "bob@gmail.com").first();
        	System.out.println("User: " + bob);
        	
        	String tag = "Funny Goals";
        	
        	
        	new Post(bob, "Who will in the Fifa Player of the Year 2008?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();

        	
        	new Post(bob, "Is Messi really better than what Maradona was at his age?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	
        	Post p = new Post(bob, "Where is fat old Ronaldo now?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	p.addComment(bob, "Adding a comment for the post");
        	p.tagItWith(tag);
        	p = new Post(bob, "Will Brazil win it again?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	p.addComment(bob, "Adding a comment for the post");
        	p.tagItWith(tag);
        	p = new Post(bob, "Who is the most overrated player?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	p.addComment(bob, "Adding a comment for the post");
        	p.tagItWith(tag);
        	p = new Post(bob, "I heard C Ronaldo is gay ", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	p.addComment(bob, "Adding a comment for the post");
        	p.tagItWith(tag);
        	p = new Post(bob, "Samuel Eto hates Guardiola", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	p.addComment(bob, "Adding a comment for the post");
        	p.tagItWith(tag);
        	p = new Post(bob, "How did Tevez got that scar?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	p.addComment(bob, "Adding a comment for the post");
        	p.tagItWith(tag);
        	p = new Post(bob, "Best soccer player of all time?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	p.addComment(bob, "Adding a comment for the post");
        	p.tagItWith(tag);
        	p = new Post(bob, "Football or Soccer?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	p.addComment(bob, "Adding a comment for the post");
        	p.tagItWith(tag);
        	p = new Post(bob, "Transfer rumors", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	p.addComment(bob, "Adding a comment for the post");
        	p.tagItWith(tag);
        	p = new Post(bob, "Was Ferguson wrong?", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	p.addComment(bob, "Adding a comment for the post");
        	p.tagItWith(tag);
        	p = new Post(bob, "Most red cards in a game", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	p.addComment(bob, "Adding a comment for the post");
        	p.tagItWith(tag);
        	p = new Post(bob, "How much ", "hgfhhfhg hjhjjj hhj hj jh h hjj hjhjhhjh hj hjh jh h ").save();
        	p.addComment(bob, "Adding a comment for the post");
        	p.tagItWith(tag);
        	
        //}
    }
 
}