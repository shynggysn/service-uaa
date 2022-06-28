package kz.ne.railways.tezcustoms.service.controller;

import kz.ne.railways.tezcustoms.service.entity.Company;
import kz.ne.railways.tezcustoms.service.entity.User;
import kz.ne.railways.tezcustoms.service.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public User getUser(){
        return userService.getUser();
    }

    @PutMapping("/company")
    public ResponseEntity<Company> updateCompany(){
        User user = userService.getUser();
        return ResponseEntity.ok(userService.updateCompany(user.getCompany().getIdentifier()));
    }

    @PutMapping("/email/{newEmail}")
    public ResponseEntity<User> updateUser(@PathVariable String newEmail){
        User user = userService.getUser();
        return ResponseEntity.ok(userService.updateUser(user, newEmail));
    }

    @GetMapping("/activate")
    public RedirectView activateChangedEmail(@RequestParam(value = "key") String key) {
        return userService.activateChangedEmail(key);
    }

}
