package com.study.springboot.dao;

import com.study.springboot.dto.BoardDto;
import com.study.springboot.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // 마이배티스와 인터페이스 함수 연결을 위한 어노테이션
public interface IBoardDao {
     List<BoardDto> list();
     int write(String board_name, String board_title, String board_content);
     BoardDto viewDto(String board_idx);
     int deleteDto(String board_idx);
     int updateDto(String board_idx, String board_name, String board_title, String board_content);
     int hit(String board_idx);

     // 페이징
    List<BoardDto> getListWithPaging(Criteria cri);

//    public int selectBoardTotalCount(Criteria criteria);
}
