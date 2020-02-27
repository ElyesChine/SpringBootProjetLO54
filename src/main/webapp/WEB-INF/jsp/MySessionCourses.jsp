<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Mes inscriptions</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<!-- LINEARICONS -->
		<link rel="stylesheet" href="WEB-FILES/fonts/linearicons/style.css">

		<!-- MATERIAL DESIGN ICONIC FONT -->
		<link rel="stylesheet" href="WEB-FILES/fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

		<!-- DATE-PICKER -->
		<link rel="stylesheet" href="WEB-FILES/date-picker/css/datepicker.min.css">

		<!-- STYLE CSS -->
		<link rel="stylesheet" href="WEB-FILES/css/style.css">

		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">

		<link rel="stylesheet" href="WEB-FILES/css/bootstrap.min.css">

	<style>
		/* SIDE MENU */
.sidenav {
  height: 100%;
  width: 0;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: #111;
  overflow-x: hidden;
  transition: 0.5s;
  padding-top: 60px;
}

.sidenav a {
  padding: 8px 10px 8px 32px;
  text-decoration: none;
  font-size: 20px;
  color: #818181;
  display: block;
  transition: 0.3s;
}

.sidenav a:hover {
  color: #f1f1f1;
}

.sidenav .closebtn {
  position: absolute;
  top: 0;
  right: 25px;
  font-size: 36px;
  margin-left: 50px;
}
@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}

		/*header (Menu)*/
		ul
		{
		  list-style-type: none;
		  margin: 0;
		  padding: 0;
		  overflow: hidden;
		  background-color: #333;
		}

		li
		{
		  float: left;
		}

		li a
		{
		  display: inline-block;
		  color: white;
		  text-align: center;
		  padding: 14px 16px;
		  text-decoration: none;
		}

		li a:hover
		{
		  background-color: #111;
		}

		.logo-active
		{
		  background-color: red;
		}

		.wrapper
		{
			background: url("../images/university.jpg") no-repeat fixed right center;
		}

		/*designing buttons*/
		.btn
		{
		    padding: 4px 12px !important;
		    background-color: green;
		}

		 tr:hover {
          background-color: #ffff99;
        }

        th {
          color: black;
          background-color: #deeef5;
        }

        .btn-delete{
        	padding: 4px 12px !important;
		    background-color: red;
		    color: white;
        }

		.lo54_footer
		{
		  position: fixed;
		  left: 0;
		  bottom: 0;
		  width: 100%;
		  height: 6%;
		  padding-top:1%;
		  background-color: #333;
		  color: white;
		  text-align: center;
		}

        #lo54_header
        {
        	position:fixed;
		    top:0;
		    width:100%;
		    z-index:100;
        }

	</style>
<!-- side menu -->
	<script>
	function openNav() {
	  document.getElementById("mySidenav").style.width = "250px";
	}

	function closeNav() {
	  document.getElementById("mySidenav").style.width = "0";
	}
	</script>
</head>

