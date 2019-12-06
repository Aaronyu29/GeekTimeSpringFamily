package aaron.spring.web.exceptiondemo.controller;

import aaron.spring.web.exceptiondemo.controller.exception.FormValidationException;
import aaron.spring.web.exceptiondemo.model.Coffee;
import aaron.spring.web.exceptiondemo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Controller
@Slf4j
@RequestMapping("/coffee")
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Coffee addCoffee(@Valid NewCoffeeRequest newCoffeeRequest,
                            BindingResult result) {
        if (result.hasErrors()) {
            log.warn("Binding Result {}", result);
            throw new FormValidationException(result);
        }
        return coffeeService.saveCoffee(newCoffeeRequest.getName(), newCoffeeRequest.getPrice());
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Coffee addJsonCoffee(@Valid @RequestBody NewCoffeeRequest newCoffeeRequest,
                                BindingResult result) {
        if (result.hasErrors()) {
            log.warn("BindingResult {}", result);
            throw new ValidationException(result.toString());
        }
        return coffeeService.saveCoffee(newCoffeeRequest.getName(), newCoffeeRequest.getPrice());
    }
}
