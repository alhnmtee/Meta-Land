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
public class arsa_islem extends JPanel {

    JScrollPane sc;
    JTextField arsaID;
    JTextField yeniSahibiID;

    public arsa_islem() {
        super();
        setLayout(null);
        setSize(1400, 800);
        setBackground(Color.decode("#c62cfe"));

        tabloOlustur();
        JLabel x1 = new JLabel("Güncellenecek Alan No:");
        x1.setBounds(200, 460, 200, 50);
        add(x1);
        x1.setVisible(true);

        JLabel x2 = new JLabel("Yeni Alan Sahibi No:");
        x2.setBounds(510, 460, 200, 50);
        add(x2);
        x2.setVisible(true);

        this.arsaID = new JTextField();
        arsaID.setBounds(370, 470, 60, 30);
        add(arsaID);
        arsaID.setVisible(true);

        this.yeniSahibiID = new JTextField();
        yeniSahibiID.setBounds(695, 470, 60, 30);
        add(yeniSahibiID);
        yeniSahibiID.setVisible(true);

        JButton arsaGuncelle = new JButton("Alan Sahibi Güncelle");
        arsaGuncelle.setBounds(800, 460, 200, 50);
        add(arsaGuncelle);
        arsaGuncelle.setVisible(true);
        arsaGuncelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int arsaIDx = Integer.parseInt(arsaID.getText());
                int yeniSahibix = Integer.parseInt(yeniSahibiID.getText());

                //          System.out.println("Alan" + arsaIDx + "yeni" + yeniSahibix);
                DatabaseConnect db = new DatabaseConnect();
                Connection conn = db.getConnection();
                try {
                    Statement myStat2 = conn.createStatement();
                    ResultSet res2 = myStat2.executeQuery("select alan_sahibi FROM Alan WHERE alan_no=" + arsaIDx);
                    res2.next();
                    int eskiSahip = res2.getInt(1);
                    //         System.out.println(eskiSahip);

                    PreparedStatement state = conn.prepareStatement("UPDATE isletme_guncel_kullanim SET isleten_no=?,isletilecek_gun_sayisi=99999999 WHERE alan_no=? AND isleten_no=?;");

                    state.setInt(1, yeniSahibix);
                    state.setInt(2, arsaIDx);
                    state.setInt(3, eskiSahip);
                    state.executeUpdate();

                    PreparedStatement state2 = conn.prepareStatement("UPDATE Alan SET alan_sahibi=? WHERE alan_no=?;");

                    state2.setInt(1, yeniSahibix);
                    state2.setInt(2, arsaIDx);

                    state2.executeUpdate();

                    PreparedStatement state3 = conn.prepareStatement("UPDATE isletme SET isletme_sahibi=? WHERE arsa_no=?;");

                    state3.setInt(1, yeniSahibix);
                    state3.setInt(2, arsaIDx);

                    state3.executeUpdate();

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

        setVisible(true);
    }

    public void tabloOlustur() {
        DatabaseConnect db = new DatabaseConnect();
        Connection conn = db.getConnection();

        String[] kolonlar = {"Alan No", "Şuanki Kullanıcı No", "Şuanki Kullanıcının Giriş Adı", " Alan Türü "};

        try {

            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select x.alan_no,x.alan_sahibi,y.kullanici_giris_adi,x.alan_turu from Kullanici AS y, Alan x WHERE y.kullanici_no=x.alan_sahibi");

            Statement myStat2 = conn.createStatement();
            ResultSet res2 = myStat2.executeQuery("select COUNT(*) from  Alan");
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
                        tabloDegerleri[k][2] = res.getString(3);
                    } else if (i == 3) {
                        tabloDegerleri[k][3] = res.getString(4);
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
