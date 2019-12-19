package com.github.adamovichas.project.web.controller;

import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.dto.UserPassportDTO;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.service.data.IUserService;
import com.github.adamovichas.project.service.util.IUtil;
import com.github.adamovichas.project.web.request.LoginRequest;
import com.github.adamovichas.project.web.service.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

@Controller
public class AuthentificationController {

    private static final Logger log = LoggerFactory.getLogger(AuthentificationController.class);

    private final IUserService userService;
    private final IUtil util;

    public AuthentificationController(IUserService userService, IUtil util) {
        this.userService = userService;
        this.util = util;
    }

    @GetMapping(value = "/login")
    public String loginGet() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
            return "login";
        }
        return "redirect:/my_page";
    }

    @PostMapping(value = "/login")
    public String loginPost(HttpServletRequest req, LoginRequest logPass) {
//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
        if (userService.userIsExist(logPass.getLogin(), logPass.getPassword())) {
//            AuthUser authUser = serviceUtil.setAuthUserInSession(req, logPass.getLogin());
            UserDTO userDTO = userService.getUserByLogin(logPass.getLogin());
            AuthUser authUser = new AuthUser(userDTO.getLogin(), userDTO.getRole());
            final Authentication authentication = new UsernamePasswordAuthenticationToken(authUser, null, getAuthorities(authUser.getRole().toString()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("user {} logIn at {}", logPass.getLogin(), LocalDateTime.now());
            return "redirect:/my_page";
        } else {
            req.setAttribute("wrongLogin", "User with this Login and password does not exist");
            log.info("user {} Wrong logIn at {}", logPass.getLogin(), LocalDateTime.now());
            return "login";
        }
    }

    private List<GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList((GrantedAuthority) () -> "ROLE_" + role);
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest req){
        AuthUser authUser = WebUtil.getUserInSession();
        SecurityContextHolder.clearContext();
        try {
            req.logout();
        } catch (ServletException e) {
            throw new RuntimeException();
        }
        log.info("User login: {} logged out at: {}", authUser.getLogin(), LocalDateTime.now());
        return "login";
    }

    @RequestMapping(value = "/my_page")
    public String redirectAfterLogin(HttpServletRequest req) {
        AuthUser authUser = WebUtil.getUserInSession();
        if (Role.ADMIN.equals(authUser.getRole())) {
            return "redirect:/admin/";
        } else {
            return "redirect:/user/";
        }
    }

    @GetMapping(value = "/registration")
    public String registrationGet(){
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registrationPost(HttpServletRequest req){
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String repeatedPassword = req.getParameter("repeatedPassword");
        if(userService.loginIsExist(login) || !password.equals(repeatedPassword)){
            req.setAttribute("loginError","A user with this login already exists or the passwords do not match");
            return "forward:/registration.jsp";
        }else{
            Map<String,String> userParam = new HashMap<>();
            userParam.put("login",login);
            userParam.put("password",password);
            userParam.put("role",Role.USER_NOT_VER.toString());
            userService.addNewUser(userParam);
            AuthUser authUser = new AuthUser(login, Role.USER_NOT_VER);
            final Authentication authentication = new UsernamePasswordAuthenticationToken(authUser, null, getAuthorities(authUser.getRole().toString()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
//            req.getSession().setAttribute("authUser",authUser);
            log.info("user created:{} at {}", login, LocalDateTime.now());
            return "user_page";
        }
    }
    @GetMapping(value = "/update")
    public ModelAndView getUpdatePage(@RequestParam(value = "action",required = false) String action){
        final ModelAndView modelAndView = new ModelAndView("update");
        if(nonNull(action)){
            modelAndView.addObject("action", action);
        }
        return modelAndView;
    }

    @PostMapping(value = "/update_password")
    public ModelAndView updatePassword(HttpServletRequest req){
        AuthUser authUser = WebUtil.getUserInSession();
        ModelAndView modelAndView = new ModelAndView("update");
        final String password = req.getParameter("password");
        final String repeatedPassword = req.getParameter("repeatedPassword");
        if(!password.equals(repeatedPassword)){
            modelAndView.addObject("message","Update is failed! ");
        }else {
            Map<String,String> userFieldsForUpdate = new HashMap<>();
            userFieldsForUpdate.put("password",password);
            userFieldsForUpdate.put("repeatedPassword",repeatedPassword);
            userService.updateUser(authUser.getLogin(),userFieldsForUpdate);
            modelAndView.addObject("message","Update is done! ");
        }
        return modelAndView;
    }

    @PostMapping(value = "/update_passport")
    public ModelAndView updatePassport(HttpServletRequest req){
        AuthUser authUser = WebUtil.getUserInSession();
        ModelAndView modelAndView = new ModelAndView("update");
        Map<String,String> passportFieldsForUpdate = new HashMap<>();
//        userFieldsForUpdate.put("password",req.getParameter("password"));
//        userFieldsForUpdate.put("repeatedPassword",req.getParameter("repeatedPassword"));
        passportFieldsForUpdate.put("firstName",req.getParameter("firstName"));
        passportFieldsForUpdate.put("lastName",req.getParameter("lastName"));
        passportFieldsForUpdate.put("passSeries",req.getParameter("passSeries"));
        util.removeEmptyValue(passportFieldsForUpdate);
        if(!passportFieldsForUpdate.isEmpty()){
            userService.updatePassport(authUser.getLogin(),passportFieldsForUpdate);
            final UserPassportDTO passport = userService.getPassport(authUser.getLogin());
            modelAndView.addObject("passport",passport);
            modelAndView.addObject("message","Update is done! ");
            log.info("Updated passport {} at {}", passport, LocalDateTime.now());
        }else {
            modelAndView.addObject("message","Update is failed! ");
        }
        return modelAndView;
    }
}
