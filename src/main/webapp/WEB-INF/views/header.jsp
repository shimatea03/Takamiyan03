<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
		<h1><c:out value="${headline  }" /></h1>
		<nav>
				<div class ="navbar">
						<a href="search">検索</a> | <a href="registration">登録</a>
				</div>
		</nav>
</header>