package br.com.exam.functional.dto.response;

import java.time.LocalDateTime;

public class UserPatchResponseBody {
    private String name;
    private String job;
    private LocalDateTime updatedAt;

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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserPatchResponseBody(String name, String job, LocalDateTime updatedAt) {
        this.name = name;
        this.job = job;
        this.updatedAt = updatedAt;
    }

    public UserPatchResponseBody() { }

    @Override
    public String toString() {
        return "UserResponseBody{name='%s', job='%s', updatedAt='%s'}".formatted(name, job, updatedAt);
    }
}
