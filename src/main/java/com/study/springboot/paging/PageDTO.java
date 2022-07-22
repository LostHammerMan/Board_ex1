package com.study.springboot.paging;

import lombok.Getter;
import lombok.ToString;

//
@Getter @ToString
public class PageDTO {

    private int startPage;
    private int endPage;
    private boolean prev, next;

    // 총 게시물
    private int total;

    // 한번에 보여지는 페이지지
   private Criteria cri;

    public PageDTO(Criteria cri, int total){
        this.cri=cri;
        this.total=total;

        this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
        this.startPage = this.endPage -9;

        // total에 1.0 곱하는 이유 : 소수로 처리하기 위해(double)
        int realEnd = (int) (Math.ceil(total * 1.0) /cri.getAmount());

        if(realEnd < this.endPage){
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;

    }
}
