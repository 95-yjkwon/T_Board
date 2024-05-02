package com.keduit.domain;

import lombok.Data;

@Data
public class Criteria {
	
	private int pageNum;
	private int amount;    //page당 게시글 갯수
	
	private String type; //T, C, W, TC, TW, TWC, CW
	private String keyword; //검색어
	
	//인스턴스 생성 될 때 자기 클래스의 다른 생성자를 찾아서 파라미터 값을 넣어준다.
	
	public Criteria() {
		this(1,10);
	}
	
	
	public Criteria(int pageNum, int amount) {
		this.pageNum=pageNum;
		this.amount=amount;
	}
	
	public String[] getTypeArr() {
		return type==null?new String[] {}: type.split("");
		}

}
