package clubpedia.in.net.clubpedia.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthResponse {
    @NotNull
    private String accessToken;
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String birthday;
    @NotNull
    private String gender;
    @NotNull
    private String email;
    @NotNull
    private Boolean isSignup;
}
