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
public class isletme_ilanlari extends JPanel {

    JScrollPane sc;
    JTextField emlakci;
    JTextField isletme_no;
    JTextField islem_yapan_no;
    JTextField islemTipi;
    JTextField ucret;

    JTextField arsaID;

    public isletme_ilanlari() {
        super();
        setLayout(null);
        setSize(1400, 800);
        setBackground(Color.decode("#c62cfe"));

        tabloOlustur();

        JLabel x1 = new JLabel("İlanı Kaldırılacak İşletme No:");
        x1.setBounds(100, 460, 200, 50);
        add(x1);
        x1.setVisible(true);

        this.arsaID = new JTextField();
        arsaID.setBounds(270, 470, 60, 30);
        add(arsaID);
        arsaID.setVisible(true);

        JLabel x2 = new JLabel("Emlakçı No:");
        x2.setBounds(100, 560, 200, 50);
        add(x2);
        x2.setVisible(true);

        this.emlakci = new JTextField();
        emlakci.setBounds(185, 570, 60, 30);
        add(emlakci);
        emlakci.setVisible(true);

        JLabel x3 = new JLabel("İşletme No:");
        x3.setBounds(280, 560, 200, 50);
        add(x3);
        x3.setVisible(true);

        this.isletme_no = new JTextField();
        isletme_no.setBounds(350, 570, 60, 30);
        add(isletme_no);
        isletme_no.setVisible(true);

        JLabel x4 = new JLabel("İşletme Sahibi Kullanıcı No:");
        x4.setBounds(450, 560, 200, 50);
        add(x4);
        x4.setVisible(true);

        this.islem_yapan_no = new JTextField();
        islem_yapan_no.setBounds(610, 570, 60, 30);
        add(islem_yapan_no);
        islem_yapan_no.setVisible(true);

        JLabel x5 = new JLabel("İşlem Tipi:");
        x5.setBounds(715, 560, 200, 50);
        add(x5);
        x5.setVisible(true);

        this.islemTipi = new JTextField();
        islemTipi.setBounds(795, 570, 60, 30);
        add(islemTipi);
        islemTipi.setVisible(true);

        JLabel x6 = new JLabel("Ücreti:");
        x6.setBounds(900, 560, 200, 50);
        add(x6);
        x6.setVisible(true);

        this.ucret = new JTextField();
        ucret.setBounds(970, 570, 60, 30);
        add(ucret);
        ucret.setVisible(true);

        JButton ilanKaldir = new JButton("İlan Kaldır");
        ilanKaldir.setBounds(350, 470, 100, 30);
        add(ilanKaldir);
        ilanKaldir.setVisible(true);
        ilanKaldir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int kaldirilacak_isletmeNo = Integer.parseInt(arsaID.getText());

                try {
                    DatabaseConnect db = new DatabaseConnect();
                    Connection conn = db.getConnection();

                    PreparedStatement state2 = conn.prepareStatement("DELETE FROM isletme_ilan  WHERE islem_yapilan_isletme_no=?;");
                    state2.setInt(1, kaldirilacak_isletmeNo);
                    state2.executeUpdate();

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

        JButton ilanEkle = new JButton("İlan Ekle");
        ilanEkle.setBounds(1050, 570, 100, 30);
        add(ilanEkle);
        ilanEkle.setVisible(true);
        ilanEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    DatabaseConnect db = new DatabaseConnect();
                    Connection conn = db.getConnection();

                    PreparedStatement state2 = conn.prepareStatement("INSERT isletme_ilan  VALUES(?,?,?,?,? )");
                    state2.setInt(1, Integer.parseInt(emlakci.getText()));
                    state2.setInt(2, Integer.parseInt(isletme_no.getText()));
                    state2.setInt(3, Integer.parseInt(islem_yapan_no.getText()));
                    state2.setString(4, islemTipi.getText());
                    state2.setInt(5, Integer.parseInt(ucret.getText()));
                    state2.executeUpdate();

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

        String[] kolonlar = {"Emlakçı No", "İşletme No", "İşletme Sahibi Kullanıcı No", "İşlem Tipi", " Ücret "};

        try {

            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select * from  isletme_ilan ");

            Statement myStat2 = conn.createStatement();
            ResultSet res2 = myStat2.executeQuery("select COUNT(*) from  isletme_ilan");
            res2.next();
            int satir_sayisi = res2.getInt(1);
            //System.out.println("satir sayisi = "+satir_sayisi);          
            Object[][] tabloDegerleri = new Object[50][5];
            int k = 0;
            while (res.next()) {

                for (int i = 0; i < 5; i++) {
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
