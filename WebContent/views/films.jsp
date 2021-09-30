<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="mb.film.dao.Film"%>
<%@ page import="mb.film.model.FilmRepository"%>
<%@ page import="mb.film.dao.Acteur"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film | Articles</title>

<!-- header -->
<jsp:include page="shared/header.html"></jsp:include>
</head>
<body>

	<header>
		<!-- menu -->
		<jsp:include page="shared/mainMenu.jsp"></jsp:include>
	</header>


	<div role="main" class="container" style="margin-top: 100px;">

		<c:if test="${action==null}">
			<h1>La boutique</h1>

			<table class="table tableFilm">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Affiche</th>
						<th scope="col">Nom</th>
						<th scope="col">Prix</th>
						<th scope="col">Stock</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${allFilms}" var="item">
						<tr>
							<td><a
								href="<%=request.getContextPath()%>/main?page=films&action=detail&id=${item.getId()}">
									<img class="imgTab mx-auto d-block" alt="test"
									src="imageFilm/${item.getFilmphotos().get(0).getPhoto().getLabel()}">
							</a></td>
							<td>${item.nom}</td>
							<td>${item.prix}€</td>
							<c:if test="${item.getQuantite()>0}">
								<td>${item.getQuantite()}</td>
							</c:if>
							<c:if test="${item.getQuantite()<=0}">
								<td>Rupture de Stock</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>


		</c:if>

		<c:if test="${action=='detail'}">

			<!--  -->
			<div class="sidenav">
				<form
					action="<%=request.getContextPath()%>/main?action=add&id=${item.id}&prix=${item.prix}"
					method="post">
					<p>Prix: ${item.prix}€</p>
					<c:if test="${item.getQuantite()>0}">
						<input type="number" id="quantity" size="10" name="quantity"
							class="form-control input-number input-sm" value="1" min="1"
							max="${item.getQuantite()}">
						<p>En stock: ${item.getQuantite()}</p>
					</c:if>
					<c:if test="${item.getQuantite()<=0}">
						<p>Rupture de Stock</p>
					</c:if>


					<c:choose>
						<c:when test="${user==null}">
							<span>Veuillez vous connecter</span>
						</c:when>
						<c:otherwise>
							<c:if test="${item.getQuantite()>0}">
								<button type="submit" class="btn btn-primary">
									<i class="material-icons">add_shopping_cart</i>
								</button>
							</c:if>
						</c:otherwise>
					</c:choose>

				</form>
			</div>
			<!--  -->
			<h3 class="text-center">${item.nom}</h3>
			<img class="imgDetail mx-auto d-block" alt="test"
				src="imageFilm/${item.getFilmphotos().get(0).getPhoto().getLabel()}">
			<blockquote class="blockquote text-center">
				<footer class="blockquote-footer">
					Film de:
					<c:forEach items="${listRealisateur}" var="real">
					${real.getRealisateur().getNom()}    
				</c:forEach>
				</footer>
				<p>${item.resumer}</p>
			</blockquote>
			<br />
			<p>Cast:</p>

			<c:forEach items="${listActeur}" var="acteur">
				<p>${acteur.getActeur().getNom()}</p>
			</c:forEach>
		</c:if>

		<c:if test="${action=='search'}">

			<h1>Résultat de la recherche "${word}"</h1>
			<br />
			<!--  -->
			<h3>Liste de Films</h3>
			<table class="table tableFilm">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Affiche</th>
						<th scope="col">Nom</th>
						<th scope="col">Prix</th>
						<th scope="col">Stock</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listFilm}" var="item">
						<tr>
							<td><a
								href="<%=request.getContextPath()%>/main?page=films&action=detail&id=${item.getId()}">
									<img class="imgTab mx-auto d-block" alt="test"
									src="imageFilm/${item.getFilmphotos().get(0).getPhoto().getLabel()}">
							</a></td>
							<td>${item.nom}</td>
							<td>${item.prix}€</td>
							<c:if test="${item.getQuantite()>0}">
								<td>${item.getQuantite()}</td>
							</c:if>
							<c:if test="${item.getQuantite()<=0}">
								<td>Rupture de Stock</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${empty listFilm}">
				<p>Aucuns Films </p>
			</c:if>
			<!--  -->

			<h3>Liste des Acteurs</h3>
			<table class="table tableFilm">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Nom</th>
						<th scope="col">Description</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listActeur}" var="item">
						<tr>

							<td>${item.nom}</td>
							<td>${item.description}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${empty listActeur}">
			<p>Aucuns Acteurs</p>
			</c:if>
			<!--  -->
			<h3>Liste des Réalisateurs</h3>
			<table class="table tableFilm">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Nom</th>
						<th scope="col">Description</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listRealisateur}" var="item">
						<tr>

							<td>${item.nom}</td>
							<td>${item.description}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${empty listRealisateur}">
			<p>Aucuns Réalisateur</p>
			</c:if>
		

			<!--  -->
		</c:if>


	</div>

	<!-- footer -->
	<jsp:include page="shared/footer.html"></jsp:include>
</body>

</html>