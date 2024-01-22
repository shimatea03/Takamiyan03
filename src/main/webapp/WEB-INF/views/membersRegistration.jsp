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
			<form:form modelAttribute="membersModel">
				<div class="form-row">
					<label for="name" class="disp-block">氏名</label>
					<form:input path="name" />
					<form:errors path="name" element="span" cssClass="errors" />
				</div>
				<div class="form-row">
					<label for="email" class="disp-block">Email</label>
					<form:input path="email" />
					<form:errors path="email" element="span" cssClass="errors" />
				</div>
				<div class="form-row">
					<label for="phoneNumber" class="disp-block">
						電話番号
						<small>(ハイフンなし・半角数字)</small>
					</label>
					<form:input path="phoneNumber" />
					<form:errors path="phoneNumber" element="span" cssClass="errors" />
				</div>
				<div class="form-row">
					<label for="birthday" class="disp-block">
						誕生日
						<small>(YYYY/MM/DD)</small>
					</label>
					<form:input path="birthday" />
					<form:errors path="birthday" element="span" cssClass="errors" />
				</div>
				<div></div>
				<div class="form-row"	>
					<input type="submit" value="登録する" class="btn">
				</div>
				<div class="form-row errors">
					<c:out value="${message }" />
				</div>
			</form:form>
		</main>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>