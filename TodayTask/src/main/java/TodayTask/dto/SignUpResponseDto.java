package TodayTask.dto;

import TodayTask.model.User;
import lombok.Data;


public class SignUpResponseDto {
    private User user;
    private String signUpStatus;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSignUpStatus() {
        return signUpStatus;
    }

    public void setSignUpStatus(String signUpStatus) {
        this.signUpStatus = signUpStatus;
    }
}
