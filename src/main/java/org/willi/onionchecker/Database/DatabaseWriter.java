/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.onionchecker.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamm
 */
public class DatabaseWriter
{
    private Connection conn;
    private PreparedStatement prstmnt;
    
    public DatabaseWriter(Connection conn)
    {
        this.conn = conn;
    }
    
    public void wirteToDB(String url, int isAlive, String date, String pageTitle, String index_html)
    {
        try
        {
            prstmnt = conn.prepareStatement(DatabaseConfig.QUERY.key());
            
            prstmnt.setString(1, url);
            prstmnt.setInt(2, isAlive);
            prstmnt.setString(3, date);
            prstmnt.setString(4, pageTitle);
            prstmnt.setString(5, index_html);
            
            prstmnt.execute();
            conn.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
