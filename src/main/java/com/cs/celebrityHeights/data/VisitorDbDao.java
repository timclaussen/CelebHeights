/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.celebrityHeights.data;

import com.cs.celebrityHeights.models.Visitor;
import java.util.List;

/**
 *
 * @author Timothy Claussen
 */
public interface VisitorDbDao {
    
    public List<Visitor> getAllVisitors();
    
    public Visitor getVisitorById(int id);
    
    public Visitor addVisitor(Visitor visit);
    
    public Visitor editVisitor(Visitor visit);
    
    public void removeVisitorById(int id);
    
}
