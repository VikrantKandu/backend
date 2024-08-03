package TodayTask.dto;

import lombok.Data;
import lombok.NonNull;


public class SignUpDto {

    @NonNull
    private String name;

    @NonNull
    private String address;

    @NonNull
    private String mobileNo;

    @NonNull
    private String email;

    @NonNull
    private String gender;

    @NonNull
    private String password;

    private String role;

    public @NonNull String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public @NonNull String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    public @NonNull String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(@NonNull String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public @NonNull String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public @NonNull String getGender() {
        return gender;
    }

    public void setGender(@NonNull String gender) {
        this.gender = gender;
    }

    public @NonNull String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}