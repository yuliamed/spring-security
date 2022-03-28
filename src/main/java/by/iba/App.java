package by.iba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"by.iba"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
