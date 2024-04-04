package br.com.exam.functional.api;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.atomic.AtomicReference;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import br.com.exam.functional.data.factories.UserRequestFactory;
import br.com.exam.functional.dto.request.UserRequestBody;
import br.com.exam.functional.dto.response.UserListResponseBody;
import br.com.exam.functional.dto.response.UserPatchResponseBody;
import br.com.exam.functional.dto.response.UserCreateResponseBody;
import br.com.exam.functional.step.UserStep;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserTest {

    @Test
    @DisplayName("Create user with success")
    @Description("Successful user creation test")
    public void createUserWithSuccessTest(){
        UserRequestBody userRequestBody = UserRequestFactory.generateFullUser();

        Response response = new UserStep().createUser(userRequestBody);

        UserCreateResponseBody userCreateResponseBody = response.getBody().as(UserCreateResponseBody.class);

        Assertions.assertAll(
            () -> Assertions.assertEquals(HttpStatus.SC_CREATED, response.statusCode()),
            () -> Assertions.assertEquals(userRequestBody.getName(), userCreateResponseBody.getName()),
            () -> Assertions.assertEquals(userRequestBody.getJob(), userCreateResponseBody.getJob()),
            () -> Assertions.assertNotNull(userCreateResponseBody.getId()),
            () -> Assertions.assertNotNull(userCreateResponseBody.getCreatedAt())
        );
    }

    @Description("Null field test")
    @ParameterizedTest(name = "{0}")
    @DisplayName("Null field test")
    @MethodSource("br.com.exam.functional.data.providers.arguments.UserArguments#provideEmptyUserRequestBody")
    public void mandatoryFieldsEmptyTest(UserRequestBody body){
        Response response = new UserStep().createUser(body);

        UserCreateResponseBody userCreateResponseBody = response.getBody().as(UserCreateResponseBody.class);

        Assertions.assertAll(
            () -> Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode()),
            () -> Assertions.assertNull(userCreateResponseBody.getId()),
            () -> Assertions.assertNull(userCreateResponseBody.getName()),
            () -> Assertions.assertNull(userCreateResponseBody.getJob()),
            () -> Assertions.assertNull(userCreateResponseBody.getCreatedAt())
        );
    }

    @Test
    @DisplayName("Get single user test")
    @Description("Get single user validation")
    public void getSingleUser(){
        UserRequestBody userRequestBody = UserRequestFactory.generateFullUser();

        Response response = new UserStep().createUser(userRequestBody);

        UserCreateResponseBody userCreateResponseBody = response.getBody().as(UserCreateResponseBody.class);

        Assertions.assertAll(
            () -> Assertions.assertEquals(HttpStatus.SC_CREATED, response.statusCode()),
            () -> Assertions.assertEquals(userRequestBody.getName(), userCreateResponseBody.getName()),
            () -> Assertions.assertEquals(userRequestBody.getJob(), userCreateResponseBody.getJob()),
            () -> Assertions.assertNotNull(userCreateResponseBody.getId()),
            () -> Assertions.assertNotNull(userCreateResponseBody.getCreatedAt())
        );
    }

    @Test
    @DisplayName("Get Users Test")
    @Description("Test about user fields")
    public void getUsersTest(){
        Response response = new UserStep().listUsers();

        AtomicReference<JsonPath> jsonPath = new AtomicReference<>();

        assertAll(
            () -> assertEquals(HttpStatus.SC_OK, response.getStatusCode()),
            () -> assertDoesNotThrow(() -> jsonPath.set(response.jsonPath()))
        );

        int listSize = jsonPath.get().getList("data").size();

        for (int i = 0; i < listSize; i++) {
            UserListResponseBody userListResponseBody = response.jsonPath().getObject("data[" + i + "]", UserListResponseBody.class);

            assertAll(
                () -> assertNotNull(userListResponseBody.getId()),
                () -> assertNotNull(userListResponseBody.getEmail()),
                () -> assertNotNull(userListResponseBody.getFirstName()),
                () -> assertNotNull(userListResponseBody.getLastName()),
                () -> assertNotNull(userListResponseBody.getAvatar())
            );
        }
    }

    @Test
    @DisplayName("Get single user with success test")
    @Description("Get single user with success")
    public void getSingleUserSuccessTest(){
        String id = "2";
        Response response = new UserStep().getUser(id);

        assertAll(
            () -> assertEquals(HttpStatus.SC_OK, response.getStatusCode()),
            () -> assertEquals(id, response.jsonPath().getString("data.id")),
            () -> assertEquals("janet.weaver@reqres.in", response.jsonPath().getString("data.email")),
            () -> assertEquals("Janet", response.jsonPath().getString("data.first_name")),
            () -> assertEquals("Weaver", response.jsonPath().getString("data.last_name")),
            () -> assertEquals("https://reqres.in/img/faces/2-image.jpg", response.jsonPath().getString("data.avatar"))
        );
    }


    @Test
    @DisplayName("Get single user with error test")
    @Description("Get single user with error")
    public void getSingleUserErrorTest(){
        Response response = new UserStep().getUser("23");

        assertAll(
            () -> assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode()),
            () -> assertEquals("{}", response.getBody().asString())
        );
    }

    @Test
    @DisplayName("Edit user properties with success")
    @Description("Edit user property")
    public void editUserTest() {
        UserRequestBody userRequestBody = UserRequestFactory.generateFullUser();
        userRequestBody.setName("Jason Dummy");

        Response response = new UserStep().editUser("2", userRequestBody);

        UserPatchResponseBody UserPatchResponseBody = response.getBody().as(UserPatchResponseBody.class);

        Assertions.assertAll(
            () -> Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode()),
            () -> Assertions.assertEquals(userRequestBody.getName(), UserPatchResponseBody.getName()),
            () -> Assertions.assertEquals(userRequestBody.getJob(), UserPatchResponseBody.getJob()),
            () -> Assertions.assertNotNull(UserPatchResponseBody.getUpdatedAt())
        );
    }
}
