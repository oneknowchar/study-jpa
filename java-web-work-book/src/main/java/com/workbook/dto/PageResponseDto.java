package com.workbook.dto;

import java.util.List;

import lombok.Builder;

public class PageResponseDto<E> {
    private int page;
    private int size;
    private int total;

    private int start;
    private int end;

    private boolean prev;
    private boolean next;

    private List<E>dtoList;

    /*
     * 메서드 이름 커스터마이징: builderMethodName을 사용하면 빌더 패턴을 사용할 때 메서드 이름을 커스터마이징
     * 예를 들어, 여러 생성자가 있을 때 각각 다른 이름을 붙여서 혼동을 피할 수 있다.
     */
    @Builder(builderMethodName = "withAll")
    public PageResponseDto(PageRequestDto pageRequestDto, List<E>dtoList, int total) {
        this.page = pageRequestDto.getPage(); //3
        this.size = pageRequestDto.getSize(); //6
        this.total = total;
        this.dtoList = dtoList;

        //책은 10개 고정이나, 나는 페이지당 항목 수에 맞춰 페이징처리를 할것이라면...
        // page를 10으로 나눈 값을 올림 처리 한 후 * 페이징사이즈
        // 3 / 10 => 0.3 => 1 => 1 * this.size
        this.end = (int)(Math.ceil(this.page / 10.0)) * this.size;
        this.start = this.end - this.size + 1;	//이것도 10고정에서 -9가 아닌, 페이징사이즈 빼고 1처리

        int last = (int)(Math.ceil(total/(double)size));

        this.end = end > last ? last : end;	//real end

        this.prev = this.start > 1;
        this.next = total  > this.end * this.size;
    }


    @Override
    public String toString() {
        return "PageResponseDto [page=" + page + ", size=" + size + ", total=" + total + ", start=" + start + ", end="
                + end + ", prev=" + prev + ", next=" + next + ", dtoList=" + dtoList + "]";
    }


}
