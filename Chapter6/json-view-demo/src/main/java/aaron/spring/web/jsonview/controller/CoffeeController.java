package aaron.spring.web.jsonview.controller;

import aaron.spring.web.jsonview.controller.request.NewCoffeeRequest;
import aaron.spring.web.jsonview.model.Coffee;
import aaron.spring.web.jsonview.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/coffee")
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping(value = "/", params = "!name")
    @ResponseBody
    public List<Coffee> getAll() {
        return coffeeService.getAll();
    }

    @GetMapping(value = "/", params = "name")
    @ResponseBody
    public Coffee getOneCoffee(@RequestParam String name) {
        return coffeeService.getCoffeeByName(name);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Coffee getById(@PathVariable Long id) {
        Coffee coffee = coffeeService.getById(id);
        log.info("Coffee {}", coffee);
        return coffee;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Coffee addCoffeeWithoutBindingResult(@Valid @RequestBody NewCoffeeRequest newCoffeeRequest) {
        return coffeeService.saveCoffee(newCoffeeRequest.getName(), newCoffeeRequest.getPrice());
    }

}
