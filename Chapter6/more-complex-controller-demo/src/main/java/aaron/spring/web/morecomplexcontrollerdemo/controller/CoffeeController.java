package aaron.spring.web.morecomplexcontrollerdemo.controller;

import aaron.spring.web.morecomplexcontrollerdemo.model.Coffee;
import aaron.spring.web.morecomplexcontrollerdemo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/coffee")
@Slf4j
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Coffee addCoffee(@Valid NewCoffeeRequest newCoffeeRequest,
                            BindingResult result) {
        if (result.hasErrors()) {
            log.warn("Result {}", result);
            return null;
        }
        return coffeeService.saveCoffee(newCoffeeRequest.getName(), newCoffeeRequest.getPrice());
    }

   /* @PostMapping(path = "/",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Coffee withoutBindingResult(@Valid NewCoffeeRequest newCoffeeRequest) {
        return coffeeService.saveCoffee(newCoffeeRequest.getName(),newCoffeeRequest.getPrice());
    }*/

    @PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public List<Coffee> batchAddCoffee(@RequestParam("file") MultipartFile file) {
        List<Coffee> list = new ArrayList<>();
        if (!file.isEmpty()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                String str;
                while ((str = reader.readLine()) != null) {
                    String[] arr = str.split(" ");
                    list.add(coffeeService.saveCoffee(arr[0],
                            Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(arr[1]))));
                }
            } catch (IOException e) {
                log.error("error: {}", e);
            } finally {
                IOUtils.closeQuietly(reader);
            }

        }
        return list;
    }


}
