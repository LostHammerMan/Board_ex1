package com.study.springboot.service;

import com.study.springboot.dao.IBoardDao;
import com.study.springboot.dao.IReplyDao;
import com.study.springboot.dto.BoardDto;
import com.study.springboot.dto.ReplyDto;
import com.study.springboot.paging.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    IBoardDao iBoardDao;

    @Autowired
    IReplyDao iReplyDao;

    // 게시판
    @Override
    public List<BoardDto> list() throws Exception {
        return iBoardDao.list();
    }

    @Override
    public int write(String board_name, String board_title, String board_content) throws Exception {
        return iBoardDao.write(board_name, board_title, board_content);
    }

    @Override
    public BoardDto viewDto(String board_idx) throws Exception {
        return iBoardDao.viewDto(board_idx);
    }

    @Override
    public int deleteDto(String board_idx) throws Exception {
        return iBoardDao.deleteDto(board_idx);
    }

    @Override
    public int updateDto(String board_idx, String board_name, String board_title, String board_content) throws Exception {
        return iBoardDao.updateDto(board_idx, board_name, board_title, board_content);
    }

    @Override
    public int hit(String board_idx) throws Exception {
        return iBoardDao.hit(board_idx);
    }

    // 페이징
    @Override
    public List<BoardDto> list(Criteria cri) {
        return iBoardDao.getListWithPaging(cri);
    }

    // 댓글
    @Override
    public List<ReplyDto> reply_list(String reply_board_idx) {
        return iReplyDao.reply_list(reply_board_idx);
    }

    @Override
    public int reply_write(String reply_name, String reply_content, String reply_board_idx) {
        return iReplyDao.reply_write(reply_name, reply_content, reply_board_idx);
    }

    @Override
    public int reply_deleteDto(String reply_idx) {
        return iReplyDao.reply_deleteDto(reply_idx);
    }
}
