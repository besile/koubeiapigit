import com.fasterxml.jackson.databind.util.ClassUtil;
import com.koubei.model.School;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanTest {
    @Test
    public void Test() throws Exception{

        Class<?> type2 = ClassUtils.forName("int",MethodParameter.class.getClassLoader());
        Class<?> type = Class.forName("java.lang.Integer");
        //Class<?> ii=Class.forName("int.class");
        Class<?> i=int.class;

        Class<Integer> intType=Integer.TYPE;
        String name = intType.getName();
        System.out.println(name);
        Class<?> t=Class.forName("com.koubei.model.School");
        Method method=t.getMethod("aa",null);
        Object obj = t.newInstance();
        Object oo=method.invoke(obj,null);
//        AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-mvc.xml");
//        School school=(School) ac.getBean("test",1);
//
//        System.out.println(school.getSum());
//        school.add();
    }
}
