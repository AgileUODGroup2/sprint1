/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

/**
 *
 * @author nathanmcmahon
 */
public class LoggedIn {
    
    boolean loggedin=false;
    String username=null;    
    
    public void LoggedIn(){
        
    }
    
    public void setUsername(String name){
        this.username=name;
    }
    public String getUsername(){
        return username;
    }
    
    public void setLoggedin(){
        loggedin=true;
    }
    public void setLoggedout(){
        loggedin=false;
    }
}
