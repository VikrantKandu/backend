package TodayTask.dto;

import java.util.List;

public class SubjectRequest {
    private String subName;
    private List<Long> studentIds;

    // Getters and Setters

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }
}
