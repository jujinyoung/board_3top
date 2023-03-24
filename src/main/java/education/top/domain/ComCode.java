package education.top.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class ComCode {

    private String comGrpCd;
    private String comGrpDesc;
    private String comCode;
    private String codeDesc;
}
