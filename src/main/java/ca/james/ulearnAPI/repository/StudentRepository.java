package ca.james.ulearnAPI.repository;

import ca.james.ulearnAPI.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> getStudentByEmail(String email);
}
