/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.celebrityHeights.ServiceLayer;

/**
 *
 * @author Timothy Claussen
 */
public class CelebNotFoundException extends Exception{
    public CelebNotFoundException(String message) {
        super(message);
    }
    
    public CelebNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}