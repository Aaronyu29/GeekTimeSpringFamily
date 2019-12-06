package aaron.spring.web;

import java.util.Arrays;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Arrays.asList("Fop", "Bar").stream()
                .filter(s -> s.equalsIgnoreCase("Fop"))
                .map(s -> s.toUpperCase())
                .forEach(System.out::println);

        Arrays.stream(new String[]{"a", "B", "c"})
                .map(s -> Arrays.asList(s))
                .flatMap(s -> s.stream())
                .forEach(System.out::println);

        System.out.println("Hello World!");
    }


}
