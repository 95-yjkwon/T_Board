package com.keduit.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	//인터페이스 안에 추상 메서드 만들었다.
	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTime2();

}
