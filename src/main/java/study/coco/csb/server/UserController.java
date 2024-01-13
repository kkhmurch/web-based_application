package study.coco.csb.server;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

  UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<String> getUsers() {
    return userService.getUsers();
  }

  @GetMapping("/users/{id}")
  public String getUser(@PathVariable("id") int id) {
    return userService.getUser(id);
  }

  @PostMapping("/users/submit")
  public void registerUser(@RequestParam("name") String name, @RequestParam("email") String email) {
    userService.createUser(name,email);
  }

  @DeleteMapping("/users/{id}")
  public String deleteUser(@PathVariable("id") int id) {
    return userService.deleteUser(id);
  }
}