/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.onionchecker.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Verbindet sich zur Datenbank, und verwendet dabei den MySQL driver. 
 * Beim erstellen des Objektes 
 * Die Methode getConnection() liefert eine offene Datenbankverbindung zurück
 */
public class DatabaseConnection
{
    private String mysqlDriver = DatabaseConfig.DRIVER.key();
    private String mysqlURL = "jdbc:mysql://" + DatabaseConfig.SERVER_ADDRESS.key() + "/" + DatabaseConfig.DATABASE.key();
    private Connection conn;
    
    
    public DatabaseConnection()
    {
        init();
    }
    
    private void init()
    {
        try
        {
            Class.forName(mysqlDriver);
            conn = DriverManager.getConnection(mysqlURL, DatabaseConfig.USERNAME.key(), DatabaseConfig.PASSWORD.key());
        }
        catch (ClassNotFoundException ex)
        {
            //Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("CLASS NOT FOUND");
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("CONNECTION ERROR");
        }
    }
    
    public Connection getConnection()
    {
        return conn;
    }
     
}
