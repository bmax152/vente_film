<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<a class="navbar-brand" href="#">FILM&CO </a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarCollapse" aria-controls="navbarCollapse"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarCollapse">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="./">Accueil</a>
			</li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/main?page=films">Films</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/main?page=contact">Contact</a></li>
			<c:if test="${user!=null}">
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/main?page=compte">| Mon
						compte</a></li>
			</c:if>
			
			<c:if test="${user.getRoleBean().getLabel()=='Admin'}">
			<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/main?page=admin"> || Administration</a></li>
			</c:if>

		</ul>
		<form class="form-inline" action="./main?action=search" method="post">
			<input class="form-control mr-sm-2" type="text" placeholder="Search" name="search"
				aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>

		<form class="form-inline" action="./login" method="post">

			<c:if test="${user==null}">
				<div class="form-group mt-sm-3 mb-2">

					<input class="form-control mr-sm-2" type="text"
						placeholder="Enter Username" name="login" value="toto" required>
					<input class="form-control mr-sm-2" type="password"
						placeholder="Enter Password" name="psw" value="tutu" required>

					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
				</div>

				<a href="./main?page=inscription">Pas de compte?</a>

			</c:if>

			<c:if test="${user!=null}">

				<div class="container-fluid">
					<div class="row">
						<div class="col-sm-4">
							<span>Connecter: ${user.login}</span>
						</div>
						<div class="col-sm-4">
							<a href="<%=request.getContextPath()%>/main?page=panier"
								class="btn btn-success"><i
								class="fa fa-shopping-basket fa-2x"></i></a>
						</div>
						<div class="col-sm-4">
							<a href="<%=request.getContextPath()%>/login?action=logout"
								class="btn btn-danger"><i class="fa fa-sign-out fa-2x"></i></a>
						</div>
					</div>
				</div>


			</c:if>

		</form>

	</div>

</nav>