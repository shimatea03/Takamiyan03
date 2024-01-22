<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>JDBC Sample</title>
<link rel="stylesheet" href="/jdbc/resources/styles.css">
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<main>
			<form:form modelAttribute="membersSearchModel">
				<div class="form-row">
					検索条件を指定する場合は「ID」または「氏名」のいずれかを入力してください
				</div>
				<div class="form-row">
					<label for="id">ID</label>
					<form:input path="id" />
					<label for="name">氏名</label>
					<form:input path="name" />
					<input type="submit" value="検索する" class="btn">
				</div>
				<div class="form-row errors">
					<c:out value="${message }" />
				</div>
			</form:form>
			<c:if test="${!empty membersList }">
					<table>
							<tr>
								<td>ID</td>
								<td>氏名</td>
								<td>Email</td>
								<td>電話番号</td>
								<td>誕生日</td>
								<td>更新</td>
								<td>削除</td>
							</tr>
							<c:forEach var="members" items="${membersList }"	>
								<tr>
									<td><c:out value="${members.id }" /></td>
									<td><c:out value="${members.name }" /></td>
									<td><c:out value="${members.email }" /></td>
									<td><c:out value="${members.phoneNumber }" /></td>
									<td><c:out value="${members.birthday }" /></td>
									<td>
										<form:form modelAttribute="membersUpdateModel" method="post" action="updateMember">
                            		        <input type="hidden" name="id" value="${members.id}" />
                             		        <input type="submit" value="更新する" class="btn" />
                         		        </form:form>
										<form action="deleteMember" method="post">
											<input type="hidden" name="id" value="${members.id}" />
											<input type="submit" value="削除する" class="btn" />
										</form>
									</td>
								</tr>
							</c:forEach>
					</table>
				</c:if>
		</main>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>