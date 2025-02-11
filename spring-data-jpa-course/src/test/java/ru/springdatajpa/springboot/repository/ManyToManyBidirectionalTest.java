package ru.springdatajpa.springboot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.springdatajpa.springboot.entity.Role;
import ru.springdatajpa.springboot.entity.User;

import java.util.List;

@SpringBootTest
public class ManyToManyBidirectionalTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void saveRole(){
        User user = new User();
        user.setFirstName("Андрей");
        user.setLastName("Сахаров");
        user.setEmail("saharov@gmail.com");
        user.setPassword("secretpassword");

        User admin = new User();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword("adminpassword");

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");

        roleAdmin.getUsers().add(user);
        roleAdmin.getUsers().add(admin);

        user.getRoles().add(roleAdmin);
        admin.getRoles().add(roleAdmin);

        roleRepository.save(roleAdmin);
    }

    @Test
    void fetchRole(){
        List<Role> roles = roleRepository.findAll();
        roles.forEach(role ->{
            System.out.println(role.getName() + "\n");
            System.out.println("------>>>> USERS = " + "\n");
            role.getUsers().forEach(user -> System.out.println(user.getFirstName() + "\n"));
        } );
    }
}
