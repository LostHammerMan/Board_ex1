package com.study.springboot.dto;

// board_idx number(4) primary key,
//         board_name varchar2(20),
//         board_title varchar2(100),
//         board_content varchar2(300),
//         board_date date DEFAULT sysdate,
//         board_hit number(4) DEFAULT 0

import lombok.*;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private int board_idx;
    private String board_name;
    private String board_title;
    private String board_content;
    private Date board_date;
    private int board_hit;


}
