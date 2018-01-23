package com.koubei.controller;

import com.koubei.annotation.QueryCache;
import com.koubei.handler.ctrl_ServiceClient;
import com.koubei.model.School;
import com.koubei.model.Student;
import com.koubei.model.m_D_Topic;
import org.junit.runners.Parameterized;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.rmi.CORBA.StubDelegate;
import java.util.Date;
import java.util.List;

@Controller
public class TopicController {

    @QueryCache(value = "school",time = 1)
    @RequestMapping(value = "/school", method = RequestMethod.GET)
    public @ResponseBody School getSchool(@RequestParam(value = "a",required = false) String a,@RequestParam(value = "b",required = false) String b) {
        School school = new School();
        school.setName("zhangsan333");
        school.setHistory(1);
        school.setCreateTime(new Date());
        school.setSum(1111);
        Student student=new Student();

        student.setName("s1111");
        school.setStudent(student);
        return school;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String employee() {
        return "employee";
    }

    @ResponseBody
    @RequestMapping(value = "/gettopic", method = RequestMethod.GET)
    public m_D_Topic getTopic(@RequestParam("topicGuid") String topicGuid) throws Exception {
        List<m_D_Topic> list = ctrl_ServiceClient.<m_D_Topic>GetService(true, "根据guid获取口碑", new Object[]{topicGuid},m_D_Topic.class);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
