package com.sktelecom.t1.Models;

import org.springframework.web.multipart.MultipartFile;

/*
后端接收实体类
 */
public class PlayerPost {
    private String playerID;
    private String position;
    private Integer age;
    private MultipartFile pic;

    public String getPlayerID() {
        return playerID;
    }

    public String getPosition() {
        return position;
    }

    public Integer getAge() {
        return age;
    }

    public MultipartFile getPic() {
        return pic;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPic(MultipartFile pic) {
        this.pic = pic;
    }
}
