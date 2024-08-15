document.addEventListener('DOMContentLoaded', function() {
    const order = document.getElementById("orderDifference");
    const revenue = document.getElementById("revenue");
    const uriage = document.getElementById("uriage");

    const orderDifference = parseInt(order.innerText, 10);
    const INTrevenue = parseInt(revenue.innerText, 10);
    const INTuriage = parseInt(uriage.innerText, 10);

    if (orderDifference > 0) {
        order.style.color = "green";
        order.textContent = "+" + orderDifference;
    } else if (orderDifference < 0) {
        order.style.color = "red";
        order.textContent = orderDifference;
    } else {
        order.textContent = orderDifference;
    }

    revenue.textContent = INTrevenue + "円";
    uriage.textContent = INTuriage + "円";

    
})