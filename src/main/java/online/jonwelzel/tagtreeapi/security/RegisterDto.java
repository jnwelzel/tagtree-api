package online.jonwelzel.tagtreeapi.security;

import java.util.Objects;

public class RegisterDto {
    private String email;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    public RegisterDto() {
    }

    public RegisterDto(String email, String userName, String password, String firstName, String lastName,
                       String dateOfBirth) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterDto authDto)) return false;
        return Objects.equals(email, authDto.email) && Objects.equals(userName, authDto.userName)
                && Objects.equals(password, authDto.password) && Objects.equals(firstName, authDto.firstName)
                && Objects.equals(lastName, authDto.lastName) && Objects.equals(dateOfBirth, authDto.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, userName, password, firstName, lastName, dateOfBirth);
    }

    @Override
    public String toString() {
        return "RegisterDto{" +
                "email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + lastName + '\'' +
                '}';
    }
}
