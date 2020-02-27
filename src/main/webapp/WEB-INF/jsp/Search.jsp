<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="fr.utbm.lo54.core.CourseSession"%>
<%@ page import="fr.utbm.lo54.core.Course"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Acceuil </title>
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
.formulaire input,label,button{
  float:left;
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

        .btn-enroll{
        	padding: 4px 12px !important;
		    background-color:  #0483ba ;
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

        .form-control.datepicker-here {
		    padding: 6px 12px !important;
		}

	</style>

	<script type="text/javascript">
		function SubmitValue()
		{
			// This Function provides you to assign the selectedindex to a form variable which would come as request value at time of reloading
		    document.f1.selectedLocationValue.value = document.f1.location.selectedIndex;
		    document.f1.submit();
		}

		function retainValues()
		{
		    var value1 =<%=request.getParameter("selectedLocationValue")%>;
		    if(value1!=null)
		    {
		    	document.f1.location.selectedIndex = value1;
		    }
		}
	</script>

</head>


<body onload="retainValues()">

	<div id="lo54_header">

		<span style="font-size:30px;cursor:pointer;padding: 10px" onclick="openNav()">&#9776; Menu</span>
<div id="mySidenav" class="sidenav">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			<a href="#"><i class="fa fa-home"></i>&nbsp;&nbsp;<font font-family="Poppins-Regular" >Accueil</font></a>
			<c:if test="${sessionScope.clientId != null}">
				<a href="mysessioncourses?idClient=${sessionScope.clientId}"><i class="fa fa-grip-vertical" onclick="mesinscriptions()"></i>&nbsp;&nbsp;Mes inscriptions</a>
				<a href="/disconnect"><i class=" 	fa fa-user-circle"></i>&nbsp;&nbsp;Déconnexion</a>
			</c:if>
			<c:if test="${sessionScope.clientId == null}">
				<a href="/connect"><i class=" 	fa fa-user-circle"></i>&nbsp;&nbsp;Connexion</a>
			</c:if>

		</div>
	</div>

			<div class="">
<div class="container col-sm-12" style="padding-top:4%;">
	<br>
	<h2><font color="#999">Filtrer des formations</font> </h2><hr>
			<c:choose>
				<c:when test="${isEnrolled == 0}">

				</c:when>
				<c:when test="${isEnrolled == 1}">
					<center><div id='enrolled_successfully_notification' style='background-color:#004d00; padding:3%; position:fixed; z-index:10; margin-top:10%; margin-left:38%; float:right;'>
        			<center><font color='white'><i class='fa fa-check-square'></i>&nbsp;&nbsp;VOTRE INSCRIPTION AU COURS<br>" ${title} "<br>EST REUSSIE !</font></center>
        			</div></center>
				</c:when>
				<c:when test="${isEnrolled == 3}">
					<center><div id='already_enrolling_notification' style='background-color:#990000; padding:3%; position:fixed; z-index:10; margin-top:10%; margin-left:34%; float:right;'>
        			<center><font color='white'><i class='fa fa-check-square'></i>&nbsp;&nbsp;VOUS ETES DEJA INSCRIT !</font></center>
        			</div></center>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
			<div id="frm-search" >
				<form action="/ManageCourses" id="f1" name="session_course_search_form" method="post" class="row flex-center">
					<label for="search">Mots-clés&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<div class='formulaire'>
						<input style="width:20%;float:left;" class="form-control mdb-autocomplete" autocomplete="off" type="search" id="form-autocomplete-1" name="keyWord"
						placeholder="Mots-clés..."
							<c:if test="${not empty keyWord}">
								  value="${keyWord}"
							</c:if>
						/>

						<label for="location">&nbsp;&nbsp;&nbsp;&nbsp;Lieu&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<select style="width:20%;float:left;" class="form-control ai_select" id="location" name="location"  onchange="javascript:SubmitValue()">

								<c:choose>
									<c:when test="${not empty location}">
										<option value="${location}">${location}</option>
										<option value="">Choisir une localisation du cours </option>
									</c:when>
									<c:when test="${empty location}">
										<option value="">Choisir une localisation du cours</option>
									</c:when>
								</c:choose>

								<c:forEach items="${listLocation}" var="selectedLocation">
									<c:set var="location" value="${selectedLocation}" />
									<option value="${selectedLocation.city}">${selectedLocation.city}</option>
								</c:forEach>
							</select>

						<label class=" requiredField" for="dateDebut">
							&nbsp;&nbsp;&nbsp;&nbsp;Date du début&nbsp;&nbsp;
						</label>
						<input style="width:20%;float:left;" type="text" class="form-control datepicker-here"  id="dateDebut" name="dateDebut"  autocomplete="off" data-language='en'
							 placeholder="DD/MM/YYYY" data-date-format="dd/mm/yyyy"
							 <c:if test="${not empty dateDebut}">
									value="${dateDebut}"
							</c:if>
						 />
						 <br><br><br>
						<label class="control-label requiredField" for="dateFin">
							Date de fin&nbsp;&nbsp;
						</label>
						<input type="text" style="width:20%;float:left;" class="form-control datepicker-here"  id="dateFin" name="dateFin"  autocomplete="off" data-language='en'
							 placeholder="DD/MM/YYYY" data-date-format="dd/mm/yyyy"
							 <c:if test="${not empty dateFin}">
								value="${dateFin}"
							</c:if>
						 />
						<button style="padding-left:5px;width:20%;float:left;margin:0px;margin-left:130px;height:34px;" type="submit" class="btn">Chercher</button>

					</div>

					<input type="hidden" name="selectedLocationValue" value="0"/>

				</form>
			</div>
		</div></div><!-- fin div formulaire de recherche -->
			<br><br><br>

			<div class="container">

			<div class='col-sm-12' style="padding-top:3%; padding-bottom:4%;">

					<h2>Liste des formations </h2><hr>
					<br><table id="dataTableSessionCourse" class="table" style="text-align:center" cellspacing="1" cellpadding="14" width="100%">
						<thead>
							<tr id="table-head">
								<th class="th-sm" width="4%">Code du cours</th>
								<th class="th-sm" width="20%">Nom</th>
								<th class="th-sm" width="8%">Lieu</th>
								<th class="th-sm">Date de début</th>

								<th class="th-sm">Date de fin</th>
								<th class="th-sm" width="4%">Max Places</th>
								<th class="th-sm" width="4%">Places réservées</th>
								<th class="th-sm">Statut / S'incrire</th>
							</tr>
						</thead>

						<c:forEach items="${listCourseSession}" var="courseSession">
							<tr>
								<td><b>${courseSession.course.code}</b></td>
								<td>${courseSession.course.title}</td>
								<td>${courseSession.location.city}</td>
								<td>${courseSession.startDate}</td>
								<td>${courseSession.endDate}</td>
								<td>${courseSession.max}</td>
								<td>${courseSession.getSize()}</td>
								<td>
									<c:if test="${(courseSession.max == courseSession.getSize())}">
										<a id="myHref" style="color: red">Pas de places disponibles</a>
									</c:if>
									<c:if test="${(courseSession.max != courseSession.getSize())}">
										<div style="width:50%;align=center;margin:auto;">
											<button id=	"myHref" href="#"  onclick="changeOption(${courseSession.id},${courseSession.getSize()},${(sessionScope.clientId!=null)?sessionScope.clientId:0})">
											S'inscrire </button>
										</div>
									</c:if>

			  					</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div> <!-- col sm 12 -->
		</div> <!-- fin container -->

	</div>

	</div> <!-- End Wrapper -->



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
		function openNav() {
			document.getElementById("mySidenav").style.width = "250px";
		}

		function closeNav() {
			document.getElementById("mySidenav").style.width = "0";
		}
		function changeOption(idCourseSession,size,id) {
			window.location.href = "enrollTransaction?typeTransaction=enroll&idCourseSession="+idCourseSession+"&size="+size+"&idClient="+id;
		}
	</script>
	<script type="text/javascript">
		function mesinscriptions(id) {
			console.log("here");
			window.location.href = "mysessioncourses?idClient="+id;
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
<script>
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}
</script>
</body>

</html>
