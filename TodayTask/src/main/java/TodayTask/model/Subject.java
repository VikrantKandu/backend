package TodayTask.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sub_name", nullable = false)
    private String sub_name;

    @ManyToMany(mappedBy = "subjects", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonIgnore
    private List<Student> students=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

//    public Subject(Long id, String sub_name,List<Student>,students) {
//        this.id = id;
//        this.sub_name = sub_name;
//        this.students=students;
//    }

    public Subject(Long id, String sub_name, List<Student> students) {
        this.id = id;
        this.sub_name = sub_name;
        this.students = students;
    }

    public Subject(){}
}
