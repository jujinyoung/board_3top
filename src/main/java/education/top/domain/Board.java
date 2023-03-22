package education.top.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    @Size(min = 1, max = 6)
    private String writer;

    @NotBlank
    private String content;
    private LocalDate rdate;
    private int readed;

}
