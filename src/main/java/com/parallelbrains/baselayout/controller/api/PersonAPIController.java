package com.parallelbrains.baselayout.controller.api;

import com.parallelbrains.baselayout.config.RouteConfig;
import com.parallelbrains.baselayout.model.Person;
import com.parallelbrains.baselayout.service.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * todo find a way to test API endpoints
 */
@Controller
@RequestMapping(value = RouteConfig.BASE_API_URL  + RouteConfig.PERSON_API_BASE_URL)
public class PersonAPIController extends BaseAPIController {

    @Autowired
    private PersonManager personManager;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,
            produces = "application/json")
    public List<Person> getPeople() throws Exception {
        return personManager.getAll();
    }

    @ResponseBody
    @RequestMapping(value = RouteConfig.PERSON_API_SINGLE_PERSON_URL,
            method = RequestMethod.GET,
            produces = "application/json")
    public Person getPerson(@PathVariable Long id) throws Exception {
        return personManager.get(id);
    }

    @ResponseBody
    @RequestMapping(value = RouteConfig.PERSON_API_SINGLE_PERSON_URL,
            method = RequestMethod.PUT,
            produces = "application/json")
    public Person updatePerson(@RequestBody Person personWithNewValues) throws Exception {
        personManager.save(personWithNewValues);

        return personWithNewValues;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT,
            produces = "application/json")
    public Person savePerson(@RequestBody Person person, HttpServletResponse response) throws Exception {
        personManager.save(person);
        return person;
    }

    @ResponseBody
    @RequestMapping(value = RouteConfig.PERSON_API_SINGLE_PERSON_URL,
            method = RequestMethod.DELETE,
            produces = "application/json")
    public Person deletePerson(@PathVariable Long id, HttpServletResponse response) throws Exception {
        personManager.delete(id);

        // todo to trigger success() function of backbone upon deletion i needed to return something..
        return new Person();
    }

}
