package com.project.library.db;

import com.project.library.entity.User;
import com.project.library.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    @Order(1)
    public void testCreateUser() {
        User user = new User();
        user.setEmail("petr@gmail.com");
        user.setPassword("123456");
        user.setName("petr");
        user.setRole(User.Role.USER);
        user.setActive(1);
        user.setFine(0);
        user.setPreferredLang(1);
        user.setModified(Calendar.getInstance());
        user.setFineLastChecked(Calendar.getInstance());


        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }

    @Test
    @Order(2)
    public void findUsersList() {
        List<User> users = repo.findAllUsers();
        assertFalse(users.isEmpty());
    }

    @Test
    @Order(3)
    public void findUserByEmail() {
        String email = "petr@gmail.com";
        User user = repo.findByEmail(email);
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    @Order(4)
    public void updateUser() {
        String email = "petr@gmail.com";
        String newPassword = "654321";
        User user = repo.findByEmail(email);
        user.setPassword(newPassword);
        User savedUser = repo.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());
        assertThat(newPassword).isEqualTo(existUser.getPassword());
    }

    @Test
    @Order(5)
    public void deleteUser() {
        String email = "petr@gmail.com";
        User user = repo.findByEmail(email);
        if (user != null) {
            repo.delete(user);
        }
        User deleted = repo.findByEmail(email);
        assertNull(deleted);
    }
}