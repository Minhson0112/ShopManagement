document.addEventListener('DOMContentLoaded', function() {
    const titleSelect = document.getElementById('title_select');
    const titleSelect1 = document.getElementById('title_select1');
    const pageWrapper = document.getElementById('page_wrapper');
    const loadingDialog = document.getElementById('loading_dialog');
    const searchError = document.getElementById('search_error');
    const dayInput = document.getElementById('day_input');
    const dayInputBefore = document.getElementById('day_input_before');
    const dayInputAfter = document.getElementById('day_input_after');
    const searchButton = document.getElementById('search_button');

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

    function fetchProductNames() {
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
    }

    function fetchAllSalesInfo() {
        fetch('/api/salesInfo', {
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
    }

    function fetchSalesInfo(title, day, dayBefore, dayAfter) {
        const url = new URL('/api/salesInfo/search', window.location.origin);
        
        if (!title && !day && (!dayBefore || !dayAfter)) {
            location.reload();
        }

        if (title) {
            url.searchParams.append('title', title);
        }
        if (day) {
            url.searchParams.append('day', day);
        } else {
            if (dayBefore) {
                url.searchParams.append('dayBefore', dayBefore);
            }
            if (dayAfter) {
                url.searchParams.append('dayAfter', dayAfter);
            }
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

    function updateTable(sales) {
        const tbody = document.getElementById('data_list_items');
        tbody.innerHTML = ''; // Clear existing data

        sales.forEach(sale => {
            const row = document.createElement('tr');

            const idCell = document.createElement('td');
            idCell.textContent = sale.tradingId;
            row.appendChild(idCell);

            const nameCell = document.createElement('td');
            nameCell.textContent = sale.productName;
            row.appendChild(nameCell);

            const clientCell = document.createElement('td');
            clientCell.textContent = sale.clientName;
            row.appendChild(clientCell);

            const addressCell = document.createElement('td');
            addressCell.textContent = sale.address;
            row.appendChild(addressCell);

            const quantityCell = document.createElement('td');
            quantityCell.textContent = sale.tradingQuantity;
            row.appendChild(quantityCell);

            const priceCell = document.createElement('td');
            priceCell.textContent = sale.price;
            row.appendChild(priceCell);

            const dateCell = document.createElement('td');
            dateCell.textContent = sale.tradingDate;
            row.appendChild(dateCell);

            tbody.appendChild(row);
        });
    }

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

        if (!title && !day && !dayBefore && !dayAfter) {
            searchError.style.display = 'block';
            return;
        }

        if (dayBefore && dayAfter && new Date(dayBefore) > new Date(dayAfter)) {
            searchError.style.display = 'block';
            return;
        }

        fetchSalesInfo(title, day, dayBefore, dayAfter);
    });

    fetchProductNames();
    fetchAllSalesInfo(); // Fetch all sales info on initial load
});
