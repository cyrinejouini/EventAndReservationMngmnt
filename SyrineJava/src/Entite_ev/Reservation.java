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
public class Reservation {
    private int id_res;
    private int user_id;
    private int id_event;
    private int num;

    public Reservation() {
    }

    public Reservation(int id_res, int user_id, int id_event, int num) {
        this.id_res = id_res;
        this.user_id = user_id;
        this.id_event = id_event;
        this.num = num;
    }

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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
        final Reservation other = (Reservation) obj;
        if (this.id_res != other.id_res) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (this.id_event != other.id_event) {
            return false;
        }
        if (this.num != other.num) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_res=" + id_res + ", user_id=" + user_id + ", id_event=" + id_event + ", num=" + num + '}';
    }
    
}
