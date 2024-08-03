package TodayTask.service;

import TodayTask.model.Subject;
import TodayTask.repository.StudentRepository;
import TodayTask.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {


    public Subject addSubjects(Subject sub_name){
        return subjectRepository.save(sub_name);
    }
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

//    public Subject createSubjectWithStudents(Subject subjectRequest, List<Long> studentIds) {
//        Subject subject = new Subject();
//        subject.setSub_name(subjectRequest.getSub_name());
//
//        List<Student> students = new ArrayList<>();
//        for (Long studentId : studentIds) {
//            Optional<Student> studentOpt = studentRepository.findById(studentId);
//            studentOpt.ifPresent(students::add);
//        }
//        subject.setStudents(students);
//
//        return subjectRepository.save(subject);
//    }
    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }


    public void delSubjectById(Long subjectId) {
        Optional<Subject> subjectOpt = subjectRepository.findById(subjectId);
        if (subjectOpt.isPresent()) {
            Subject subject = subjectOpt.get();
            subject.getStudents().forEach(student -> student.getSubjects().remove(subject));
            studentRepository.saveAll(subject.getStudents());
            subjectRepository.deleteById(subjectId);
        } else {
            throw new RuntimeException("Subject not found with ID: " + subjectId);
        }
    }

    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }
}
