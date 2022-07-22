package com.study.springboot.BoardMapper;

import com.study.springboot.dao.IBoardDao;
import com.study.springboot.dto.BoardDto;
import com.study.springboot.paging.Criteria;
import com.study.springboot.paging.PageDTO;
import oracle.jdbc.logging.annotations.Log;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import java.util.List;
import java.util.function.Supplier;

@AutoConfigureTestDatabase
public class BoardMapperTest {
    Logger log = (Logger) LoggerFactory.getLogger(WebFluxTest.class);

    @Autowired
    public IBoardDao iBoardDao;

    @Test
    public void testPaging(){


        // 1 ~ 10
        Criteria cri= new Criteria();

        List<BoardDto> list = iBoardDao.getListWithPaging(cri);

//        list.forEach(b -> log.info(String.valueOf(b)));
    }

    @Test
    public void testPageDTO(){
        Criteria cri = new Criteria();
        cri.setPageNum(21);

        PageDTO pageDTO = new PageDTO(cri, 250);

        log.info("pageDTO={}", pageDTO);
    }
}
