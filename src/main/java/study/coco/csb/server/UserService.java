package study.coco.csb.server;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

  private ArrayList<User> users = new ArrayList<>();

  public UserService() {
    users.add(new User("Max Mustermann", "max.mustermann@example.com"));
    users.add(new User("Erika Mustermann", "erika.mustermann@example.com"));
    users.add(new User("John Doe", "john.doe@example.com"));
  }

  public List<User> getUsers() {
    return users;
  }

  public User getUser(int id) {
    return users.get(id);
  }

  public User deleteUser(int id) {
    return users.remove(id);
  }

  public void createUser(String name, String email) {

    for (User existingUser : users) {
      if (existingUser.getEmail().equals(email)) {
        return;
      }
    }

    users.add(new User(name, email));
  }

  public void updateUserEmail(int id, String email) {
    users.get(id).setEmail(email);
  }
}
