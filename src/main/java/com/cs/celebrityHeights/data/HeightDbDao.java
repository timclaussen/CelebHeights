/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.celebrityHeights.data;

import com.cs.celebrityHeights.models.Height;
import java.util.List;

/**
 *
 * @author Timothy Claussen
 */
public interface HeightDbDao {
    public List<Height> listAllHeights(); 
    
    public Height getHeightByTotalInches(int totalInch);
}
