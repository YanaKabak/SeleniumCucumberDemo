package api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationResponse{
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private int expiresIn;
}
