package com.keduit.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	//테스트를 위한 게시글 번호 배열
	private Long[] bnoArr= {1835040L,
			1835039L,
			1835038L,
			1835037L,
			1835036L};
		@Autowired
		private ReplyMapper mapper;
		
		@Test
		public void testMapper() {
			log.info(mapper);
		}
		@Test
		public void testCreate() {
			IntStream.rangeClosed(1, 10).forEach(i->{
				ReplyVO vo=new ReplyVO();
				
				vo.setBno(bnoArr[i%5]);
				vo.setReply("댓글 테스트"+i);
				vo.setReplyer("작성자"+i);
				
				mapper.insert(vo);
			});
			
		}
		
		@Test
		public void testRead() {
			Long targetRno=5L;
			ReplyVO vo=mapper.read(targetRno);
			log.info(vo);
		}
		
		@Test
		public void testDelte() {
			log.info("--------------------------mapper-----------------"+mapper.delete(1L));
		}
		
		@Test
		
		public void testUpdate() {
			Long targetRno=10L;
			ReplyVO vo=mapper.read(targetRno);
			vo.setReply("댓글만 수정 가능하다 이 쉬키야");
			log.info("======update count:"+mapper.update(vo));
		}
		
		@Test
		public void testList() {
			Criteria cri=new Criteria();
			
			List<ReplyVO> replies=mapper.getListWithPaging(cri, bnoArr[2]);
			replies.forEach(reply->log.info(reply));
		}
		@Test
		public void testList2() {
			Criteria cri=new Criteria(2,10);
			List<ReplyVO>replies=mapper.getListWithPaging(cri, 1835041L);
			
			replies.forEach(reply->log.info("testList2 reply---------------:"+reply));
		}
}
