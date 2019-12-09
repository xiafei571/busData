<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css"
	integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
	crossorigin="" />
<!-- Make sure you put this AFTER Leaflet's CSS -->
<script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js"
	integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og=="
	crossorigin=""></script>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Bus情報</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						TotalCount： ${result.pagination.totalCount}
						<br /> CARNUM: ${carNum}
					</tr>
					<tr>
						<th scope="col">#</th>
						<th scope="col">SZ_NAME</th>
						<th scope="col">車輌番号</th>
						<th scope="col">出発予定日時</th>
						<th scope="col">出発実績日時</th>
						<th scope="col">到着予定日時</th>
						<th scope="col">到着実績日時</th>

						<th scope="col">Map</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${result.pageData}" var="list">
						<tr>
							<th scope="row">${list.resultId}</th>
							<td>${list.dianame}</td>
							<td>${list.carNum}</td>
							<td><fmt:formatDate value="${list.departure}"
									pattern="yyyy/MM/dd HH:mm" /></td>
							<td><fmt:formatDate value="${list.departured}"
									pattern="yyyy/MM/dd HH:mm" /></td>
							<td><fmt:formatDate value="${list.arrival}"
									pattern="yyyy/MM/dd HH:mm" /></td>
							<td><fmt:formatDate value="${list.arrived}"
									pattern="yyyy/MM/dd HH:mm" /></td>
							<td><a href="#" onclick="refreshData(${list.resultId})">查看</a>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<nav aria-label="...">
				<ul class="pagination">
					<!-- 如果当前页是第一页的话，上一页置灰 -->
					<li class="page-item" id="page_pre"><a class="page-link"
						href="list?pageIndex=${result.pagination.pageIndex-1}&pageSize=${result.pagination.pageSize}"
						tabindex="-1">Pre</a></li>

					<!-- 循环生成页签 改成用jquery实现  
                    
                    <!-- 如果当前页是最后一页的话，下一页置灰 -->
					<li class="page-item" id="page_next"><a class="page-link"
						href="list?pageIndex=${result.pagination.pageIndex+1}&pageSize=${result.pagination.pageSize}"
						tabindex="-1">Next</a></li>

				</ul>
			</nav>
		</div>
		<div id="mapid" class="row" style="height: 800px;"></div>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" />
	<script
		src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/page.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/map.js"></script>
	<script type="text/javascript">
		window.onload = function() {
			initPage('${result.pagination.pageIndex}',
					'${result.pagination.pageSize}',
					'${result.pagination.totalCountPage}');
			initMap( '${first_resultId}');
		}
	</script>
</body>
</html>