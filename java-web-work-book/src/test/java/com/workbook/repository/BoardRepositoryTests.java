package com.workbook.repository;



import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.workbook.entity.Board;

@SpringBootTest
@Transactional
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    // 테스트시 BeforeEach 추천.
    // @PostConstruct

//    @BeforeEach
//    public void beforeEach() {
//        Board board = Board.builder()
//                .writer("jiseong")
//                .title("hello world!")
//                .content("this is content")
//                .build();
//        boardRepository.save(board);
//
//    }

    @Test
    public void findBy() {
        Optional<Board> result = boardRepository.findById(2L);

        Board board = result.orElseThrow();
        System.out.println(board);
    }

    @Test
    @Rollback(false)
    public void findAll() {
        List<Board> borads = boardRepository.findAll();
        borads.forEach(vo -> System.out.println(vo));
    }

    @Test
    public void delete() {
        Long bno = 2L;

        boardRepository.deleteById(bno);
        System.out.println("tests");
    }

    @Test
//    @Rollback(false) 실제 db 데이터를 삭제하고싶다면
    public void delete2() {
        Long bno = 3L;
        Optional<Board> board = boardRepository.findById(bno);
        if (board.isPresent()) {
            boardRepository.deleteById(bno);
            //영속성 컨텍스트의 상태(예: 삭제된 엔티티)를 데이터베이스에 즉시 반영하기 위함
            //Spring에서는 테스트 시에 트랜잭션이 자동으로 롤백되는 기본 설정을 제공 따라서 rollback(false)
            boardRepository.flush();
            //커밋하는 방법?
        }
        System.out.println("tests");
    }
}
