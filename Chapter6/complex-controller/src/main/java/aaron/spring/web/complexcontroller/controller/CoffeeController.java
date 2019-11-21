package aaron.spring.web.complexcontroller.controller;

import aaron.spring.web.complexcontroller.model.Coffee;
import aaron.spring.web.complexcontroller.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/coffee")
@Slf4j()
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    /*@RequestMapping(value = "/",params = "!name")
    @ResponseBody
    public List<Coffee> getAll() {
        return coffeeService.getAllCoffee();
    }*/
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Coffee getById(@PathVariable Long id) {
        return coffeeService.getOneCoffee(id);
        // pathVariable 指定把 URI 中参数传给 Long 的 id，必须写一样
    }

    @RequestMapping(path = "/",params = "name")
    @ResponseBody
    // a method parameter should be bound to a web
    // * request parameter.
    // You can use the @RequestParam annotation to bind Servlet request parameters (that is,
    // query parameters or form
    // data) to a method argument in a controller.
//    https://docs.spring.io/spring/docs/5.1.5.RELEASE/spring-framework-reference/web.html#mvc-ann-requestmapping-proxying
    /*public Coffee getByName(@RequestParam(name = "name") String abc) {
        log.info("Name {}",abc);
        return coffeeService.getCoffee(abc);
    }*/
    public Coffee getByName(String name) {
        log.info("Name {}",name);
        return coffeeService.getCoffee(name);
    }
}
