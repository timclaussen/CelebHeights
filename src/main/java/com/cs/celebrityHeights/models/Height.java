/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.celebrityHeights.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Timothy Claussen
 */
@Entity
@Table(name = "Height")
public class Height {
    private @Id int totalInches;
    private int feet;
    private int remainingInches;

    public int getTotalInches() {
        return totalInches;
    }

    public void setTotalInches(int totalInches) {
        this.totalInches = totalInches;
    }

    public int getFeet() {
        return feet;
    }

    public void setFeet(int feet) {
        this.feet = feet;
    }

    public int getRemainingInches() {
        return remainingInches;
    }

    public void setRemainingInches(int remainingInches) {
        this.remainingInches = remainingInches;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.totalInches;
        hash = 37 * hash + this.feet;
        hash = 37 * hash + this.remainingInches;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Height other = (Height) obj;
        if (this.totalInches != other.totalInches) {
            return false;
        }
        if (this.feet != other.feet) {
            return false;
        }
        if (this.remainingInches != other.remainingInches) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Height{" + "totalInches=" + totalInches + ", feet=" + feet + ", remainingInches=" + remainingInches + '}';
    }
    
    
}
