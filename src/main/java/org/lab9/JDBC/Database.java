package org.lab9.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private static Connection con;
    private static Database single_instance;
    Database(){
        single_instance = null;
        connectWithDatabase();
    }
    private static  void connectWithDatabase(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/MusicAlbum?serverTimezone=UTC","root","password");

        }catch(Exception e){ System.out.println(e);}
    }
    public static ResultSet makeQuery(String queryString){
        try {
            Statement stmt=con.createStatement();
            return stmt.executeQuery(queryString);
        }catch(Exception e){ System.out.println(e);}
        return  null;
    }
    public static void makeUpdate(String queryString){
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate(queryString);
        }catch(Exception e){ System.out.println(e);}
    }
    public static void closeConnection() {
        try {
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
    public static Database getInstance()
    {
        if (single_instance == null)
            single_instance = new Database();

        return single_instance;
    }
}
