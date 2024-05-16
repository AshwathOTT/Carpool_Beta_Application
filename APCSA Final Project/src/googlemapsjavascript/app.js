//Set map options
var myLatLng = {lat: 33.4484, lng: -112.0740};

var mapOptions = {
    center: myLatLng,
    zoom: 12,
    mapTypeId: google.maps.MapTypeId.ROADMAP
};

//Create the map
var map = new google.maps.Map(document.getElementById("googleMap"), mapOptions);

//Create a Directions service object to use the route method and get a result for our request
var directionsService = new google.maps.DirectionsService();

//Create a DirectionsRenderer object which we will use to display the route
var directionsDisplay = new google.maps.DirectionsRenderer();

//bind the directionsRenderer to the map
directionsDisplay.setMap(map);

//Function that calculates distance and display information: origin, destination, distance, and time
function calcRoute() {
    //Add waypoints
    var waypts = [];
    var checkboxArray = document.getElementById('Rider');
    waypts.push({
        location: checkboxArray.value,
        stopover: true
    });

    //Create Request;
    var request = {
        //Get the value in "origin"
        origin: document.getElementById("Origin").value,

        //Get the geocoded in "destination" field
        destination: {lat: dLAT, lng: dLNG},

        //Array of waypoints/location of rider
        waypoints: waypts,

        //Find the most efficient route between the locations
        optimizeWaypoints: true,

        //Different types of traveling mode (i.e. DRIVING, WALKING, & TRANSIT)
        travelMode: google.maps.TravelMode.DRIVING,

        //Unit system: Imperial Units
        unitSystem: google.maps.UnitSystem.IMPERIAL
    }

    //Pass the request to the route method
    directionsService.route(request, (result, status) => {
        if (status == google.maps.DirectionsStatus.OK) {

            //get distance and time show up in <div> "output"
            const output = document.querySelector('#output');

            //Outputs --> "From: " + origin + "\nTo: " + destination + "\nDriving distance: " + distance + "\nDuration: " + time
            output.innerHTML =
                "<div class = 'alert-info'> From: "+ document.getElementById("Origin").value +
                ". <br />To: " + document.getElementById("Rider").value +
                ". <br />To: " + document.getElementById("Destination").value +
                ". <br />Driving distance <i class='fa-solid fa-road'></i> : " + result.routes[0].legs[0].distance.text +
                ". <br />Duration <i class='fa-solid fa-hourglass'></i> : " + result.routes[0].legs[0].duration.text +
                ". </div>";

            //Display routes
            directionsDisplay.setDirections(result);
            /*
            const route = response.routes[0];
            const summaryPanel = document.getElementById("directions-panel");

            summaryPanel.innerHTML = "";

            // For each route, display summary information.
            for (let i = 0; i < route.legs.length; i++) {
                const routeSegment = i + 1;

                summaryPanel.innerHTML += "<b>Route Segment: " + routeSegment + "</b><br>";
                summaryPanel.innerHTML += route.legs[i].start_address + " to ";
                summaryPanel.innerHTML += route.legs[i].end_address + "<br>";
                summaryPanel.innerHTML += route.legs[i].distance.text + "<br><br>";
            }
            */

        }
        else {
            //delete route from map
            directionsDisplay.setDirections({ routes: []});

            //Center map in Phoenix
            map.setCenter(myLatLng);

            //Error message because of inability to retrieve data due to impossible travel
            output.innerHTML = "<div class = 'alert-danger'><i class='fa-solid fa-triangle-exclamation'></i> Could not retrieve driving distance. </div>";
        }
    });
}

//Create autocomplete objects for all inputs
var options = {
    types: ['(cities)']
}

//Autocompletes the origin text input
var input1 = document.getElementById("Origin");
var autocomplete1 = new google.maps.places.Autocomplete(input1, options);