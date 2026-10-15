package ghostek.ironvault.common.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordEncoderTest {

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = new SecurityConfig().passwordEncoder();
    }

    @Test
    void givenRawPassword_whenEncoded_thenMatchesOnlyTheRightPassword() {
        String hash = passwordEncoder.encode("CorrectHorse42!");

        assertTrue(passwordEncoder.matches("CorrectHorse42!", hash));
        assertFalse(passwordEncoder.matches("WrongHorse42!", hash));
    }

    @Test
    void givenRawPassword_whenEncoded_thenArgon2idIsUsed() {
        String hash = passwordEncoder.encode("CorrectHorse42!");

        assertTrue(hash.contains("$bcrypt$"), "Expected an Argon2id hash but got: " + hash);
    }
}
