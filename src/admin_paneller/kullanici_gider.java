/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin_paneller;

import main.DatabaseConnect;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author kerem
 */
public class kullanici_gider extends JPanel{

    JScrollPane sc;
    public kullanici_gider() {
        super();
        setLayout(null);
        setSize(1400, 800);
        setBackground(Color.decode("#c62cfe"));
        tabloOlustur();
        
        
    }
    
    public void tabloOlustur() {
        DatabaseConnect db = new DatabaseConnect();
        Connection conn = db.getConnection();

        String[] kolonlar = {"Kullanici No", "Açıklama", "Miktarı", "Tarihi"};

        try {

            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select * from kullanici_gider ORDER BY kullanici_no");

            Statement myStat2 = conn.createStatement();
            ResultSet res2 = myStat2.executeQuery("select COUNT(kullanici_no) from  kullanici_gider");
            res2.next();
            int satir_sayisi = res2.getInt(1);
            //System.out.println("satir sayisi = "+satir_sayisi);          
            Object[][] tabloDegerleri = new Object[satir_sayisi][4];
            int k = 0;
            while (res.next()) {

                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        tabloDegerleri[k][0] = res.getInt(1);
                    } else if (i == 1) {
                        tabloDegerleri[k][1] = res.getString(2);
                    } else if (i == 2) {
                        tabloDegerleri[k][2] = res.getInt(3);
                    }
                    else if (i == 3) {
                        tabloDegerleri[k][3] = res.getDate(4);
                    }

                }
                k++;
            }
            JTable tablo = new JTable(tabloDegerleri, kolonlar);
            //tablo.setPreferredScrollableViewportSize(new Dimension(800, 400)); 
            tablo.setBounds(50, 50, 1000, 600);
            JScrollPane sp = new JScrollPane(tablo);
            sp.setBounds(50, 50, 1000, 600);
            add(sp);
            sc = sp;

        } catch (Exception e) {
        }
    }
}
    

