package bookstore.models;

import lombok.Getter;

@Getter
public class TokenResponse {
    String token;
    String expires;
    String status;
    String result;
}
