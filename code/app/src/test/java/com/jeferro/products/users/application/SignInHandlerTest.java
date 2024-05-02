package com.jeferro.products.users.application;

import com.jeferro.products.shared.domain.exceptions.UnauthorizedException;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.products.users.domain.models.UserMother;
import com.jeferro.products.users.domain.repositories.UsersInMemoryRepository;
import com.jeferro.products.users.domain.services.FakePasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SignInHandlerTest {

    public FakePasswordEncoder fakePasswordEncoder;

    public UsersInMemoryRepository usersInMemoryRepository;

    public SignInHandler signInHandler;

    @BeforeEach
    void beforeEach() {
        fakePasswordEncoder = new FakePasswordEncoder();
        usersInMemoryRepository = new UsersInMemoryRepository();

        signInHandler = new SignInHandler(usersInMemoryRepository, fakePasswordEncoder);
    }

    @Test
    void should_signInUser_when_credentialsAreCorrect() {
        var expected = UserMother.user();
        usersInMemoryRepository.init(expected);

        var command = new SignInCommand(
                AuthMother.anonymous(),
                expected.getUsername(),
                expected.getEncodedPassword()
        );

        var result = signInHandler.execute(command);

        assertEquals(expected, result);
    }

    @Test
    void should_signInUser_when_userIsAlreadyAuthenticated() {
        var expected = UserMother.user();
        usersInMemoryRepository.init(expected);

        var command = new SignInCommand(
                AuthMother.user(),
                expected.getUsername(),
                expected.getEncodedPassword()
        );

        var result = signInHandler.execute(command);

        assertEquals(expected, result);
    }

    @Test
    void should_throwUnauthorized_when_userDoesNotExist() {
        var user = UserMother.user();

        var command = new SignInCommand(
                AuthMother.anonymous(),
                user.getUsername(),
                user.getEncodedPassword()
        );

        assertThrows(UnauthorizedException.class,
                () -> signInHandler.execute(command));
    }

    @Test
    void should_throwUnauthorized_when_passwordIsWrong() {
        var user = UserMother.user();
        usersInMemoryRepository.init(user);

        var command = new SignInCommand(
                AuthMother.anonymous(),
                user.getUsername(),
                "wrong-password"
        );

        assertThrows(UnauthorizedException.class,
                () -> signInHandler.execute(command));
    }
}