package com.study.springboot.controller;

import com.study.springboot.dao.IBoardDao;
import com.study.springboot.dao.IReplyDao;
import com.study.springboot.dto.BoardDto;
import com.study.springboot.dto.ReplyDto;
import com.study.springboot.paging.Criteria;
import com.study.springboot.paging.PageDTO;
import com.study.springboot.service.BoardServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class MyController {

    @Autowired // 자동으로 빈 생성
    IBoardDao boardDao; //

    @Autowired
    IReplyDao replayDao;

    @Autowired
    BoardServiceImpl service;

    @RequestMapping("/")
//    @ResponseBody // 문자열 그대로 리턴
    public String root(){
        //return "root() 함수 반환" // 문자열로 반환
        log.info("listForm 으로 리타이렉드!!");
        return "redirect:listForm"; // listForm 으로 리다이렉트 됨
    }


//    // 게시판 목록 보기
//    @GetMapping("/listForm")
//    public String listForm(Model model) throws Exception {
//        List<BoardDto> list = service.list();
//        model.addAttribute("list", list);
//        log.info("message={}", list);
//        return "listForm"; // listForm.html dispatch 해줌
//    }

//    게시판 목록 + 페이징
    @GetMapping("/listForm")
    public String listForm(Criteria cri, Model model) throws Exception {
//        List<BoardDto> list = service.list(cri);
        log.info("-----------------------------");
        log.info("listForm={}", cri);
        model.addAttribute("list", service.list(cri));
        model.addAttribute("pageMaker", new PageDTO(cri, 123));

        log.info("message={}", service.list(cri));
        return "listForm"; // listForm.html dispatch 해줌
    }

    // 게시글 작성
    @GetMapping("/writeForm")
    public String writeForm(){
        return "writeForm"; // writeForm.html dispatch 해줌
    }

    // 작성 폼의 데이터를 품고 있음
    @PostMapping("/writeAction")
    @ResponseBody
    public String writeAction(@RequestParam("board_name") String board_name,
                              @RequestParam("board_title") String board_title,
                              @RequestParam("board_content") String board_content,
                              HttpServletRequest request) throws Exception {
        int result = service.write(board_name, board_title, board_content);
        if(result == 1){
            log.info("글쓰기 성공!!!!");
//            return "redirect:listForm"; // 성공시 글 목록으로 리다이렉트
            return "<script>alert('글쓰기성공'); location.href='/listForm';</script>;";
        }else {

            log.info("글쓰기 실패!!!!");
//            return "redirect:writeForm"; // 실패시 다시 작성폼으로
            return "<script>alert('글쓰기실패'); location.href='/writeForm';</script>;";


        }
    }

    // 글 보기
    @GetMapping("/contentForm")
    // board_dix 는 get 방식으로 전송됨 -> String
    public String contentForm(@RequestParam("board_idx") String board_idx, Model model,
                                HttpServletRequest request) throws Exception {

        // 조회수 증가
//        boardDao.hit(board_idx);
        service.hit(board_idx);

        //게시글 보기
//        BoardDto dto = boardDao.viewDto(board_idx);
        BoardDto dto = service.viewDto(board_idx);
        log.info("dto={}", dto);
        model.addAttribute("dto", dto);

        // 댓글 리스트 가져오기
//        List<ReplyDto> reply_list = replayDao.reply_list(board_idx);
        List<ReplyDto> reply_list = service.reply_list(board_idx);
        log.info("reply_list={}", reply_list);
        model.addAttribute("reply_list", reply_list);

        return "contentForm";
    }



    // 글 삭제
    @GetMapping("/deleteAction")
    @ResponseBody
    public String deleteAction(@RequestParam("board_idx") String board_idx, HttpServletRequest request) throws Exception {
//        int result = boardDao.deleteDto(board_idx);
        int result = service.deleteDto(board_idx);
        if (result == 1){
            log.info("글 삭제 성공!!!!!");
//            request.getSession().setAttribute("alert_message", "삭제 성공");
//            return "redirect:listForm";
            return "<script>alert('삭제 성공'); location.href='/listForm';</script>;";

        }else {
            log.info("글 삭제 실패!!!!");
//            request.getSession().setAttribute("alert_message", "삭제 실패......");
//            return "redirect:contentForm?board_idx=" + board_idx; // updateForm으로 리다이렉트
            return "<script>alert('수정 실패'); location.href='/contentForm?board_idx=" + board_idx +"';</script>;";


        }
    }

    @PostMapping("/updateAction")
    @ResponseBody
    public String alert_message(@RequestParam("board_idx") String board_idx,
                                @RequestParam("board_name") String board_name,
                                        @RequestParam("board_title") String board_title,
                                @RequestParam("board_content") String board_content, HttpServletRequest request) throws Exception {
//        int result = boardDao.updateDto(board_idx, board_name, board_title, board_content);
            int result = service.updateDto(board_idx, board_name, board_title, board_content);
        if (result == 1){
            log.info("글 수정 성공!!!!!");
//            request.getSession().setAttribute("alert_message", "삭제 성공");
//            return "redirect:listForm";
            return "<script>alert('수정 성공'); location.href='/listForm';</script>;";

        }else {
            log.info("글 수정 실패!!!!");
//            request.getSession().setAttribute("alert_message", "삭제 실패......");
//            return "redirect:contentForm?board_idx=" + board_idx; // updateForm으로 리다이렉트
            return "<script>alert('수정 실패'); location.href='/contentForm?board_idx=" + board_idx +"';</script>;";

        }

    }

    // 댓글 작성
    @PostMapping("/writeReplyAction")
    @ResponseBody
    public String writeReplyAction(@RequestParam("reply_name") String reply_name,
                                   @RequestParam("reply_content") String reply_content,
                                   @RequestParam("reply_board_idx") String reply_board_idx,
                                   HttpServletRequest request)
    {
//        int result = replayDao.reply_write(reply_name, reply_content, reply_board_idx);
        int result = service.reply_write(reply_name, reply_content, reply_board_idx);
        if(result == 1){
            log.info("댓 작성 성공!!!!");
//            return "redirect:listForm"; // 성공시 글 목록으로 리다이렉트
            return "<script>alert('댓 작성 성공'); location.href='/contentForm?board_idx=" + reply_board_idx + "';</script>;";
        }else {

            log.info("댓 작성 실패!!!!");
//            return "redirect:writeForm"; // 실패시 다시 작성폼으로
            return "<script>alert('댓 작성 ㅎ실패'); location.href='/contentForm?board_idx=" + reply_board_idx + "';</script>;";


        }
    }

    // 댓글 삭제
    // 글 삭제
    @GetMapping("/deleteReplyAction")
    @ResponseBody
    public String deleteReplyAction(@RequestParam("reply_idx") String reply_idx,
                                    @RequestParam("board_idx") String board_idx,
                                    HttpServletRequest request) throws Exception {
//        int result = replayDao.reply_deleteDto(reply_idx);
        int result = service.reply_deleteDto(reply_idx);
        if (result == 1){
            log.info("댓글 삭제 성공!!!!!");
//            request.getSession().setAttribute("alert_message", "삭제 성공");
//            return "redirect:listForm";
            return "<script>alert('댓 삭 성공'); location.href='/contentForm?board_idx=" + board_idx + "';</script>;";

        }else {
            log.info("댓글 삭제 실패!!!!");
//            request.getSession().setAttribute("alert_message", "삭제 실패......");
//            return "redirect:contentForm?board_idx=" + board_idx; // updateForm으로 리다이렉트
            return "<script>alert('댓 삭 ㅎ실패'); location.href='/contentForm?board_idx=" + board_idx + "';</script>;";


        }
    }
}
