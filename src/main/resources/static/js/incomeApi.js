document.addEventListener('DOMContentLoaded', function() {
    const searchButton = document.getElementById('search_button');
    const monthSelect = document.getElementById('month_select');
    const searchError = document.getElementById('search_error');
    const revenueElement = document.getElementById('chart_revenue');
    const profitElement = document.getElementById('chart_profit');
    const salesElement = document.getElementById('number_of_sales');
    const salesPieChartContainer = document.getElementById('salesPieChart_container');
    const revenueAreaChartContainer = document.getElementById('revenueAreaChart_container');
    const ctxPie = document.getElementById('salesPieChart').getContext('2d');
    const ctxArea = document.getElementById('revenueAreaChart').getContext('2d');
    let salesPieChart;
    let revenueAreaChart;

    searchButton.addEventListener('click', function(event) {
        event.preventDefault(); // Ngăn không cho form submit truyền thống

        const selectedMonth = monthSelect.value;
        if (selectedMonth === "") {
            searchError.style.display = 'block';
            salesPieChartContainer.style.display = "none";
            revenueAreaChartContainer.style.display = "none";
            searchError.textContent = 'Vui lòng chọn một tháng!';
            return;
        }

        // Gửi yêu cầu API để lấy dữ liệu cho biểu đồ tròn
        fetch(`/api/income/salesProduct?month=${selectedMonth}`)
            .then(response => {
                if (response.status === 204) {
                    searchError.style.display = 'block';
                    salesPieChartContainer.style.display = "none";
                    searchError.textContent = 'Không tìm thấy dữ liệu.';
                    return null;
                } else if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                if (data) {
                    console.log("Sales Product Data:", data);
                    searchError.style.display = 'none';

                    const labels = data.map(item => item.productName);
                    const salesData = data.map(item => item.totalSales);
                    const backgroundColors = data.map(() => getRandomColor());

                    // Xóa biểu đồ cũ nếu tồn tại
                    if (salesPieChart) {
                        salesPieChart.destroy();
                    }

                    // Tạo biểu đồ mới
                    salesPieChart = new Chart(ctxPie, {
                        type: 'pie',
                        data: {
                            labels: labels,
                            datasets: [{
                                data: salesData,
                                backgroundColor: backgroundColors
                            }]
                        },
                        options: {
                            responsive: true,
                            maintainAspectRatio: false,
                            plugins: {
                                legend: {
                                    position: 'bottom',
                                }
                            }
                        }
                    });

                    salesPieChartContainer.style.display = 'flex';
                }
            })
            .catch(error => {
                console.error('There has been a problem with your fetch operation:', error);
                searchError.style.display = 'block';
                searchError.textContent = 'Đã xảy ra lỗi khi tìm kiếm. Vui lòng thử lại sau.';
            });
        





        // Gửi yêu cầu API để lấy dữ liệu thu nhập (incomeInfo)
        fetch(`/api/income/incomeInfo?month=${selectedMonth}`)
            .then(response => {
                if (response.status === 204) {
                    searchError.style.display = 'block';
                    salesPieChartContainer.style.display = "none";
                    revenueAreaChartContainer.style.display = "none";
                    searchError.textContent = 'Không tìm thấy dữ liệu.';
                    return null;
                } else if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                if (data) {
                    console.log("Income Info Data:", data);
                    

                    // Cập nhật nội dung của các thẻ HTML với dữ liệu từ API
                    revenueElement.textContent = `Doanh Thu: ${data.revenue}`;
                    profitElement.textContent = `Lãi Ròng: ${data.profit}`;
                    salesElement.textContent = `Số đơn hàng: ${data.sales}`;

                    salesPieChartContainer.style.display = 'flex';
                    revenueAreaChartContainer.style.display = 'flex';
                }
            })
            .catch(error => {
                console.error('There has been a problem with your fetch operation:', error);
                searchError.style.display = 'block';
                searchError.textContent = 'Đã xảy ra lỗi khi tìm kiếm. Vui lòng thử lại sau.';
            });
        
        // Gửi yêu cầu API để lấy dữ liệu cho biểu đồ vùng
        fetch(`/api/income/revenueByPeriod?month=${selectedMonth}`)
            .then(response => {
                if (response.status === 204) {
                    searchError.style.display = 'block';
                    searchError.textContent = 'Không tìm thấy dữ liệu.';
                    return null;
                } else if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                if (data) {
                    console.log("Revenue Period Data:", data);
                    

                    const labels = data.map(item => item.period);
                    const revenueData = data.map(item => item.revenue);
                    const profitData = data.map(item => item.profit);

                    // Xóa biểu đồ cũ nếu tồn tại
                    if (revenueAreaChart) {
                        revenueAreaChart.destroy();
                    }

                    // Tạo biểu đồ mới với hai datasets
                    revenueAreaChart = new Chart(ctxArea, {
                        type: 'line',
                        data: {
                            labels: labels,
                            datasets: [
                                {
                                    label: `Doanh thu`,
                                    data: revenueData,
                                    fill: true,
                                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                                    borderColor: 'rgba(54, 162, 235, 1)',
                                    tension: 0.4
                                },
                                {
                                    label: `Lãi ròng`,
                                    data: profitData,
                                    fill: true,
                                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                    borderColor: 'rgba(75, 192, 192, 1)',
                                    tension: 0.4
                                }
                            ]
                        },
                        options: {
                            responsive: true,
                            maintainAspectRatio: false,
                            scales: {
                                y: {
                                    beginAtZero: true
                                }
                            },
                            plugins: {
                                legend: {
                                    position: 'bottom',
                                }
                            }
                        }
                    });

                    revenueAreaChartContainer.style.display = 'flex';
                }
            })
            .catch(error => {
                console.error('There has been a problem with your fetch operation:', error);
                searchError.style.display = 'block';
                searchError.textContent = 'Đã xảy ra lỗi khi tìm kiếm. Vui lòng thử lại sau.';
            });
    });

    // Hàm tạo màu ngẫu nhiên
    function getRandomColor() {
        const letters = '0123456789ABCDEF';
        let color = '#';
        for (let i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }
});
