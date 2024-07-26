document.addEventListener('DOMContentLoaded', function() {
    const addProductButton = document.getElementById("add_product_button");
    const pageWrapper = document.getElementById("page_wrapper");
    const addProductDialog = document.getElementById("add_product_dialog");
    const closeDialogButton = document.getElementById("close_add_product_button");
    const submitAddForm = document.getElementById("decide_add_product_button");
    const addProductCategorySelect = document.getElementById("add_product_category_select");
    const addProductError = document.getElementById('add_product_error');
    const titleInputError = document.getElementById("add_product_title_input_error");
    const inputCategory = document.getElementById("input_category");
    
    addProductButton.addEventListener("click", function(event) {
        event.preventDefault();
        pageWrapper.style.display = "flex";
        addProductDialog.style.display = "flex";
        
    });

    closeDialogButton.addEventListener("click", function(event) {
        event.preventDefault();
        pageWrapper.style.display = "none";
        addProductDialog.style.display = "none";
    });

    submitAddForm.addEventListener("click", function(event) {
        event.preventDefault();
        const titleInput = document.getElementById("add_product_title_input").value;
        let selectedCategory = addProductCategorySelect.value;
        if (selectedCategory === "") {
            selectedCategory = inputCategory.value.trim();
            if (selectedCategory === "" || selectedCategory === null){
                addProductError.textContent = "Chủng loại không được bỏ trống"
                addProductError.style.display = 'block';
            }
        }
        const entryPrice = document.getElementById("input_entry_price").value;
        const sellPrice = document.getElementById("input_sell_price").value;
        
        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
        
        const nameRegex = /^.{1,200}$/;
        if (!nameRegex.test(titleInput)) {
            titleInputError.style.display = 'block';
            return;
        }
        
        const payload = {
            productName: titleInput,
            category: selectedCategory,
            entryPrice: entryPrice,
            sellPrice: sellPrice,
            addDate: new Date().toISOString().slice(0, 10),
            isDelete: false
        };
        
        fetch('/api/productInfo', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(payload)
        })
        .then(response => {
            if (!response.ok) {
                addProductError.style.display = "block";
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Success:', data);
            pageWrapper.style.display = "none";
            addProductDialog.style.display = "none";
            location.reload();
        })
        .catch(error => {
            console.error('Error:', error);
            addProductError.style.display = 'block';
        });
    });
});