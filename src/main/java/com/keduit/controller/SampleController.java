package com.keduit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.domain.SampleVO;
import com.keduit.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j

public class SampleController {
		@GetMapping(value="/getText", produces="text/plain; charset=UTF-8")
		public String getText(){
			log.info("MIME TYPE: "+MediaType.TEXT_PLAIN_VALUE);
			return "안녕하세요";
		}

		
		//디폴트는 xml이다.
@GetMapping(value="/getSample", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public SampleVO getSample() {
	return new SampleVO(1, "홍", "길동");
}

@GetMapping("/getSample2")
public SampleVO getSample2() {
	return new SampleVO(2, "정교", "한");
}

@GetMapping("/getList")
public List<SampleVO> getList(){
    return IntStream.range(1, 10).mapToObj(i-> new SampleVO(i,i+"First",i+"Last"))
            .collect(Collectors.toList());
}


@GetMapping("/getMap")
public Map<String, SampleVO>getMap(){
	Map<String, SampleVO>map=new HashMap<String, SampleVO>();
	map.put("First", new SampleVO(111, "자바", "김"));
	
	return map;
}

@GetMapping(value="/check", params= {"height","weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight){
	//""는 String 처리
	SampleVO vo=new SampleVO(0, ""+height,""+weight);
	ResponseEntity<SampleVO>result=null;
	
	if(height<150) {
		//오류 번호 502가 badgateway
		result=ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
	}else {
		result=ResponseEntity.status(HttpStatus.OK).body(vo);
	}
	return result;
}

@GetMapping("/product/{cat}/{pid}")
public String[]getPath(@PathVariable("cat")String cat,
		@PathVariable("pid") Integer pid){
	return new String[] {
			"category: "+cat, "productId: "+pid
	};
}

@PostMapping("/ticket")
public Ticket convert(@RequestBody Ticket ticket){
	
	log.info("convert................."+ticket);
	
	
	
	return ticket;
}


{
	
}
}