package com.keduit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;
import com.keduit.mapper.BoardMapper;
import com.keduit.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
@RequiredArgsConstructor
//서비스는 세밀하게 규칙을 더 지키기 위해 만들어준다..
@Service
@Log4j
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyMapper mapper;
	private final BoardMapper boardMapper;

	
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		log.info("=======(S)register: "+vo);
		
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("==================(s)get: "+rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("============(s)modify: "+vo);
		
		return mapper.update(vo);
	}
	
	@Transactional
	@Override
	public int remove(Long rno) {
		log.info("============(s)remove: "+rno);
		ReplyVO vo=mapper.read(rno);
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("============(s)getList: "+cri+","+bno);
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
	
		return new ReplyPageDTO(mapper.getCountByBno(bno),
				mapper.getListWithPaging(cri, bno));
	}

}
