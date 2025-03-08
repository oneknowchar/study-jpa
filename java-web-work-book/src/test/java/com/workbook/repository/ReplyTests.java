package com.workbook.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.workbook.dto.BoardListReplyCountDto;
import com.workbook.entity.Board;
import com.workbook.entity.Reply;
import com.workbook.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ReplyTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Test
    public void  insert() {
        Board board = boardRepository.findById(5L).orElseThrow();
        Reply reply = new Reply(null, board, "test1", "test11");
        replyRepository.save(reply);
    }

    @Test
    @Transactional
    /*
     * @Transactional
     * reply 쿼리를 실행했지만, Board 객체를 같이 조회하려면 추가 쿼리를 실행해야 한다.
     * 다시, 데이터베이스를 연결해야하는데 테스트 코드는 한 번만 쿼리를 실행하므로,
     * 강제로 이를 실행하고 싶다면 @Transactional 을 추가
     *
     */

    public void listOfBoard() {
        Long bno = 310L;

        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());

        Page<Reply> result =  replyRepository.listOfBoard(bno, pageable);

        result.getContent().forEach(reply ->{
            log.info("reply = {}", reply);
            String writer = reply.getBoard().getWriter();

            System.out.println("writer = " + writer);
        });
    }

    @Test
    public void testSearchReplyCount() {
        String[] types = {"t", "c", "w"};

        String keyword = "hello";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<BoardListReplyCountDto> result = boardService.searchWithReplyCount(types, keyword, pageable);

        //total pages
        log.info("result.getTotalPages() = {}", result.getTotalPages());
        log.info("result.getSize() = {}", result.getSize());
        log.info("result.getNumber() = {}", result.getNumber());
    }
}
