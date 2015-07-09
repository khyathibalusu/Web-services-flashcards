 package org.apache.ws.axis2;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ImageDataBase {
    
    
	public static void main(String args[]) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "hello123");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            
            stmt = c.createStatement();
            //String sql = "create table pics(imgname text, img bytea)";
            //stmt.executeUpdate(sql);
            
            File file = new File("D:/download.jpg");
            FileInputStream fis = new FileInputStream(file);
            PreparedStatement ps = c.prepareStatement("INSERT INTO pics  VALUES (?, ?)");
            ps.setString(1, file.getName());
            ps.setBinaryStream(2, fis, file.length());
            ps.executeUpdate();
           
            ps = c.prepareStatement("SELECT * FROM pics");
            
            
           // ps.setString(1,"D:/download.jpg");
            
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while(rs.next()) {
                    
                    byte[] imagebytes = rs.getBytes(1);
                    for (byte i = 0; i < imagebytes.length; i++) {
                         System.out.println(imagebytes[i] + " ");
                      }
                }
                
                rs.close();
            }
            
            ps.close();
            fis.close();
            stmt.close();
            c.commit();
            c.close();
         } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
         }
    }
}