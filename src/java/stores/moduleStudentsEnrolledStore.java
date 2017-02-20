/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;
import java.sql.*;
/**
 *
 * @author ashawittchen
 */
public class moduleStudentsEnrolledStore {
     int Matriculation_Number;
     int Module_ID;
     
     public void setMatriculation_Number(int Matriculation_Number){
         this.Matriculation_Number = Matriculation_Number;
     }
     
      public void setModule_ID(int Module_ID){
         this.Module_ID = Module_ID;
     }
      
      public int getMatriculation_Number(){
          return Matriculation_Number;
      }
      
      public int getModule_ID(){
          return Module_ID;
      }
}
