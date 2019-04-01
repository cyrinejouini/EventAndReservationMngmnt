/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite_ev;

/**
 *
 * @author amine
 */
public class Ligne_Res {
    private int id_res;
    private int event_id;
    private int quantite;
     private int res_id;
     static int id_user=1;

    public Ligne_Res() {
    }

    public Ligne_Res(int id_res, int event_id, int quantite, int res_id) {
        this.id_res = id_res;
        this.event_id = event_id;
        this.quantite = quantite;
        this.res_id = res_id;
    }

    public Ligne_Res(int event_id, int quantite, int res_id) {
        this.event_id = event_id;
        this.quantite = quantite;
        this.res_id = res_id;
    }

   

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

    @Override
    public String toString() {
        return "Ligne_Res{" + "id_res=" + id_res + ", event_id=" + event_id + ", quantite=" + quantite + ", res_id=" + res_id + '}';
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
        final Ligne_Res other = (Ligne_Res) obj;
        if (this.id_res != other.id_res) {
            return false;
        }
        if (this.event_id != other.event_id) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (this.res_id != other.res_id) {
            return false;
        }
        return true;
    }
     
}
