/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.sql.Date;
import lib.database.DatabaseConnection;
import stores.Result;

/**
 *
 * @author viivipursiainen
 */
public class ResultModel {
    DatabaseConnection db;
    
    public java.util.LinkedList<Result> getResultsForDates(Date date1, Date date2) {
        java.util.LinkedList<Result> Results = new java.util.LinkedList<>();
        return Results;
    }
}
