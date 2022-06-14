package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class RoleRepositoryTests {

  @Autowired private RoleRepository roleRepository;

  @Test
  void testCreateFirstRole() {
    Role roleAdmin = new Role("Admin", "Manage Everything");
    Role savedRole = roleRepository.save(roleAdmin);
    Assertions.assertThat(savedRole.getId()).isPositive();
  }

  @Test
  void testCreateRestRole() {
    Role roleSalesPerson =
        new Role(
            "SalesPerson", "manage product price, customer, shipping, orders and sales report");

    Role roleEditor = new Role("Editor", "manage categories, brands, products, articles and menus");

    Role roleShippper = new Role("Shipper", "view products, view orders, and update order status");

    Role roleAssistant = new Role("Assistant", "manage questions and reviews");

    roleRepository.saveAll(List.of(roleSalesPerson, roleEditor, roleShippper, roleAssistant));
  }
}
