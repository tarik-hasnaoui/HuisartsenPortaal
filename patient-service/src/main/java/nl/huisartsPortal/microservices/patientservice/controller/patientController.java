package nl.huisartsPortal.microservices.patientservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.security.Principal;

@RestController
@RequestMapping("user")
public class patientController {

