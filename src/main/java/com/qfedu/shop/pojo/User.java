package com.qfedu.shop.pojo;

public class User {
    //所有数据类型都需要有数据库和java的对应关系
    //int 对应java  int   Integer
    //当数据库中int 类型是一个无符号数的时候
    //int类型的数据最大值将会变为原来的二倍
    //java中的int就不够大了。
    //所以java的策略是，无符号的int对应成java的long 或者Long
    private Long uid;//用户编号
    private String username;//用户名
    private String password;//密码   数据库用一个摘要算法去保存用户密码SHA-1,MD5
    private String gender;//性别
    private String email;//邮箱
    private String telephone;//电话
    private String introduce;//介绍
    private String activeCode;//激活码
    private String role;//角色
    private int state;//用户状态：0代表未激活，1代表已激活，2代表注销
    private String registTime;//用户注册时间


    public User() {
    }

    public User(String username, String password, String gender, String email, String telephone, String introduce) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.telephone = telephone;
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", introduce='" + introduce + '\'' +
                ", activeCode='" + activeCode + '\'' +
                ", role='" + role + '\'' +
                ", state=" + state +
                ", registTime='" + registTime + '\'' +
                '}';
    }

    public Long getUid() {
        return uid;
    }

    public User setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public User setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getIntroduce() {
        return introduce;
    }

    public User setIntroduce(String introduce) {
        this.introduce = introduce;
        return this;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public User setActiveCode(String activeCode) {
        this.activeCode = activeCode;
        return this;
    }

    public String getRole() {
        return role;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }

    public int getState() {
        return state;
    }

    public User setState(int state) {
        this.state = state;
        return this;
    }

    public String getRegistTime() {
        return registTime;
    }

    public User setRegistTime(String registTime) {
        this.registTime = registTime;
        return this;
    }
}
