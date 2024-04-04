package br.com.exam.functional.data.providers.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import br.com.exam.functional.data.factories.UserRequestFactory;


public class UserArguments {
    public static Stream<Arguments> provideEmptyUserRequestBody() {
        return Stream.of(
            Arguments.of(UserRequestFactory.generateEmptyUser()),
            Arguments.of(UserRequestFactory.generateUserWithNameOnly()),
            Arguments.of(UserRequestFactory.generateUserWithJobOnly())
        );
    }
}
