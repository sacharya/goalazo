import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }
    
    @Test
    public void createAndRetrieveUser() {
    	new User("bob@gmail.com", "Passw0rd", "Bob", "Crazy", "crazybob").save();
    	
    	User bob = User.find("byEmail", "bob@gmail.com").first();
    	
    	assertNotNull(bob);
    	assertEquals("Bob", bob.firstName);
    	
    }
    
    @Test
    public void tryConnectAsUser() {
    	new User("bob@gmail.com", "Passw0rd", "Bob", "Crazy", "crazybob").save();
    	
    	assertNotNull(User.connect("bob@gmail.com", "Passw0rd"));
    	assertNull(User.connect("bob@gmail.com", "badpassword"));
    	assertNull(User.connect("tom@gmil.com", "Passw0rd"));
    }

}
