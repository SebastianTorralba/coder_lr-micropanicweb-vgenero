var map;
/*varios marker */

function loadResults(data) {
    var items, markers_data = [];

    if (data.data.length > 0) {
        items = data.data;
        for (var i = 0; i < items.length; i++) {
            var item = items[i];
            if (item.gpsLat != undefined && item.gpsLon != undefined) {
                var icon = 'http://panico.coder.com.ar:8080/micropanicweb/img/'+item.icono;
                
                var fecha = moment(item.fechaOperacion);
                fecha = fecha.format("DD-MM-YYYY HH:MM");
                var leyenda = item.username + '<br />' + item.razonSocial + '<br />' + item.tel + '<br/>' + fecha;
                var url = '<a href="eventos/' + item.id + '">' + leyenda + '</a>';
                //console.log(url);
                markers_data.push({
                    lat: item.gpsLat,
                    lng: item.gpsLon,
                    title: item.username,
                    icon: {
                        size: new google.maps.Size(32, 32),
                        url: icon
                    },
                    infoWindow: {
                        content: url
                    }
                });
            }
        }
    }
    map.addMarkers(markers_data);
}

/*un solo marker*/
function loadResult(data) {
    item = data.data;

    if (item.gpsLat != undefined && item.gpsLon != undefined) {
        var icon = 'http://panico.coder.com.ar:8080/micropanicweb/img/'+item.icono;
        var fecha = moment(item.fechaOperacion);
        fecha = fecha.format("DD-MM-YYYY HH:MM");
        var leyenda = item.username + '<br />' + item.razonSocial + '<br />' + item.tel + '<br/>' + fecha;
        var url = '<a href="#">' + leyenda + '</a>';
        //console.log(url);
        map.addMarker({
            lat: item.gpsLat,
            lng: item.gpsLon,
            title: item.username,
            icon: {
                size: new google.maps.Size(32, 32),
                url: icon
            },
            infoWindow: {
                content: url
            }
        });

        //anidimos panorama
        map.addControl({
            position: 'top_right',
            content: 'Vista panoramica',
            style: {
                margin: '5px',
                padding: '1px 6px',
                border: 'solid 1px #717B87',
                background: '#fff'
            },
           events: {
                click: function () {
                    panorama = GMaps.createPanorama({
                        el: '#map',
                        lat: item.gpsLat,
                        lng: item.gpsLon,
                    });
                }
            }
        });
    }
}
function printResults(data) {
    $('#datosjson').text(JSON.stringify(data));
    // prettyPrint();
}

$(document).on('click', '.pan-to-marker', function (e) {
    e.preventDefault();

    var position, lat, lng, $index;

    $index = $(this).data('marker-index');
    alert($index);
    position = map.markers[$index].getPosition();

    lat = position.lat();
    lng = position.lng();

    map.setCenter(lat, lng);
});