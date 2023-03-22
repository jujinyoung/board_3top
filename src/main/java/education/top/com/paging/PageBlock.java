package education.top.com.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageBlock {
    private int currentPage;
    private int numberPerPage;
    private int numberOfPageBlock;
    private int startOfPageBlock;
    private int endOfPageBlock;
    private boolean prev, next;
}
