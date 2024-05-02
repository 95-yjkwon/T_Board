package com.keduit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.domain.PageDTO;
import com.keduit.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
//컨트롤은 호출
public class BoardController {
	
	private final BoardService service;
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		log.info(".....list...........");
//		model.addAttribute("list", service.getList());
//	}
	
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("...................controller list:"+cri);
		model.addAttribute("list", service.getList(cri)); 
		//페이지 처리를 위해 pageDTO도 모델에 담아줌.
		
		//전체 게시물 갯수를 가져옴.
		int total=service.getTotalCount(cri);
		log.info(".................전체 게시물의 수:"+total);
		model.addAttribute("pageMaker", new PageDTO(cri,total));
	}
	
	
	//메서드 오버라이딩
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info(".....register"+board);
		service.register(board);	
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";
	}
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno")Long bno, @ModelAttribute("cri")Criteria cri, Model model) {
		log.info("===========get or modify===============");
		model.addAttribute("board", service.get(bno));
	}
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		log.info("modify:"+board);
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		//리다이렉트 일 때는 담아줘야 한다.
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list";
	}
	@PostMapping("/remove")
	public String remove(@RequestParam("bno")Long bno, Criteria cri, RedirectAttributes rttr) {
		log.info(".....remove...."+bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list";
	}

	
		
}
