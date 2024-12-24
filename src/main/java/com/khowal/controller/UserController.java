package com.khowal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khowal.entity.User;
import com.khowal.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String loadForm(Model model) {
        User userObj = new User();
        model.addAttribute("user", userObj);
        return "index";
    }

    @PostMapping("/user")
    public String handleSubmit(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "index";
        }
        boolean isSaved = userService.saveUsers(user);
        if (isSaved) {
            String subject = "Welcome to UserEmailer !";
            String message = """
                        <html>
                            <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                                <h2 style="color: #4CAF50;">Thank You for Registering!</h2>
                                <p>Dear <b>%s</b>,</p>
                                <p>We are thrilled to have you on board. Your registration was successful, and we hope you enjoy this journey with us.</p>
                                <p>Click the button below to explore more about me:</p>
                                <a href="https://chaitanyakhowalportfolio.netlify.app/" target="_blank"
                                    style="background-color: #4CAF50; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; display: inline-block; font-size: 16px;">
                                    Visit My Portfolio
                                </a>
                                <p>Warm regards,</p>
                                <p><b>Chaitanya Sundarlal Khowal</b></p>
                            </body>
                        </html>
                    """
                    .formatted(user.getUserName());
            String to = user.getUserEmail();
            userService.sendEmail(to, message, subject);
            model.addAttribute("success", "User saved successfully. Please check your mail ");
        } else {
            model.addAttribute("error", "User not saved");
        }
        return "index";
    }

    @GetMapping("/view")
    public String getUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "view";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("uid") Integer id, Model model) {
        User userById = userService.getUserById(id);
        model.addAttribute("user", userById);
        return "index";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("uid") Integer id, Model model) {
        boolean deleteUserById = userService.deleteUserById(id);
        if (deleteUserById) {
            model.addAttribute("success", "User deleted successfully");
            List<User> allUsers = userService.getAllUsers();
            model.addAttribute("users", allUsers);
        } else {
            model.addAttribute("error", "User not deleted");
        }
        return "view";
    }

    // Pagination method added
    @GetMapping("/paginate")
    public String getPaginatedUsers(
        @RequestParam(defaultValue = "1") int pageNo,
        @RequestParam(defaultValue = "3") int pageSize,
        Model model) {
        Page<User> paginatedUsers = userService.getPaginatedUsers(pageNo, pageSize);
        model.addAttribute("users", paginatedUsers.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", paginatedUsers.getTotalPages());
        model.addAttribute("totalItems", paginatedUsers.getTotalElements());
        return "view"; // Render the paginated list in the 'view.html'
    }
}
