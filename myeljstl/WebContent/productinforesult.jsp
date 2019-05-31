<%@page import="com.kitri.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var = "p" value="${requestScope.productinfo}"/>
<c:set var = "no" value="${p.prod_no}"/>

<script>
$(function(){
	var $bt = $(".submit dl>dt>button");
	$bt.click(function(){
		$.ajax({
		  url:'addcart',
		  method:'get',
		  data:
				'no=${no}&quantity='+$("input[name=quantity]").val(),
		  success:function(result){
			  //$("section").html(result.trim());
			  $("div.addcartresult").remove();    //기존의 알림메세지 제거
			  $("section").append(result.trim()); //알림메세지 생성
		  }
		});
		return false;
	});
});

</script>

<div >
	<div>
		<img src="images/${no}.jpg">
	</div>
<div>
		<h4>${p.prod_name}</h4>
		<p>${p.prod_detail}</p>
		<div>
		
	<!-- <form action="addcart" method="get"> -->
			<input type="hidden" name="no" value="${no}">
				<ul>
				<li class="no">
					<dl>
						<dt>상품번호:</dt>
						<dd>${no}</dd>
					</dl>
				</li>
				<li class="name">
					<dl>
						<dt>가격:</dt>
						<dd>
						<fmt:formatNumber value="${p.prod_price}"
						type="currency" currencySymbol="&#65510;"/>
						</dd>
					</dl>
				</li>
					<li class="quantitiy">
						<dl>
							<dt>수량:</dt>
							<dd>
								<input type="number" name="quantity" value="1" min="1" max="999">
							</dd>
						</dl>
					</li>
					<li class="submit">
						<dl>					
							<dt><button>장바구니넣기</button></dt>
						</dl>
					</li>
			</ul>
		<!-- </form> -->
		</div>
	</div>
</div>