<body>
	<div id="lo54_header">
		<span style="font-size:30px;cursor:pointer;padding: 10px" onclick="openNav()">&#9776; Menu</span>
		<div id="mySidenav" class="sidenav">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			<a href="/"><i class="fa fa-home"></i>&nbsp;&nbsp;<font font-family="Poppins-Regular" >Accueil</font></a>
			<c:if test="${sessionScope.clientId != null}">
				<a href="/disconnect"><i class=" 	fa fa-user-circle"></i>&nbsp;&nbsp;Déconnexion</a>
			</c:if>
			<c:if test="${sessionScope.clientId == null}">
				<a href="/connect"><i class=" 	fa fa-user-circle"></i>&nbsp;&nbsp;Connexion</a>
			</c:if>
		</div>

	</div>

			<c:choose>
				<c:when test="${isDeleted == 0}"></c:when>
				<c:when test="${isDeleted == 1}">
					<center><div id='course_deleted_notification' style='background-color:#990000; padding:3%; position:fixed; z-index:10; margin-top:10%; margin-left:34%; float:right;'>
        			<center><font color='white'><i class='fa fa-check-square'></i>&nbsp;&nbsp;VOTRE INSCRIPTION A <br>"${title}"<br>IS ANNULEE AVEC SUCCESS !</font></center>
        			</div></center>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>

		<div class="container" style="padding-top:4%;">
			<div class='col-sm-12' style="padding-top:3%; padding-bottom:4%;">
				<table id="dataTableSessionCourse" class="table" style="text-align:center" cellspacing="1" cellpadding="14" width="100%">
					<thead>
						<tr id="table-head">
							<th class="th-sm" width="4%">Code du cours</th>
							<th class="th-sm" width="20%">Nom</th>
							<th class="th-sm" width="8%">Lieu</th>
							<th class="th-sm">Date de début</th>
							<th class="th-sm">Date de fin</th>
							<th class="th-sm">Se désinscrire</th>
						</tr>
						<c:forEach items="${listOfEnrolledCourseSession}" var="courseSessionEnrolled">
							<tr>
								<td>${courseSessionEnrolled.course.code}</td>
								<td>${courseSessionEnrolled.course.title}</td>
								<td>${courseSessionEnrolled.location.city}</td>
								<td>${courseSessionEnrolled.startDate}</td>
								<td>${courseSessionEnrolled.endDate}</td>
								<td>
									<center>
										<a style="background-color:red;"  href="#" class="btn-delete" onclick="deleteFunction(${courseSessionEnrolled.id},${idClient})">
											Supprimer
										</a>
									</center>
								</td>
							</tr>
						</c:forEach>
					</thead>
				</table>
			</div>
		</div>

	<!-- JQuery -->
	<script src="WEB-FILES/js/jquery-3.3.1.min.js"></script>

		<!-- DATE-PICKER -->
		<script src="WEB-FILES/date-picker/js/datepicker.js"></script>
		<script src="WEB-FILES/date-picker/js/datepicker.en.js"></script>

		<script src="WEB-FILES/js/main.js"></script>

		<script src="WEB-FILES/js/bootstrap.min.js"></script>

	<!-- Extra JavaScript/CSS added manually in "Settings" tab -->
	<!-- Include JQuery  -->
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

	<!-- Include Date Range Picker -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js">
	</script>

	<script>
		$("#AlsoResetDates").click(function(){
		    $('#dateDebut').val('').datepicker('remove').datepicker();
		    $('#dateFin').val('').datepicker('remove').datepicker();
		})
	</script>

	<script type="text/javascript">
		function changeOption(idCourseSession,size) {
			var element = document.getElementById('client').value;
			window.location.href = "enrollTransaction?typeTransaction=enroll&idCourseSession="+idCourseSession+"&size="+size+"&idClient="+element;
		}
	</script>
	<script type="text/javascript">
		function mySessionCoursesRedirect() {
			var element = document.getElementById('client').value;
			window.location.href = "mysessioncourses?idClient="+element;
		}
	</script>

	<script type="text/javascript">
		  // make div of "user enrolled successfully" disappear after a few seconds
	      $(document).ready(function() {
	          setTimeout(function() {
	              $("#enrolled_successfully_notification").fadeOut(1500);
	          },3000); // disappear after 3 seconds
	      });

	      // make div of "user already enrolling course" disappear after a few seconds
	      $(document).ready(function() {
	          setTimeout(function() {
	              $("#already_enrolling_notification").fadeOut(1500);
	          },3000); // disappear after 3 seconds
	      });
      </script>


	<script type="text/javascript">
		function deleteFunction(idCourseSessionEnrolled,idClient)
		{
			window.location.href = "deleteTransaction?typeTransaction=delete&idCourseSession="+idCourseSessionEnrolled+"&idClient="+idClient;
		}
	</script>

	<script>
		// make div of "course deleted" disappear after a few seconds
	      $(document).ready(function() {
	          setTimeout(function() {
	              $("#course_deleted_notification").fadeOut(1500);
	          },3000); // disappear after 3 seconds
	      });
      </script>

	</body>
</html>
