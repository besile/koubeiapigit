import com.koubei.util.com_EhcacheHelper;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;

import java.net.URL;

public class EhCacheTest {
    @Test
    public void test(){
        //com_EhcacheHelper.put("name",);
        URL url =EhCacheTest.class.getClassLoader().getResource("config.properties");
        CacheManager cacheManager=new CacheManager(url);
        //获取到cache
        Cache cache=cacheManager.getCache("UserCache");
        if(cache==null){
            Element element=new Element("key1","value1");
            cache.put(element);
        }


        Element element1=cache.get("key1");
//        System.out.println(element1.get);
        System.out.println(element1.getValue());
    }
@Test
    public void test2(){
        String url="/blog/edit/1";
        int i=url.lastIndexOf(47);
        System.out.println(i);
        String s=url.substring(i + 1);
        System.out.println(s);
    }
}
