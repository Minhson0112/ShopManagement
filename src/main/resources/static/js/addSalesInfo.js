document.addEventListener('DOMContentLoaded', function() {
    const addSalesButton = document.getElementById('add_sales_button');
    const addSalesDialog = document.getElementById('add_sales_dialog');
    const closeAddSalesButton = document.getElementById('close_add_sales_button');
    const decideAddSalesButton = document.getElementById('decide_add_sales_button');
    const closeInvoice = document.getElementById("close_invoice");
    const pageWrapper = document.getElementById('page_wrapper');
    const loadingDialog = document.getElementById('loading_dialog');
    const addSalesError = document.getElementById('add_sales_error');
    const selectClientName = document.getElementById('select_clienName');
    const inputClientName = document.getElementById('input_clienName');
    const titleSelect1 = document.getElementById('title_select1');
    const inputQuantity = document.getElementById('input_quantity');
    const inputAddress = document.getElementById('input_address');
    const invoice = document.getElementById("invoice");

    addSalesButton.addEventListener('click', function(event) {
        event.preventDefault();
        pageWrapper.style.display = 'flex';
        addSalesDialog.style.display = 'flex';
        fetchClientNames();
    });
    
    closeInvoice.addEventListener("click", function(event){
        event.preventDefault();
        pageWrapper.style.display = 'none';
        invoice.style.display = "none"
        location.reload(); 
    });

    closeAddSalesButton.addEventListener('click', function(event) {
        event.preventDefault();
        pageWrapper.style.display = 'none';
        addSalesDialog.style.display = 'none';
    });

    decideAddSalesButton.addEventListener('click', function(event) {
        event.preventDefault();
        addSalesError.style.display = 'none';

        const clientName = selectClientName.value.trim() || inputClientName.value.trim();
        const productName = titleSelect1.value.trim();
        const quantity = inputQuantity.value.trim();
        const address = inputAddress.value.trim();

        if (!productName || !clientName || !quantity || !address) {
            addSalesError.textContent = 'Vui lòng nhập đầy đủ thông tin.';
            addSalesError.style.display = 'block';
            return;
        }

        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        const salesData = {
            productName: productName,
            clientName: clientName,
            address: address,
            tradingQuantity: parseInt(quantity, 10)
        };

        pageWrapper.style.display = 'flex';
        loadingDialog.style.display = 'block';

        fetch('/api/salesInfo', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(salesData)
        })
        .then(response => {
            loadingDialog.style.display = 'none';
            if (!response.ok) {
                addSalesError.textContent = "Không Đủ số lượng hàng trong kho";
                addSalesError.style.display = 'block';
                return;
            }
            return response.json();
        })
        .then(data => {
            if(data){
                addSalesDialog.style.display = 'none';
                showInvoice(data,salesData);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            addSalesError.style.display = 'block';
        });
    });

    function fetchClientNames() {
        pageWrapper.style.display = 'flex';
        loadingDialog.style.display = 'block';

        fetch('/api/salesInfo', {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        })
        .then(response => {
            loadingDialog.style.display = 'none';
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            updateClientNameSelect(data);
        })
        .catch(error => {
            console.error('Error:', error);
            addSalesError.style.display = 'block';
        });
    }

    function updateClientNameSelect(sales) {
        selectClientName.innerHTML = '<option value="" selected>Chọn Tên khách Đã Từng Mua</option>';
        const clientNames = [...new Set(sales.map(sale => sale.clientName))];
        clientNames.forEach(clientName => {
            const option = document.createElement('option');
            option.value = clientName;
            option.textContent = clientName;
            selectClientName.appendChild(option);
        });
    }

    function showInvoice(data,salesData){
        const localDate = document.getElementById("local_date");
        const clientName = document.getElementById("client_name"); 
        const clientAddress = document.getElementById("client_address");
        const productName = document.getElementById("product_name");
        const productQuantity = document.getElementById("product_quantity");
        const price = document.getElementById("price");
        const paymentMethod = document.getElementById("payment_method_content");
        const endWord = document.getElementById("end_word");

        localDate.textContent = `${data.tradingDate}`;
        clientName.textContent = `Tên khách hàng: ${data.clientName}`;
        clientAddress.textContent = `Địa chỉ giao hàng: ${data.address}`;
        productName.textContent = "Tên sản phẩm: " + salesData.productName;
        productQuantity.textContent = `Số Lượng: ${data.tradingQuantity}`;
        price.textContent = `Thành tiền: ${data.price}円`;
        paymentMethod.textContent = `Chuyển khoản vào số tài khoản 123456 ngân hàng yuuchou tên hongnhung với số tiền ${data.price}円`;
        endWord.textContent = `Cảm ơn và hẹn gặp lại bạn ${data.clientName}, chúc bạn một ngày tốt lành.`

        invoice.style.display = "flex";
    }
});
