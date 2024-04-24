package lkm.starterproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PasswordEncoderTests {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testBCryptPasswordEncoder() {
        String rawPassword = "1234";
        String encodedPassword = "$2a$10$2/InptFtNPwg81Uh7Ef.7.pHuv4Spsi9E3FNZWjaD0lJIKGh.6riq";

        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword), "The password should match the encoded version");
    }
}
