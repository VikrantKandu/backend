package TodayTask.service;


import TodayTask.model.Student;
import TodayTask.model.Subject;
import TodayTask.repository.StudentRepository;
import TodayTask.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public Student createStudent(Student studentRequest) {
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setAddress(studentRequest.getAddress());
        List<Subject> subjects = new ArrayList<>();
        for (Subject subject : studentRequest.getSubjects()) {
            Optional<Subject> foundSubject = subjectRepository.findById(subject.getId());
            foundSubject.ifPresent(subjects::add);
        }
        student.setSubjects(subjects);
        return studentRepository.save(student);
    }


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void delStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}
