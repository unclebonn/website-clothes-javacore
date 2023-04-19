/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoita.login;

/**
 *
 * @author Fstore
 */
public class LoginDTO {
    private String account;
    private String password;
    
    public LoginDTO(){
        
    }
    
    public LoginDTO(String account, String password){
        this.account = account;
        this.password = password;
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

    @Override
    public String toString() {
        return  "Account=" + account + ", Password=" + password;
    }
    
    
}
