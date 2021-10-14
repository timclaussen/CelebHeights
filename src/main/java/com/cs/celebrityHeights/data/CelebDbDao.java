/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.celebrityHeights.data;

import com.cs.celebrityHeights.models.Celeb;
import java.util.List;

/**
 *
 * @author Timothy Claussen
 */
public interface CelebDbDao {
    
    public List<Celeb> listAllCelebs();
    
    public Celeb getCelebById(int id);
    
    public Celeb addCeleb(Celeb celeb);
    
    public Celeb editCeleb(Celeb celeb);
    
    public void removeCelebById(int id);
}
