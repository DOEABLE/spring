<%--
  User: campus2H036
  Date: 2024-11-25
  Time: AM 11:31
  Description: [여기에 페이지 설명 추가]
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<html>
<%@ include file="./head.jsp"%>
<body>
<div class="container mx-auto">
    <h1 class="text-3xl text-green-800">Shop Main</h1>
    <h3 class="text-lg">${version}</h3>

    <c:set var="num" value="100000"/>
    <fmt:formatNumber value="${num}" var="numx" pattern="#,###"/>
    num: ${numx}
    <fmt:formatDate value="${createdate}" var="createdatex" pattern="yyyy-MM-dd HH:mm:ss"/>
    , createdate: ${createdatex}

    <ul class="border p-3 w-96">
        <c:forEach var="cust" items="${custs}" varStatus="status">
            <li class='<c:if test="${status.first}">XXX</c:if> <c:if test="${status.last}">ZZZ</c:if>'>
                <a href="/modify/${cust.id}" class="hover:text-blue-800">${status.count} - ${cust.id}. ${cust.name}
                    <small class="text-xs text-gray-500">${cust.tel}</small>
                </a>
                <a href="javascript:del(${cust.id})" class="hover:text-blue-600 float-right">빛났다.사라져.</a><%--정말 삭제하시겠습니까? 라고 물어봐줘--%>
            </li>
        </c:forEach>
        <c:if test="${custs.size() == 0}">
            <p>There is no custs...</p>
        </c:if>
    </ul>

    <div>
        <a href="/add" class="underline text-blue-500">Add cust 이게뭐야으아아앙ㄱ</a>
    </div>
    <script>
        function del(id){
            //정말 삭제하시겠습니까?
            if(confirm('진짜 삭제하시겠습니까? ㄹㅇ?')){
                window.location.href='/remove/'+id;
            }
        }
    </script>
</div>
</body>
</html>
