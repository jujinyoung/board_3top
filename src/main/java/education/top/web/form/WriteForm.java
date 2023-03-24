package education.top.web.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
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
