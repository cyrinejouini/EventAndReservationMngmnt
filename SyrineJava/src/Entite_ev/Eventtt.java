/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite_ev;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author amine
 */
public class Eventtt {
    private int id_event;
    private Date   date;
    private String nom_event;
    private String description;
    
    private int nb_place;
    private String image;
   

    public Eventtt() {
    }

    public Eventtt(Date date, String nom_event, String description, int nb_place, String image) {
        this.date = date;
        this.nom_event = nom_event;
        this.description = description;
        this.nb_place = nb_place;
        this.image = image;
        
    }

    public Eventtt(int id_event, Date date, String nom_event, String description, int nb_place, String image) {
        this.id_event = id_event;
        this.date = date;
        this.nom_event = nom_event;
        this.description = description;
        this.nb_place = nb_place;
        this.image = image;
    }


 

   

    

    

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

  

    @Override
    public String toString() {
        return "Eventtt{" + "id_event=" + id_event + ", date=" + date + ", nom_event=" + nom_event + ", description=" + description + ", nb_place=" + nb_place + ", image=" + image + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Eventtt other = (Eventtt) obj;
        if (this.id_event != other.id_event) {
            return false;
        }
        if (this.nb_place != other.nb_place) {
            return false;
        }
        
        if (!Objects.equals(this.nom_event, other.nom_event)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
   
    
}
