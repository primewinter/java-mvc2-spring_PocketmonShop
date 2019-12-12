<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=euc-kr" %>


<div class="container text-center">
		 
		 <nav>
		 
		 <ul class="pagination" >
	
				<c:if test="${ resultPage.currentPage <= resultPage.pageUnit }">
						◀ 이전
				</c:if>
				<c:if test="${ resultPage.currentPage > resultPage.pageUnit }">
						<a href="javascript:fncGetList('${ resultPage.currentPage-1}','${search.sort}')">◀ 이전</a>
				</c:if>
				
				<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
					<a href="javascript:fncGetList('${ i }','${search.sort}');">${ i }</a>
				</c:forEach>
				
				<c:if test="${ resultPage.endUnitPage >= resultPage.maxPage }">
						이후 ▶
				</c:if>
				<c:if test="${ resultPage.endUnitPage < resultPage.maxPage }">
						<a href="javascript:fncGetList('${resultPage.endUnitPage+1}','${search.sort}')">이후 ▶</a>
				</c:if>

		  </ul>
		  
		</nav>
		
</div>