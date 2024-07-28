package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleService roleService, RoleRepository roleRepository, RoleRepository roleRepository1) {
        this.userService = userService;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model, Principal principal) {
        model.addAttribute("allUsers", userService.getAll());
        model.addAttribute("allRoles", roleService.findAll());
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("currentUserId", user.getId());
        model.addAttribute("addUser", new User());
        return "admin";
    }

    @PostMapping("/admin/edit/{id}")
    public String updateUser(@ModelAttribute("addUser") User user, @RequestParam(name = "selectedRoles", required = false) Long[] selectedRoles, @PathVariable("id") Long id) {
        List<Role> roles = new ArrayList<>();
        if (selectedRoles != null && selectedRoles.length > 0) {
            roles = roleRepository.findAllById(Arrays.asList(selectedRoles));
        }
        userService.editUser(id, user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), roles);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete")
    public String deletedUser( @RequestParam(value = "id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(name = "selectedRoles", required = false) Long[] selectedRoles) {
        List<Long> roles = new ArrayList<>();
        if (selectedRoles != null && selectedRoles.length > 0) {
            roles = Arrays.asList(selectedRoles);
        }
        userService.add(user, roles);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String showUserPage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", userService.showUser(user.getId()));
        return "user";
    }

    @GetMapping("/admin/user")
    public String showAdminInfo(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", userService.showUser(user.getId()));
        return "user";
    }

}
