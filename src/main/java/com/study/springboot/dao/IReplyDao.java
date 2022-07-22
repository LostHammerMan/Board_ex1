package com.study.springboot.dao;

import com.study.springboot.dto.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IReplyDao {
    public List<ReplyDto> reply_list(String reply_board_idx);
    public int reply_write(String reply_name, String reply_content, String reply_board_idx);
    public int reply_deleteDto(String reply_idx);
}
