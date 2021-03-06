package com.study.springboot.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Criteria {

    private int pageNum; // 페이지 번호
    private int amount; // 한 페이지당 출력 개수

    public Criteria(){
        this(1, 10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
}
