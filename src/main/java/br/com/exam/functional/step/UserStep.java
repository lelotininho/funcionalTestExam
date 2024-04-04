package br.com.exam.functional.step;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.atomic.AtomicReference;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.exam.functional.component.Environment;
import br.com.exam.functional.component.LogBuffer;
import br.com.exam.functional.dto.request.UserRequestBody;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserStep implements LogBuffer {
    private Environment ENV = Environment.getEnviroment();
    private String baseUrl = ENV.getProperty("api.path.base");
    private String usersPath = ENV.getProperty("api.path.users");

    @Step("Send 'create user' request")
    public Response createUser(UserRequestBody userBody){
        Response response =
            given()
                .filters(requestLoggingFilter, responseLoggingFilter)
                .contentType(ContentType.JSON)
                .body(userBody)
            .when()
                .post("%s/%s".formatted(baseUrl, usersPath))
            .then()
                .extract()
                .response();

        logAll();
        return response;
    }

    @Step("Consulting All Users Informations")
    public Response listUsers(){
        Response response =
            given()
                .filters(requestLoggingFilter, responseLoggingFilter)
                .contentType(ContentType.JSON)
            .when()
                .get("%s/%s".formatted(baseUrl, usersPath))
            .then()
                .extract()
                .response();

        logAll();
        return response;
    }

    @Step("Get single user")
    public Response getUser(String id){
        Response response =
            given()
                .filters(requestLoggingFilter, responseLoggingFilter)
            .when()
                .get("%s/%s/%s".formatted(baseUrl, usersPath, id))
            .then()
                .extract()
                .response();

        logAll();
        return response;
    }

    @Step("Edit user")
    public Response editUser(String id, UserRequestBody userBody){
        Response response =
            given()
                .filters(requestLoggingFilter, responseLoggingFilter)
                .contentType(ContentType.JSON)
                .body(userBody)
            .when()
                .patch("%s/%s/%s".formatted(baseUrl, usersPath, id))
            .then()
                .extract()
                .response();

        logAll();
        return response;
    }
}
