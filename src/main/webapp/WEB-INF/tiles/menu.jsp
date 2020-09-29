<style>
    <%@include file="../styles/menu.css" %>
</style>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<div class="sidenav">
    <a href="${contextPath}/welcome">Welcome page</a>
    <a href="${contextPath}/">Home page</a>
    <a href="${contextPath}/documentation">Documentation page</a>
</div>