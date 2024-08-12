document.addEventListener('DOMContentLoaded', function() {
    const price = document.getElementById("priceDifference");
    const order = document.getElementById("orderDifference");

    const orderDifference = parseInt(order.innerText, 10);
    const priceDifference = parseInt(price.innerText, 10);

    if (orderDifference > 0) {
        order.style.color = "green";
        order.textContent = "+" + orderDifference;
    } else {
        order.style.color = "red";
        order.textContent = orderDifference.toString();
    }

    if (priceDifference > 0) {
        price.style.color = "green";
        price.textContent = "+" + priceDifference;
    } else {
        price.style.color = "red";
        price.textContent = priceDifference.toString();
    }
});