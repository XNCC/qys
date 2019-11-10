package ncc.frontdesk.frontdesk.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-03 21:24
 */
@Controller
public class login {
    @Value("${server.port}")
    Integer port;

    @GetMapping("/")
    public String login() {
        System.out.println("当前使用端口为"+port);
        return "/login";
    }

    @PostMapping("/login")
    public String login1(String username, String password, HttpServletRequest request) {
        System.out.println("qweasd" + username);
        if (username != null && password != null && !username.equals("") && !password.equals("")) {
            if (username.equals("ncc") && password.equals("ncc")) {
                request.getSession().setAttribute("username", username);

                return "redirect:/index";
            } else {
                return "/login";
            }
        }
        return "/login";
    }

}
