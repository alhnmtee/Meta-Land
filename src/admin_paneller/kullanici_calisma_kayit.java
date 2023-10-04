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
public class kullanici_calisma_kayit extends JPanel{

    JScrollPane sc;
    public kullanici_calisma_kayit() {
        super();
        setLayout(null);
        setSize(1400, 800);
        setBackground(Color.decode("#c62cfe"));
        tabloOlustur();
    
    }
    
    public void tabloOlustur() {
        DatabaseConnect db = new DatabaseConnect();
        Connection conn = db.getConnection();

        String[] kolonlar = {"Kullanici No", "Çalıştığı İşletme No", "Çalışma Başlangıç Tarihi", "Çalışma Bitiş Tarihi","Çalışılan Gün Sayısı","Günlük Çalışılan Saat Sayısı"};

        try {

            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select * from Calisma_kayit ORDER BY calisan_no");

            Statement myStat2 = conn.createStatement();
            ResultSet res2 = myStat2.executeQuery("select * from  Calisma_kayit ORDER BY calisan_no");
            res2.next();
            int satir_sayisi = res2.getInt(1);
            //System.out.println("satir sayisi = "+satir_sayisi);          
            Object[][] tabloDegerleri = new Object[50][6];
            int k = 0;
            while (res.next()) {

                for (int i = 0; i < 6; i++) {
                    if (i == 0) {
                        tabloDegerleri[k][0] = res.getInt(1);
                    } else if (i == 1) {
                        tabloDegerleri[k][1] = res.getInt(2);
                    } else if (i == 2) {
                        tabloDegerleri[k][2] = res.getDate(3);
                    }
                    else if (i == 3) {
                        tabloDegerleri[k][3] = res.getDate(4);
                    }else if (i == 4) {
                        tabloDegerleri[k][4] = res.getInt(5);
                    } else if (i == 5) {
                        tabloDegerleri[k][5] = res.getInt(6);
                    }

                }
                k++;
            }
            JTable tablo = new JTable(tabloDegerleri, kolonlar);
            //tablo.setPreferredScrollableViewportSize(new Dimension(800, 400)); 
            tablo.setBounds(50, 50, 1000, 500);
            JScrollPane sp = new JScrollPane(tablo);
            sp.setBounds(50, 50, 1000, 300);
            add(sp);
            sc = sp;

        } catch (Exception e) {
        }
    }
}

