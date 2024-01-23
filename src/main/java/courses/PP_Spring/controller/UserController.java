package courses.PP_Spring.controller;

import courses.PP_Spring.model.User;
import courses.PP_Spring.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String listOfUsers(Model model) {
        model.addAttribute("listOfUsers", userService.allUsers());
        return "user/allUsers";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "user/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/user";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam(name = "id") Integer id, Model model) {
        try {
            User user = userService.getById(id);
            model.addAttribute("editUser", user);
            return "user/edit";
        } catch (EntityNotFoundException e) {
            return "redirect:/user";
        }

    }

    @PostMapping("/edit")
    public String editUserFromDB(@Valid @ModelAttribute("user") User user) {
        try {
            userService.update(user);
        } catch (EntityNotFoundException e) {
            return "redirect:/user";
        }
        return "redirect:/user";
    }

    @GetMapping("/delete")
    public String showDeleteUser() {
        return "user/delete";
    }

    @PostMapping("/delete")
    public String deleteUserFromDB(@RequestParam(name = "id") int id) {
        try {
            User user = userService.getById(id);
            userService.delete(user);
        } catch (EntityNotFoundException e) {
            return "redirect:/user";
        }

        return "redirect:/user";
    }

}
