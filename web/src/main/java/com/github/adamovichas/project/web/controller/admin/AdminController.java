package com.github.adamovichas.project.web.controller.admin;

import com.github.adamovichas.project.model.dto.AppCashAccountDTO;
import com.github.adamovichas.project.model.dto.UserPassportDTO;
import com.github.adamovichas.project.model.user.passport.VereficationStatus;
import com.github.adamovichas.project.service.data.ICashAccountService;
import com.github.adamovichas.project.service.data.IUserService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    @Value("${pass.upload.path}")
    private String uploadPath;
    private final ICashAccountService cashAccountService;
    private final IUserService userService;

    public AdminController(ICashAccountService cashAccountService, IUserService userService) {
        this.cashAccountService = cashAccountService;
        this.userService = userService;
    }

    //    @RequestParam(value = "action",required = false) String action
    @GetMapping(value = "")
    public ModelAndView adminGet(){
        AppCashAccountDTO appCashAccount = cashAccountService.getAppCashAccount();
        return new ModelAndView("admin_page","appCash",appCashAccount);
    }

    @GetMapping(value = "/get_not_verified_users")
    public ModelAndView getNotVerifiedUsers(@RequestParam(value = "currentPage",required = false) Integer currentPage){
        ModelAndView modelAndView = adminGet();
        if (currentPage == null) {
            currentPage = 1;
        }
        modelAndView.addObject("currentPage", currentPage);
        List<UserPassportDTO> notVerifiedPassports = userService.getPassportOnPageByVerificationStatusWaitAndRoleUser(currentPage);
        if(!notVerifiedPassports.isEmpty()){
            modelAndView.addObject("passportVerif",notVerifiedPassports);
        }
        Long maxPages = userService.getPassportMaxPagesByVerificationStatusWaiting();
        modelAndView.addObject("maxPages", maxPages);
        return modelAndView;
    }

    @GetMapping(value = "/get_not_verified_users/get_passport_info")
    public ModelAndView getPassportInfo(//@RequestParam(value = "action") String action,
                                        @RequestParam(value = "currentPage") Integer currentPage,
                                        @RequestParam(value = "login") String login){
        ModelAndView modelAndView = getNotVerifiedUsers(currentPage);
        modelAndView.addObject("login",login);
        UserPassportDTO passport = userService.getPassport(login);
        modelAndView.addObject("passportForVer",passport);
//        String fileForView = uploadPath + passport.getPassFileName() + ".jpg";
//        modelAndView.addObject("fileForView",fileForView);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/get_img", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] testphoto(@RequestParam(value = "photo")String photo) throws IOException {
        File f = new File(uploadPath+photo);
        InputStream in=new FileInputStream(f);
        return IOUtils.toByteArray(in);
    }

    @PostMapping(value = "/get_not_verified_users/get_passport_info/verify")
    public ModelAndView veriefyUser(HttpServletRequest req){
        String login = req.getParameter("login");
        VereficationStatus newVerificationStatus = VereficationStatus.valueOf(req.getParameter("newVerificationStatus"));
        Integer currentPage = Integer.valueOf(req.getParameter("currentPage"));
        userService.verifyUser(login,newVerificationStatus);
        ModelAndView modelAndView = getNotVerifiedUsers(currentPage);
        return modelAndView;
    }

}
