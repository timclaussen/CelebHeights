/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.celebrityHeights.controller;

import com.cs.celebrityHeights.ServiceLayer.CelebNotFoundException;
import com.cs.celebrityHeights.ServiceLayer.ServiceLayer;
import com.cs.celebrityHeights.models.Celeb;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Timothy Claussen
 */
@Controller
public class indexController {

    @Autowired
    ServiceLayer service;

    @GetMapping("/home")
    public String loadOnHome(Model model) {
        //populate carousel
        List<Celeb> carouselCelebs = service.populateCarousel();
        model.addAttribute("carouselCelebs", carouselCelebs);
        //make full list underneath
        List<Celeb> allCelebs = service.listAllCelebs();
        model.addAttribute("allCelebs", allCelebs);

        return "home";
    }

    @PostMapping("searchCeleb")
    public String searchCeleb(HttpServletRequest request) {
        String celebName = request.getParameter("name");

        try {
            service.searchForCelebHeight(celebName);
        } catch (CelebNotFoundException ex) {
            return "redirect:/error";
        }

        return "redirect:/home";
    }

    @GetMapping("error")
    public String outputError(Model model) {
        String errorMsg = "Search invalid";
        model.addAttribute("err", errorMsg);
        return "redirect:/error";

    }

    @GetMapping("deleteCeleb")
    public String deleteCeleb(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        service.removeCelebById(id);

        return "redirect:/home";
    }

    @GetMapping("editCeleb")
    public String editCeleb(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Celeb celeb = new Celeb();
        try {
            celeb = service.getCelebById(id);
        } catch (CelebNotFoundException ex) {
            return "redirect:/error";
        }

        List<Celeb> allCelebs = service.listAllCelebs();
        model.addAttribute("allCelebs", allCelebs);

        model.addAttribute("celeb", celeb);
        return "editCeleb";
    }
}
