<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.kitri.dto.RepBoard"%>
<%@page import="com.kitri.dto.PageBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
  div.boardlist{width: 70%; }
  div.boardlist>h3{ font-weight: bold; text-align: center;}
  div.boardlist>div.pageInfo{text-align:right; font-style: italic;}
  div.boardlist>div.table{display:table; margin: 10 auto; width: 90%;}
  div.boardlist>div.table>div.tr{display: table-row;}
  div.boardlist>div.table>div.tr>div.th{display:table-cell; font-weight: bold; text-align: center;}
  div.boardlist>div.table>div.tr>div.td{display:table-cell;}
  div.boardlist>div.table, div.boardlist div.th, div.boardlist div.td{
   border: 1px solid #93DAFF; border-collapse: collapse;
  }
  div.boardlist>div.pagegroup{
    width: 90%; 
  }
  div.boardlist>div.pagegroup>ul{
    margin: 0 auto;
  }
  div.boardlist>div.pagegroup>ul>li{
    
    list-style: none;
    display: inline-block;
  }
   
  div.boardlist>div.pagegroup a{
    margin:10px;
    text-decoration: none;    
  }
  
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
 
<script>
$(function(){
	$("div.boardlist>div.table>div.tr").click(function(){
		if($(this).index() != 0){
			var board_seq = $(this).find("div.board_seq").html().trim();
			alert(board_seq+"번 글의 상세정보를 보여줍니다.");
			/* $.ajax({
				url:'boarddetail',
				method:'get',
				data:'board_seq='+board_seq,
				success:function(result){
					//~~~~
				}
			}); */
		}
		return false;
	});
	$("div.boardlist>div.pagegroup a").click(function(){
		var currentPage=$(this).attr("href");
		alert(currentPage+"페이지를 보여줍니다.");
		 $.ajax({
			url:'boardlist',
			method:'get',
			data:'currentPage='+currentPage,
			success:function(result){
				$("section").html(result.trim());
			}
		});
		return false;
	});
});

</script>   
<% 
PageBean pb = (PageBean)request.getAttribute("pb");

%>

<div class="boardlist">
  <h3>게시글 목록</h3>
  <div class="pageInfo">현재페이지:<%=pb.getCurrentPage()%>, 총페이지:<%=pb.getTotalPage()%></div>
  <div class="table">
    <div class="tr">
      <div class="th board_seq">글번호</div>
      <div class="th board_subject">글제목</div>
      <div class="th board_writer">작성자</div>
      <div class="th board_date">작성일자</div>
      <div class="th board_viewcont">조회수</div>
    </div>
<% for(int i=0;i<pb.getList().size();i++){%>   
    <div class="tr">
      <div class="td board_seq"><%=pb.getList().get(i).getBoard_seq() %></div>
      <div class="td board_subject"><%=pb.getList().get(i).getBoard_subject() %></div>
      <div class="td board_writer"><%=pb.getList().get(i).getBoard_writer() %></div>
      <div class="td board_date"><%=pb.getList().get(i).getBoard_date() %></div>
      <div class="td board_viewcont"><%=pb.getList().get(i).getBoard_viewcount() %></div>
    </div>
<%}%> 
  </div>
  <div class="pagegroup"> 
      <ul>
      <%if(pb.getStartPage() !=1){ %>
       <li><a href="<%=pb.getStartPage()-1 %>" >◀</a></li>
       <%} %>
       <%for(int i = 0;i<pb.getCntPerPageGroup();i++){ %>
       		
       		<%if(pb.getStartPage() < pb.getTotalPage()){%>
       <li><a href="<%=pb.getStartPage()+i%>"><%=pb.getStartPage()+i%></a></li>

       		<%}%>
       <%}%>
       
       <%if(pb.getEndPage() != pb.getTotalPage()){%>
       <li><a href="<%=pb.getEndPage()+1%>">▶</a></li>
       <%}%>     
      </ul>   
  </div>   
</div>
