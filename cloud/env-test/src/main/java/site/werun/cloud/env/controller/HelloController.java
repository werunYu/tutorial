package site.werun.cloud.env.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import site.werun.cloud.env.domain.Person;


/**
 * @author werun
 * @version 1.0
 * @date 2024/10/20 15:55
 * @description
 **/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping(value = "/person/{personId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findPerson(@PathVariable("personId") Integer personId) {
        Person p = new Person();
        p.setId(personId);
        p.setName("werun");
        p.setAge(27);
        return p;
    }
}
