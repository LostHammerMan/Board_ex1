package com.study.springboot.dto;

import lombok.*;

import java.util.Date;

//REPLY_IDX, REPLY_NAME, REPLY_CONTENT, REPLY_DATE, REPLY_BOARD_IDX
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {
    private int reply_idx;
    private String reply_name;
    private String reply_content;
    private Date reply_date;
    private int reply_board_idx;
}
