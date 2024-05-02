<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                    <h1 class="page-header">게시글 등록</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-10">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           Board Register 
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-10">
                                    <form role="form" action="/board/register" method="post">
                                        <div class="form-group">
                                            <label>제목</label>
                                            <input class="form-control" name="title" required>
                        
                                        </div>
                                        <div class="form-group">
                                            <label>작성자</label>
                                            <input class="form-control" name="writer" required>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>내용</label>
                                            <textarea class="form-control" rows="5" name="content"></textarea>
                                        </div>

                                       
                              
                                        <button type="submit" class="btn btn-default">글 작성</button>
                                        <button type="reset" class="btn btn-default">취소</button>
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
            <%@ include file="../includes/footer.jsp" %>
</body>
</html>