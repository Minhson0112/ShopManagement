document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('login_form_contents');
    const errorDiv = document.getElementById('login_error');

    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('error')) {
      errorDiv.textContent = "đăng nhập thất bại";
      errorDiv.style.display = 'block';
    }

    loginForm.addEventListener('submit', function(event) {
        const usernameInput = document.getElementById('id_input');
        const passwordInput = document.getElementById('password_input');
        const loginId = usernameInput.value.trim();
        const password = passwordInput.value.trim();

        if (!loginId || !password) {
            event.preventDefault();
            errorDiv.textContent = "đăng nhập thất bại";
            errorDiv.style.display = "block";
            return;
        }

        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                [csrfHeader]: csrfToken
            },
            body: new URLSearchParams({
                'login_id': loginId,
                'password': password
            })
        })
        .then(response => {
            if (response.ok) {
                
            } else {
                errorDiv.textContent = "đăng nhập thất bại";
                errorDiv.style.display = "block";
            }
        })
        .catch(error => {
            console.error('Error:', error);
            errorDiv.textContent = "đăng nhập thất bại";
            errorDiv.style.display = "block";
        });
    });
});
