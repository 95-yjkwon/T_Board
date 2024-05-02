package com.keduit.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
	//게시물 댓글 수 추가
	private int replyCnt;

}
