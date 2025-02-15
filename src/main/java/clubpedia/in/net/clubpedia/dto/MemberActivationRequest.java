package clubpedia.in.net.clubpedia.dto;


import clubpedia.in.net.clubpedia.domain.Gender;
import clubpedia.in.net.clubpedia.global.validation.BooleanTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberActivationRequest {
    @NotNull(message = "이름은 필수 입력 항목입니다.")
    @Size(min = 2, message = "이름은 최소 2글자 이상이어야 합니다.")
    @Pattern(regexp = "^[가-힣]{2,}$", message = "이름은 완성형 한글만 입력 가능합니다.")
    private String name;

    @NotNull(message = "전화번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "^(\\d{3}-\\d{4}-\\d{4})$", message = "전화번호가 올바르지 않습니다. (형식: 000-0000-0000)")
    private String phoneNumber;

    @NotNull
    private Date birthday;

    @NotNull(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "이메일 형식이 올바르지 않습니다."
    )
    private String email;

    @NotNull
    private Gender gender;

    @NotNull(message = "필수 동의 항목입니다.")
    @BooleanTrue(message = "필수 동의 항목입니다.")
    private Boolean isOver14Agreed;

    @NotNull(message = "필수 동의 항목입니다.")
    @BooleanTrue(message = "필수 동의 항목입니다.")
    private Boolean isServiceTermAgreed;

    @NotNull(message = "필수 동의 항목입니다.")
    @BooleanTrue(message = "필수 동의 항목입니다.")
    private Boolean isPrivacyPolicyAgreed;

    @NotNull(message = "필수 동의 항목입니다.")
    @BooleanTrue(message = "필수 동의 항목입니다.")
    private Boolean isLocationTermAgreed;

    @NotNull
    private Boolean isMarketingAgreed;
}
