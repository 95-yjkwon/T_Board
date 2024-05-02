package com.keduit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

public interface BoardMapper {
//	@Select("select * from t_board order by bno desc")
// sql쿼리는 xml에서 처리(boardmapper)
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board);

	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);

	public int getTotalCount(Criteria cri);
	
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
}
