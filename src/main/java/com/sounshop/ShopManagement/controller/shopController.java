package com.sounshop.ShopManagement.controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class shopController {
    @GetMapping(value = "/home")
    public String homePage(HttpServletRequest request, Model model) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken);
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

}
