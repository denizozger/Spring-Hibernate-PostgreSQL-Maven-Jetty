package com.parallelbrains.baselayout.repository;

import com.parallelbrains.baselayout.model.Person;
import com.parallelbrains.baselayout.utils.TestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class PersonDaoTest extends BaseDaoTest {

	@Autowired
	private PersonDao personDao;

	@Test
	public void saveAPerson() {
        Person person = TestUtils.getAPersonObject();

        personDao.save(person);

		assertNotNull(person.getId());
	}

	@Test
	public void getAPerson() {
        Person person = TestUtils.getAPersonObject();

        personDao.save(person);

        Person savedPerson = personDao.get(person.getId());

        assertNotNull(savedPerson);
	}

    @Test
	public void listPeople() {
        Person person = TestUtils.getAPersonObject();

        personDao.save(person);

		List<Person> people = personDao.getAll();

		assertEquals(1, people.size());
	}

    @Test
    public void deleteAPerson() {
        Person person = TestUtils.getAPersonObject();

        personDao.save(person);

        Long personId = person.getId();

        assertNotNull(personId);

        personDao.delete(personId);

        Person deletedPerson = personDao.get(personId);

        assertNull(deletedPerson);
    }

}
