package study.coco.csb.server;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

  private ArrayList<User> users = new ArrayList<>();

  public UserService() {
    users.add(new User("Max Mustermann", "max.mustermann@example.com"));
    users.add(new User("Erika Mustermann", "erika.mustermann@example.com"));
    users.add(new User("John Doe", "john.doe@example.com"));
  }

  public List<String> getUsers() {
    return users.stream().map(User::getName).collect(Collectors.toList());
  }

  public String getUser(int id) {
    return users.get(id).getName();
  }

  public String deleteUser(int id) {
    return users.remove(id).getName();
  }

  public void createUser(String name, String email) {
    users.add(new User(name, email));
  }
}