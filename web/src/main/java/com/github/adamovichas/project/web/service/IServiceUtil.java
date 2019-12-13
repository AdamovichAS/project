package com.github.adamovichas.project.web.service;

import com.github.adamovichas.project.model.dto.AuthUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface IServiceUtil {
    Cookie getCookie(String cookieName, Cookie[] cookies);

    AuthUser setAuthUserInSession(HttpServletRequest request, String login);

    void setUserBetsInReq(HttpServletRequest req);
}
