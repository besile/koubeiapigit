package com.koubei.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
public class School implements Serializable {
    private static final long serialVersionUID = -3371451210123762490L;
    public School() {
    }

    public School(int sum) {
        this.sum = sum;
    }

    public void add() {
        System.out.println("add方法");
    }

    public String aa() {
        return "aa";
    }

    private String name;
    private int sum;
    private int history; // 历史

    private Date createTime;    //创建时间
    @XmlElement(name = "student")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    private Student student;
    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @XmlElement
    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    @XmlElement
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

