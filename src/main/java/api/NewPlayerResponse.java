package api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewPlayerResponse {
    private boolean bonusesAllowed;
    private Object birthdate;
    private Object gender;
    private String surname;
    private Object timezoneId;
    private String name;
    private Object phoneNumber;
    private int id;
    private boolean isVerified;
    private Object countryId;
    private String email;
    private String username;
}
