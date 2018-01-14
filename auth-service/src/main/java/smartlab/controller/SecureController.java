package smartlab.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SecureController {

    @GetMapping("/ping")
    @PreAuthorize("#oauth2.hasScope('read')")
    public String ping() {
        return "Secure Hello!";
    }

}