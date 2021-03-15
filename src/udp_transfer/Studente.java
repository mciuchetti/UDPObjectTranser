/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp_transfer;

import java.io.Serializable;

/**
 *
 * @author Monica Ciuchetti
 */

public class Studente implements Serializable {
    private static final long serialVersionUID = 1;
    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    private int id;
    private String name;
    private String email;

public Studente(int id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
}

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String toString() {
    return "Id = " + BLUE + getId() + RESET + " Nome e Cognome = " + BLUE + getName() + RESET + " Email = " + BLUE + getEmail() + RESET;
}
}
