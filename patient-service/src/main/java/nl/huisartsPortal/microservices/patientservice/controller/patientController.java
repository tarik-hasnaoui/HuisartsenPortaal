package nl.huisartsPortal.microservices.patientservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.security.Principal;

@RestController
public class patientController {

    @GetMapping("/admin/hello")
    public String sayHelloToAdmin() {
        return "Hello Admin";
    }

    @GetMapping("/user/hello")
    public String sayHelloToUser() {
        return "Hello User";
    }

    @GetMapping("/guest/hello")
    public String sayHelloToGuest() {
        return "Hello Guest" ;
    }


    }

