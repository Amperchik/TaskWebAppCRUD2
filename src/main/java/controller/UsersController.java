package controller;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.UserService;

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
    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    /**
     * Метод контроллера который возвращает пользователя из БД по id.
     * Параемтр переданный в строке URL извлекается с помощью аннотации @PathVariable
     * и присваивается переменной id.
     */
    @GetMapping("/create")
    public String createForm(User user, Model model) {
        model.addAttribute(user);
        return "/create";
    }

    @PostMapping("/create")
    public String create(User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model) {
        User user = userService.getUserId(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}
