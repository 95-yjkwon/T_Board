package com.keduit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
@Service
@Log4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	//생성자 주입을 받겠다.
	//의존성 추가
	//서비스는 매퍼가 있어야 한다.
	//서비스는 매퍼에게 시킨다!
	private final BoardMapper mapper;
	
	@Override
	public void register(BoardVO board) {
		log.info("========register========="+board);
		mapper.insertSelectKey(board);

	}
	//한 건 읽기
	@Override
	public BoardVO get(Long bno) {
		log.info("............get........."+bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("========modify===="+board);
		return mapper.update(board)==1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("======remove======="+bno);
		return mapper.delete(bno)==1;
	}

	@Override
	public List<BoardVO> getList() {
		log.info("getList.......");
		return mapper.getList();
	}
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("........get List with Criteria:"+cri);
		return mapper.getListWithPaging(cri);
	}
	@Override
	public int getTotalCount(Criteria cri) {
		log.info(".................get total count");
		return mapper.getTotalCount(cri);
	}

}
