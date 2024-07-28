document.addEventListener('DOMContentLoaded', function() {
    const addEntryProductButton = document.getElementById('add_entry_product_button');
    const addEntryProductDialog = document.getElementById('add_entry_product_dialog');
    const closeAddEntryProductButton = document.getElementById('close_add_entry_product_button');
    const decideAddEntryProductButton = document.getElementById('decide_add_entry_product_button');
    const addEntryProductError = document.getElementById('add_entry_product_error');
    const pageWrapper = document.getElementById('page_wrapper');
    const loadingDialog = document.getElementById('loading_dialog');
    
    // Mở dialog nhập hàng khi nhấn nút thêm
    addEntryProductButton.addEventListener('click', function(event) {
        event.preventDefault();
        addEntryProductDialog.style.display = 'flex';
        pageWrapper.style.display = 'flex';
    });

    // Đóng dialog nhập hàng khi nhấn nút hủy
    closeAddEntryProductButton.addEventListener('click', function(event) {
        event.preventDefault();
        addEntryProductDialog.style.display = 'none';
        pageWrapper.style.display = 'none';
        addEntryProductError.style.display = 'none';
    });

    // Xử lý logic khi xác nhận nhập hàng
    decideAddEntryProductButton.addEventListener('click', function(event) {
        event.preventDefault();
        const titleSelect = document.getElementById('title_select1').value;
        const inputQuantity = document.getElementById('input_quantity').value;

        if (!titleSelect || !inputQuantity) {
            addEntryProductError.style.display = 'block';
            return;
        }

        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        const entryProductData = {
            productName: titleSelect,
            quantity: inputQuantity
        };

        pageWrapper.style.display = 'flex';
        loadingDialog.style.display = 'block';

        fetch('/api/entryProduct', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(entryProductData)
        })
        .then(response => {
            pageWrapper.style.display = 'none';
            loadingDialog.style.display = 'none';
            if (!response.ok) {
                addEntryProductError.style.display = 'block';
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Success:', data);
            location.reload();
        })
        .catch(error => {
            console.error('Error:', error);
            addEntryProductError.style.display = 'block';
        });
    });
});
