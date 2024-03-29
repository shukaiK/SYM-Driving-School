$(document).ready(function() {
    var slides = $('.slide');
    var currentSlide = 0;

    slides.eq(currentSlide).addClass('active');
    var slideInterval = setInterval(nextSlide,4000); // Change slide every 3 seconds

    function nextSlide() {
        slides.eq(currentSlide).removeClass('active');
        currentSlide = (currentSlide + 1) % slides.length;
        slides.eq(currentSlide).addClass('active');
    }
});
 
