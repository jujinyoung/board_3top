package education.top.web.form;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class WriteForm {

    @NotBlank
    private String title;

    @NotBlank
    @Size(min = 1, max = 6)
    private String writer;

    @NotBlank
    private String content;

    private Boolean writeCheck;
}
