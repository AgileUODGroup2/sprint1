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
    boolean staff;
    String firstName;
    String lastName;
    int matricNo;
    
    public void LoggedIn(){
        
    }
    
    public void setUsername(String name){
        this.username=name;
    }
    public String getUsername(){
        return username;
    }
    
    public int getID(){
        return Integer.parseInt(username);
    }
    
    public void setAsStaff(boolean staff){
        this.staff = staff;
    }
    
    public boolean isStaff(){
        return staff;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    
    public void setLoggedin(){
        loggedin=true;
    }
    public void setLoggedout(){
        loggedin=false;
    }
}
