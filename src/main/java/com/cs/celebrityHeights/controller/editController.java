/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.celebrityHeights.controller;

import com.cs.celebrityHeights.ServiceLayer.CelebNotFoundException;
import com.cs.celebrityHeights.ServiceLayer.ServiceLayer;
import com.cs.celebrityHeights.models.Celeb;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Timothy Claussen
 */
@Controller
public class editController {
    
    @Autowired
    ServiceLayer service;
    
     @PostMapping("editTeacher")
     public String performEditTeacher(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("celebId"));
        Celeb celeb = new Celeb();
        try {
            celeb = service.getCelebById(id);
        } catch (CelebNotFoundException ex) {
            return "redirect:/error";
        }

        celeb.setCelebName(request.getParameter("celebName"));
        celeb.setTotalInches(Integer.parseInt(request.getParameter("totalInches")));
        celeb.setTotalInchesString(request.getParameter("totalInchesString"));
        
        service.editCeleb(celeb);
        
        return "redirect:/home";
    }
}
