package lkm.starterproject.auth.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginCredentials {
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public UserLoginCredentials() {
        // 기본 생성자는 JSON 역직렬화를 위해 필요합니다.
    }

    public UserLoginCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
