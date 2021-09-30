<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="mb.film.dao.Film" %>
<%@ page import="mb.film.model.FilmRepository" %>
<%@ page import="java.util.List" %>

<%

		FilmRepository repo = new FilmRepository();
		List<Film> listFilms = repo.nouveautFilm();
		HttpSession sessionTest = request.getSession();
		sessionTest.setAttribute("listFilms", listFilms);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film | Accueil</title>

	<!-- header -->
	<jsp:include page="views/shared/header.html"></jsp:include>
</head>
<body>

	<header>
		<!-- menu -->
		<jsp:include page="views/shared/mainMenu.jsp"></jsp:include>
	</header>
	

	<div role="main" class="container" style="margin-top:100px;">
		<h1 style="margin-bottom:50px;">Les dernières nouveautées!</h1>
		
		<c:set var="row" value="1"/>
		
		<c:forEach items="${listFilms}" var="item">


			<c:if test="${row==1}">
				<div class="row">
			</c:if>
			
			<div class="col-md-6">
			<h3 class="titreImage text-center">${item.nom}</h3>
			<a href="<%=request.getContextPath()%>/main?page=films&action=detail&id=${item.getId()}">
				<img class="imgIndex mx-auto d-block" alt="test" src="imageFilm/${item.getFilmphotos().get(0).getPhoto().getLabel()}">
			</a>
			</div>
			 <c:choose>
			    <c:when test="${row==1}">
			       <c:set var="row" value="0"/>
			       
			    </c:when>   
			     
			    <c:otherwise>
			        </div>
			        <c:set var="row" value="1"/>
			    </c:otherwise>
			</c:choose> 
		
		
		</c:forEach>

	</div>

	<!-- footer -->
	<jsp:include page="views/shared/footer.html"></jsp:include>
</body>

</html>