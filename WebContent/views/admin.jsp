<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film | Administration</title>

<!-- header -->
<jsp:include page="shared/header.html"></jsp:include>
</head>
<body>

	<header>
	

		<!-- menu -->
		<jsp:include page="shared/mainMenu.jsp"></jsp:include>

	</header>


	<div role="main" class="container" style="margin-top: 100px;">
		<h1>Admin:</h1>

		<c:if test="${user.getRoleBean().getLabel()=='Admin'}">
		
			<h2 style="margin-top: 100px;">Ajouter un Acteur</h2>
			<form action="./main?action=ajouteracteur" method="post">
				<div class="form-group">
					<label for="nomActeur">Nom de l'acteur</label> 
					<input type="text" class="form-control" name="nomActeur" id="nomActeur" placeholder="Entrez le nom"> 
				</div>
				
				<div class="form-group">
					<label for="descActeur">Description</label> 
					<!-- <input type="text" class="form-control" name="descActeur" id="descActeur" > -->
					<textarea class="form-control" name="descActeur" id="descActeur" rows="3" placeholder="Moins de 550 caractères"></textarea>
				</div>
			
				<button type="submit" class="btn btn-primary">Envoyer</button>
			</form>
		
			<h2 style="margin-top: 100px;">Ajouter un Réalisateur</h2>
			<form action="./main?action=ajouterreal" method="post">
				<div class="form-group">
					<label for="nomActeur">Nom du réalisateur</label> 
					<input type="text" class="form-control" name="nomReal" id="nomReal" placeholder="Entrez le nom"> 
				</div>
				
				<div class="form-group">
					<label for="descActeur">Description</label> 
					<textarea class="form-control" name="descReal" id="descReal" rows="3" placeholder="Moins de 550 caractères"></textarea>
				</div>
			
				<button type="submit" class="btn btn-primary">Envoyer</button>
			</form>
		
		
		
			<h2 style="margin-top: 100px;">Ajouter un film</h2>

			<form action="./upload" method="post" enctype = "multipart/form-data">
				<div class="form-group">
					<label for="nomFilm">Nom du Film</label> 
					<input type="text" class="form-control" name="nomFilm" id="nomFilm" placeholder="Entrez le nom du Film"> 
				</div>
				
				<div class="form-group">
					<label for="descFilm">Description</label> 
					<input type="text" class="form-control" name="descFilm" id="descFilm" placeholder="Mot-clé séparer de virgules">
				</div>
				
				<div class="form-group">
					<label for="prixFilm">Prix de vente</label> 
					<input type="text" class="form-control" name="prixFilm" id="prixFilm" placeholder="Prix...">
				</div>
				
				<div class="form-group">
					<label for="resumerFilm">Résumer</label> 
					<!-- <input type="text" class="form-control" name="resumerFilm" id="resumerFilm" placeholder="Moins de 550 caractères"> -->
					<textarea class="form-control" name="resumerFilm" id="resumerFilm" rows="3" placeholder="Moins de 550 caractères"></textarea>
				</div>
				
				<div class="form-group">
					<label for="quantiterFilm">Stock</label> 
					<input type="number" class="form-control input-number input-sm" name="quantiterFilm" id="quantiterFilm" value="0" min="0">
				</div>
				
				<div class="file-field">
					<label for="afficheFilm">Affiche</label>
				    <div class="d-flex ">
						<div class="btn btn-mdb-color btn-rounded float-left">
				        	<input type="file" name="afficheFilm" id="afficheFilm" accept="image/jpeg">
				      	</div>
				    </div>
				</div>
				<br />
				<select name="filmAct" class="custom-select" multiple>
					<option selected>Acteur</option>
					<c:forEach items="${listAct}" var="acteur">
				  		<option value="${acteur.getId()}">${acteur.getNom()}</option>
					</c:forEach>
				</select>
				<br />
				<br />
				<select name="filmReal" class="custom-select" multiple>
					<option selected>Realisateur</option>
					<c:forEach items="${listReal}" var="realisateur">
				  		<option value="${realisateur.getId()}">${realisateur.getNom()}</option>
					</c:forEach>
				</select>
				<br />		
				<br />	

				<button type="submit" class="btn btn-primary">Evoyer</button>
			</form>

		</c:if>

		<c:if test="${user.getRoleBean().getLabel()!='Admin'}">
			<h1 style="margin-top: 100px;">Vous ne disposez pas des droits requis</h1>
		</c:if>

	</div>

	<!-- footer -->
	<jsp:include page="shared/footer.html"></jsp:include>
	
	
</body>

</html>