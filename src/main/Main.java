package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        DatabaseConnect dtbc = new DatabaseConnect();
        Connection conn = dtbc.getConnection();
        new Login();
        if (conn != null) {
            try {
                Statement myStat = conn.createStatement();
               ResultSet res = myStat.executeQuery("select kullanici_soyadi from Kullanici");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                dtbc.closeConnection();
            }
        }
        //  try {
        // Class.forName("com.mysql.jdbc.Driver");
        //Connection conn =DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=car;encrypt=true;trustServerCertificate=true;","sa","1");
        //Statement myStat =conn.createStatement();
        // ResultSet res= myStat.executeQuery("select * from cars");
        // while (res.next()){
        //System.out.println(res.getString("car_id")+ "-" +res.getString("cars_name")+ "-"+res.getString("cars_speed"));
        //  }
        //}catch (Exception e){
        //  e.printStackTrace();
        //}
        //}
    }
}