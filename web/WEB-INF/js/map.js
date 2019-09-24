var mymap;

function initMap() {

	

	mymap = L.map('mapid').setView([ 35.679, 139.77 ], 13);

	L.tileLayer(
					'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw',
					{
						maxZoom : 18,
						attribution : 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, '
								+ '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, '
								+ 'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
						id : 'mapbox.streets'
					}).addTo(mymap);

	refreshData();

}

var linesFeatureLayer = null;

function refreshData() {
	if(null != linesFeatureLayer){
		mymap.removeLayer(linesFeatureLayer); 
	}
	var year = $('#input_year').val();
	var month = $('#input_month').val();
	var day = $('#input_day').val();
	var hour = $('#input_hour').val();
	var interval = $('#input_interval').val();
	
	var timeIndex = year+month+day+hour;
	console.log(timeIndex);
	console.log(interval);

	$.ajax({
		url : 'map/json?timeIndex=' + timeIndex + '&timeSize=' + interval,
		success : function(result) {

			var states = $.parseJSON(result.res);
			
			linesFeatureLayer = L.geoJSON(states, {
				style : function(feature) {
					switch (feature.properties.speed) {
					case 0:
						return {
							color : "#A0A0A0"
						};
					case 1:
						return {
							color : "#FF0000"
						};
					case 2:
						return {
							color : "#FF4000"
						};
					case 3:
						return {
							color : "#FF8000"
						};
					case 4:
						return {
							color : "#FFA000"
						};
					case 5:
						return {
							color : "#A0FF00"
						};
					case 6:
						return {
							color : "#80FF00"
						};
					case 7:
						return {
							color : "#00FF00"
						};
					case 8:
						return {
							color : "#00E000"
						};
					case 9:
						return {
							color : "#00C000"
						};
					case 10:
						return {
							color : "#00C000"
						};
					}
				}
			});
			linesFeatureLayer.addTo(mymap);
		}
	});

}