/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.onionchecker.Database;

/**
 * Konfiguration der Datenbank. Ich verwende MySQL. 
  
   Danbank Schema: 
 
   CREATE TABLE `Site` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `URL` varchar(70) NOT NULL,
  `isAlive` bit(1) NOT NULL,
  `DateChecked` datetime NOT NULL,
  `PageTitle` text,
  `IndexHTML` longtext,
  PRIMARY KEY (`ID`)

 */
public enum DatabaseConfig
{
    SERVER_ADDRESS("192.168.1.200"),
    SERVER_PORT("3306"),
    DATABASE("TorSites"),
    USERNAME("onion"),
    PASSWORD("onionchecker"),
    DRIVER("org.gjt.mm.mysql.Driver"),
    QUERY("INSERT INTO Site (URL, isAlive, DateChecked, PageTitle, indexHtml) VALUES(?, ?, ? ,?, ?)");
    
    private String key;
    DatabaseConfig(String key)
    {
        this.key = key;
    }
    
    public String key()
    {
        return key;
    }
}
