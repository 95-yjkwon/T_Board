package com.keduit.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Autowired
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	@Test
	public void testRegister() {
		BoardVO board=new BoardVO();
		board.setTitle("service를 테스트");
		board.setContent("service는 테스트 내용임");
		board.setWriter("user00");
		service.register(board);
		log.info("생성된 게시물 번호"+board.getBno());
	}
	@Test
	public void testGetList() {
//		service.getList().forEach(board->log.info(board));
//		페이지처리를 위한 목록 가져오기
		service.getList(new Criteria(3,10)).forEach(board->log.info(board));
	}
	@Test
	public void testGet() {
		service.get(1L);
	}
	@Test
	public void testUpdate() {
		BoardVO board=service.get(1L);
		if(board==null) {
			return;
		}
		board.setTitle("서비스에서 수정한 title");
		log.info("=====testUpdate===="+service.modify(board));
	}
	@Test
	public void testDelete() {
		log.info("....testDelete"+service.remove(21L));
	}
}
