var isShowMapActive = 0;

function setPosition(lat, lon){
  var fromProjection = new OpenLayers.Projection("EPSG:4326"); // Transform from WGS 1984
  var toProjection = new OpenLayers.Projection("EPSG:900913"); // to Spherical Mercator Projection
  var position = new OpenLayers.LonLat(lon, lat).transform( fromProjection, 
  toProjection);
  return position;
}

function handler(position, message, map){
  var popup = new OpenLayers.Popup.FramedCloud("Popup", 
  position, null,
  message, null,
  true // <-- true if we want a close (X) button, false otherwise
  );
  map.addPopup(popup);
}

function create_map() {
  map = new OpenLayers.Map("Map");
  var mapnik = new OpenLayers.Layer.OSM();
  map.addLayer(mapnik);

  //Markers
  var markers = new OpenLayers.Layer.Markers( "Markers" );
  map.addLayer(markers);

  //Protos Marker
  var position=setPosition(lat_main,lon_main);
  var mar=new OpenLayers.Marker(position);
  markers.addMarker(mar);
  mar.events.register('mousedown', mar, function(evt) { 
    handler(position,'nothing',map);}
);


  //Orismos zoom
  const zoom = 11;
  map.setCenter(position, zoom);
}



var div_create = document.createElement('div');

function clear_map() {
  document.getElementById("show_map_button").disabled = false;
  if(isShowMapActive >= 1){
    div_create.remove();
  }
}

function Show_map() {
  
  isShowMapActive += 1;
  document.getElementById("show_map_button").disabled = true;


  console.log("Was empty");
  div_create.innerHTML = `
    <div id="Map" style="height:600px; width:700px"></div>
    `
	document.getElementById('my_map').appendChild(div_create);



}








  
