package TodayTask.model;

import TodayTask.model.Subject;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;



@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Subject> subjects=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Student(Long id, String name, String address, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.subjects = subjects;
    }

    public Student(){

    }
}
