package com.workbook.repository;



import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
//        int EA = 100;
//
//        for(int i = 0; i< EA; i++) {
//            Board board = Board.builder()
//                    .writer("jiseong"+i)
//                    .title("hello world!"+i)
//                    .content("this is content"+i)
//                    .build();
//            boardRepository.save(board);
//        }
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
        List<Board> boards = boardRepository.findAll();
        System.out.println(boards.size());
        boards.forEach(vo -> System.out.println(vo));
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

    @Test
    public void pageableTest() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.findAll(pageable);

        System.out.println("토탈 카운트 : " + result.getTotalElements());
        System.out.println("토탈 페이지 수 : " + result.getTotalPages());
        System.out.println("페이지 넘버 : " + result.getNumber());
        System.out.println("페이지 사이즈 : " + result.getSize());
        System.out.println("본문 데이터 : " + result.getContent());
    }

    @Test
    public void testSearch1() {
        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
        //querydsl을 이용한 방법1
        Page<Board> result1 = boardRepository.search1ByQuerydslRepositorySupport(pageable);

        System.out.println(result1.getContent());
        //querydsl을 이용한 방법2 *추천*
        Page<Board> result2 = boardRepository.search1ByJPAQueryFactory(pageable);
    }

    @Test
    public void testSearchAll() {
        String[]types = {"t", "c", "w"};
        String keyword = "hello";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        System.out.println(result.getContent());
        System.out.println(result.hasPrevious());	//false
        System.out.println(result.hasNext());	//true
        System.out.println(result.getNumber());	//0
        System.out.println(result.getTotalPages());	//10
        System.out.println(result.getTotalElements());	//100
    }
}


