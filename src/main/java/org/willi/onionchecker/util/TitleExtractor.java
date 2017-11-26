/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.onionchecker.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author phamm
 */
public class TitleExtractor
{

    private static final Pattern TITLE_TAG = Pattern.compile("\\<title>(.*)\\</title>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    public static String getPageTitle(String response) 
    {
       
            // extract the title
            Matcher matcher = TITLE_TAG.matcher(response);
            if (matcher.find())
            {
                /* replace any occurrences of whitespace (which may
                 * include line feeds and other uglies) as well
                 * as HTML brackets with a space */
                return matcher.group(1).replaceAll("[\\s\\<>]+", " ").trim();
            }
            else
            {
                return null;
            }
        }
    
}


