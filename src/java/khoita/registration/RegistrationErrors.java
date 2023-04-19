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
public class RegistrationErrors {
    private String usernameLengthError;
    private String passwordLengthError;
    private String fullnameLengthError;
    private String usernameExisted;
    private String confirmPassword;

    public RegistrationErrors() {
    }

    public RegistrationErrors(String usernameLengthError, String passwordLengthError, String fullnameLengthError, String usernameExisted,String confirmPassword) {
        this.usernameLengthError = usernameLengthError;
        this.passwordLengthError = passwordLengthError;
        this.fullnameLengthError = fullnameLengthError;
        this.confirmPassword = confirmPassword;
        this.usernameExisted = usernameExisted;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    
    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getFullnameLengthError() {
        return fullnameLengthError;
    }

    public void setFullnameLengthError(String fullnameLengthError) {
        this.fullnameLengthError = fullnameLengthError;
    }

    public String getUsernameExisted() {
        return usernameExisted;
    }

    public void setUsernameExisted(String usernameExisted) {
        this.usernameExisted = usernameExisted;
    }

    @Override
    public String toString() {
        return "RegistrationErrors{" + "usernameLengthError=" + usernameLengthError + ", passwordLengthError=" + passwordLengthError + ", fullnameLengthError=" + fullnameLengthError + ", usernameExisted=" + usernameExisted + ", confirmPassword=" + confirmPassword + '}';
    }
   
    
    
}
