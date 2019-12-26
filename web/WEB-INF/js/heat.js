var map;
var heatmapLayer;

var cfg = {
		// radius should be small ONLY if scaleRadius is true (or small
		// radius
		// is intended)
		// if scaleRadius is false it will be the constant radius used
		// in pixels
		"radius" : 20,
		"maxOpacity" : 8,
		// scales the radius based on map zoom
		 "scaleRadius": false,
		// if set to false the heatmap uses the global maximum for
		// colorization
		// if activated: uses the data maximum within the current map
		// boundaries
		// (there will always be a red spot with useLocalExtremas true)
		"useLocalExtrema" : true,
		// which field name in your data represents the latitude -
		// default "lat"
		latField : 'lat',
		// which field name in your data represents the longitude -
		// default
		// "lng"
		lngField : 'lng',
		// which field name in your data represents the data value -
		// default
		// "value"
		valueField : 'value'
	};

function refreshData(){
	if (null != heatmapLayer) {
		map.removeLayer(heatmapLayer);
	}
	
	var year = $('#input_year').val();
	var month = $('#input_month').val();
	var day = $('#input_day').val();
	var hour = $('#input_hour').val();
	var interval = $('#input_interval').val();

	var timeIndex = year + month + day + hour;
	req_url = 'heatData?timeIndex=' + timeIndex + '&timeSize=' + interval;

	$.ajax({
		url : req_url,
		success : function(result) {

			var testData = {
				max : 10,
				data : result.res
			};

			heatmapLayer = new HeatmapOverlay(cfg);
			heatmapLayer.setData(testData);
			heatmapLayer.addTo(map);

		}
	});
	
}

function initMap(resultId) {
	
	var baseLayer = L.tileLayer(
			'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw',
			{
				maxZoom : 18,
				attribution : 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, '
						+ '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, '
						+ 'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
				id : 'mapbox.streets'
			})

	var year = $('#input_year').val();
	var month = $('#input_month').val();
	var day = $('#input_day').val();
	var hour = $('#input_hour').val();
	var interval = $('#input_interval').val();

	var timeIndex = year + month + day + hour;
	req_url = 'heatData?timeIndex=' + timeIndex + '&timeSize=' + interval;

	$.ajax({
		url : req_url,
		success : function(result) {

			var testData = {
				max : 10,
				data : result.res
			};

			heatmapLayer = new HeatmapOverlay(cfg);
			heatmapLayer.setData(testData);
			map = new L.Map('mapid', {
				center : new L.LatLng(35.679, 139.77),
				zoom : 10,
				layers : [ baseLayer, heatmapLayer ]
			});

		}
	});

}