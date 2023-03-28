package education.top.com.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PageBlock {
    private int currentPage;    //현재 페이지
    private int numberPerPage;  //페이지당 보여줄 데이터수
    private int numberOfPageBlock;  //하단에 표시할 페이지 개수
    private int startOfPageBlock;   //시작번호
    private int endOfPageBlock;     //끝 번호
    private boolean prev, next;     //이전,다음 버튼 여부
}
