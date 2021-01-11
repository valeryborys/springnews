<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>News Portal</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/>" />
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="lang" var="locale" />
<fmt:message bundle="${locale}" key="news" var="listHead" />
<fmt:message bundle="${locale}" key="lang.english" var="english" />
<fmt:message bundle="${locale}" key="lang.russian" var="russian" />
<fmt:message bundle="${locale}" key="list.newsList" var="newsList" />
<fmt:message bundle="${locale}" key="list.addNews" var="addList" />
<fmt:message bundle="${locale}" key="newsBlock.latestNews"
	var="latestNews" />
<fmt:message bundle="${locale}" key="newsBlock.editButton"
	var="editButton" />
<fmt:message bundle="${locale}" key="newsBlock.viewButton"
	var="viewButton" />
<fmt:message bundle="${locale}" key="newsBlock.deleteButton"
	var="deleteButton" />
</head>
<body>
	<header class="header-block">
		<p class="logo">News management</p>
		
		<div align="right" class="bottom-margin">
			<form class="lang" action="localeChange" method="post">
				<input type="hidden" name="locale" value="en"> <input
					type="submit" value="${english}" />
			</form>

			<form class="lang" action="localeChange" method="post">
				<input type="hidden" name="locale" value="ru"> <input
					type="submit" value="${russian}" />
			</form>
		</div>
	</header>
	
	
	<main>
	<div class="side-block">
		<sec:authorize access="authenticated" var="authenticated" />
		<aside class="management-block">
			<sec:authorize access="!isAuthenticated()">
			<form method="POST" action="${contextPath}/login" >
				<p align="center">Please, login</p>
				<div align="center" class="form-group ${error != null ? 'has-error' : ''}">
					<p align="center">${message}</p>
					<input align="center" name="username" type="text"  placeholder="Username"/>
					<input align="center" name="password" type="password" placeholder="Password"/>
					<p align="center">${error}</p>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<p align="center"> <button type="submit">Log In</button></p>
					<div align="center"> <a href="${contextPath}/registration">Or create an account</a></div>
				</div>
			</form>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<p align="center"> Welcome, XXXX.</p> <%--<sec:authentication property="username" />--%>
				<p align="center"> <a href="<spring:url value="/logout"/>">Logout</a></p>
			</sec:authorize>
		</aside>
		<aside class="management-block">
			<p align="center"><c:out value="${listHead}:" /></p>
			<ul>
				<li><a href="list"><c:out value="${newsList}" /></a></li>
				<li><a href="addForm"><c:out value="${addList}" /></a></li>
			</ul>
		</aside>
		</div>

		<form:form action="groupDelete" class="news-block" method="post">
			<p class="title"><c:out value="${latestNews}:" /></p>
			
			<c:forEach items="${listNews}" var="news">
				<p>
					<span class="title"><a href="show?id=${news.id}">${news.title}</a></span>
					<c:set var="loc" value="${sessionScope.locale}" />
					<c:set var="ru_loc" value="ru" />
					<c:set var="en_loc" value="en" />
					<c:if test="${loc==ru_loc}">
						<fmt:formatDate pattern="dd-MM-yyyy HH:mm"
							value="${news.datetime}" var="ru_time" />
						<i class="time">${ru_time}</i>
					</c:if>
					<c:if test="${loc==en_loc}">
						<fmt:formatDate pattern="MM/dd/yy HH:mm" value="${news.datetime}"
							var="en_time" />
						<i class="time">${en_time}</i>
					</c:if>
				</p>
				
				<p class="brief">${news.brief}</p>
				
				<div class="bottom-margin">
					<input class="management-buttons" type="checkbox"
						name="deleteCheckbox" value="${news.id}"> <input
						class="management-buttons" type="button" value="${editButton}"
						onclick='location.href="editForm?id=${news.id}"'> <input
						class="management-buttons" type="button" value="${viewButton}"
						onclick='location.href="show?id=${news.id}"'></br> </br>
				</div>
				
			</c:forEach>
			
			
			<div class="management-buttons bottom-margin">
				<button type="submit">
					<c:out value="${deleteButton}" />
				</button>
			</div>
		</form:form>
		
	</main>
</body>
</html>

