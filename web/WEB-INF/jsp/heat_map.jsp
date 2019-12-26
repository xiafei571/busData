<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
	integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
	crossorigin="" />
<!-- Make sure you put this AFTER Leaflet's CSS -->
<script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js"
	integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew=="
	crossorigin=""></script>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Map</title>
</head>
<body>
	<div class="container">
		<form>
			<div class="form-row">
				<div class="col-2">
					<input type="text" class="form-control" placeholder="年"
						id="input_year" value="2019">
				</div>
				<div class="col-2">
					<input type="text" class="form-control" placeholder="月"
						id="input_month" value="07">
				</div>
				<div class="col-2">
					<input type="text" class="form-control" placeholder="日"
						id="input_day" value="01">
				</div>
				<div class="col-2">
					<input type="text" class="form-control" placeholder="時"
						id="input_hour" value="08">
				</div>
				<div class="col-2">
					<input type="text" class="form-control" placeholder="時間間隔"
						id="input_interval" value="01">
				</div>
				<div class="col-auto">
					<a type="button" class="btn btn-primary mb-2"
						onclick="refreshData()">Submit</a>
				</div>
			</div>
		</form>

		<div id="mapid" style="height: 800px;"></div>
	</div>


	<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
	<script
		src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/heatmap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/leaflet-heatmap/leaflet-heatmap.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/heat.js"></script>
	<script type="text/javascript">
		window.onload = function() {
			initMap(null);
		}
	</script>
</body>
</html>