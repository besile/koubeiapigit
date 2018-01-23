import com.koubei.bll.bll_Topic;
import com.koubei.handler.ctrl_ServiceClient;
import com.koubei.model.m_D_Topic;
import org.junit.Test;

import java.util.List;

public class TopicTest {
    @Test
    public void Test() throws Exception {
        //Object o = bll_Topic.kb_API_GetTopicIdByGuid("CD4FC7C1-2B4F-4820-BC6B-EC6097DB5205");
        String topicGuid = "CD4FC7C1-2B4F-4820-BC6B-EC6097DB5205";
        List<m_D_Topic> list = ctrl_ServiceClient.<m_D_Topic>GetService(true, "根据guid获取口碑", new Object[]{topicGuid},m_D_Topic.class);
        for(m_D_Topic item:list){
            System.out.println(item.getTopicId());
        }
    }
    @Test
    public void  Test2() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    }
}
