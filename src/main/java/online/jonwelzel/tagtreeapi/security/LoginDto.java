package online.jonwelzel.tagtreeapi.security;

import java.util.Objects;

public class LoginDto {
    private String email;
    private String password;

    public LoginDto() {
    }

    public LoginDto(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginDto loginDto)) return false;
        return Objects.equals(password, loginDto.password) && Objects.equals(email, loginDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, email);
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
