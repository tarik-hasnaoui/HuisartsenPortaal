package nl.huisartsPortal.microservices.patientservice.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

@RestController
@RequestMapping("user")
public class patientController {

         @GetMapping("/")
          public String getHello() {
        return "hello";
    }

        @GetMapping("/login")
        public String getUser() {
            return "hello user";
        }

        @GetMapping("/logout")
        public void logout(HttpServletRequest request) throws ServletException {
            request.logout();
        }
    }

