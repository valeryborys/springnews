<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>

<head>
<title>News List</title>
</head>

<body>

	<div id="container">

		<div id="content">

			<table>
				<tr>
					<th>Title</th>
					<th>Brief</th>
					<th>Content</th>
				</tr>

				<!-- loop over and print our customers -->
				<c:forEach var="tempNews" items="${listNews}">

					<tr>
						<td>${tempNews.title}</td>
						<td>${tempNews.brief}</td>
						<td>${tempNews.content}</td>

					</tr>

				</c:forEach>

			</table>

		</div>

	</div>


</body>

</html>
