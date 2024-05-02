package com.keduit.controller;

import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.apache.ibatis.annotations.Param;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@WebAppConfiguration
@Log4j
public class BoardControllerTests {
	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testList() throws Exception{
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap());
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "3")
				.param("amount", "50"))
				.andReturn().getModelAndView().getModelMap());
	}
	
	
	@Test
	public void testRegister()throws Exception{
		String resultPage=mockMvc.perform(MockMvcRequestBuilders.post("/board/register").param("title", "mockmvc 새글 등록 title").param("content", "mockmvc 새글 등록 content").param("writer", "user00")).andReturn().getModelAndView().getViewName();
		log.info("-====================================testregister========"+resultPage);
				
	}
	@Test
	public void testGet() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders
				.get("/board/get")
				.param("bno", "1"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
	@Test
	public void testModify() throws Exception{
		String resultPage=mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "2")
				.param("title", "mockmvc에서 수정한 타이틀")
				.param("content", "mockmvc에서 수정한 content")
				.param("writer", "user00")).andReturn().getModelAndView().getViewName();
	}
	@Test
	
	public void testRemove() throws Exception{
		String resultPage=mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "1")).andReturn().getModelAndView().getViewName();
		
		log.info("=====resultpage========"+resultPage);
	}
	
	
	
}
