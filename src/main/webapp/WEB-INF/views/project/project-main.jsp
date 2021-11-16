<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<title>프로젝트 메인</title>

<link type="text/css" rel="stylesheet" href="/resources/css/nav.css?ver=2">
</head>
<body>

	<div class="wrap">
		<header>


			<%@ include file="/WEB-INF/views/include/nav/header.jsp"%>


		</header>

		<div class="con">
			<nav>

				<%@ include file="/WEB-INF/views/include/nav/main-nav.jsp"%>


			</nav>
			<section>
				
				<div>환영합니다.</div>

			</section>
			<aside>


				<%@ include file="/WEB-INF/views/include/nav/aside.jsp"%>
			</aside>
		</div>

	</div>
</body>
</html>