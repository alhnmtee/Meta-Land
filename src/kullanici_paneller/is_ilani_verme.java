/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kullanici_paneller;

import main.DatabaseConnect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author kerem
 */
public class is_ilani_verme extends JPanel {

    int kullanici_no;
    JScrollPane sc;
    JScrollPane sc2;

    JTextField isletme_no;
    JTextField ucret;
    JTextField saat;
    JLabel sonuclabel;
    JTextField isletmeNo2;

    public is_ilani_verme(int no) {
        super();
        setSize(620, 1080);
        this.kullanici_no = no;
        setLayout(null);
        setBackground(Color.decode("#334671"));

        JLabel yazi = new JLabel("İş İlanı Verebileceğiniz İşletmeler");
        yazi.setVisible(true);
        yazi.setBounds(120, 100, 510, 40);
        yazi.setFont(yazi.getFont().deriveFont(Font.BOLD, 25));
        add(yazi);
        yazi.setForeground(Color.BLACK);
        JLabel yazi2 = new JLabel("Kullanıcı No = " + this.kullanici_no);
        yazi2.setVisible(true);
        yazi2.setBounds(240, 60, 220, 40);
        yazi2.setFont(yazi2.getFont().deriveFont(Font.ITALIC, 20));
        add(yazi2);
        yazi2.setForeground(Color.BLACK);

        tabloOlustur();
        tabloOlustur2();

        isletme_no = new JTextField("İşletme No");
        isletme_no.setBounds(100, 540, 80, 30);
        isletme_no.setVisible(true);
        add(isletme_no);

        ucret = new JTextField("Ücret");
        ucret.setBounds(200, 540, 80, 30);
        ucret.setVisible(true);
        add(ucret);

        saat = new JTextField("Saat");
        saat.setBounds(300, 540, 80, 30);
        saat.setVisible(true);
        add(saat);

        isletme_no.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                isletme_no.setText("");
            }
        });

        ucret.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                ucret.setText("");
            }
        });

        saat.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                saat.setText("");
            }
        });

        isletmeNo2 = new JTextField("İşletme No");
        isletmeNo2.setBounds(300, 600, 80, 30);
        isletmeNo2.setVisible(true);
        add(isletmeNo2);

        sonuclabel = new JLabel();
        sonuclabel.setBounds(50, 600, 280, 30);
        sonuclabel.setVisible(false);
        add(sonuclabel);

        isletmeNo2.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                isletmeNo2.setText("");
            }
        });

        JButton ilanKaldir = new JButton("İlan Kaldır");
        ilanKaldir.setBounds(400, 600, 100, 30);
        ilanKaldir.setVisible(true);
        add(ilanKaldir);
        ilanKaldir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int isletmeNo_2 = Integer.parseInt(isletmeNo2.getText());

                DatabaseConnect db = new DatabaseConnect();
                Connection conn = db.getConnection();

                try {
                    Statement st = conn.createStatement();
                    ResultSet sonuc = st.executeQuery("SELECT * FROM isletme WHERE isletme_sahibi=" + kullanici_no + " AND isletme_no=" + isletmeNo_2);
                    if (sonuc.next()) {
                        Statement stm = conn.createStatement();
                        stm.executeUpdate("DELETE from is_ilan where ilan_veren_isletme_no=" + isletmeNo_2);
                    } else {
                        System.out.println("ilan sahibi değil");
                        sonuclabel.setText("İlanın Sahibi Değilsiniz");
                        sonuclabel.setVisible(true);

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                remove(sc2);
                tabloOlustur2();
                repaint();
            }
        });

        JButton ilanVer = new JButton("İlan Ver");
        ilanVer.setBounds(400, 540, 100, 30);
        ilanVer.setVisible(true);
        ilanVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int isletmeNo = Integer.parseInt(isletme_no.getText());
                int ucreti = Integer.parseInt(ucret.getText());
                int saati = Integer.parseInt(saat.getText());

                DatabaseConnect db = new DatabaseConnect();
                Connection conn = db.getConnection();

                try {
                    Statement st = conn.createStatement();
                    ResultSet sonuc = st.executeQuery("SELECT * FROM is_ilan WHERE ilan_veren_isletme_no=" + isletmeNo);
                    if (sonuc.next()) {
                        sonuclabel.setText("İlanınız Mevcut");
                        sonuclabel.setVisible(true);
                    } else {
                        Statement stm = conn.createStatement();
                        ResultSet sonc = stm.executeQuery("SELECT * from isletme WHERE isletme_no=" + isletmeNo + " AND isletme_sahibi=" + kullanici_no);
                        if (sonc.next()) {

                            Statement stm2 = conn.createStatement();
                            ResultSet sonc2 = stm.executeQuery("SELECT kapasitesi, calisan_sayisi from isletme WHERE isletme_no=" + isletmeNo + " AND isletme_sahibi=" + kullanici_no);
                            sonc2.next();
                            int isletmedeKapasitesi = sonc2.getInt(1);
                            int isletmeMaxCalisanSayisi = sonc2.getInt(2);
                            System.out.println("işletme max kapasite calışan sayısı " + isletmedeKapasitesi + " - " + isletmeMaxCalisanSayisi);
                            Statement stm4 = conn.createStatement();
                            ResultSet sonc4 = stm.executeQuery("SELECT COUNT(*),SUM(calisma_saat) from kullanici_guncel_calisma WHERE isletme_no=" + isletmeNo);
                            sonc4.next();
                            System.out.println("aktif çalışan sayısı = " + sonc4.getInt(1) + " kullanılan kapasite =" + sonc4.getInt(2));
                            int aktifCalisanSayisi = sonc4.getInt(1);
                            int kullanilanKapasite = sonc4.getInt(2);

                            if (isletmedeKapasitesi >= kullanilanKapasite + saati && isletmeMaxCalisanSayisi >= aktifCalisanSayisi + 1) {
                                PreparedStatement sta = conn.prepareStatement("INSERT is_ilan VALUES(?,?,?)");
                                sta.setInt(1, isletmeNo);
                                sta.setInt(2, ucreti);
                                sta.setInt(3, saati);
                                sta.executeUpdate();
                                sonuclabel.setText("İlanınızı Verdiniz");
                                sonuclabel.setVisible(true);
                            } else {
                                if (kullanilanKapasite + saati > isletmedeKapasitesi) {
                                    sonuclabel.setText("İşletmenin Maximum Kapasitesini Aştınız");
                                    sonuclabel.setVisible(true);
                                } else {
                                    System.out.println("İşletmenizindeki çalışan sayısı maximum düzeyde");
                                    sonuclabel.setText("İşletmenin Çalışan Sayısını Aştınız");
                                    sonuclabel.setVisible(true);
                                }
                            }

                        } else {
                            sonuclabel.setText("İşletme Size Ait Değil");
                            sonuclabel.setVisible(true);
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                remove(sc2);
                tabloOlustur2();
                repaint();
            }
        });
        add(ilanVer);

    }

    public void tabloOlustur() {
        DatabaseConnect db = new DatabaseConnect();
        Connection conn = db.getConnection();

        try {
            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select * from isletme where isletme_sahibi=" + this.kullanici_no);
            Object[] sutunisim = {"İşletme NO", "Alan No", "Alan Türü", "Seviyesi", "Seviye Başlangıç Tarihi"};

            //Statement myStat3 = conn.createStatement();
            // ResultSet res2 = myStat3.executeQuery("select COUNT(*) from Kullanici");
            //ResultSetMetaData rsMetaData=res.getMetaData();
            //int satirSayisi = rsMetaData.getColumnCount();
            DefaultTableModel model = new DefaultTableModel(sutunisim, 0);

            //Object[][] veriler = new Object[2][satirSayisi];
            while (res.next()) {

                Object[] satir = {res.getInt(1), res.getInt(2), res.getString(4), res.getInt(5), res.getDate(10)};
                model.addRow(satir);
            }

            JTable table = new JTable(model);
            table.setBounds(40, 220, 520, 300);
            JScrollPane sp = new JScrollPane(table);
            sp.setBounds(40, 220, 520, 300);

            add(sp);
            sc = sp;
            sp.setVisible(true);
            setVisible(true);
        } catch (Exception e) {
        }
    }

    public void tabloOlustur2() {
        DatabaseConnect db = new DatabaseConnect();
        Connection conn = db.getConnection();

        try {
            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("SELECT y.ilan_veren_isletme_no, x.turu, y.ucret, y.calisma_saati FROM isletme AS x, is_ilan AS y WHERE x.isletme_no=y.ilan_veren_isletme_no AND x.isletme_sahibi=" + kullanici_no);
            Object[] sutunisim = {"İşletme NO", "İşletme Türü", "Ücret", "Çalışma Saati"};

            //Statement myStat3 = conn.createStatement();
            // ResultSet res2 = myStat3.executeQuery("select COUNT(*) from Kullanici");
            //ResultSetMetaData rsMetaData=res.getMetaData();
            //int satirSayisi = rsMetaData.getColumnCount();
            DefaultTableModel model = new DefaultTableModel(sutunisim, 0);

            //Object[][] veriler = new Object[2][satirSayisi];
            while (res.next()) {
                //   System.out.println(res.getString(2));
                Object[] satir = {res.getInt(1), res.getString(2), res.getInt(3), res.getInt(4)};
                model.addRow(satir);
            }

            JTable table = new JTable(model);
            table.setBounds(40, 650, 520, 300);
            JScrollPane sp = new JScrollPane(table);
            sp.setBounds(40, 650, 520, 300);

            add(sp);
            sc2 = sp;
            sp.setVisible(true);
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
