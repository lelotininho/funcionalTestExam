package br.com.exam.functional.data.factories;

import br.com.exam.functional.dto.request.UserRequestBody;

public class UserRequestFactory {

    public static UserRequestBody generateEmptyUser(){
        return new UserRequestBody();
    }
    public static UserRequestBody generateUserWithJobOnly(){
        return new UserRequestBody(null, "carpinter");
    }
    public static UserRequestBody generateUserWithNameOnly(){ return new UserRequestBody("Joshua", null); }
    public static UserRequestBody generateFullUser(){
        return new UserRequestBody("Joshua", "carpinter");
    }
}
