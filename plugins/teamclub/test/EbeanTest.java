import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import models.teamclub.AppUser;
import org.junit.Test;

/**
 * Created by zhangmeng on 16-8-30.
 */


public class EbeanTest extends BaseTest{
    @Test
    public void testDB(){
        EbeanServer server = Ebean.getServer("jira");
        AppUser user = server.find(AppUser.class).where().eq("userKey","ccsoul").findUnique();
        System.out.println(user.getUserKey());
    }

}
