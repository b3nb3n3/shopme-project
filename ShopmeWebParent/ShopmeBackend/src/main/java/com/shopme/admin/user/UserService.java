package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public UserService(UserRepository userRepository,
      RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  public List<User> listAll() {
    return (List<User>) userRepository.findAll();
  }

  public List<Role> listRole() {
    return (List<Role>) roleRepository.findAll();
  }

  public void save(User user) {
    userRepository.save(user);
  }
}
