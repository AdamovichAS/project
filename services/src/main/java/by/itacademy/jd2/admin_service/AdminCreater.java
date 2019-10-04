package by.itacademy.jd2.admin_service;

import by.itacademy.jd2.user.Admin;

import java.util.Map;

public enum  AdminCreater {
    CREATER;

    private AdminFieldsSetter SETTER;

    AdminCreater() {
        SETTER = AdminFieldsSetter.SETTER;
    }



    public Admin createAdmin(Map<String,String> adminFieldsAndValues){
        Admin admin = new Admin();
        SETTER.setFields(adminFieldsAndValues,admin);
        return admin;
    }
}
