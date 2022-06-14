package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

  private final UserService userService;

  private static final Logger logger = Logger.getLogger(UserController.class.getName());

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public String listAll(Model model) {
    List<User> userList = userService.listAll();
    model.addAttribute("userList",userList);

    return "users";
  }

  @GetMapping("/users/new")
  public String newUser(Model model) {
    List<Role> roleList = userService.listRole();
    User user = new User();

    user.setEnabled(true);
    model.addAttribute("user", user);
    model.addAttribute("roleList", roleList);

    return "user_form";
  }

  @PostMapping("/users/save")
  public String saveUser(User user, RedirectAttributes redirectAttributes) {
    logger.info(user.toString());
    userService.save(user);

    redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
    return "redirect:/users";
  }
}
