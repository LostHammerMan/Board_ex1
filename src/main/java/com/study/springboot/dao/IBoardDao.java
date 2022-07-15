package com.study.springboot.dao;

import com.study.springboot.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // 마이배티스와 인터페이스 함수 연결을 위한 어노테이션
public interface IBoardDao {
    public List<BoardDto> list();
    public int write(String board_name, String board_title, String board_content);

}
