package com.project.library.service;

import com.project.library.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    private static final String email = "vasyl@gmail.com";

    @Before
    public void addUser() {
        User user = new User();
        user.setEmail(email);
        user.setPassword("123456");
        user.setName("vasyl");
        userService.addUser(user);
    }

    @Test
    public void findUserByLogin() {
        User user = userService.findUserByEmail(email);
        assertNotNull(user);
    }

    @Test
    public void blockUser() {
        adminService.blockUnblockUserByEmail(email);
        assertEquals(0, userService.findUserByEmail(email).getActive());
    }

    @After
    public void deleteUser() {
        User user = userService.findUserByEmail(email);
        userService.deleteUser(user.getEmail());
    }

}
