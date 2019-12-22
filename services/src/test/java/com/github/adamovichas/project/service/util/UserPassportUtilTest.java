package com.github.adamovichas.project.service.util;

import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.config.HibernateConfig;
import com.github.adamovichas.project.model.dto.UserPassportDTO;
import com.github.adamovichas.project.model.user.passport.VereficationStatus;
import com.github.adamovichas.project.service.config.ServiceConfig;
import com.github.adamovichas.project.service.util.user.passport.IUserPassportUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class, ServiceConfig.class})
public class UserPassportUtilTest {

    @Autowired
    IUserPassportUtil passportUtil;

    @Test
    void createTestPassport(){
        Map<String,String> passportFieldsAndValues = new HashMap<>();
        passportFieldsAndValues.put("firstName","firstName");
        passportFieldsAndValues.put("lastName","lastName");
        passportFieldsAndValues.put("passSeries","passSeries");
        passportFieldsAndValues.put("passFileName","passFileName");

        UserPassportDTO passport = passportUtil.createPassport("userLogin", passportFieldsAndValues);
        assertEquals(passport.getUserLogin(),"userLogin");
        assertEquals(passport.getVereficationStatus(), VereficationStatus.VEREF_WAITING);
        assertEquals(passport.getFirstName(),"firstName");
        assertEquals(passport.getLastName(),"lastName");
        assertEquals(passport.getPassFileName(),"passFileName");
        assertEquals(passport.getPassSeries(),"passSeries");
    }
}
