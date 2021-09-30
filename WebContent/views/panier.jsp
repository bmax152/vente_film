<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film | Mon Panier</title>

<!-- header -->
<jsp:include page="shared/header.html"></jsp:include>
</head>
<body>

	<header>
		<!-- menu -->
		<jsp:include page="shared/mainMenu.jsp"></jsp:include>
	</header>


	<div role="main" class="container" style="margin-top: 100px;">
		<h1>Mon Panier:</h1>
		
			<div class="form-group mt-sm-3 mb-2">
				<!--  -->
				<table class="table tableFilm">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Affiche</th>
							<th scope="col">Nom</th>
							<th scope="col">Quantiter</th>
							<th scope="col">Prix</th>
						</tr>
					</thead>
					<tbody>
						<c:set  var="total" value="0"/>
						<c:forEach items="${listCommande}" var="article">
						
							 <tr>
								<td><a
									href="<%=request.getContextPath()%>/main?page=films&action=detail&id=${article.getFilm().getId()}">
										<img class="imgTab mx-auto d-block" alt="test"
										src="imageFilm/${article.getFilm().getFilmphotos().get(0).getPhoto().getLabel()}">
								</a></td>
								<td>${article.getFilm().getNom()}</td>
								<td>${article.getQuantite()}</td>
								<c:set var="prix" value="${article.getPrix()}" />
								 <c:set var="quant" value="${article.getQuantite()}" />
								<c:set var="sousTotal" value="${quant * prix}" />
								<td>${sousTotal}€</td>
								<c:set  var="total" value="${total + sousTotal}"/>
							</tr> 
							
						</c:forEach>
					</tbody>
					
					<tfoot class="thead-light">
						<tr>
							<th scope="col"></th>
							<th scope="col"></th>
							<th scope="col">Total</th>
							<th scope="col">${total}€</th>
						</tr>
				
					</tfoot>
				</table>

		<form action="<%=request.getContextPath()%>/main?action=commande" method="post">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Passer
					la commande</button>
		</form>
			</div>
		
	</div>

	<!-- footer -->
	<jsp:include page="shared/footer.html"></jsp:include>
</body>

</html>