package api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewPlayerRequest {
    private String surname;
    private String name;
    private String passwordChange;
    private String passwordRepeat;
    private String email;
    private String currencyCode;
    private String username;
}
