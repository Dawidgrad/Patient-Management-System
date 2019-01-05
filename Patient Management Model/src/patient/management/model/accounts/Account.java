/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.model.accounts;

/**
 *
 * @author Davio
 */
public abstract class Account {
    private String name;
    private String surname;
    private String address;
    private String idNumber;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Account(String name, String surname, String address, String idNumber)
    {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.idNumber = idNumber;
    }
}
