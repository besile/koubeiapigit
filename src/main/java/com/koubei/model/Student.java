package com.koubei.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

public @XmlRootElement
class Student implements Serializable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
