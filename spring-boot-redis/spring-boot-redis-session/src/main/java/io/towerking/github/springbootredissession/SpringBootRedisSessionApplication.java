package io.towerking.github.springbootredissession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@SpringBootApplication
@RestController
@EnableRedisHttpSession
public class SpringBootRedisSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisSessionApplication.class, args);
    }

    @RequestMapping("hello")
    public String getSession(HttpSession session, String name) {
        String storedName = (String) session.getAttribute("name");
        if (storedName == null) {
            session.setAttribute("name", name);
            storedName = name;
        }

        return "Hello " + storedName;
    }

}
