package com.keduit.service;

import java.util.List;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

public interface BoardService {
	//등록
	public void register(BoardVO board);
	//한 건 읽어오기
	public BoardVO get(Long bno);
	// 수정
	public boolean modify(BoardVO board);
	//삭제
	public boolean remove(Long bno);
	//목록 읽기
	public List<BoardVO> getList();
	//목록의 page처리
	public List<BoardVO> getList(Criteria cri);
	
	//전체 게시물 갯수 처리
	public int getTotalCount(Criteria cri);
}
