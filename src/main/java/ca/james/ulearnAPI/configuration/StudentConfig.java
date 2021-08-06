package ca.james.ulearnAPI.configuration;

import ca.james.ulearnAPI.model.Student;
import ca.james.ulearnAPI.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student james = new Student(
                    "James",
                    "james.test@gmail.com",
                    LocalDate.of(2000, 8, 10)
            );

            Student alex = new Student(
                    "Alex",
                    "alex.chen@gmail.com",
                    LocalDate.of(2004, 8, 10)
            );

            repository.saveAll(List.of(james, alex));
        };
    }
}
