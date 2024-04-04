package br.com.exam.functional.dto.response;

import java.time.LocalDateTime;

public class UserCreateResponseBody {
    private String name;
    private String job;
    private String id;
    private LocalDateTime createdAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserCreateResponseBody(String name, String job, String id, LocalDateTime createdAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = createdAt;
    }

    public UserCreateResponseBody() { }

    @Override
    public String toString() {
        return "UserResponseBody{name='%s', job='%s', id='%s', createdAt='%s'}".formatted(name, job, id, createdAt);
    }
}
