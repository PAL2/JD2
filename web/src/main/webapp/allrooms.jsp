<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Show all rooms</title>
</head>
<body>
	<h3>All rooms</h3>
	<table border="1">
		<thead align="center">
			<tr>
				<th>Room ID</th>
				<th>Category</th>
				<th>Place</th>
				<th>Price</th>
			</tr>
		</thead>
		<tbody align="center">
			<c:forEach items="${allRooms}" var="room">
				<tr>
					<td><c:out value="${room.roomId}" /></td>
					<td><c:out value="${room.category}" /></td>
					<td><c:out value="${room.place}" /></td>
					<td><c:out value="${room.price}$" /></td>
			</c:forEach>
		</tbody>
	</table>

	<c:choose>
		<c:when test="${currentPage != 1}">
			<td><a href="controller?command=allroom&currentPage=${currentPage - 1}">На предыдущую</a></td>
		</c:when>
		<c:otherwise>
			<td></td>
		</c:otherwise>
	</c:choose>

	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<c:forEach begin="1" end="${numberOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<td>${i}</td>
					</c:when>
					<c:otherwise>
						<td><a href="controller?command=allroom&currentPage=${i}">${i}</a></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</table>

	<c:choose>
		<c:when test="${currentPage lt numberOfPages}">
			<td><a href="controller?command=allroom&currentPage=${currentPage + 1}">На следующую</a></td>
		</c:when>
		<c:otherwise>
			<td></td>
		</c:otherwise>
	</c:choose>

    <form name="operationsForm" method="POST" action="controller">
        <input type="hidden" name="command" value="allroom" />
        <input type="text" name="currentPage" value="" size="3"/>
        <input type="submit" value="Перейти" />
    </form>

    <form name="recordsPerPageForm" method="POST" action="controller">
        <input type="hidden" name="command" value="allroom"/>
        <select name="recordsPerPage">
            <option value="3">3</option>
            <option value="5">5</option>
            <option value="10">10</option>
        </select>
        <input type="submit" value="Обновить" />
    </form>

	<br>
	<table>
		<tr>
			<td><form method="post" action="controller">
					<input type="hidden" name="command" value="newbooking" /> <input
						type="submit" value="Show new bookings" />
				</form></td>
			<td><form method="post" action="controller">
					<input type="hidden" name="command" value="allbooking" /> <input
						type="submit" value="Show all bookings" />
				</form></td>
			<td><form method="post" action="controller">
					<input type="hidden" name="command" value="allaccount" /> <input
						type="submit" value="Show all accounts" />
				</form></td>
			<td><form method="post" action="controller">
					<input type="hidden" name="command" value="alluser" /> <input
						type="submit" value="Show all users" />
				</form></td>
		</tr>
	</table>
	<br>
	<form method="post" action="controller">
		<input type="hidden" name="command" value="Logout" /> <input
			type="submit" value="Log out" />
	</form>
</body>
</html>