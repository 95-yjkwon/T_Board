package com.keduit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;

//규칙 정하기 위해 인터페이스를 만든다.
public interface ReplyMapper {
	public int insert(ReplyVO vo);
	
	public ReplyVO read(Long rno);
	
	public int delete(Long rno);
	
	public int update(ReplyVO reply);
	
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri,@Param("bno") Long bno);
	
	public int getCountByBno(Long bno);

}
