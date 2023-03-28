package education.top.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private Long id;

    @NotBlank(message = "제목을 작성해 주세요.")
    private String title;

    @NotBlank
    @Size(min = 1, max = 6)
    private String writer;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH.mm.ss", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss")
    private LocalDateTime rdate;
    private int readed;

    public Board(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }
}
