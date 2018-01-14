package smartlab.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smartlab.model.Account;

@RestController
public class AccountController {
    @RequestMapping("/{id}")
    @PreAuthorize("#oauth2.hasScope('read')")
    public Account findAccountById(@PathVariable("id") Integer id) {
        return new Account(id, "123456789");
    }

}
