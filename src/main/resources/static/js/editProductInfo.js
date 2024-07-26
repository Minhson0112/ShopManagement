document.addEventListener('DOMContentLoaded', function() {
    const pageWrapper = document.getElementById('page_wrapper');
    const editProductDialog = document.getElementById('edit_product_dialog');
    const closeDialogButton = document.getElementById('close_edit_product_button');
    const submitEditFormButton = document.getElementById('decide_edit_product_button');
    const editProductCategorySelect = document.getElementById('edit_product_category_select');
    const editError = document.getElementById('edit_product_error');
    const titleInputError = document.getElementById("edit_product_title_input_error");
    let currentProductId = null;

    function openEditDialog(button) {
        let tr = button.closest('tr');
        currentProductId = tr.querySelector('td:nth-child(1)').innerText;
        const productName = tr.querySelector('td:nth-child(2)').innerText;
        const category = tr.querySelector('td:nth-child(3)').innerText;
        const entryPrice = tr.querySelector('td:nth-child(4)').innerText;
        const sellPrice = tr.querySelector('td:nth-child(5)').innerText;

        document.getElementById("edit_product_title_input").value = productName;
        editProductCategorySelect.value = category;
        document.getElementById("edit_category").value = '';
        document.getElementById("edit_entry_price").value = entryPrice;
        document.getElementById("edit_sell_price").value = sellPrice;

        pageWrapper.style.display = 'flex';
        editProductDialog.style.display = 'flex';
    }

    function attachEditEvents() {
        document.querySelectorAll('.edit_product_button').forEach(function(button) {
            button.addEventListener('click', function() {
                openEditDialog(button);
            });
        });
    }

    attachEditEvents();
    document.addEventListener('tableUpdated', attachEditEvents);

    closeDialogButton.addEventListener("click", function(event) {
        event.preventDefault();
        pageWrapper.style.display = "none";
        editProductDialog.style.display = "none";
    });

    submitEditFormButton.addEventListener("click", function(event) {
        event.preventDefault();
        editError.style.display = 'none';
        titleInputError.style.display = 'none';

        const productName = document.getElementById("edit_product_title_input").value.trim();
        const category = editProductCategorySelect.value.trim() || document.getElementById("edit_category").value.trim();
        const entryPrice = document.getElementById("edit_entry_price").value.trim();
        const sellPrice = document.getElementById("edit_sell_price").value.trim();

        if (!productName || !category || !entryPrice || !sellPrice) {
            editError.textContent = 'Vui lòng nhập đầy đủ thông tin.';
            editError.style.display = 'block';
            return;
        }

        const payload = {
            productName: productName,
            category: category,
            entryPrice: entryPrice,
            sellPrice: sellPrice
        };

        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        fetch(`/api/productInfo/${currentProductId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(payload)
        })
        .then(response => {
            if (!response.ok) {
                editError.textContent = 'Lưu Thất Bại';
                editError.style.display = 'block';
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Success:', data);
            pageWrapper.style.display = 'none';
            editProductDialog.style.display = 'none';
            location.reload();
        })
        .catch(error => {
            console.error('Error:', error);
            editError.textContent = 'Lưu Thất Bại';
            editError.style.display = 'block';
        });
    });
});
