package com.sounshop.ShopManagement.controller;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.sounshop.ShopManagement.service.SalesInfoService;
import com.sounshop.ShopManagement.service.IncomeDetailsService;


@Controller
public class shopController {

    @Autowired
    private SalesInfoService salesInfoService;

    @Autowired
    private IncomeDetailsService incomeDetailsService;

    @GetMapping(value = "/home")
    public String homePage(HttpServletRequest request, Model model) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);
        model.addAttribute("today", formattedDate);

        int numberOfOrderToday = salesInfoService.numberOfOrderToday();
        model.addAttribute("numberOfOderToday", numberOfOrderToday);

        int orderDifference = salesInfoService.calculateOrderDifference();
        model.addAttribute("order", orderDifference);

        int priceDifference = salesInfoService.calculatePriceDifference();
        model.addAttribute("priceDifference", priceDifference);

        int priceToday = salesInfoService.priceToday();
        model.addAttribute("priceToday", priceToday);

        System.out.println("check home");
        return "home";
    }

    @GetMapping(value = "/storage")
    public String storage(HttpServletRequest request, Model model) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken);
        System.out.println("check Kho");

        return "storage";
    }
    
    @GetMapping(value = "/entryProduct")
    public String entryProduct(HttpServletRequest request, Model model) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken);
        System.out.println("check nhập hàng");

        return "entryProduct";
    }

    @GetMapping(value = "/sales")
    public String sales(HttpServletRequest request, Model model) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken);
        System.out.println("check bán hàng");

        return "sales";
    }

    @GetMapping(value = "/login")
    public String login(HttpServletRequest request, Model model) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken);
        System.out.println("check login");
        return "login";
    }

    @GetMapping(value = "/income")
    public String income(HttpServletRequest request, Model model) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken);

        int currentMonthorders = incomeDetailsService.monthOrders();
        model.addAttribute("currentMonthorders", currentMonthorders);

        int orderDifference = incomeDetailsService.calculateOrderDifference();
        model.addAttribute("orderDifference", orderDifference);

        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        model.addAttribute("currentMonth", currentMonth);


        int uriage = incomeDetailsService.uriageOfmonth();
        model.addAttribute("uriage", uriage);

        int revenue = incomeDetailsService.revenueOfMonth();
        model.addAttribute("revenue", revenue);

        String clientName = incomeDetailsService.topClient();
        model.addAttribute("clientName", clientName);

        int countPurchases = incomeDetailsService.countPurchases();
        model.addAttribute("countPurchases", countPurchases);
        System.out.println("check income");
        return "incomeDetails";
    }

}
