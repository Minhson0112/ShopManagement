document.addEventListener('DOMContentLoaded', function() {
    const pageWrapper = document.getElementById('page_wrapper');
    const loadingDialog = document.getElementById('loading_dialog');
    const searchError = document.getElementById('search_error');
    const titleInputError = document.getElementById('title_input_error');
    const categorySelect = document.getElementById('category_select');

    function fetchProductNames() {
        fetch('/api/productInfo', {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            updateCategorySelects(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }

    function fetchStorage(title, category) {
        const url = new URL('/api/storage', window.location.origin);
        if (title) {
            url.searchParams.append('title', title);
        }
        if (category) {
            url.searchParams.append('category', category);
        }

        pageWrapper.style.display = 'flex';
        loadingDialog.style.display = 'block';

        fetch(url, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        })
        .then(response => {
            pageWrapper.style.display = 'none';
            loadingDialog.style.display = 'none';
            if (!response.ok) {
                searchError.style.display = 'block';
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            updateTable(data);
        })
        .catch(error => {
            searchError.style.display = 'block';
            console.error('Error:', error);
        });
    }

    function updateCategorySelects(products) {
        const categories = [...new Set(products.map(product => product.category))];

        categorySelect.innerHTML = '<option value="" selected>Ấn vào đây để chọn</option>'; // Clear existing options
        categories.forEach(category => {
            const option = document.createElement('option');
            option.value = category;
            option.textContent = category;
            categorySelect.appendChild(option);
        });
    }

    function updateTable(products) {
        const tbody = document.getElementById('data_list_items');
        tbody.innerHTML = ''; // Clear existing data

        products.forEach(product => {
            const row = document.createElement('tr');

            const idCell = document.createElement('td');
            idCell.textContent = product.productId;
            row.appendChild(idCell);

            const nameCell = document.createElement('td');
            nameCell.textContent = product.productName;
            row.appendChild(nameCell);

            const categoryCell = document.createElement('td');
            categoryCell.textContent = product.category;
            row.appendChild(categoryCell);

            const quantityCell = document.createElement('td');
            quantityCell.textContent = product.quantity;
            row.appendChild(quantityCell);

            tbody.appendChild(row);
        });

        const event = new Event('tableUpdated');
        document.dispatchEvent(event);
    }

    document.getElementById('search_button').addEventListener('click', function(event) {
        event.preventDefault();
        const title = document.getElementById('title_input').value.trim();
        const category = categorySelect.value.trim();

        if (title.length > 200) {
            titleInputError.style.display = 'block';
            return;
        }

        fetchStorage(title, category);
    });

    fetchProductNames();
    fetchStorage(); // Fetch all storage on initial load
});
