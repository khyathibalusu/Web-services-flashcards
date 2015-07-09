 package org.apache.ws.axis2;

import java.sql.*;
import java.util.Scanner;
public class DataConnection {

    /**
     * @param args
     */
    public static void main(String args[]) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "hello123");
            c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter input1:");
        int input1 = scanner.nextInt();
        System.out.println("enter input2:");
        int input2 = scanner.nextInt(); 
        
        
        scanner.close();
        
        stmt = c.createStatement();
        String sql = "INSERT INTO data  (a,b) VALUES ("+input1+","+input2+")";
        stmt.executeUpdate(sql);

        
        
        ResultSet rs = stmt.executeQuery( "SELECT * FROM data;" );
        while ( rs.next() ) {
           
           int  a = rs.getInt("a");
           int b  = rs.getInt("b");
           int finalvalue = a+b;  
           System.out.println( "SUM is"+finalvalue );
          
        }
        
        rs.close();
        stmt.close();
        c.commit();
        c.close();
     } catch (Exception e) {
        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        System.exit(0);
     }
        
        
    }
}