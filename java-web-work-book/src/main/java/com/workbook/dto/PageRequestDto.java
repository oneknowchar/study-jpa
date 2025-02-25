package com.workbook.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class PageRequestDto {
    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    private String type; // tcw
    private String keyword;
    private String link;

    public String[] getTypes() {
        return this.type != null ? type.split("") : null;
    }

    public Pageable getPageable(String ...props) {
        return PageRequest.of(this.page -1, this.size, Sort.by(props).descending());
    }

    public String getLink() {
        StringBuilder sb = new StringBuilder();
        sb.append("page=" + this.page);
        sb.append("&size=" + this.size);

        if(type != null) {
            sb.append("&type=" + this.type);
        }

        if(keyword != null) {
            try {
                sb.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        link = sb.toString();

        return link;
    }

    public int getSkip() {
        return (page -1) * 10;
    }

}
