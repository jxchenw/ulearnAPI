package ca.james.ulearnAPI.service;

import ca.james.ulearnAPI.model.Student;
import ca.james.ulearnAPI.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();

    }

    public void addNewStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.getStudentByEmail(student.getEmail());
        if(optionalStudent.isPresent())
            throw new IllegalStateException("email taken");
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if(!exists)
            throw new IllegalStateException(
                    "student with id " + id + " does not exist"
            );
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                    "student with id " + studentId + " does not exist"
                )
        );

        if(name != null && name.length() > 0 && !Objects.equals(name, student.getName()))
            student.setName(name);

        if(email != null && email.length() > 0 && !Objects.equals(email, student.getEmail())) {
            Optional<Student> studentOptional = studentRepository.getStudentByEmail(email);
            if(studentOptional.isPresent())
                throw new IllegalStateException("email taken");

            student.setEmail(email);
        }
    }
}
