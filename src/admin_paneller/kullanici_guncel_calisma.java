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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author kerem
 */
public class kullanici_guncel_calisma extends JPanel {

    JScrollPane sc;
    JTextField kullaniciNo;
    JTextField yeni_isletmeNo;
    JTextField maasi;
    public kullanici_guncel_calisma() {

        super();
        setLayout(null);
        setSize(1400, 800);
        setBackground(Color.decode("#c62cfe"));
        tabloOlustur();
        
        JLabel x2 = new JLabel("Kullanıcı No:");
        x2.setBounds(150, 560, 200, 50);
        add(x2);
        x2.setVisible(true);

        this.kullaniciNo = new JTextField();
        kullaniciNo.setBounds(235, 570, 60, 30);
        add(kullaniciNo);
        kullaniciNo.setVisible(true);

        JLabel x3 = new JLabel("Yeni İşletme No:");
        x3.setBounds(350, 560, 200, 50);
        add(x3);
        x3.setVisible(true);

        this.yeni_isletmeNo = new JTextField();
        yeni_isletmeNo.setBounds(485, 570, 60, 30);
        add(yeni_isletmeNo);
        yeni_isletmeNo.setVisible(true);

        JLabel x4 = new JLabel("Maaş:");
        x4.setBounds(600, 560, 200, 50);
        add(x4);
        x4.setVisible(true);

        this.maasi = new JTextField();
        maasi.setBounds(700, 570, 60, 30);
        add(maasi);
        maasi.setVisible(true);       
              
        

        JButton isletmeGuncelle = new JButton("Güncelle");
        isletmeGuncelle.setBounds(800, 570, 185, 30);
        add(isletmeGuncelle);
        isletmeGuncelle.setVisible(true);
        isletmeGuncelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DatabaseConnect db = new DatabaseConnect();
                Connection conn = db.getConnection();

                try {
                    PreparedStatement state = conn.prepareStatement("UPDATE kullanici_guncel_calisma SET isletme_no=?,calisan_maas=? WHERE kullanici_no="+Integer.parseInt(kullaniciNo.getText()));
                    state.setInt(1, Integer.parseInt(yeni_isletmeNo.getText()));
                    state.setInt(2, Integer.parseInt(maasi.getText()));
                    
                    state.executeUpdate();
                } catch (Exception ex) {
                    System.out.println("hata");
                }

                remove(sc);
                revalidate();
                repaint();
                tabloOlustur();
                revalidate();
                repaint();
            }
        });
    }
    
     public void tabloOlustur() {
        DatabaseConnect db = new DatabaseConnect();
        Connection conn = db.getConnection();

        String[] kolonlar = {"İşletme No", "Kullanıcı No", "Başlama Tarihi", "Maaşı"};

        try {

            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select * from kullanici_guncel_calisma ORDER BY kullanici_no");

            Statement myStat2 = conn.createStatement();
            ResultSet res2 = myStat2.executeQuery("select * from  kullanici_guncel_calisma ORDER BY kullanici_no");
            res2.next();
            int satir_sayisi = res2.getInt(1);
            //System.out.println("satir sayisi = "+satir_sayisi);          
            Object[][] tabloDegerleri = new Object[50][4];
            int k = 0;
            while (res.next()) {

                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        tabloDegerleri[k][0] = res.getInt(1);
                    } else if (i == 1) {
                        tabloDegerleri[k][1] = res.getInt(2);
                    } else if (i == 2) {
                        tabloDegerleri[k][2] = res.getDate(3);
                    }
                    else if (i == 3) {
                        tabloDegerleri[k][3] = res.getInt(4);
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
