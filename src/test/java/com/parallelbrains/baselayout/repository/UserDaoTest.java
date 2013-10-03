package com.parallelbrains.baselayout.repository;

import com.parallelbrains.baselayout.model.User;
import com.parallelbrains.baselayout.utils.TestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest extends BaseDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void saveAPerson() {
        User user = TestUtils.getAUserObject("deniz@parallelbrains.com", "birdistheword");

        userDao.save(user);

        assertNotNull(user.getId());
    }

    @Test
    public void getAPerson() {
        User user = TestUtils.getAUserObject("deniz@parallelbrains.com", "birdistheword");

        userDao.save(user);

        User savedUser = userDao.get(user.getId());

        assertNotNull(savedUser);
    }

    @Test
    public void listPeople() {
        User user = TestUtils.getAUserObject("deniz@parallelbrains.com", "birdistheword");

        userDao.save(user);

        List<User> users = userDao.getAll();

        assertEquals(1, users.size());
    }

    @Test
    public void deleteAPerson() {
        User user = TestUtils.getAUserObject("deniz@parallelbrains.com", "birdistheword");

        userDao.save(user);

        Long userId = user.getId();

        assertNotNull(userId);

        userDao.delete(userId);

        User deletedUser = userDao.get(userId);

        assertNull(deletedUser);
    }

}