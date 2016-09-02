import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import models.AppSubjectUser;
import jira.User;
import org.junit.Test;

import java.util.List;

/**
 * Created by zhangmeng on 16-8-30.
 */


public class EbeanTest extends BaseTest{
    @Test
    public void testDB(){
        EbeanServer server = Ebean.getServer("jira");
        User user = server.find(User.class).findUnique();
        System.out.println(user.getUserKey());
    }

    @Test
    public void testTeamClubDB() {
        List<AppSubjectUser> list = Ebean.find(AppSubjectUser.class).findList();
        System.out.println(list);
    }
}
