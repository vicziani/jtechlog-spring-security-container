package jtechlog.springsecurity.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Web réteg.
 */
@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("index.html")
    public String index() {
        return "index";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping("/user.html")
    public String user() {
        LOGGER.debug("Bejelentkezett felhasználó: {}",
                new Object[] {SecurityContextHolder.getContext().getAuthentication().getPrincipal()});
        return "user";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/admin.html")
    public String admin() {
        LOGGER.debug("Bejelentkezett felhasználó: {}",
                new Object[] {SecurityContextHolder.getContext().getAuthentication().getPrincipal()});
        return "admin";
    }
}
