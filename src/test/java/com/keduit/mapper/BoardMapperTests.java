package com.keduit.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j

public class BoardMapperTests {
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board->log.info(board));
	}
	@Test
	public void testInsert() {
		BoardVO board=new BoardVO();
		board.setTitle("insert에서 추가 함.");
		board.setContent("insert에서 내용을 추가함");
		board.setWriter("user01");
		mapper.insert(board);
		log.info(board);
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board=new BoardVO();
		board.setTitle("키를 먼저 읽고 insert title");
		board.setContent("키를 먼저 읽고 insert content");
		board.setWriter("user02");
		
		mapper.insertSelectKey(board);
		log.info("......................insertSelectKey.."+board);
	}
	@Test
	public void testRead() {
		BoardVO board=mapper.read(1L);
		log.info("....read============>"+board);
	}
	@Test
	public void testDelete() {
		log.info("delete count:"+mapper.delete(3L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board=new BoardVO();
		board.setBno(1L);
		board.setTitle("수정한 제목");
		board.setContent("수정한 내용");
		board.setWriter("user03");
		
		int count=mapper.update(board);
		log.info("================update count========>"+count);
		
	}
@Test
	
	public void testPaging() {
		Criteria cri=new Criteria();
		cri.setAmount(10);
		cri.setPageNum(3);
		List<BoardVO>list=mapper.getListWithPaging(cri);
		list.forEach(board->log.info(board));
	}
@Test
public void testSearch() {
	Criteria cri=new Criteria();
	cri.setKeyword("테스트");
	cri.setType("TCW");
	
	List<BoardVO>list=mapper.getListWithPaging(cri);
	list.forEach(board->log.info(board));
}
}
