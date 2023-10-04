/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin_paneller;

import main.DatabaseConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author kerem
 */
public class emlak_goruntule extends JPanel {

    JScrollPane sc;

    public emlak_goruntule() {
        super();
        setLayout(null);
        setSize(1400, 800);
        setBackground(Color.decode("#c62cfe"));
        tabloOlustur();
        
        JButton isletmeyeGore = new JButton("İşlem Yapılan Alana Göre Sırala");
        isletmeyeGore.setBounds(520, 550, 300, 40);
        add(isletmeyeGore);
        isletmeyeGore.setVisible(true);
        isletmeyeGore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(sc);
                    revalidate();
                    repaint();
                    tabloOlusturIsletmeSırala();
                    revalidate();
                    repaint();
            }
        });
        
        
    }

    public void tabloOlustur() {
        DatabaseConnect db = new DatabaseConnect();
        Connection conn = db.getConnection();

        String[] kolonlar = {"Emlakçı İşletme No", "İşlem Yapan Kişi No", "İşlem Yapılan Yer No",
             "İşlem Tipi", "Satış Tarihi", "Kiralama Tarihi", "Kiralama Bitiş Tarihi", "Kira Süresi"};

        try {

            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select * from emlak_islem_kayit");

            Statement myStat2 = conn.createStatement();
            ResultSet res2 = myStat2.executeQuery("select COUNT(*) from  emlak_islem_kayit");
            res2.next();
            int satir_sayisi = res2.getInt(1);
            //System.out.println("satir sayisi = "+satir_sayisi);          
            Object[][] tabloDegerleri = new Object[50][8];
            int k = 0;
            while (res.next()) {

                for (int i = 0; i < 8; i++) {
                    if (i == 0) {
                        tabloDegerleri[k][0] = res.getInt(1);
                    } else if (i == 1) {
                        tabloDegerleri[k][1] = res.getInt(2);
                    } else if (i == 2) {
                        tabloDegerleri[k][2] = res.getInt(3);
                    } else if (i == 3) {
                        tabloDegerleri[k][3] = res.getString(4);
                    } else if (i == 4) {
                        tabloDegerleri[k][4] = res.getDate(5);
                    } else if (i == 5) {
                        tabloDegerleri[k][5] = res.getDate(6);
                    } else if (i == 6) {
                        tabloDegerleri[k][6] = res.getDate(7);
                    } else if (i == 7) {
                        tabloDegerleri[k][7] = res.getInt(8);
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
    public void tabloOlusturIsletmeSırala() {
        DatabaseConnect db = new DatabaseConnect();
        Connection conn = db.getConnection();

        String[] kolonlar = {"Emlakçı İşletme No", "İşlem Yapan Kişi No", "İşlem Yapılan Yer No",
             "İşlem Tipi", "Satış Tarihi", "Kiralama Tarihi", "Kiralama Bitiş Tarihi", "Kira Süresi"};

        try {

            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select * from emlak_islem_kayit ORDER BY satilan_yer_no");

            Statement myStat2 = conn.createStatement();
            ResultSet res2 = myStat2.executeQuery("select COUNT(*) from  emlak_islem_kayit");
            res2.next();
            int satir_sayisi = res2.getInt(1);
            //System.out.println("satir sayisi = "+satir_sayisi);          
            Object[][] tabloDegerleri = new Object[50][8];
            int k = 0;
            while (res.next()) {

                for (int i = 0; i < 8; i++) {
                    if (i == 0) {
                        tabloDegerleri[k][0] = res.getInt(1);
                    } else if (i == 1) {
                        tabloDegerleri[k][1] = res.getInt(2);
                    } else if (i == 2) {
                        tabloDegerleri[k][2] = res.getInt(3);
                    } else if (i == 3) {
                        tabloDegerleri[k][3] = res.getString(4);
                    } else if (i == 4) {
                        tabloDegerleri[k][4] = res.getDate(5);
                    } else if (i == 5) {
                        tabloDegerleri[k][5] = res.getDate(6);
                    } else if (i == 6) {
                        tabloDegerleri[k][6] = res.getDate(7);
                    } else if (i == 7) {
                        tabloDegerleri[k][7] = res.getInt(8);
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
