 // Define the time slots and days of the week
 var timeSlots = ["8am-9am ", "9am-10am ", "10am-11am ", "11am-12pm ", "12pm-1pm ", "1pm-2pm ", "2pm-3pm ", "3pm-4pm ", "4pm-5pm ", "5pm-6pm ", "6pm-7pm ", "7pm-8pm "];
 var daysOfWeek = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];

 // Function to dynamically populate the table
 function populateTable() {
     var tableBody = document.getElementById("tableBody");

     // Loop through each time slot
     for (var i = 0; i < timeSlots.length; i++) {
         var row = document.createElement("tr");

         // Add the time slot in the first column
         var timeCell = document.createElement("th");
         timeCell.setAttribute("scope", "row");
         timeCell.textContent = timeSlots[i];
         row.appendChild(timeCell);

         // Loop through each day of the week to create cells with checkboxes
         for (var j = 0; j < daysOfWeek.length; j++) {
             var cell = document.createElement("td");
             var checkboxWrapper = document.createElement("div");
             checkboxWrapper.className = "checkbox-wrapper";
             var checkbox = document.createElement("input");
             checkbox.setAttribute("type", "checkbox");
             checkboxWrapper.appendChild(checkbox);
             cell.appendChild(checkboxWrapper);
             row.appendChild(cell);
         }

         // Append the row to the table body
         tableBody.appendChild(row);
     }
 }

 // Call the function to populate the table
 populateTable();

 // Add event listener to the submit button
 document.getElementById("submitButton").addEventListener("click", function () {
     // Function to store information of checked checkboxes
     storeCheckedCheckboxes();
 });

 // Function to store information of checked checkboxes
 function storeCheckedCheckboxes() {

     let M = "";
     let T = "";
     let W = "";
     let Th = ""
     let F = ""
     let S = ""
     let Sun = ""
     let x = 0;
     let time
     let Min_1 = 0;
     var checkboxes = document.querySelectorAll('input[type="checkbox"]');
     checkboxes.forEach(function (checkbox) {

         if (x % 7 == 0) {
             time = timeSlots[x / 7];
         }

         if (checkbox.checked) {
             // Store information of checked checkboxes as needed
             Min_1++;
             if (x % 7 == 0) {
                 M += time + "\n,"
             }
             else if (x % 7 == 1) {
                 T += time + "\n,"
             }
             else if (x % 7 == 2) {
                 W += time + "\n,"
             }
             else if (x % 7 == 3) {
                 Th += time + "\n,"
             }
             else if (x % 7 == 4) {
                 F += time + "\n,"
             }
             else if (x % 7 == 5) {
                 S += time + "\n,"
             }
             else {
                 Sun += time + ","
             }

         }

         x++;


     });
     if (Min_1 < 1)
     {
        var warningDiv = document.getElementById("warning");
        warningDiv.textContent = "Warning: Needs to select at least one date";
        warningDiv.style.color = "red";
         return;
     }

     document.getElementById("Monday").value = M;
     document.getElementById("Tuesday").value = T;
     document.getElementById("Wednesday").value = W;
     document.getElementById("Thursday").value = Th;
     document.getElementById("Friday").value = F;
     document.getElementById("Saturday").value = S;
     document.getElementById("Sunday").value = Sun;
     console.log(M); // For testing, you can change this to whatever action you want
     console.log(T);
     console.log(W);
     console.log(Th);
     console.log(F);
     console.log(S);
     console.log(Sun);
     document.getElementById("formbutton").click();
 }
 
 var map = L.map('map');
 var geocodeUrl = 'https://nominatim.openstreetmap.org/reverse?format=json&lat={lat}&lon={lon}';
 var marker;

 L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
     maxZoom: 19,
     attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
 }).addTo(map);

 // Function to add marker at a specific location
 function addMarker(latlng) {
     if (marker) {
         map.removeLayer(marker);
     }
     marker = L.marker(latlng).addTo(map);
     reverseGeocode(latlng);
 }

 // Function to perform reverse geocoding
 function reverseGeocode(latlng) {
     fetch(geocodeUrl.replace('{lat}', latlng.lat).replace('{lon}', latlng.lng))
         .then(response => response.json())
         .then(data => {
             const address = data.display_name || "Location not provided";
             document.getElementById('address').value = address;
         })
         .catch(error => {
             console.error('Error:', error);
             const address = "Location not provided";
             document.getElementById('address').value = address;
         });
 }

 // Set user's location as default marker
 function setDefaultMarker(pos) {
     const lat = pos.coords.latitude;
     const lng = pos.coords.longitude;
     const accuracy = pos.coords.accuracy;

     const latlng = L.latLng(lat, lng);
     addMarker(latlng);

     map.setView(latlng, 13);
 }

 // Handle errors in getting user's location
 function error(err) {
     if (err.code == 1) {
         console.log("Location access denied, location has not been recorded");
     } else {
         console.log("Failed to get user location, location has not been recorded");
     }
 }

 // Get user's location and set default marker
 navigator.geolocation.getCurrentPosition(setDefaultMarker, error);

 // Allow user to click on the map to place a marker
 map.on('click', function(e) {
     addMarker(e.latlng);
 });