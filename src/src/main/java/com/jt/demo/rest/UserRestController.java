package com.jt.demo.rest;

import com.jt.demo.entity.User;
import com.jt.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;

    // inject our User Service
    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User theUser) {
        // set to 0 in case an id is passed in
        theUser.setId(0);
        return userService.save(theUser);
    }

    @PostMapping("/users/{firstName}/{lastName}/{email}")
    public User addUserByPath(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String email) {
        User newUser = new User();
        newUser.setId(0);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);

        return userService.save(newUser);
    }

    @GetMapping("/users/lists/{firstName}")
    public List<User> getByFirstName(@PathVariable String firstName) {
        return userService.findByFirstName(firstName);
    }

    @GetMapping("/users/count")
    public String getUserCount() {
        return "There are " + userService.findAll().size() + " user(s) in the system";
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        User user = userService.findById(id);

        if (user == null) {
            throw new RuntimeException("Employee id not found, id input = " + id);
        }
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {

        User user = userService.findById(id);

        if (user == null) {
            throw new RuntimeException("User id not found, id input " + id);
        }

        userService.deleteById(id);

        return "Deleted user: " + user;

    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User theUser) {
        return userService.save(theUser);
    }
}
