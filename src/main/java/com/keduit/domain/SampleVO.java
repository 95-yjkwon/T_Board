package com.keduit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//기본 생성자 이외의 것은 아래의 어노테이션을 추가해줘야 한다.
@AllArgsConstructor
public class SampleVO {
	
	private Integer mno;
	private String  firstName;
	private String lastName;

}
