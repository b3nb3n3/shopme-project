package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
class UserRepositoryTests {

  @Autowired UserRepository userRepository;

  @Autowired private TestEntityManager entityManager;

  @Test
  void testCreateUser() {
    Role roleAdmin = entityManager.find(Role.class, 1);
    User userb3n = new User("b3nb3n@gmail.com", "b3nb3n", "Vinh", "Hong");
    userb3n.addRole(roleAdmin);

    User savedUser = userRepository.save(userb3n);
    Assertions.assertThat(savedUser.getId()).isPositive();
  }

  @Test
  void testCreateUserWithTwoRoles() {
    User userPiudu = new User("piudu@gmail.com", "piudu", "piu", "du");
    Role roleEditor = new Role(3);
    Role roleAssistant = new Role(5);

    userPiudu.addRole(roleEditor);
    userPiudu.addRole(roleAssistant);

    User savedUser = userRepository.save(userPiudu);

    Assertions.assertThat(savedUser.getId()).isPositive();
  }

  @Test
  void testListAllUsers() {
    Iterable<User> userList = userRepository.findAll();
    userList.forEach(System.out::println);
  }

  @Test
  void testGetUserById() {
    User user = userRepository.findById(1).get();
    System.out.println(user);
    Assertions.assertThat(user).isNotNull();
  }

  @Test
  void testUpdateUserDetails() {
    User user = userRepository.findById(1).get();
    user.setEnabled(true);
    user.setEmail("b3nb3nqp@gmail.com");

    userRepository.save(user);
  }

  @Test
  void testUpdateUserRole() {
    User user = userRepository.findById(1).get();
    user.setRoles(Set.of(new Role(1), new Role(2)));
  }

  @Test
  void testDeleteUser() {
    userRepository.delete(userRepository.findById(3).get());
  }
}
