package dev.yidafu.auncel.user.center.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="username", nullable = false, length = 50)
    private String username;

    @Column(name="realname", nullable = true, length = 50)
    private String realname;

    @Lob
    @Column(name="avatar", nullable = false, columnDefinition = "text")
    private  String avatar;

    @Column(name = "role", columnDefinition = "int")
    private Integer role;

    @Column(name="status")
    private String status;

    @Column(name = "register_time")
    private Date registerTime;
    // https://stackoverflow.com/questions/197045/setting-default-values-for-columns-in-jpa
    @Column(name="register_ip"/*, columnDefinition="varchar(15) default '0.0.0.0'" */)
    private String registerIp = "0.0.0.0";

    @Column(name ="school")
    private String school;

    public User() {}

    public User(String username, String realname, String avatar, int role, String status, Date registerTime, String registerIp, String school) {
        this.username = username;
        this.realname = realname;
        this.avatar = avatar;
        this.role = role;
        this.status = status;
        this.registerTime = registerTime;
        this.registerIp = registerIp;
        this.school = school;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRealname() {
        return realname;
    }

    public String getAvatar() {
        return avatar;
    }

    public Integer getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public String getSchool() {
        return school;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}

