package TodayTask.controller;

import TodayTask.model.Subject;
import TodayTask.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/getsubject")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }
    @PostMapping("/addsubject")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject sub_name) {
        Subject createdSubject = subjectService.addSubjects(sub_name);
        return ResponseEntity.ok(createdSubject);
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<String> delSubjectById(@PathVariable Long subjectId) {
        subjectService.delSubjectById(subjectId);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @GetMapping("/{id}")
    public Optional<Subject> getStudentById(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }

}