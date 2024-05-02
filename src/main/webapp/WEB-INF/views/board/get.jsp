<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../includes/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<div class="col-lg-10">
			<h1 class="page-header">게시글 상세조회</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-10">
			<div class="panel panel-default">
				<div class="panel-heading">Board Register</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-10">

							<div class="form-group">
								<label>번호</label> <input class="form-control" name="bno"
									value="${board.bno}" readonly>

							</div>
							<div class="form-group">
								<label>제목</label> <input class="form-control" name="title"
									value="${board.title}" readonly>

							</div>

							<div class="form-group">
								<label>내용</label>
								<textarea class="form-control" rows="5" name="content" readonly>${board.content}</textarea>
							</div>
							<div class="form-group">
								<label>작성자</label> <input class="form-control" name="writer"
									value="${board.writer}">
							</div>



							<%-- 	<button data-oper='modify' class="btn btn-default"
								onclick="location.href='/board/modify?bno=${board.bno}'">글
								수정</button>
							<button data-oper='list' class="btn btn-info" onclick="location.href='/board/list'">목록</button>
                                   --%>
							<button data-oper='modify' class="btn btn-default">글수정</button>
							<button data-oper='list' class="btn btn-info">목록</button>

							<!-- 페이지 이동 처리를 위한 수정 -->
							<form id="operForm" action="/board/modify" method="GET">
								<input type="hidden" id="bno" name="bno" value="${board.bno}" />
								<input type="hidden" id="pageNum" name="pageNum"
									value="${cri.pageNum}" /> <input type="hidden" id="amount"
									name="amount" value="${cri.amount}" /> <input type="hidden"
									id="type" name="type" value="${cri.type}" /> <input
									type="hidden" id="keyword" name="keyword"
									value="${cri.keyword}" />
							</form>
						</div>
						<!-- /.col-lg-6 (nested) -->

					</div>
					<!-- /.row (nested) -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div class="row">
		<div class="col-lg-10">
			<div class="panel panel-default">
				<div class="panel-heading">
					<i class="fa fa-comments fa-fw"></i>댓글
					<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>댓글등록</button>
				</div>
				<div class="panel-body">
					<ul class="chat">
						<li class="left clearfix" data-rno="12">
						<div>
							<div class="header">

								<strong class="primary-font">user00</strong> <small
									class="pull-right text-muted">2018-01-01 14:14</small>
							</div>
							<p>good job!</p>


						</div>
						</li>
					</ul>
					<!-- 댓글의 페이지 번호 -->
					<div class="panel-footer"></div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">댓글 모달</h4>
							</div>
							<div class="modal-body">
								<div class='form-group'>
									<label>댓글</label>
									<input class='form-control' name='reply' value='New Reply!!!'>
								</div><div class='form-group'>
									<label>작성자</label>
									<input class='form-control' name='replyer' value='New Replyer!!!'>
								</div><div class='form-group'>
									<label>작성일</label>
									<input class='form-control'  name='replyDate' value='replyDate'>
								</div>
							
							</div>
							<div class="modal-footer">
								<button id="modalModBtn" type="button" class="btn btn-warning">수정</button>
								<button id="modalRemoveBtn" type="button" class="btn btn-danger">삭제</button>
								<button id="modalRegisterBtn" type="button" class="btn btn-primary">등록</button>
								<button  id="modalCloseBtn" type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
								<!--  <button type="button" class="btn btn-primary">Save changes</button> -->
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
	<script type="text/javascript" src="/resources/js/reply.js"></script>

	<script type="text/javascript">
		console.log("==================");
		console.log("JS TEST");

		const bnoValue = '${board.bno}';//el code
		
		const replyUl=$('.chat');//jquery
		
		showList(1);
	
		
		
		//데이터 뿌려주기
		function showList(page){
	        //  조회하기
	        replyService.getList({
	            bno:bnoValue,
	            page:page||1
	        }, function(replyCnt, list){ 

	            console.log("-----replyCnt : " + replyCnt);
	            console.log("-----list : " + list);
	            let str="";
	            // 읽어온 댓글이 없는 경우
	            if(list == null || list.length == 0){
	                replyUl.html("");
	                return;
	            }

	            for(let i = 0, len = list.length||0; i<len; i++){
	                str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";
	                str += " <div><div class='header'><strong class='primary-font'>" + list[i].replyer+"</strong>";
	                str += "  <small class='pull-right text muted'>" + replyService.displayTime(list[i].replyDate) + "</small></div>";
	                str += "  <p>" + list[i].reply + "</p></div></li>";
	            }
	            replyUl.html(str);
	            showReplyPage(replyCnt);
	        });
	    } // end showList
	    
	    
	
	    
	    
	    /*댓글 모달*/
		
		
		const modal=$(".modal");
		const modalInputReply=modal.find("input[name='reply']");
		const modalInputReplyer=modal.find("input[name='replyer']");
		const modalInputReplyDate=modal.find("input[name='replyDate']");
		
		
		const modalModBtn=$("#modalModBtn");
		const modalRemoveBtn=$("#modalRemoveBtn");
		const modalRegisterBtn=$("#modalRegisterBtn");
		
		$("#addReplyBtn").on("click", function(e){
		
			modal.find("input").val("");
			modalInputReplyDate.closest("div").hide(); //현재 엘리먼트의 가장 가까운 조상
			modal.find("button[id!='modalCloseBtn']").hide();
			
			modalRegisterBtn.show();
			
			$(".modal").modal("show");
			
			
			
		});
		
		//모달창에서의 등록 버튼
		modalRegisterBtn.on("click", function(e){
		
			const reply={
					reply:modalInputReply.val(),
					replyer: modalInputReplyer.val(),
					bno:bnoValue
			};
			
			replyService.add(reply, function(result){
			alert(result);
			 modal.find("input").val("");            //모달창의 입력 항목 비우기
			 modal.modal("hide");
			
			
			showList(1); //댓글 추가 후 새로운 댓글 목록 가져오기
		});
			
	});
		//이벤트 전가:이벤트 발생은 <ul>, 하지만 이벤트를 처리하는 대상은 li로 지정
		$(".chat").on("click","li",function(e){
        const rno = $(this).data("rno");  // $(this).data("rno"); <li>의 커스텀 속성 data-rno에서 값을 가져오기
        console.log("chat click ------->" +rno);

        replyService.get(rno,function(reply){
            modalInputReply.val(reply.reply);
            modalInputReplyer.val(reply.replyer);
            modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
            modal.data("rno",reply.rno);

            modal.find("button[id != 'modalCloseBtn']").hide();
            modalInputReplyDate.closest("div").show();
            modalModBtn.show();
            modalRemoveBtn.show();

            $(".modal").modal("show");
        });
    })
		//댓글 수정 버튼 이벤트
		modalModBtn.on("click", function(e){
			const reply={rno:modal.data("rno"), reply:modalInputReply.val()};
			replyService.update(reply, function(result){
				alert(result);
				modal.modal("hide");
				showList(pageNum);
				
			})
			
		})
		//댓글 삭제 버튼
		modalRemoveBtn.on("click", function(e){
			
			const rno=modal.data("rno");
			replyService.remove(rno, function(count){
				alert(count);
				modal.modal("hide");
				showList(pageNum);
			});
		});
		
		
		
		
		
	    /* 댓글의 페이지 번호 처리*/
	    
	  let pageNum=1;
    const replyPageFooter = $('.panel-footer');

    function showReplyPage(replyCnt){
        let endNum = Math.ceil(pageNum / 10.0) * 10; // 한 페이지에 보이는 페이지 번호의 끝 번호 
        let startNum = endNum - 9;
        let prev = startNum != 1;
        let next = false;


        if(endNum * 10 >= replyCnt){
            endNum = Math.ceil(replyCnt / 10.0); // 페이지 번호의 끝 번호를 전체 댓글의 수에 맞게 조정
        }
        // 페이지의 다음 페이지가 있음
        if(endNum * 10 < replyCnt){
            next = true;
        }
        let str = "<ul class='pagination pull-right'>";
        if(prev){
            str += "<li class='page-item'><a class='page-link' href='"+ (startNum - 1)+"'> 이전</a></li>";
        }

        for(let i = startNum; i<=endNum; i++){
            let active = pageNum == i? "active" : "";
            str += "<li class='page-item " + active +"'><a class='page-link' href='" + i + "'>"
            + i + "</a></li>"
        }

        if(next){
            str += "<li class='page-item'><a class='page-link' href='"
                + (endNum + 1) + "'> 다음</a></li>"
        }

        str += "</ul>";
        console.log(str);
        replyPageFooter.html(str);
    }
    
    //댓글 페이지 번호 이벤트 처리
    
    replyPageFooter.on("click", "li a", function(e){
    	
    	e.preventDefault();
    	
    	const targetPageNum=$(this).attr("href");
    	console.log("targetPageNum:"+targetPageNum);
    	pageNum=targetPageNum;
    	
    	
    	showList(pageNum);
    	
    })
    
    
		
	/*댓글을 처리하는 함수(reply.js) 테스트 부분*/	
		
		/*   replyService.add(
		       {reply: "JS TEST",replyer: "tester", bno:bnoValue},
		       //callback 함수
		       //controller에서 받아온 결과값
		       function(result){
		           alert("result : " + result);
		       }
		   ); */

		

		//26번 댓글 삭제 테스트
		/* replyService.remove(26, function(count){
			console.log(count);
			if(count==="success"){
				alert("removed");
			}
		}, function(err){
			alert("error");
			
		}); */

		/* update(reply, callback, error) */
	/* 	replyService.update({
			rno : 29,
			bno : bnoValue,
			reply : "123과연 이제 될까.... js에서 수정한 댓글 이지은 짱"
		}, function(result) {
			alert("댓글 수정 완료!")
		});

		replyService.get(29, function(data) {
			console.log("...........data: " + data)
		});

		$(document).ready(function() {
			console.log(replyService);
		}); */
	</script>
	<script type="text/javascript">
		const operForm = $("#operForm");

		$("button[data-oper='modify']").on("click", function(e) {
			operForm.attr("action", "/board/modify").submit();
		});
		$("button[data-oper='list']").on("click", function(e) {
			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list");
			operForm.submit();
		})
	</script>

	<%@ include file="../includes/footer.jsp"%>
</body>
</html>