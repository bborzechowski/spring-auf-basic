package bborzechowski.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecurityRestController {

    @GetMapping("/user")
    public String userRolePlayGround(){
        return "You are the user";
    }

    @GetMapping("/admin")
    public String adminRolePlayGround(){
        return "You are the admin";
    }

    @GetMapping("/home")
    public String freeForAll(){
        return "You are in the home page";
    }

}
