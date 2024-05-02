package com.keduit.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;import org.springframework.remoting.jaxws.SimpleHttpServerJaxWsServiceExporter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;
import com.keduit.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/replies")
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	
	@PostMapping(value="/new",
			//consumes는 클라이언트가 서버에게 보내는 데이터 타입을 명시한다.
			consumes="application/json",
			//produces는 서버가 클라이언트에게 반환하는 데이터 타입을 명시한다.
			produces= {MediaType.TEXT_PLAIN_VALUE})
	//ResponseEntity:전송할 데이터 +HTTP헤더 상태코드, 에러메시지 ////컨트롤러에서 클라이언트로 응답을 보낼 때 사용
	//RequestBody: json으로 전송된 데이터를 객체로 변환
	public ResponseEntity<String>create(@RequestBody ReplyVO vo){
		
		log.info("ReplyVO:"+vo);
		
		int insertCount=service.register(vo);
		log.info("...................reply insert count:"+insertCount);
		
		return insertCount==1? new ResponseEntity<String>("success", HttpStatus.OK)
				:new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	/*
	 * @GetMapping(value="/pages/{bno}/{page}", produces= {
	 * MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) public
	 * ResponseEntity<List<ReplyVO>>getList(
	 */
	
	
	//list<replyvo>를 rpelypagedto에 포함시킴
	
	@GetMapping(value = "/pages/{bno}/{page}",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE })
            public ResponseEntity<ReplyPageDTO> getList(
            @PathVariable("page") int page,
            @PathVariable("bno") Long bno) {

        log.info("getList............");
        Criteria cri = new Criteria(page,10);
        log.info(cri);

        return new ResponseEntity<>(service.getListPage(cri, bno),HttpStatus.OK);
    }
			
	@GetMapping(value="/{rno}",
			produces= {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO>get(@PathVariable("rno")Long rno){
		log.info("get:"+rno);
		return new ResponseEntity<>(service.get(rno),HttpStatus.OK);
	}
	
	
	@DeleteMapping(value= "/{rno}",
            produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
        log.info("remove : " + rno);
        return service.remove(rno) == 1 ? new ResponseEntity<>("success",HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.PATCH},
			value="{rno}",
			consumes="application/json",
			produces= {MediaType.TEXT_PLAIN_VALUE})
	//requestbody에 json을 넣어서 modify메서드를 실행한다.
	public ResponseEntity<String>modify(@RequestBody ReplyVO vo,
			@PathVariable("rno")Long rno){
		vo.setRno(rno);
		log.info("rno:"+rno);
		log.info("modify:"+vo);
		
		return service.modify(vo)==1
				?new ResponseEntity<>("success", HttpStatus.OK)
				:new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
