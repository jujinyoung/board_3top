package education.top.service;

import education.top.domain.Board;
import education.top.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void findAllBoard() {
        Board board1 = new Board("제목1", "주진영1", "내용1");
        Board board2 = new Board("제목2", "주진영2", "내용2");
        Board board3 = new Board("제목3", "주진영3", "내용3");

        boardRepository.write(board1);
        boardRepository.write(board2);
        boardRepository.write(board3);

        int begin = 1;
        int end = 3;
        List<Board> boards = boardRepository.findAll(begin, end);

        assertThat(boards.size()).isEqualTo(end);
    }

    @Test
    void write() {
        Board board1 = new Board("제목1", "주진영1", "내용1");

        Board saveBoard = boardRepository.write(board1);
        log.info("저장된 게시글번호={}", saveBoard.getId());

        Board findBoard = boardRepository.findById(saveBoard.getId());
        assertThat(saveBoard.getId()).isEqualTo(findBoard.getId());
    }

    @Test
    void delete() {
        Board board1 = new Board("제목1", "주진영1", "내용1");

        Board saveBoard = boardRepository.write(board1);
        boardRepository.delete(saveBoard.getId());
        Board findBoard = boardRepository.findById(saveBoard.getId());

        assertThat(findBoard).isNull();
    }

    @Test
    void deleteMulti() {
        Board board1 = new Board("제목1", "주진영1", "내용1");
        Board board2 = new Board("제목2", "주진영2", "내용2");
        Board board3 = new Board("제목3", "주진영3", "내용3");

        List<Long> boards = new ArrayList<>();
        Board saveBoard1 = boardRepository.write(board1);
        Board saveBoard2 = boardRepository.write(board2);
        Board saveBoard3 = boardRepository.write(board3);
        boards.add(saveBoard1.getId());
        boards.add(saveBoard2.getId());
        boards.add(saveBoard3.getId());
        boardRepository.deleteMulti(boards);

        Board findBoard1 = boardRepository.findById(saveBoard1.getId());
        Board findBoard2 = boardRepository.findById(saveBoard2.getId());
        Board findBoard3 = boardRepository.findById(saveBoard3.getId());

        assertThat(findBoard1).isNull();
        assertThat(findBoard2).isNull();
        assertThat(findBoard3).isNull();
    }

    @Test
    void update() {
        Board board1 = new Board("제목1", "주진영1", "내용1");
        Board saveBoard = boardRepository.write(board1);
        Long boardId = saveBoard.getId();

        Board updateBoard = new Board(boardId, "제목 수정1", "내용 수정1");
        boardRepository.update(updateBoard);

        Board findBoard = boardRepository.findById(boardId);
        assertThat(findBoard.getTitle()).isEqualTo(updateBoard.getTitle());
        assertThat(findBoard.getContent()).isEqualTo(updateBoard.getContent());
    }

    @Test
    void getAllBoardByWord() {
        Board board1 = new Board("testCode1", "테스트용임1", "테스트내용1");
        Board board2 = new Board("testCode2", "테스트용임2", "테스트내용2");
        Board board3 = new Board("testCode3", "테스트용임3", "테스트내용3");

        Board findBoard1 = boardRepository.findById(boardRepository.write(board1).getId());
        Board findBoard2 = boardRepository.findById(boardRepository.write(board2).getId());
        Board findBoard3 = boardRepository.findById(boardRepository.write(board3).getId());

        test(1, 3, "01", "testCode", findBoard1, findBoard2, findBoard3);
        test(1, 3, "01", "testCode1", findBoard1);
        test(1, 3, "01", "testCode2", findBoard2);
        test(1, 3, "01", "testCode3", findBoard3);
        test(1, 3, "02", "테스트용임", findBoard1, findBoard2, findBoard3);
        test(1, 3, "02", "테스트용임1", findBoard1);
        test(1, 3, "02", "테스트용임2", findBoard2);
        test(1, 3, "02", "테스트용임3", findBoard3);
    }

    void test(int begin, int end, String searchCondition, String searchWord, Board... boards) {
        List<Board> result = boardRepository.findAllByWord(begin, end, searchCondition, searchWord);
        Collections.reverse(result);
        assertThat(result).containsExactly(boards);
    }
}