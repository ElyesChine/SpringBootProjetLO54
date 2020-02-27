<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Authentification</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<!-- LINEARICONS -->
		<link rel="stylesheet" href="WEB-FILES/fonts/linearicons/style.css">

		<!-- MATERIAL DESIGN ICONIC FONT -->
		<link rel="stylesheet" href="WEB-FILES/fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

		<!-- DATE-PICKER -->
		<link rel="stylesheet" href="WEB-FILES/vendor/date-picker/css/datepicker.min.css">

		<!-- STYLE CSS -->
		<link rel="stylesheet" href="WEB-FILES/css/style.css">

		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
	</head>

	<body>
		<div class="wrapper" style="height:70%;padding:20px;" align=center >
				<form style="width:50%;margin:auto;"action="/connect" method="POST" align=center>
				<center><font size="3"><h1>Connexion </h1></font></center><br>
						<font color=red >${AccountDoesNotExist}</font><br>
						<div class="form-wrapper" align=center >
							<center><label>Email</label> <input class="form-control" style="width:40%" name="email"  required></center><br>
							<label>Password </label><input class="form-control" style="width:40%" name="password" type="password"  required>
							<button data-text="Log in">
								<span>Log in</span>
							</button>
					</div>
				</form>

				<form style="width:50%;margin:auto;"action="signup" method="POST">
				<center><font size="3" align=center><h1>Inscription </h1></font></center>
				<br><br>
				<c:choose>
					<c:when test="${AccountExists == 1}">
						<center><div id='user_exists' style='background-color:#990033; padding:3%; position:fixed; z-index:10; margin-top:10%; margin-left:19%; float:right;'>
        				<center><font color='white'><i class='fa fa-exclamation-triangle'></i>&nbsp;&nbsp;Il existe un compte <br>avyant cet email !</font></center>
        				</div></center>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>

					<div class="form-row"align=center >
						<div class="form-wrapper" >
							<label for="">Nom </label>
							<input type="text" name="lastName" class="form-control" required>
						</div>
						<div class="form-wrapper" align=center >
							<label for="">Prénom </label>
							<input type="text" name="firstName" class="form-control" required>
						</div>
					</div>
					<div class="form-row" align=center >
						<div class="form-wrapper">
							<label for="">Adresse </label>
							<input type="text" class="form-control" name="adresse" required>
						</div>
						<div class="form-wrapper" align=center >
							<label for="">Téléphone </label>
							<input type="text" class="form-control" name="phone" required>
						</div>
					</div>
					<div class="form-row last" align=center >
						<div class="form-wrapper">
							<label for="">Email </label>
							<input type="email" class="form-control" name="email" required>
						</div>
						<div class="form-wrapper" align=center >
							<label for="">Mot de passe </label>
							<input type="password" class="form-control" name="password" required>
						</div>
					</div>

					<center>
						<button data-text="Inscription">
							<span>Inscription</span>
						</button>
					</center>
				</form>
		</div>

		<script src="WEB-FILES/js/jquery-3.3.1.min.js"></script>

		<!-- DATE-PICKER -->
		<script src="WEB-FILES/vendor/date-picker/js/datepicker.js"></script>
		<script src="WEB-FILES/vendor/date-picker/js/datepicker.en.js"></script>

		<script src="WEB-FILES/js/main.js"></script>

		<script type="text/javascript">

	  // make div of "user exists" disappear after a few seconds
      $(document).ready(function() {
          setTimeout(function() {
              $("#user_exists").fadeOut(1500);
          },3000);
      });

      </script>
	</body>
</html>
