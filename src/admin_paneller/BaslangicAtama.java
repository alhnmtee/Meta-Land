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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kerem
 */
public class BaslangicAtama extends JPanel {

    JTextField baslangic_yemek_miktari;
    JTextField baslangic_para_miktari;
    JTextField baslangic_esya_miktari;
    JTextField gunluk_yemek_gider;
    JTextField gunluk_para_gider;
    JTextField gunluk_esya_gider;
    JTextField yonetici_calisma_ucreti;
    JTextField oyun_alan_boyutu;
    JTextField oyun_baslangic_tarihi;
    JTextField magazaKurmaUcretMarket;
    JTextField marketKurmaUcretMarket;
    JTextField emlakKurmaUcretMarket;
    JTextField yemekilkFiyat;
    JTextField esyailkFiyat;
    JTextField emlakilkKomisyon;

    JButton atama;

    public BaslangicAtama() {
        super();
        setLayout(null);
        setSize(1000, 800);
        setVisible(true);
        DatabaseConnect db = new DatabaseConnect();
        Connection conn = db.getConnection();
        setBackground(Color.decode("#c62cfe"));


        JLabel yazi=new JLabel("Başlangıç Değerleri");
        yazi.setVisible(true);
        yazi.setBounds(350,50,350,50);
        yazi.setFont(yazi.getFont().deriveFont(Font.BOLD,25));
        add(yazi);


        this.baslangic_yemek_miktari = new JTextField();
        baslangic_yemek_miktari.setBounds(360, 200, 90, 30);
        add(baslangic_yemek_miktari);
        baslangic_yemek_miktari.setVisible(true);

        this.baslangic_para_miktari = new JTextField();
        baslangic_para_miktari.setBounds(360, 240, 90, 30);
        add(baslangic_para_miktari);
        baslangic_para_miktari.setVisible(true);

        this.baslangic_esya_miktari = new JTextField();
        baslangic_esya_miktari.setBounds(360, 280, 90, 30);
        add(baslangic_esya_miktari);
        baslangic_esya_miktari.setVisible(true);

        this.gunluk_yemek_gider = new JTextField();
        gunluk_yemek_gider.setBounds(360, 320, 90, 30);
        add(gunluk_yemek_gider);
        gunluk_yemek_gider.setVisible(true);

        this.gunluk_para_gider = new JTextField();
        gunluk_para_gider.setBounds(360, 360, 90, 30);
        add(gunluk_para_gider);
        gunluk_para_gider.setVisible(true);

        this.gunluk_esya_gider = new JTextField();
        gunluk_esya_gider.setBounds(360, 400, 90, 30);
        add(gunluk_esya_gider);
        gunluk_esya_gider.setVisible(true);

        this.yonetici_calisma_ucreti = new JTextField();
        yonetici_calisma_ucreti.setBounds(360, 440, 90, 30);
        add(yonetici_calisma_ucreti);
        yonetici_calisma_ucreti.setVisible(true);

        this.oyun_alan_boyutu = new JTextField();
        oyun_alan_boyutu.setBounds(360, 480, 90, 30);
        add(oyun_alan_boyutu);
        oyun_alan_boyutu.setVisible(true);

        this.oyun_baslangic_tarihi = new JTextField();
        oyun_baslangic_tarihi.setBounds(360, 525, 90, 30);
        add(oyun_baslangic_tarihi);
        oyun_baslangic_tarihi.setVisible(true);

        JLabel x1 = new JLabel("Başlangıç Yemek Miktarı:");
        x1.setBounds(150, 200, 200, 30);
        add(x1);
        x1.setVisible(true);

        JLabel x2 = new JLabel("Başlangıç Para Miktarı:");
        x2.setBounds(150, 240, 200, 30);
        add(x2);
        x2.setVisible(true);

        JLabel x3 = new JLabel("Başlangıç Eşya Miktarı:");
        x3.setBounds(150, 280, 200, 30);
        add(x3);
        x3.setVisible(true);

        JLabel x4 = new JLabel("Günlük Yemek Gideri");
        x4.setBounds(150, 320, 200, 30);
        add(x4);
        x4.setVisible(true);

        JLabel x5 = new JLabel("Günlük Para Gideri");
        x5.setBounds(150, 360, 200, 30);
        add(x5);
        x5.setVisible(true);

        JLabel x6 = new JLabel("Günlük Eşya Gideri");
        x6.setBounds(150, 400, 200, 30);
        add(x6);
        x6.setVisible(true);

        JLabel x7 = new JLabel("Yönetici Yanında Çalışma Ücreti:");
        x7.setBounds(150, 440, 200, 30);
        add(x7);
        x7.setVisible(true);

        JLabel x8 = new JLabel("Oyun Alan Boyutu:");
        x8.setBounds(150, 480, 200, 30);
        add(x8);
        x8.setVisible(true);

        JLabel x9 = new JLabel("Oyun Başlangıç Tarihi:");
        x9.setBounds(150, 520, 200, 30);
        add(x9);
        x9.setVisible(true);

        this.magazaKurmaUcretMarket = new JTextField();
        magazaKurmaUcretMarket.setBounds(680, 200, 90, 30);
        add(magazaKurmaUcretMarket);
        magazaKurmaUcretMarket.setVisible(true);

        this.marketKurmaUcretMarket = new JTextField();
        marketKurmaUcretMarket.setBounds(680, 240, 90, 30);
        add(marketKurmaUcretMarket);
        marketKurmaUcretMarket.setVisible(true);

        this.emlakKurmaUcretMarket = new JTextField();
        emlakKurmaUcretMarket.setBounds(680, 280, 90, 30);
        add(emlakKurmaUcretMarket);
        emlakKurmaUcretMarket.setVisible(true);

        this.yemekilkFiyat = new JTextField();
        yemekilkFiyat.setBounds(680, 320, 90, 30);
        add(yemekilkFiyat);
        yemekilkFiyat.setVisible(true);

        this.esyailkFiyat = new JTextField();
        esyailkFiyat.setBounds(680, 360, 90, 30);
        add(esyailkFiyat);
        esyailkFiyat.setVisible(true);

        this.emlakilkKomisyon = new JTextField();
        emlakilkKomisyon.setBounds(680, 400, 90, 30);
        add(emlakilkKomisyon);
        emlakilkKomisyon.setVisible(true);

        JLabel x11 = new JLabel("Mağaza Kurma Ücreti:");
        x11.setBounds(470, 280, 200, 30);
        add(x11);
        x11.setVisible(true);

        JLabel x12 = new JLabel("Market Kurma Ücreti:");
        x12.setBounds(470, 200, 200, 30);
        add(x12);
        x12.setVisible(true);

        JLabel x13 = new JLabel("Emlak Kurma Ücreti:");
        x13.setBounds(470, 240, 200, 30);
        add(x13);
        x13.setVisible(true);

        JLabel x14 = new JLabel("Admin Market Yemek Ücreti:");
        x14.setBounds(470, 320, 200, 30);
        add(x14);
        x14.setVisible(true);

        JLabel x15 = new JLabel("Admin Mağaza Eşya Ücreti:");
        x15.setBounds(470, 360, 200, 30);
        add(x15);
        x15.setVisible(true);

        JLabel x16 = new JLabel("Admin Emlak Komisyon Ücreti:");
        x16.setBounds(470, 400, 200, 30);
        add(x16);
        x16.setVisible(true);

        atama = new JButton("Oyunu Başlat");
        atama.setBounds(400, 580, 200, 40);
        atama.setVisible(true);
        add(atama);
        atama.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                int baslangic_yemek_miktariS = Integer.parseInt(baslangic_yemek_miktari.getText());
                int baslangic_para_miktarS = Integer.parseInt(baslangic_para_miktari.getText());
                int baslangic_esya_miktariS = Integer.parseInt(baslangic_esya_miktari.getText());
                int kullanici_gunluk_yemek_gideriS = Integer.parseInt(gunluk_yemek_gider.getText());
                int kullanici_gunluk_para_gideriS = Integer.parseInt(gunluk_para_gider.getText());
                int kullanici_gunluk_esya_gideriS = Integer.parseInt(gunluk_esya_gider.getText());
                int yonetici_yaninda_calisma_ucretiS = Integer.parseInt(yonetici_calisma_ucreti.getText());
                String oyun_alan_boyutuS = oyun_alan_boyutu.getText();
                String oyun_baslangic_tarihiS = oyun_baslangic_tarihi.getText();
                //    String oyun_guncel_tarihiS = oyun_baslangic_tarihi.getText();
                Date oyunBaslangicTarih = Date.valueOf(oyun_baslangic_tarihiS);

                int magazaKurmaUcretMarketS = Integer.parseInt(magazaKurmaUcretMarket.getText());
                int marketKurmaUcretMarketS = Integer.parseInt(marketKurmaUcretMarket.getText());
                int emlakKurmaUcretMarketS = Integer.parseInt(emlakKurmaUcretMarket.getText());
                int yemekilkFiyatS = Integer.parseInt(yemekilkFiyat.getText());
                int esyailkFiyatS = Integer.parseInt(esyailkFiyat.getText());
                int emlakilkKomisyonS = Integer.parseInt(emlakilkKomisyon.getText());

                DatabaseConnect db = new DatabaseConnect();
                Connection conn = db.getConnection();

                try {
                    String query = "INSERT INTO Baslangic  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

                    PreparedStatement pst = conn.prepareStatement(query);

                    pst.setInt(1, baslangic_yemek_miktariS);
                    pst.setInt(2, baslangic_para_miktarS);
                    pst.setInt(3, baslangic_esya_miktariS);
                    pst.setInt(4, kullanici_gunluk_yemek_gideriS);
                    pst.setInt(5, kullanici_gunluk_para_gideriS);
                    pst.setInt(6, kullanici_gunluk_esya_gideriS);
                    pst.setInt(7, yonetici_yaninda_calisma_ucretiS);
                    pst.setString(8, oyun_alan_boyutuS);
                    pst.setDate(9, oyunBaslangicTarih);
                    pst.setDate(10, oyunBaslangicTarih);
                    pst.setInt(11, magazaKurmaUcretMarketS);
                    pst.setInt(12, marketKurmaUcretMarketS);
                    pst.setInt(13, emlakKurmaUcretMarketS);

                    pst.executeUpdate();

                    int satir = Integer.parseInt(String.valueOf(oyun_alan_boyutuS.charAt(0)));
                    int sutun = Integer.parseInt(String.valueOf(oyun_alan_boyutuS.charAt(2)));

                    String arsa = "INSERT Alan VALUES(?,?,?)";
                    PreparedStatement pst2 = conn.prepareStatement(arsa);
                    for (int i = 1; i <= satir * sutun; i++) {
                        pst2.setInt(1, i);
                        pst2.setInt(2, 1);
                        if (i > 0 && i < 4) {
                            pst2.setString(3, "İşletme");
                        } else {
                            pst2.setString(3, "Arsa");
                        }
                        pst2.addBatch();
                    }
                    pst2.executeBatch();

                    String oyuncuGuncel = "UPDATE Kullanici SET kullanici_yemek_miktari=" + baslangic_yemek_miktariS
                            + ", kullanici_esya_miktari=" + baslangic_esya_miktariS + ", kullanici_para_miktari=" + baslangic_para_miktarS + " WHERE kullanici_no<>1";
                    PreparedStatement pst3 = conn.prepareStatement(oyuncuGuncel);
                    pst3.executeUpdate();

                    String isletme = "INSERT INTO isletme (arsa_no, isletme_sahibi, turu,seviyesi, kapasitesi, calisan_sayisi, sabit_gelir_miktari , sabit_gelir_orani, mevcut_seviye_baslangic_tarihi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pst5 = conn.prepareStatement(isletme);

                    pst5.setInt(1, 1); // arsa no
                    pst5.setInt(2, 1); // işletme sahibi
                    pst5.setString(3, "Market"); // işletme türü
                    pst5.setInt(4, 3); // işletme seviyesi
                    pst5.setInt(5, 9999999); // işletme kapasitesi
                    pst5.setInt(6, 9999999); // işletme çalışan sayısı                    
                    pst5.setInt(7, 9999999); // işletme sabit gelir
                    pst5.setInt(8, 1); // işletme sabit gelir orani
                    pst5.setDate(9, oyunBaslangicTarih); // Seviye Başlangıç Tarihi

                    pst5.executeUpdate();

                    pst5.setInt(1, 2); // arsa no
                    pst5.setInt(2, 1); // işletme sahibi
                    pst5.setString(3, "Mağaza"); // işletme türü
                    pst5.setInt(4, 3); // işletme seviyesi
                    pst5.setInt(5, 9999999); // işletme kapasitesi
                    pst5.setInt(6, 9999999); // işletme çalışan sayısı
                    
                    pst5.setInt(7, 9999999); // işletme sabit gelir
                    pst5.setInt(8, 1); // işletme sabit gelir orani
                    pst5.setDate(9, oyunBaslangicTarih); // Seviye Başlangıç Tarihi
                    pst5.executeUpdate();

                    pst5.setInt(1, 3); // arsa no
                    pst5.setInt(2, 1); // işletme sahibi
                    pst5.setString(3, "Emlak"); // işletme türü
                    pst5.setInt(4, 3); // işletme seviyesi
                    pst5.setInt(5, 9999999); // işletme kapasitesi
                    pst5.setInt(6, 9999999); // işletme çalışan sayısı
                    
                    pst5.setInt(7, 9999999); // işletme sabit gelir
                    pst5.setInt(8, 1); // işletme sabit gelir orani
                    pst5.setDate(9, oyunBaslangicTarih); // Seviye Başlangıç Tarihi
                    pst5.addBatch();

                    pst5.executeUpdate();

                    String MarketucretlerEkleme = "INSERT INTO Market  VALUES (?,?)";
                    String MagazaucretEkleme = "INSERT INTO Magaza  VALUES (?,?)";
                    String emlakKomisyonEkleme = "INSERT INTO Emlak  VALUES (?,?)";

                    PreparedStatement pstx1 = conn.prepareStatement(MarketucretlerEkleme);
                    pstx1.setInt(1, 1);
                    pstx1.setInt(2, yemekilkFiyatS);
                    pstx1.executeUpdate();

                    PreparedStatement pstx2 = conn.prepareStatement(MagazaucretEkleme);
                    pstx2.setInt(1, 2);
                    pstx2.setInt(2, esyailkFiyatS);
                    pstx2.executeUpdate();

                    PreparedStatement pstx3 = conn.prepareStatement(emlakKomisyonEkleme);
                    pstx3.setInt(1, 3);
                    pstx3.setInt(2, emlakilkKomisyonS);
                    pstx3.executeUpdate();

                    String arsailan = "INSERT INTO Arsa_ilan  VALUES (?,?,?,?)";

                    PreparedStatement pstz1 = conn.prepareStatement(arsailan);

                    pstz1.setInt(1, 3);
                    pstz1.setInt(3, 1);
                    pstz1.setInt(4, 1000);

                    for (int i = 4; i <= satir * sutun; i++) {
                        pstz1.setInt(2, i);
                        pstz1.executeUpdate();

                    }

                    String is_ilan = "INSERT INTO is_ilan  VALUES (?,?,?)";

                    PreparedStatement pstxx1 = conn.prepareStatement(is_ilan);

                    for (int i = 1; i < 4; i++) {
                        pstxx1.setInt(1, i);
                        pstxx1.setInt(2, 1);
                        pstxx1.setInt(3, yonetici_yaninda_calisma_ucretiS);
                        pstxx1.executeUpdate();

                    }

                } catch (SQLException ex) {
                    Logger.getLogger(BaslangicAtama.class.getName()).log(Level.SEVERE, null, ex);
                }
                atama.setEnabled(false);
            }

        });


    }

}
