package com.study.springboot.controller;

import com.study.springboot.dao.IBoardDao;
import com.study.springboot.dto.BoardDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class MyController {

    @Autowired // 자동으로 빈 생성
    IBoardDao boardDao; //

    @RequestMapping("/")
//    @ResponseBody // 문자열 그대로 리턴
    public String root(){
        //return "root() 함수 반환" // 문자열로 반환
        log.info("listForm으로 리타이렉드!!");
        return "redirect:listForm"; // listForm 으로 리다이렉트 됨
    }


    // 게시판 목록 보기
    @GetMapping("/listForm")
    public String listForm(Model model){
        List<BoardDto> list = boardDao.list();
        model.addAttribute("list", boardDao.list());
        log.info("message={}", boardDao.list());
        return "listForm"; // listForm.html dispatch 해줌
    }

    // 게시글 작성
    @GetMapping("/writeForm")
    public String writeForm(){
        return "writeForm"; // writeForm.html dispatch 해줌
    }

    // 작성 폼의 데이터를 품고 있음
    @PostMapping("/writeAction")
    public String writeAction(@RequestParam("board_name") String board_name,
                              @RequestParam("board_title") String board_title,
                              @RequestParam("board_content") String board_content)
    {
        int result = boardDao.write(board_name, board_title, board_content);
        if(result == 1){
            log.info("글쓰기 성공!!!!");
        }else {
            log.info("글쓰기 실패!!!!");
        }
        return "redirect:listForm";
    }

    // 글 보기
    @GetMapping("/contentForm")
    public String

}
