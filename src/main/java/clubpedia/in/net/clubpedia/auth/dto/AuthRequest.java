package clubpedia.in.net.clubpedia.auth.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthRequest {
    @NotNull
    private String redirectUri;
    @NotNull
    private String code;
}
