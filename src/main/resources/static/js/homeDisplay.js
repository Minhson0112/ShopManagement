document.addEventListener('DOMContentLoaded', function() {
    const price = document.getElementById("priceDifference");
    const order = document.getElementById("orderDifference");
    const priceToday = document.getElementById("price_today");

    const orderDifference = parseInt(order.innerText, 10);
    const priceDifference = parseInt(price.innerText, 10);
    const INTpriceToday = parseInt(priceToday.innerText, 10);

    if (orderDifference > 0) {
        order.style.color = "green";
        order.textContent = "+" + orderDifference;
    } else if (orderDifference < 0) {
        order.style.color = "red";
        order.textContent = orderDifference;
    } else {
        order.textContent = orderDifference;
    }

    if (priceDifference > 0) {
        price.style.color = "green";
        price.textContent = "+" + priceDifference + "円";
    } else if (priceDifference < 0) {
        price.style.color = "red";
        price.textContent = priceDifference + "円";
    } else {
        price.textContent = priceDifference + "円";
    }

    priceToday.textContent = INTpriceToday + "円";
});
