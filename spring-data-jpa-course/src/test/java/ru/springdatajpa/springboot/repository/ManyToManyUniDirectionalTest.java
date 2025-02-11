package ru.springdatajpa.springboot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.springdatajpa.springboot.entity.Role;
import ru.springdatajpa.springboot.entity.User;

@SpringBootTest
public class ManyToManyUniDirectionalTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUser() {
        User user = new User();
        user.setFirstName("Андрей");
        user.setLastName("Сахаров");
        user.setEmail("saharov@gmail.com");
        user.setPassword("secretpassword");

        Role admin = new Role();
        admin.setName("ROLE_ADMIN");

        Role customer = new Role();
        customer.setName("ROLE_CUSTOMER");

        user.getRoles().add(admin);
        user.getRoles().add(customer);

        userRepository.save(user);
    }

    @Test
    void updateUser() {
        User user = userRepository.findById(1L).get();
        user.setFirstName("Andr");
        user.setPassword("andrey@gmail.com");

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        user.getRoles().add(roleUser);

        userRepository.save(user);
    }

    @Test
    void fetchUser() {
        User user = userRepository.findById(1L).get();
        System.out.println(user.getFirstName());
        System.out.println("==============");
        user.getRoles().forEach(role -> System.out.println(role.getName() + "\n"));
    }

    @Test
    void deleteUser(){
        userRepository.deleteById(1L);

    }
}
