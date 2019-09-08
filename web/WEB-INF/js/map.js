function initMap() {
	var mymap = L.map('mapid').setView([ 35.679, 139.77 ], 13);
	
	L.tileLayer(
					'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw',
					{
						maxZoom : 13,
						attribution : 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, '
								+ '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, '
								+ 'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
						id : 'mapbox.streets'
					}).addTo(mymap);
	
	
	var myLines = [{
		"type": "LineString",
		"coordinates": [ [ 139.771526, 35.673567 ],
			[ 139.771476, 35.679496 ],
			[ 139.771426, 35.679456 ],
			[ 139.771386, 35.679395 ],
			[ 139.771336, 35.679334 ],
			[ 139.771276, 35.679234 ],
			[ 139.770797, 35.679327 ],
			[ 139.770717, 35.679186 ],
			[ 139.770528, 35.679853 ],
			[ 139.770508, 35.679833 ] ]
	}, {
		"type": "LineString",
		"coordinates": [[-105, 40], [-110, 45], [-115, 55]]
	}];

	var myStyle = {
		"color": "#ff7800",
		"weight": 5,
		"opacity": 0.65
	};

	L.geoJSON(myLines, {
		style: myStyle
	}).addTo(mymap);
}