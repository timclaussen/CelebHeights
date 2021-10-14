/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.celebrityHeights.ServiceLayer;

import com.cs.celebrityHeights.models.Celeb;
import com.cs.celebrityHeights.models.Visitor;
import java.util.List;

/**
 *
 * @author Timothy Claussen
 */
public interface ServiceLayer {

    //validate hieght input
    public int validateHeightInput(String enteredString);

    //Search for celeb name Google
    public Celeb searchForCelebHeight(String celebname) throws CelebNotFoundException ;

    //validate celeb page - has correct name and height
//    public boolean isCelebPageValid()
    //get height, name, URL and insert into Celeb table
    public Celeb insertSearchedCelebToTable(Celeb celeb);

    //Grab 8 random celebs
    public List<Celeb> populateCarousel();

    //total inches to feet'inch"
    public String inchesToFeetInches(int inches);

    public int cmToInches(int cm);

    //Dao stuff 
    //visitorDao
    public List<Visitor> getAllVisitors();

    public Visitor getVisitorById(int id);

    public Visitor addVisitor(Visitor visit);

    public Visitor editVisitor(Visitor visit);

    public void removeVisitorById(int id);

    //Celeb Dao
    public List<Celeb> listAllCelebs();

    public Celeb getCelebById(int id) throws CelebNotFoundException;

    public Celeb addCeleb(Celeb celeb);

    public Celeb editCeleb(Celeb celeb);

    public void removeCelebById(int id);
}
