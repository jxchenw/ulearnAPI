package ca.james.ulearnAPI.service;

import ca.james.ulearnAPI.model.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    public List<Student> getStudents() {
        return List.of(new Student(
                1L, "James", "james.test@gmail.com", LocalDate.of(2000, 8, 10), 22
        ));

    }
}
