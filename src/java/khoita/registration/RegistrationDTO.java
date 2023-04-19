/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoita.registration;

/**
 *
 * @author Fstore
 */
public class RegistrationDTO {
    private String account;
    private String password;
    private String fullname;
    private boolean role ;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String account, String password, String fullname) {
        this.account = account;
        this.password = password;
        this.fullname = fullname;
    }


    
    public RegistrationDTO(String account, String password, String fullname, boolean role) {
        this.account = account;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

   

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return  "Account=" + account + ", Password=" + password + ", Fullname=" + fullname + ", Role=" + role;
    }

    
    
}
