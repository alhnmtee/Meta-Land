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
public class isletme_islem extends JPanel {

    JScrollPane sc;
    JTextField isletmeNo;

    JTextField yeni_arsaNo;
    JTextField yeni_isletme_sahibiNo;
    JTextField yeni_tur;
    JTextField yeni_seviye;
    JTextField yeni_kapasite;
    JTextField yeni_calisan_sayisi;
    JTextField yeni_calisan_maasi;
    JTextField yeni_sabit_gelir_miktari;
    JTextField yeni_sabit_gelir_orani;
    JTextField yeni_seviye_baslangic_tarihi;
    JTextField yeni_ozellik;

    public isletme_islem() {

        super();
        setLayout(null);
        setSize(1400, 800);
        setBackground(Color.decode("#c62cfe"));
        tabloOlustur();

        JLabel x2 = new JLabel("Silinecek İşletme No:");
        x2.setBounds(1100, 220, 200, 50);
        add(x2);
        x2.setVisible(true);

        this.isletmeNo = new JTextField();
        isletmeNo.setBounds(1225, 230, 60, 30);
        add(isletmeNo);
        isletmeNo.setVisible(true);

        JLabel x4 = new JLabel("Arsa No:");
        x4.setBounds(100, 400, 200, 50);
        add(x4);
        x4.setVisible(true);

        this.yeni_arsaNo = new JTextField();
        yeni_arsaNo.setBounds(160, 410, 60, 30);
        add(yeni_arsaNo);
        yeni_arsaNo.setVisible(true);

        JLabel x5 = new JLabel("İşletme Sahibi No:");
        x5.setBounds(240, 400, 200, 50);
        add(x5);
        x5.setVisible(true);

        this.yeni_isletme_sahibiNo = new JTextField();
        yeni_isletme_sahibiNo.setBounds(350, 410, 60, 30);
        add(yeni_isletme_sahibiNo);
        yeni_isletme_sahibiNo.setVisible(true);

        JLabel x6 = new JLabel("İşletme Türü:");
        x6.setBounds(430, 400, 200, 50);
        add(x6);
        x6.setVisible(true);

        this.yeni_tur = new JTextField();
        yeni_tur.setBounds(510, 410, 60, 30);
        add(yeni_tur);
        yeni_tur.setVisible(true);

        JLabel x7 = new JLabel("Seviyesi:");
        x7.setBounds(580, 400, 200, 50);
        add(x7);
        x7.setVisible(true);

        this.yeni_seviye = new JTextField();
        yeni_seviye.setBounds(650, 410, 60, 30);
        add(yeni_seviye);
        yeni_seviye.setVisible(true);

        JLabel x8 = new JLabel("Kapasitesi:");
        x8.setBounds(100, 470, 200, 50);
        add(x8);
        x8.setVisible(true);

        this.yeni_kapasite = new JTextField();
        yeni_kapasite.setBounds(170, 480, 60, 30);
        add(yeni_kapasite);
        yeni_kapasite.setVisible(true);

        JLabel x9 = new JLabel("Çalışan Sayısı:");
        x9.setBounds(240, 470, 200, 50);
        add(x9);
        x9.setVisible(true);

        this.yeni_calisan_sayisi = new JTextField();
        yeni_calisan_sayisi.setBounds(330, 480, 60, 30);
        add(yeni_calisan_sayisi);
        yeni_calisan_sayisi.setVisible(true);

        JLabel y1 = new JLabel("Sabit Gelir Miktarı:");
        y1.setBounds(650, 470, 200, 50);
        add(y1);
        y1.setVisible(true);

        this.yeni_sabit_gelir_miktari = new JTextField();
        yeni_sabit_gelir_miktari.setBounds(760, 480, 60, 30);
        add(yeni_sabit_gelir_miktari);
        yeni_sabit_gelir_miktari.setVisible(true);

        JLabel y2 = new JLabel("Sabit Gelir Oranı:");
        y2.setBounds(845, 470, 200, 50);
        add(y2);
        y2.setVisible(true);

        this.yeni_sabit_gelir_orani = new JTextField();
        yeni_sabit_gelir_orani.setBounds(945, 480, 60, 30);
        add(yeni_sabit_gelir_orani);
        yeni_sabit_gelir_orani.setVisible(true);

        JLabel y3 = new JLabel("Seviye Başlangıç Tarihi:");
        y3.setBounds(720, 400, 200, 50);
        add(y3);
        y3.setVisible(true);

        this.yeni_seviye_baslangic_tarihi = new JTextField();
        yeni_seviye_baslangic_tarihi.setBounds(860, 410, 60, 30);
        add(yeni_seviye_baslangic_tarihi);
        yeni_seviye_baslangic_tarihi.setVisible(true);

        JLabel xz = new JLabel("Komisyon/Yemek/Eşya Ücreti:");
        xz.setBounds(400, 470, 200, 50);
        add(xz);
        xz.setVisible(true);

        this.yeni_ozellik = new JTextField();
        yeni_ozellik.setBounds(580, 480, 60, 30);
        add(yeni_ozellik);
        yeni_ozellik.setVisible(true);

        JButton isletmeEkle = new JButton("İşletme Ekle");
        isletmeEkle.setBounds(520, 550, 185, 40);
        add(isletmeEkle);
        isletmeEkle.setVisible(true);
        isletmeEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DatabaseConnect db = new DatabaseConnect();
                Connection conn = db.getConnection();
                try {

                    PreparedStatement stater2 = conn.prepareStatement("INSERT isletme(arsa_no, isletme_sahibi, turu, seviyesi, kapasitesi, calisan_sayisi, sabit_gelir_miktari, sabit_gelir_orani, mevcut_seviye_baslangic_tarihi) VALUES(?,?,?,?,?,?,?,?,?)");
                    stater2.setInt(1, Integer.parseInt(yeni_arsaNo.getText()));
                    stater2.setInt(2, Integer.parseInt(yeni_isletme_sahibiNo.getText()));
                    stater2.setString(3, yeni_tur.getText());
                    stater2.setInt(4, Integer.parseInt(yeni_seviye.getText()));
                    stater2.setInt(5, Integer.parseInt(yeni_kapasite.getText()));
                    stater2.setInt(6, Integer.parseInt(yeni_calisan_sayisi.getText()));
                    // stater2.setInt(7, Integer.parseInt(yeni_calisan_maasi.getText()));
                    stater2.setInt(7, Integer.parseInt(yeni_sabit_gelir_miktari.getText()));
                    stater2.setInt(8, Integer.parseInt(yeni_sabit_gelir_orani.getText()));
                    stater2.setDate(9, java.sql.Date.valueOf(yeni_seviye_baslangic_tarihi.getText()));
                    stater2.executeUpdate();

                    PreparedStatement state2 = conn.prepareStatement("UPDATE Alan SET alan_turu=? WHERE alan_no=?;");

                    state2.setString(1, "İşletme");
                    state2.setInt(2, Integer.parseInt(yeni_arsaNo.getText()));
                    state2.executeUpdate();

                    String eklenecekYer = null;
                    if (yeni_tur.getText().equalsIgnoreCase("Market")) {
                        eklenecekYer = "Market";
                    } else if (yeni_tur.getText().equalsIgnoreCase("Mağaza")) {
                        eklenecekYer = "Magaza";
                    } else if (yeni_tur.getText().equalsIgnoreCase("Emlak")) {
                        eklenecekYer = "Emlak";
                    }
                    //      System.out.println("ne ekledi "+eklenecekYer+"arsa nosu"+yeni_arsaNo.getText());
                    Statement myStatzz2 = conn.createStatement();
                    ResultSet reszz2 = myStatzz2.executeQuery("select isletme_no FROM isletme Where arsa_no=" + yeni_arsaNo.getText());
                    reszz2.next();
                    PreparedStatement statez2 = conn.prepareStatement("INSERT " + eklenecekYer + " VALUES(?,?)");
                    statez2.setInt(1, reszz2.getInt(1));
                    statez2.setInt(2, Integer.parseInt(yeni_ozellik.getText()));
                    statez2.executeUpdate();

                    // güncel işletme listesine ekle
                    PreparedStatement statezz1 = conn.prepareStatement("INSERT isletme_guncel_kullanim VALUES(?,?,?)");
                    statezz1.setInt(1, Integer.parseInt(yeni_arsaNo.getText()));
                    statezz1.setInt(2, Integer.parseInt(yeni_isletme_sahibiNo.getText()));
                    statezz1.setInt(3, 999999999);
                    statezz1.executeUpdate();
                    System.out.println("x");
                    remove(sc);
                    revalidate();
                    repaint();
                    tabloOlustur();
                    revalidate();
                    repaint();

                } catch (Exception ex) {
                    System.out.println("hata");
                }

            }
        });

        JButton isletmeSil = new JButton("İşletmeyi Sil");
        isletmeSil.setBounds(1100, 270, 185, 40);
        add(isletmeSil);
        isletmeSil.setVisible(true);
        isletmeSil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DatabaseConnect db = new DatabaseConnect();
                Connection conn = db.getConnection();
                try {

                    Statement myStat2 = conn.createStatement();
                    ResultSet res2 = myStat2.executeQuery("select arsa_no FROM isletme WHERE isletme_no=" + Integer.parseInt(isletmeNo.getText()));
                    res2.next();
                    int alanNo = res2.getInt(1);
                    System.out.println(alanNo);

                    PreparedStatement stater2 = conn.prepareStatement("DELETE FROM kullanici_guncel_calisma  WHERE isletme_no=?;");
                    stater2.setInt(1, Integer.parseInt(isletmeNo.getText()));
                    stater2.executeUpdate();

                    PreparedStatement statex2 = conn.prepareStatement("DELETE FROM Market  WHERE isletme_no=?;");
                    statex2.setInt(1, Integer.parseInt(isletmeNo.getText()));
                    statex2.executeUpdate();

                    PreparedStatement statez2 = conn.prepareStatement("DELETE FROM Magaza  WHERE isletme_no=?;");
                    statez2.setInt(1, Integer.parseInt(isletmeNo.getText()));
                    statez2.executeUpdate();

                    PreparedStatement staten2 = conn.prepareStatement("DELETE FROM Emlak  WHERE isletme_no=?;");
                    staten2.setInt(1, Integer.parseInt(isletmeNo.getText()));
                    staten2.executeUpdate();

                    PreparedStatement state4 = conn.prepareStatement("UPDATE Alan SET alan_turu=?  WHERE alan_no=?;");
                    state4.setString(1, "Arsa");
                    state4.setInt(2, alanNo);
                    state4.executeUpdate();

                    PreparedStatement state3 = conn.prepareStatement("DELETE FROM isletme_guncel_kullanim  WHERE alan_no=?;");
                    state3.setInt(1, alanNo);
                    state3.executeUpdate();

                    PreparedStatement state2 = conn.prepareStatement("DELETE FROM isletme  WHERE isletme_no=?;");
                    state2.setInt(1, Integer.parseInt(isletmeNo.getText()));
                    state2.executeUpdate();

                    remove(sc);
                    revalidate();
                    repaint();
                    tabloOlustur();
                    revalidate();
                    repaint();

                } catch (Exception ex) {
                    System.out.println("hata");
                }

            }
        });
    }

    public void tabloOlustur() {
        DatabaseConnect db = new DatabaseConnect();
        Connection conn = db.getConnection();

        String[] kolonlar = {"İşletme No", "Arsa No", "İşletme Sahibi No", " İşletme Türü ", " İşletme Seviyesi ",
            " İşletme Kapasitesi ", " Çalışan Sayısı ", " Sabit Gelir Miktarı ",
            " Sabit Gelir Oranı ", " Mevcut Seviye Başlangıç Tarihi "};

        try {

            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select * from isletme");

            Statement myStat2 = conn.createStatement();
            ResultSet res2 = myStat2.executeQuery("select COUNT(*) from  Alan");
            res2.next();
            int satir_sayisi = res2.getInt(1);
            //System.out.println("satir sayisi = "+satir_sayisi);          
            Object[][] tabloDegerleri = new Object[50][10];
            int k = 0;
            while (res.next()) {

                for (int i = 0; i < 11; i++) {
                    if (i == 0) {
                        tabloDegerleri[k][0] = res.getInt(1);
                    } else if (i == 1) {
                        tabloDegerleri[k][1] = res.getInt(2);
                    } else if (i == 2) {
                        tabloDegerleri[k][2] = res.getInt(3);
                    } else if (i == 3) {
                        tabloDegerleri[k][3] = res.getString(4);
                    } else if (i == 4) {
                        tabloDegerleri[k][4] = res.getInt(5);
                    } else if (i == 5) {
                        tabloDegerleri[k][5] = res.getInt(6);
                    } else if (i == 6) {
                        tabloDegerleri[k][6] = res.getInt(7);
                    } else if (i == 7) {
                        tabloDegerleri[k][7] = res.getInt(8);
                    } else if (i == 8) {
                        tabloDegerleri[k][8] = res.getInt(9);
                    } else if (i == 9) {
                        tabloDegerleri[k][9] = res.getDate(10);
                    }

                }
                k++;
            }
            JTable tablo = new JTable(tabloDegerleri, kolonlar);
            //tablo.setPreferredScrollableViewportSize(new Dimension(800, 400)); 
            tablo.setBounds(50, 50, 1000, 300);
            JScrollPane sp = new JScrollPane(tablo);
            sp.setBounds(50, 50, 1000, 300);
            add(sp);
            sc = sp;

        } catch (Exception e) {
        }
    }

}
