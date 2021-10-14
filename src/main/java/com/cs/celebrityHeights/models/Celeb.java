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
import javax.persistence.Transient;

/**
 *
 * @author Timothy Claussen
 */
@Entity
@Table(name = "Celeb")
public class Celeb {
    //celebId
    private @Id @GeneratedValue int celebId;
    //totalInches
    private int totalInches;
    private String totalInchesString;
    private String celebName;
    //date time searched
    private LocalDateTime dateSearched;
    //wikiurl
    private String searchUrl;
    //Photo
    @Transient
    private String photoUrl;

    public int getCelebId() {
        return celebId;
    }

    public void setCelebId(int celebId) {
        this.celebId = celebId;
    }

    public int getTotalInches() {
        return totalInches;
    }

    public void setTotalInches(int totalInches) {
        this.totalInches = totalInches;
    }

    public LocalDateTime getDateSearched() {
        return dateSearched;
    }

    public void setDateSearched(LocalDateTime dateSearched) {
        this.dateSearched = dateSearched;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTotalInchesString() {
        return totalInchesString;
    }

    public void setTotalInchesString(String totalInchesString) {
        this.totalInchesString = totalInchesString;
    }

    public String getCelebName() {
        return celebName;
    }

    public void setCelebName(String celebName) {
        this.celebName = celebName;
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.celebId;
        hash = 73 * hash + this.totalInches;
        hash = 73 * hash + Objects.hashCode(this.totalInchesString);
        hash = 73 * hash + Objects.hashCode(this.celebName);
        hash = 73 * hash + Objects.hashCode(this.dateSearched);
        hash = 73 * hash + Objects.hashCode(this.searchUrl);
        hash = 73 * hash + Objects.hashCode(this.photoUrl);
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
        final Celeb other = (Celeb) obj;
        if (this.celebId != other.celebId) {
            return false;
        }
        if (this.totalInches != other.totalInches) {
            return false;
        }
        if (!Objects.equals(this.totalInchesString, other.totalInchesString)) {
            return false;
        }
        if (!Objects.equals(this.celebName, other.celebName)) {
            return false;
        }
        if (!Objects.equals(this.searchUrl, other.searchUrl)) {
            return false;
        }
        if (!Objects.equals(this.photoUrl, other.photoUrl)) {
            return false;
        }
        if (!Objects.equals(this.dateSearched, other.dateSearched)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Celeb{" + "celebId=" + celebId + ", totalInches=" + totalInches + ", totalInchesString=" + totalInchesString + ", celebName=" + celebName + ", dateSearched=" + dateSearched + ", searchUrl=" + searchUrl + ", photoUrl=" + photoUrl + '}';
    }

   

    
    
    
    
    
}
