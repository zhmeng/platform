import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;

import modelx.teamclub.User;
import org.junit.Test;

/**
 * Created by zhangmeng on 16-8-30.
 */


public class EbeanTest extends BaseTest{
    @Test
    public void testDB(){
        EbeanServer server = Ebean.getServer("jira");
        User user = server.find(User.class).where().eq("userKey","ccsoul").findUnique();
        System.out.println(user.getUserKey());
    }

}
