document.addEventListener('DOMContentLoaded', function() {
    const pageWrapper = document.getElementById('page_wrapper');
    const removeProductDialog = document.getElementById('remove_product_dialog');
    const closeDialogButton = document.getElementById('close_remove_product_button');
    const submitRemoveForm = document.getElementById('decide_remove_product_button');
    const removeProductError = document.getElementById('remove_product_error');
    let productId = null;

    function openRemoveDialog(button) {
        let tr = button.closest('tr');
        productId = tr.querySelector('td:nth-child(1)').innerText;
        const productName = tr.querySelector('td:nth-child(2)').innerText;
        const removeDialogMsg = document.getElementById('remove_dialog_msg');
        removeDialogMsg.innerHTML = `Bạn có chắc chắn muốn xóa sản phẩm "${productName}"? <br> Hành động này không thể hoàn tác.`;
        console.log("selected product id: ", productId);

        pageWrapper.style.display = 'flex';
        removeProductDialog.style.display = 'flex';
    }
    
    function attachRemoveEvents() {
        document.querySelectorAll('.remove_product_button').forEach(function(button) {
            button.addEventListener('click', function() {
                openRemoveDialog(button);
            });
        });
    }
    
    attachRemoveEvents();
    document.addEventListener('tableUpdated', attachRemoveEvents);

    closeDialogButton.addEventListener("click", function(event) {
        event.preventDefault();
        pageWrapper.style.display = "none";
        removeProductDialog.style.display = "none";
    });

    submitRemoveForm.addEventListener("click", function(event) {
        event.preventDefault();
        
        console.log('Product ID:', productId);
        
        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
        console.log('CSRF Token:', csrfToken);
        console.log('CSRF Header:', csrfHeader);

        fetch(`/api/productInfo/${productId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                [csrfHeader]: csrfToken
            }
        })
        .then(response => {
            if (!response.ok) {
                removeProductError.textContent = "Không thể xoá sản phẩm còn tồn kho";
                removeProductError.style.display = 'block';
                return Promise.reject('Error deleting product'); // Trả về một promise bị từ chối để dừng chuỗi promise
            } else {
                return Promise.resolve(); // Trả về một promise rỗng nếu phản hồi thành công và không có nội dung
            }
        })
        .then(() => {
            console.log('Success');
            location.reload();
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
});
