package controller;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserService;

import java.security.Principal;

@Controller
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод контроллера который выводит список всех user с БД .
     * В возвращаемом значение название View (html страницы).
     * Аннотация указывает приложению что это get запрос HTTP
     */
    @GetMapping(value = "/admin")
    public String index(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    /**
     * Метод контроллера который возвращает пользователя из БД по id.
     * Параемтр переданный в строке URL извлекается с помощью аннотации @PathVariable
     * и присваивается переменной id.
     */
    @GetMapping("/admin/create")
    public String createForm(User user, Model model) {
        model.addAttribute(user);
        return "/create";
    }

    @PostMapping("/admin/create")
    public String create(User user, String[] role) {
        user.setRoles(role);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete")
    public String deleteById(@RequestParam long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/update")
    public String updateUserForm(@RequestParam long id, Model model) {
        User user = userService.getUserId(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "update";
    }

    @PostMapping("/admin/update")
    public String updateUser(User user, String[] role) {
        user.setRoles(role);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String test(Model model, Principal user) {
        model.addAttribute("user", userService.findByLastName(user.getName()));
        return "/user";
    }
}
