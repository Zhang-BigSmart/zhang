import com.zhang.cms.dao.model.CmsArticle;
import com.zhang.cms.dao.model.CmsArticleExample;
import com.zhang.cms.dao.model.CmsCategory;
import com.zhang.cms.dao.model.CmsUser;
import com.zhang.cms.rpc.api.CmsApiService;
import com.zhang.cms.rpc.api.CmsArticleService;
import com.zhang.cms.rpc.api.CmsCategoryService;
import net.sf.json.util.JSONUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/8/10
 * @history
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:applicationContext.xml",
        "classpath:applicationContext-dubbo-consumer.xml"
})
public class CmsServiceTest {

    @Autowired
    private CmsArticleService cmsArticleService;

    @Autowired
    private CmsApiService cmsApiService;

    @Test
    public void cmsAtrticleTest(){
        CmsUser cmsUser = cmsApiService.selectCmsUserByUsername("edison");
        System.out.println(cmsUser.toString());
    }
}
