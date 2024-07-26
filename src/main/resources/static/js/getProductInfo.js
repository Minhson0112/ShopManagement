document.addEventListener('DOMContentLoaded', function() {
    const searchButton = document.getElementById('search_button');
    const pageWrapper = document.getElementById('page_wrapper');
    const loadingDialog = document.getElementById('loading_dialog');
    const searchError = document.getElementById('search_error');
    const titleInputError = document.getElementById('title_input_error');
    const categorySelect = document.getElementById('category_select');
    const addProductCategorySelect = document.getElementById('add_product_category_select');
    const editProductCategorySelect = document.getElementById('edit_product_category_select');

    searchButton.addEventListener('click', function(event) {
        event.preventDefault();
        searchError.style.display = 'none';
        titleInputError.style.display = 'none';

        const title = document.getElementById('title_input').value.trim();
        const category = categorySelect.value.trim();

        if (title.length > 200) {
            titleInputError.style.display = 'block';
            return;
        }

        const url = new URL('/api/productInfo/search', window.location.origin);
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
            console.log('Success:', data);
            updateTable(data);
        })
        .catch(error => {
            searchError.style.display = 'block';
            console.error('Error:', error);
        });
    });

    function fetchProducts() {
        const url = '/api/productInfo';

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
            console.log('Success:', data);
            updateTable(data);
            updateCategorySelects(data);
        })
        .catch(error => {
            searchError.style.display = 'block';
            console.error('Error:', error);
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

            const entryPriceCell = document.createElement('td');
            entryPriceCell.textContent = product.entryPrice;
            row.appendChild(entryPriceCell);

            const sellPriceCell = document.createElement('td');
            sellPriceCell.textContent = product.sellPrice;
            row.appendChild(sellPriceCell);

            const editCell = document.createElement('td');
            const editButton = document.createElement('button');
            editButton.classList.add('edit_product_button');
            const editImg = document.createElement('img');
            editImg.src = '/imgs/edit.png';
            editImg.alt = 'edit';
            editButton.appendChild(editImg);
            editCell.appendChild(editButton);
            row.appendChild(editCell);

            const removeCell = document.createElement('td');
            const removeButton = document.createElement('button');
            removeButton.classList.add('remove_product_button');
            const removeImg = document.createElement('img');
            removeImg.src = '/imgs/remove.png';
            removeImg.alt = 'remove';
            removeButton.appendChild(removeImg);
            removeCell.appendChild(removeButton);
            row.appendChild(removeCell);

            tbody.appendChild(row);
        });

        const event = new Event('tableUpdated');
        document.dispatchEvent(event);
    }

    function updateCategorySelects(products) {
        const categories = [...new Set(products.map(product => product.category))];

        [categorySelect, addProductCategorySelect, editProductCategorySelect].forEach(select => {
            select.innerHTML = '<option value="" selected>Ấn vào đây để chọn</option>'; // Clear existing options
            categories.forEach(category => {
                const option = document.createElement('option');
                option.value = category;
                option.textContent = category;
                select.appendChild(option);
            });
        });
    }

    // Fetch and display products on initial load
    fetchProducts();
});
