package com.github.adamovichas.project.web.controller;

import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.model.dto.UserPassportDTO;
import com.github.adamovichas.project.service.data.IUserService;
import com.github.adamovichas.project.web.service.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Controller
public class UserInfoUpdateController {

    private static final Logger log = LoggerFactory.getLogger(AuthentificationController.class);

    private final IUserService userService;

    @Value("${pass.upload.path}")
    private String uploadPath;

    public UserInfoUpdateController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/update")
    public ModelAndView getUpdatePage(@RequestParam(value = "action", required = false) String action) {
        final ModelAndView modelAndView = new ModelAndView("update");
        if (nonNull(action)) {
            modelAndView.addObject("action", action);
        }
        return modelAndView;
    }

    @PostMapping(value = "/update_password")
    public ModelAndView updatePassword(HttpServletRequest req) {
        AuthUser authUser = WebUtil.getUserInSession();
        ModelAndView modelAndView = new ModelAndView("update");
        final String password = req.getParameter("password");
        final String repeatedPassword = req.getParameter("repeatedPassword");
        if (!password.equals(repeatedPassword)) {
            modelAndView.addObject("message", "Update is failed! ");
        } else {
            Map<String, String> userFieldsForUpdate = new HashMap<>();
            userFieldsForUpdate.put("password", password);
            userFieldsForUpdate.put("repeatedPassword", repeatedPassword);
            userService.updateUser(authUser.getLogin(), userFieldsForUpdate);
            modelAndView.addObject("message", "Update is done! ");
        }
        return modelAndView;
    }

    @PostMapping(value = "/update_passport")
    public ModelAndView updatePassport(MultipartHttpServletRequest req, @RequestParam("passportImg") MultipartFile passportImg) throws IOException {
        AuthUser authUser = WebUtil.getUserInSession();
        ModelAndView modelAndView = new ModelAndView("update");
        Map<String, String> passportFieldsForUpdate = new HashMap<>();
        if (nonNull(passportImg) && !passportImg.getOriginalFilename().isEmpty()) {
            byte[] bytes = passportImg.getBytes();
            String uuidFile = UUID.randomUUID().toString();
            String fileName = uuidFile + passportImg.getOriginalFilename();
            File dir = new File(uploadPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File uploadedFile = new File(dir.getAbsolutePath() + File.separator + fileName);

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
            stream.write(bytes);
            stream.flush();
            stream.close();
            passportFieldsForUpdate.put("passFileName",fileName);
        }
        passportFieldsForUpdate.put("firstName", req.getParameter("firstName"));
        passportFieldsForUpdate.put("lastName", req.getParameter("lastName"));
        passportFieldsForUpdate.put("passSeries", req.getParameter("passSeries"));
        WebUtil.removeEmptyValue(passportFieldsForUpdate);
        if (!passportFieldsForUpdate.isEmpty()) {
            userService.updatePassport(authUser.getLogin(), passportFieldsForUpdate);
            final UserPassportDTO passport = userService.getPassport(authUser.getLogin());
            modelAndView.addObject("passport", passport);
            modelAndView.addObject("message", "Update is done! ");
            log.info("Updated passport {} at {}", passport, LocalDateTime.now());
        } else {
            modelAndView.addObject("message", "Update is failed! ");
        }
        return modelAndView;
    }
}
