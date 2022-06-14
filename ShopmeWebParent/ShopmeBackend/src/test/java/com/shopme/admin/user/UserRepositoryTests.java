package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
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
public class UserRepositoryTests {

  @Autowired
  UserRepository userRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testCreateUser() {
    Role roleAdmin = entityManager.find(Role.class, 1);
    User userb3n = new User("b3nb3n@gmail.com", "b3nb3n", "Vinh", "Hong");
    userb3n.addRole(roleAdmin);

    User savedUser = userRepository.save(userb3n);
    Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

  }
}
