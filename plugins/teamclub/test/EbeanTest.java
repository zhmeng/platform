import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import models.teamclub.AppUser;
import models.xteamclub.VersionManager;
import org.junit.Test;

/**
 * Created by zhangmeng on 16-8-30.
 */


public class EbeanTest extends BaseTest{
    @Test
    public void testDB(){
        VersionManager versionManager = new VersionManager();
        versionManager.setCommitVersion("1234");
        versionManager.setFilePath("/asdf/sdf");
        versionManager.save();
    }

}
