package com.study.springboot.service;

import com.study.springboot.dto.BoardDto;
import com.study.springboot.dto.ReplyDto;
import com.study.springboot.paging.Criteria;

import java.util.List;

public interface BoardService {

    // 게시판
     List<BoardDto> list() throws Exception;
     int write(String board_name, String board_title, String board_content) throws Exception;
     BoardDto viewDto(String board_idx) throws Exception;
     int deleteDto(String board_idx) throws Exception;
     int updateDto(String board_idx, String board_name, String board_title, String board_content) throws Exception;
     int hit(String board_idx) throws Exception;

    // 페이징(오버로딩)
    List<BoardDto> list(Criteria cri);

    // 댓글
     List<ReplyDto> reply_list(String reply_board_idx);
     int reply_write(String reply_name, String reply_content, String reply_board_idx);
     int reply_deleteDto(String reply_idx);
}
