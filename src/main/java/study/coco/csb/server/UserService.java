package study.coco.csb.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
    initUsers();
  }

  private void initUsers() {
    userRepository.save(new User("Max Mustermann", "max.mustermann@example.com"));
    userRepository.save(new User("Erika Mustermann", "erika.mustermann@example.com"));
    userRepository.save(new User("John Doe", "john.doe@example.com"));
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public User getUser(Long id) {
    return userRepository.findById(id).get();
  }

  public User deleteUser(Long id) {
    User deletedUser = userRepository.findById(id).get();
    userRepository.deleteById(id);
    return deletedUser;
  }

  public void createUser(String name, String email) {

    for (User existingUser : userRepository.findAll()) {
      if (existingUser.getEmail().equals(email)) {
        return;
      }
    }

    userRepository.save(new User(name, email));
  }

  public void updateUserEmail(Long id, String email) {

    for (User existingUser : userRepository.findAll()) {
      if (existingUser.getEmail().equals(email)) {
        return;
      }
    }

    User user = getUser(id);
    user.setEmail(email);
    userRepository.save(user);
  }
}
