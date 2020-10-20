package com.test.xiding;

/**
 * @createTime: 2020/10/19
 * @author: lady_zhou
 * @Description:
 */
public class Star {
    private String name;
    private String groundName;

    public Star(String name, String groundName) {
        this.name = name;
        this.groundName = groundName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroundName() {
        return groundName;
    }

    public void setGroundName(String groundName) {
        this.groundName = groundName;
    }
}
