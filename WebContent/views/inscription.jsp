<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film | Inscription</title>

	<!-- header -->
	<jsp:include page="shared/header.html"></jsp:include>
</head>
<body>

	<header>
		<!-- menu -->
		<jsp:include page="shared/mainMenu.jsp"></jsp:include>
	</header>
	

	<div role="main" class="container" style="margin-top:100px;">
		<h1>Inscription:</h1>
		
	<form action="./main?action=inscription" method="post">
		<div class="form-group">
			<label for="nom">Nom d'utilisateur:</label>
			<input type="text" class="form-control" name="nom" id="nom" placeholder="Jean">
		</div>
		<div class="form-group">
			<label for="email">Addresse Mail:</label>
			<input type="email" class="form-control" name="email" id="email" placeholder="jean@exemple.fr">
		</div>
		<div class="form-group">
 			<label for="password">Mot de passe</label>
 			<input type="password" class="form-control" name="password" id="password" placeholder="xxxxx">
		</div>
		<button type="submit" class="btn btn-primary">S'inscrire</button>
	</form>
	

	</div>

	<!-- footer -->
	<jsp:include page="shared/footer.html"></jsp:include>
</body>

</html>