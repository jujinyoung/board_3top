package education.top.web.form;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class WriteForm {

    @NotBlank(message = "제목을 작성해 주세요.")
    private String title;

    @NotBlank(message = "작성자를 작성해 주세요.")
    @Size(min = 1, max = 6, message = "작성자 길이는 최대 6글자 입니다.")
    private String writer;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    private Boolean writeCheck;
}
