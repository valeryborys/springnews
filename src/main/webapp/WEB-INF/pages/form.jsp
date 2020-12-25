<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>News Portal</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/>" />
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="lang" var="locale" />
<fmt:message bundle="${locale}" key="news" var="listHead" />
<fmt:message bundle="${locale}" key="title" var="title" />
<fmt:message bundle="${locale}" key="brief" var="brief" />
<fmt:message bundle="${locale}" key="content" var="content" />
<fmt:message bundle="${locale}" key="lang.english" var="english" />
<fmt:message bundle="${locale}" key="lang.russian" var="russian" />
<fmt:message bundle="${locale}" key="list.newsList" var="newsList" />
<fmt:message bundle="${locale}" key="list.addNews" var="addList" />
<fmt:message bundle="${locale}" key="newsBlock.confirmButton"
	var="confirm" />
<fmt:message bundle="${locale}" key="newsBlock.latestNews"
	var="latestNews" />
<fmt:message bundle="${locale}" key="newsBlock.editButton"
	var="editButton" />
<fmt:message bundle="${locale}" key="newsBlock.viewButton"
	var="viewButton" />
<fmt:message bundle="${locale}" key="newsBlock.deleteButton"
	var="deleteButton" />
<fmt:message bundle="${locale}" key="newsBlock.cancelButton"
	var="cancelButton" />
<fmt:message bundle="${locale}" key="warning.title"
	var="titleWarningMessage" />
<fmt:message bundle="${locale}" key="warning.brief"
	var="briefWarningMessage" />
<fmt:message bundle="${locale}" key="warning.content"
	var="contentWarningMessage" />
</head>

<body>
	<header class="header-block">
		<p class="logo">News management</p>


		<div align="right" class="bottom-margin">
			<p class="lang">
				<input type="hidden" name="locale" value="en"> <input
					type="submit" value="${english}" />
			</p>
			<p class="lang">
				<input type="hidden" name="locale" value="ru"> <input
					type="submit" value="${russian}" />
			</p>
		</div>
	</header>



	<main>
		<aside class="management-block">
			<p align="center">
				<c:out value="${listHead}:" />
			</p>
			<ul>
				<li><a href="list"><c:out value="${newsList}" /></a></li>
				<li><a href="addForm"><c:out value="${addList}" /></a></li>
			</ul>
		</aside>


		<form:form action="save" modelAttribute="news" method="POST"
			class="news-block">
			<form:hidden path="id" />

			<c:if test="${news.datetime != null}">
				<form:hidden path="datetime" />
			</c:if>
			
			<p><c:out value="${title}:" /></p>
			<p><form:input path="title" /></p>
			<c:if test="${titleWarning == false}">
				<p align="center" class="warning">
					<c:out value="${titleWarningMessage}" />
				</p>
			</c:if>

			<p><c:out value="${brief}:" /></p>
			<p><form:input path="brief" /></p>
			<c:if test="${briefWarning == false}">
				<p align="center" class="warning">
					<c:out value="${briefWarningMessage}" />
				</p>
			</c:if>


			<p><c:out value="${content}:" /></p>
			<p><form:textarea class="content-input" path="content" /></p>
			<c:if test="${contentWarning == false}">
				<p align="center" class="warning">
					<c:out value="${contentWarningMessage}" />
				</p>
			</c:if>
			
			
			<div class="bottom-margin" align="center">
				<input type="submit" value="${confirm}" /> <input type="button"
					value="${cancelButton}" onclick='location.href="list"'>
			</div>
		</form:form>
	</main>
</body>
</html>