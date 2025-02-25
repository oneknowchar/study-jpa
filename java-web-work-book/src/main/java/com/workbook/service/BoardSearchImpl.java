package com.workbook.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.workbook.entity.Board;
import com.workbook.entity.QBoard;

import jakarta.persistence.EntityManager;

@Service
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    /**
     * 방법 1. QuerydslRepositorySuppor 장점: QuerydslRepositorySupport를 상속하여 QueryDSL
     * 관련 기능들을 자동으로 사용할 수 있습니다. Spring Data JPA에서 제공하는 PagingAndSortingRepository와
     * 함께 잘 작동하며, 기본적인 페이징 처리에 유리합니다.
     *
     * 단점: QuerydslRepositorySupport에서 제공하는 기본 쿼리 방식이 상대적으로 제한적입니다. 복잡한 쿼리를 작성할 때는
     * 코드가 덜 직관적일 수 있습니다
     */
    @Override
    public Page<Board> search1ByQuerydslRepositorySupport(Pageable pageable) {
        QBoard board = QBoard.board;

        // 쿼리 작성
        JPQLQuery<Board> query = from(board);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(board.title.contains("11"));
        booleanBuilder.or(board.content.contains("11"));

        query.where(booleanBuilder);
        query.where(board.bno.gt(0L));

        // 페이징 처리
        List<Board> list = query.offset(pageable.getOffset()) // 페이징 처리
                .limit(pageable.getPageSize()) // 페이징 처리
                .fetch();

        /**
         * where ( b1_0.title like ? escape '!' or b1_0.content like ? escape '!' ) and
         * b1_0.bno>?
         */

        long count = query.fetchCount();

        // PageImpl을 사용해 페이징된 결과 반환
        return new PageImpl<>(list, pageable, count);
    }

    // ##########################################################################################################################################
    // ##########################################################################################################################################
    // ##########################################################################################################################################
    // ##########################################################################################################################################

    /**
     * 방법 2. JPAQueryFactory(추천)
     *
     * 장점: JPAQueryFactory는 더 직관적이고 유연하게 쿼리를 작성할 수 있는 장점이 있습니다. 복잡한 조건, 조인, 서브쿼리 등
     * 고급 쿼리 작성 시 JPAQueryFactory가 더 유용할 수 있습니다. JPAQueryFactory를 사용하는 방식은
     * QuerydslRepositorySupport보다 설정이 간단하고, 쿼리와 페이징 로직을 명확히 분리할 수 있습니다. 단점:
     * JPAQueryFactory는 직접 EntityManager를 주입받아야 하므로, 이 방식에서는
     * entityManager를 @Autowired로 주입받는 방식으로 처리해야 합니다. QuerydslRepositorySupport와 달리
     * 기본적인 페이징 처리가 포함되어 있지 않으므로 페이징 처리 로직을 명시적으로 작성해야 합니다.
     */
    @Autowired
    private EntityManager entityManager;

    @Override
    public Page<Board> search1ByJPAQueryFactory(Pageable pageable) {
        QBoard board = QBoard.board;
        JPAQueryFactory query = new JPAQueryFactory(entityManager);

        // 쿼리 작성
        List<Board> list = query.select(board).from(board).where(board.title.contains("hello"))
                .offset(pageable.getOffset()) // 페이징 처리
                .limit(pageable.getPageSize()) // 페이징 처리
                .fetch();

        // 전체 레코드 수 카운트
        long count = query
                .select(board.count()) // count()를 사용하여 전체 개수를 구함
                .from(board)
                .where(board.title.contains("hello"))
                .fetchOne(); // 단일 값 반환

        return new PageImpl<>(list, pageable, count); // PageImpl을 사용해 페이징된 결과 반환
    }

    /**
     * 검색 조건이 들어간 목록조회
     *
     * @MethodName: searchAll
     * @Desc:
     * @param types
     * @param keyword
     * @param pageable
     * @return
     */
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        for (String type : types) {
            switch (type) {
            case "t": {
                booleanBuilder.or(board.title.contains(keyword));
                break;
            }
            case "c": {
                booleanBuilder.or(board.content.contains(keyword));
                break;
            }
            case "w": {
                booleanBuilder.or(board.writer.contains(keyword));
                break;
            }
            }
        }
        List<Board> list = query
                .where(booleanBuilder)
                .where(board.bno.gt(0L))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long count = (int)query
                .select(board.count())
                .where(booleanBuilder)
                .where(board.bno.gt(0L))
                .fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

}
