/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.celebrityHeights.models;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Timothy Claussen
 */
@Entity
@Table(name = "Visitor")
public class Visitor {
    private @Id @GeneratedValue int visitorId;
    private int totalInches;
    private LocalDateTime entryTime;
//    private boolean deleted;

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int userId) {
        this.visitorId = userId;
    }

    public int getTotalInches() {
        return totalInches;
    }

    public void setTotalInches(int totalInches) {
        this.totalInches = totalInches;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.visitorId;
        hash = 29 * hash + this.totalInches;
        hash = 29 * hash + Objects.hashCode(this.entryTime);
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
        final Visitor other = (Visitor) obj;
        if (this.visitorId != other.visitorId) {
            return false;
        }
        if (this.totalInches != other.totalInches) {
            return false;
        }
        if (!Objects.equals(this.entryTime, other.entryTime)) {
            return false;
        }
        return true;
    }

    
    

    @Override
    public String toString() {
        return "Visitor{" + "visitorId=" + visitorId + ", totalInches=" + totalInches + ", entryTime=" + entryTime + '}';
    }
    
    
}
