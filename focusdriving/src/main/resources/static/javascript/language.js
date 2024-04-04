document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('english').addEventListener('click', function() {
        // Change text content to English
        document.getElementById('main-title').textContent = 'Focus Driving School';
        document.getElementById('subtitle').textContent = 'Navigate the Road to Success with Our Lessons';
        document.getElementById('content-1').textContent = 'Focus Driving School is where excellence meets experience! With over a decade of dedicated service, we have been shaping safe and confident drivers across British Columbia. Whether you\'re aiming for a Class 5 or Class 7 license, our experienced instructors are here to guide you through every turn and intersection.';
        document.getElementById('content-2').textContent = 'Focus Driving School offers the flexibility for students to switch instructors until they find one that perfectly matches their learning style and preferences.';
    });

    document.getElementById('chinese').addEventListener('click', function() {
        // Change text content to Chinese
        document.getElementById('main-title').textContent = '精英驾校';
        document.getElementById('subtitle').textContent = '冰激凌';
        document.getElementById('content-1').textContent = '冰激凌';
        document.getElementById('content-2').textContent = '冰激凌';
    });
});
