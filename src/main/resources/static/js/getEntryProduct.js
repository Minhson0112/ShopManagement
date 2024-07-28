document.addEventListener('DOMContentLoaded', function() {
    const searchButton = document.getElementById('search_button');
    const searchError = document.getElementById('search_error');
    const titleSelect = document.getElementById('title_select');
    const titleSelect1 = document.getElementById('title_select1');
    const dayInput = document.getElementById('day_input');
    const dayInputBefore = document.getElementById('day_input_before');
    const dayInputAfter = document.getElementById('day_input_after');
    const pageWrapper = document.getElementById('page_wrapper');
    const loadingDialog = document.getElementById('loading_dialog');

    document.querySelector('.swich_type_date').addEventListener('click', function(event) {
        event.preventDefault();
        const module1 = document.getElementById('module1');
        const module2 = document.getElementById('module2');

        if (module1.style.display === 'none') {
            module1.style.display = 'flex';
            module2.style.display = 'none';
        } else {
            module1.style.display = 'none';
            module2.style.display = 'flex';
        }
    });

    searchButton.addEventListener('click', function(event) {
        event.preventDefault();
        searchError.style.display = 'none';

        const title = titleSelect.value.trim();
        const day = dayInput.value.trim();
        const dayBefore = dayInputBefore.value.trim();
        const dayAfter = dayInputAfter.value.trim();

        if (!title && !day && (!dayBefore || !dayAfter)) {
            location.reload();
        }

        if (dayBefore && dayAfter && dayBefore > dayAfter) {
            searchError.style.display = 'block';
            return;
        }

        const url = new URL('/api/entryProduct/search', window.location.origin);
        if (title) {
            url.searchParams.append('title', title);
        }
        if (day) {
            url.searchParams.append('day', day);
        }
        if (dayBefore && dayAfter) {
            url.searchParams.append('dayBefore', dayBefore);
            url.searchParams.append('dayAfter', dayAfter);
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
    });

    function updateTable(entries) {
        const tbody = document.getElementById('data_list_items');
        tbody.innerHTML = ''; // Clear existing data

        entries.forEach(entry => {
            const row = document.createElement('tr');

            const idCell = document.createElement('td');
            idCell.textContent = entry.entryId;
            row.appendChild(idCell);

            const nameCell = document.createElement('td');
            nameCell.textContent = entry.productName;
            row.appendChild(nameCell);

            const categoryCell = document.createElement('td');
            categoryCell.textContent = entry.category;
            row.appendChild(categoryCell);

            const quantityCell = document.createElement('td');
            quantityCell.textContent = entry.quantity;
            row.appendChild(quantityCell);

            const priceCell = document.createElement('td');
            priceCell.textContent = entry.totalPrice;
            row.appendChild(priceCell);

            const dateCell = document.createElement('td');
            dateCell.textContent = entry.entryDate;
            row.appendChild(dateCell);

            tbody.appendChild(row);
        });

        const event = new Event('tableUpdated');
        document.dispatchEvent(event);
    }

    function updateTitleSelect(products) {
        // Clear existing options in both select elements
        [titleSelect, titleSelect1].forEach(select => {
            select.innerHTML = '<option value="" selected>Ấn vào đây để chọn</option>';
        });

        // Add options to both select elements
        products.forEach(product => {
            [titleSelect, titleSelect1].forEach(select => {
                const option = document.createElement('option');
                option.value = product.productName;
                option.textContent = product.productName;
                select.appendChild(option);
            });
        });
    }

    // Fetch initial data for title select
    fetch('/api/productInfo', {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        updateTitleSelect(data);
    })
    .catch(error => {
        console.error('Error:', error);
    });

    // Fetch initial data for entry products
    fetch('/api/entryProduct', {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        updateTable(data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
});
