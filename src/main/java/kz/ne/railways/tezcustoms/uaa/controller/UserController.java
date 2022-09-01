package kz.ne.railways.tezcustoms.uaa.controller;

import kz.ne.railways.tezcustoms.common.entity.Company;
import kz.ne.railways.tezcustoms.common.entity.User;
import kz.ne.railways.tezcustoms.common.payload.response.UserProfile;
import kz.ne.railways.tezcustoms.uaa.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/profile")
    public UserProfile getUserProfile(){
        return userService.getUserProfile();
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
}
