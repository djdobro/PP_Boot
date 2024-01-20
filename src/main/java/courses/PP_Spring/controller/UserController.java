package courses.PP_Spring.controller;

import courses.PP_Spring.model.User;
import courses.PP_Spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String newUser(@ModelAttribute("user") User user){
        return "user/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/user";
    }
    @GetMapping("/edit")
    public String editUser(Model model){
        return "user/edit";
    }
    @PostMapping("/edit")
    public String editUserFromDB(@RequestParam(name = "id") Integer id, @RequestParam(name = "name") String name,
                                 @RequestParam(name = "lastname") String lastname, @RequestParam(name = "age") byte age, Model model) {
        User user = userService.getById(id);
        if(user != null) {
            user.setName(name);
            user.setLastname(lastname);
            user.setAge(age);
        } else {
            System.out.println("Такого пользователя нет!");
        }
        User updatedUser = userService.update(user);
        model.addAttribute("updateUser", updatedUser);
        return "redirect:/user";
    }

    @GetMapping("/delete")
    public String showDeleteUser() {
        return "user/delete";
    }

    @PostMapping("/delete")
    public String deleteUserFromDB(@RequestParam(name = "id") int id){
        User user = userService.getById(id);
        if(user != null) {
            userService.delete(user);
        } else {
            System.out.println("Такого пользователя нет!");
        }
        return "redirect:/user";
    }

}
