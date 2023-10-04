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
public class kullanici_islem extends JPanel {

    JScrollPane sc;
    JTextField oyuncuID;
    JTextField yeniPara;
    JTextField yeniEsya;
    JTextField yeniYemek;

    public kullanici_islem() {
        super();
        setLayout(null);
        setSize(1400, 800);

        setVisible(true);
        setBackground(Color.decode("#c62cfe"));
        tabloOlustur();

        JButton paraGuncelle = new JButton("Parasını Güncelle");
        paraGuncelle.setBounds(800, 400, 150, 50);
        add(paraGuncelle);
        paraGuncelle.setVisible(true);

        JButton esyaGuncelle = new JButton("Eşya Güncelle");
        esyaGuncelle.setBounds(800, 460, 150, 50);
        add(esyaGuncelle);
        esyaGuncelle.setVisible(true);

        JButton yemekGuncelle = new JButton("Yemek Güncelle");
        yemekGuncelle.setBounds(800, 520, 150, 50);
        add(yemekGuncelle);
        yemekGuncelle.setVisible(true);

        JLabel x1 = new JLabel("Güncellenecek Oyuncu ID");
        x1.setBounds(200, 460, 200, 50);
        add(x1);
        x1.setVisible(true);

        JLabel x2 = new JLabel("Yeni Para Değeri");
        x2.setBounds(510, 400, 200, 50);
        add(x2);
        x2.setVisible(true);

        JLabel x3 = new JLabel("Yeni Eşya Değeri");
        x3.setBounds(510, 460, 200, 50);
        add(x3);
        x3.setVisible(true);

        JLabel x4 = new JLabel("Yeni Yemek Değeri");
        x4.setBounds(510, 520, 200, 50);
        add(x4);
        x4.setVisible(true);

        this.oyuncuID = new JTextField();
        oyuncuID.setBounds(370, 470, 60, 30);
        add(oyuncuID);
        oyuncuID.setVisible(true);

        this.yeniPara = new JTextField();
        yeniPara.setBounds(695, 410, 60, 30);
        add(yeniPara);
        yeniPara.setVisible(true);

        this.yeniEsya = new JTextField();
        yeniEsya.setBounds(695, 470, 60, 30);
        add(yeniEsya);
        yeniEsya.setVisible(true);

        this.yeniYemek = new JTextField();
        yeniYemek.setBounds(695, 530, 60, 30);
        add(yeniYemek);
        yeniYemek.setVisible(true);

        paraGuncelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int kullaniciID = Integer.parseInt(oyuncuID.getText());
                
                DatabaseConnect db = new DatabaseConnect();
                Connection conn = db.getConnection();

                try {
                    PreparedStatement state = conn.prepareStatement("UPDATE Kullanici SET kullanici_para_miktari=? WHERE kullanici_no=?;");
                    state.setInt(1, Integer.parseInt(yeniPara.getText()));
                    state.setInt(2, kullaniciID);
                    state.executeUpdate();
                } catch (Exception except) {
                    System.out.println("paraGuncelleme Sorun");
                }
                
                remove(sc);
                revalidate();
                repaint();
                tabloOlustur();
                revalidate();
                repaint();
            }
        });

        esyaGuncelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int kullaniciID = Integer.parseInt(oyuncuID.getText());

                DatabaseConnect db = new DatabaseConnect();
                Connection conn = db.getConnection();

                try {
                    PreparedStatement state = conn.prepareStatement("UPDATE Kullanici SET kullanici_esya_miktari=? WHERE kullanici_no=?;");
                    state.setInt(1, Integer.parseInt(yeniEsya.getText()));
                    state.setInt(2, kullaniciID);
                    state.executeUpdate();
                } catch (Exception except) {
                    System.out.println("esyaGuncelleme Sorun");
                }

                remove(sc);
                revalidate();
                repaint();
                tabloOlustur();
                revalidate();
                repaint();
            }
        });

        yemekGuncelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int kullaniciID = Integer.parseInt(oyuncuID.getText());
                
                DatabaseConnect db = new DatabaseConnect();
                Connection conn = db.getConnection();

                try {
                    PreparedStatement state = conn.prepareStatement("UPDATE Kullanici SET kullanici_yemek_miktari=? WHERE kullanici_no=?;");
                    state.setInt(1, Integer.parseInt(yeniYemek.getText()));
                    state.setInt(2, kullaniciID);
                    state.executeUpdate();
                } catch (Exception except) {
                    System.out.println("yemekGuncelle Sorun");
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

        String[] kolonlar = {"Kullanıcı No", "Kullanıcı Adı", "Kullanıcı Soyadı", "Kullanıcı Giriş Adı", "Kullanıcı Yemek Miktarı", "Kullanıcı Para Miktarı", "Kullanıcı Eşya Miktarı"};

        try {

            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select * from Kullanici");

            Statement myStat2 = conn.createStatement();
            ResultSet res2 = myStat2.executeQuery("select COUNT(*) from Kullanici");
            res2.next();
            int satir_sayisi = res2.getInt(1);
            //System.out.println("satir sayisi = "+satir_sayisi);          
            Object[][] tabloDegerleri = new Object[50][7];
            int k = 0;
            while (res.next()) {

                for (int i = 0; i < 7; i++) {
                    if (i == 0) {
                        tabloDegerleri[k][0] = res.getInt(1);
                    } else if (i == 1) {
                        tabloDegerleri[k][1] = res.getString(2);
                    } else if (i == 2) {
                        tabloDegerleri[k][2] = res.getString(3);
                    } else if (i == 3) {
                        tabloDegerleri[k][3] = res.getString(4);
                    } else if (i == 4) {
                        tabloDegerleri[k][4] = res.getInt(6);
                    } else if (i == 5) {
                        tabloDegerleri[k][5] = res.getInt(7);
                    } else if (i == 6) {
                        tabloDegerleri[k][6] = res.getInt(8);
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
