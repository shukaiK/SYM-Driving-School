document.querySelectorAll('.nav-link').forEach(function(link) {
    link.addEventListener('mouseenter', function() {
      this.style.setProperty('--width', this.offsetWidth + 'px');
    });
  });
