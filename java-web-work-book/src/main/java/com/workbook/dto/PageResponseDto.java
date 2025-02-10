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
}
