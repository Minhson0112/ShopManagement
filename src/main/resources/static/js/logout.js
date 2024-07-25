document.addEventListener('DOMContentLoaded', function() {
    const logoutButton = document.getElementById('logout_button');

    logoutButton.addEventListener('click', function(event) {
        event.preventDefault();
        console.log("Logout button clicked");

        // Lấy CSRF token và header từ meta tags
        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        fetch('/logout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken // Thêm CSRF token vào header
            }
        })
        .then(response => {
            console.log('Response status:', response.status);
            console.log('Response status text:', response.statusText);
            return response.text().then(text => {
                console.log('Response body:', text);
                if (response.ok) {
                    window.location.href = '/login';
                } else {
                    console.error('Logout failed');
                }
            });
        })
        .catch(error => {
            console.error('Error during logout request:', error);
        });
    });
});
