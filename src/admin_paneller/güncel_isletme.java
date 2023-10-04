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
public class güncel_isletme extends JPanel {

    JScrollPane sc;
    JTextField alanNo;
    JTextField yeni_isletmeci;
    JTextField gunSayisi;

    public güncel_isletme() {
        super();
        setLayout(null);
        setSize(1400, 800);
        setBackground(Color.decode("#c62cfe"));
        tabloOlustur();

        JLabel x2 = new JLabel("Alan No:");
        x2.setBounds(150, 560, 200, 50);
        add(x2);
        x2.setVisible(true);

        this.alanNo = new JTextField();
        alanNo.setBounds(235, 570, 60, 30);
        add(alanNo);
        alanNo.setVisible(true);

        JLabel x3 = new JLabel("Yeni İşletmeci No:");
        x3.setBounds(350, 560, 200, 50);
        add(x3);
        x3.setVisible(true);

        this.yeni_isletmeci = new JTextField();
        yeni_isletmeci.setBounds(485, 570, 60, 30);
        add(yeni_isletmeci);
        yeni_isletmeci.setVisible(true);

        JLabel x4 = new JLabel("Gün Sayısı:");
        x4.setBounds(600, 560, 200, 50);
        add(x4);
        x4.setVisible(true);

        this.gunSayisi = new JTextField();
        gunSayisi.setBounds(700, 570, 60, 30);
        add(gunSayisi);
        gunSayisi.setVisible(true);       
              
        

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
                    PreparedStatement state = conn.prepareStatement("UPDATE isletme_guncel_kullanim SET isleten_no=?,isletilecek_gun_sayisi=? WHERE alan_no=?");
                    state.setInt(1, Integer.parseInt(yeni_isletmeci.getText()));
                    state.setInt(2, Integer.parseInt(gunSayisi.getText()));
                    state.setInt(3, Integer.parseInt(alanNo.getText()));
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

        String[] kolonlar = {"Alan No", "İşleten Kişi No", "Kalan Gün   "};

        try {

            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select * from isletme_guncel_kullanim");

            Statement myStat2 = conn.createStatement();
            ResultSet res2 = myStat2.executeQuery("select COUNT(*) from  isletme_guncel_kullanim");
            res2.next();
            int satir_sayisi = res2.getInt(1);
            //System.out.println("satir sayisi = "+satir_sayisi);          
            Object[][] tabloDegerleri = new Object[50][3];
            int k = 0;
            while (res.next()) {

                for (int i = 0; i < 3; i++) {
                    if (i == 0) {
                        tabloDegerleri[k][0] = res.getInt(1);
                    } else if (i == 1) {
                        tabloDegerleri[k][1] = res.getInt(2);
                    } else if (i == 2) {
                        tabloDegerleri[k][2] = res.getInt(3);
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
