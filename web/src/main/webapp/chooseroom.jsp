<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Choose a room</title>
</head>
<body>
<table border="1">
		<thead align="center">
			<tr>
				<th>Room ID</th>
				<th>Category</th>
				<th>Place</th>
				<th>Price</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody align="center">
			<c:forEach items="${availableRooms}" var="room">
				<tr>
					<td><c:out value="${room.roomId}" /></td>
					<td><c:out value="${room.category}" /></td>
					<td><c:out value="${room.place}" /></td>
					<td><c:out value="${room.price}" /></td>
					<td><form method="post" action="controller">
							<input type="hidden" name="command" value="bill" />
							<input type="hidden" name="room_id" value="${room.roomId}">
							<input type="submit" value="Bill"/>
						</form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<form method="post" action="controller">
		<input type="hidden" name="command" value="Logout" /> <input
			type="submit" value="Log out" />
	</form>
</body>
</html>