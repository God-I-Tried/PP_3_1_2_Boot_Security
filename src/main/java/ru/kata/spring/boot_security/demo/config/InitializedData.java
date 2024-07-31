package ru.kata.spring.boot_security.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Configuration
public class InitializedData {
    @Bean
    public CommandLineRunner initData(UserServiceImpl userServiceImpl, RoleRepository  roleRepository) {
        return args -> {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            User user1 = new User();
            user1.setUsername("user1");
            user1.setPassword("123");
            user1.setFirstName("first name 1");
            user1.setLastName("last name 1");
            user1.setEmail("email1@gmail.com");
            userServiceImpl.addUser(user1, new Long[] {userRole.getId()});

            User user2 = new User();
            user2.setUsername("user2");
            user2.setPassword("123");
            user2.setFirstName("first name 2");
            user2.setLastName("last name 2");
            user2.setEmail("email2@gmail.com");
            userServiceImpl.addUser(user2, new Long[] {userRole.getId()});

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("123");
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setEmail("admin@gmail.com");
            userServiceImpl.addUser(admin, new Long[] {adminRole.getId(), userRole.getId()});
        };
    }
}
