import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.koubei.model.School;
import com.koubei.util.com_MemcachedHelper;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MemcacheTest {
    @Test
    public void Test() throws IOException {
        School school=new School();
        school.setSum(1);
        school.setName("zhangsan");
        school.setCreateTime(new Date());

        com_MemcachedHelper.put("key1", new String[]{"a","b"}, 10);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        String json = mapper.writeValueAsString(school);
        System.out.println(json);
        com_MemcachedHelper.put("key2", json, 10);
        Map<String, Object> map = com_MemcachedHelper.getMulti("key2");

        Object ss=com_MemcachedHelper.get("key2");


        School user = mapper.readValue(ss.toString(), School.class);
    }
}
