package br.com.exam.functional.dto.request;

public class UserRequestBody {
    private String name;
    private String job;

    public UserRequestBody(String name, String job) {
        this.name = name;
        this.job = job;
    }
    public UserRequestBody() {
        name = null;
        job = null;
    }

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

    @Override
    public String toString() {
        return "UserRequestBody{name='%s', job='%s'}".formatted(name, job);
    }
}
