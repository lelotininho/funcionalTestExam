package br.com.exam.functional.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserListResponseBody {
    private String id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String email;
    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserListResponseBody(String id, String firstName, String lastName, String email, String avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.avatar = avatar;
    }

    public UserListResponseBody() { }

    @Override
    public String toString() {
        return "UserListResponseBody{id='%s', firstName='%s', lastName='%s', email='%s', avatar='%s'}"
            .formatted(id, firstName, lastName, email, avatar);
    }
}
