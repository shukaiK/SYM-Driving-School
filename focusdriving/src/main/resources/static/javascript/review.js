 // Get the modal element
 var modal = document.getElementById('myModal');

 // Get the button that opens the modal
 var btn = document.getElementById("openModalBtn");

 // Get the <span> element that closes the modal
 var span = document.getElementsByClassName("close")[0];

 // When the user clicks the button, open the modal
 btn.onclick = function () {
   modal.style.display = "block";
 }

 // When the user clicks on <span> (x), close the modal
 span.onclick = function () {
   modal.style.display = "none";
 }

 // When the user clicks anywhere outside of the modal, close it
 window.onclick = function (event) {
   if (event.target == modal) {
     modal.style.display = "none";
   }
 }

 const currentDate = new Date();


 function addLeadingZero(number) {
   return number < 10 ? "0" + number : number;
 }
 const monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
   "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

 const year = currentDate.getFullYear();
 const monthIndex = currentDate.getMonth(); // Months are zero-based, so January is 0
 const monthName = monthNames[monthIndex];
 const day = addLeadingZero(currentDate.getDate());



 var subbtn = document.getElementById("submitButton");


 subbtn.addEventListener("click", function () {
   var stars = document.getElementsByName('rate');

   var selectedValue = "";

   for (var i = 0; i < stars.length; i++) {
     if (stars[i].checked) {
       selectedValue = stars[i].value;
       break;
     }
   }
   document.getElementById("star_val").value = selectedValue;
   document.getElementById("date").value = monthName + " " + day + " ," + year;
   document.getElementById("submits").click();


 })

 var textarea = document.getElementById("feedback");


 var charCount = document.getElementById("charCount");

 // Add event listener for input event
 textarea.addEventListener("input", function () {
   // Get the current character count
   var count = textarea.value.length;

   // Check if the character count exceeds the limit
   if (count > 217) {
     // Trim the text to the limit
     textarea.value = textarea.value.slice(0, 217);
     count = 217; // Update the character count
   }

   // Update the character count display
   charCount.textContent = count + "/217";
 });
