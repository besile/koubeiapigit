import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koubei.util.com_PropertiesFile;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Iterator;

public class ConfigTest {
    @Test
    public void Test() throws Exception {

        String path = ConfigTest.class.getResource("/GetCase.json").getFile();
        Reader reader = new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-8"));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(reader);
        JsonNode invokeConfig = rootNode.get("InvokeConfig");
        Iterator<JsonNode> list = invokeConfig.elements();
        while (list.hasNext()) {
            JsonNode item = list.next();
            System.out.println(item.get("ID"));
            System.out.println(item.get("ClassName"));
            System.out.println(item.get("MethodName"));
        }
    }

    @Test
    public void Test2() {
        System.out.println(System.getProperty("file.encoding"));
        System.out.println("中文");
    }

    @Test
    public void test3() {
        String name=com_PropertiesFile.getInstance("config").get("aliyun.oss.bucketName");
        System.out.println(name);
    }
}
