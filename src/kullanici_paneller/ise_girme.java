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
public class ise_girme extends JPanel {

    int kullanici_no;
    JScrollPane sc;
    JTextField jisletmeNo;

    Icon iseGir_icon = new ImageIcon(this.getClass().getResource("/resim/ise_giris.png"));
    JButton iseGir = new JButton(iseGir_icon);

    Icon isAyril = new ImageIcon(this.getClass().getResource("/resim/isAyril.gif"));

    JButton istenAyril = new JButton(isAyril);

    public ise_girme(int no) {
        super();
        setSize(620, 1080);
        this.kullanici_no = no;
        setLayout(null);
        setBackground(Color.decode("#334671"));
        JLabel yazi = new JLabel("Güncel İş İlanları");
        yazi.setVisible(true);
        yazi.setBounds(170, 30, 310, 40);
        yazi.setFont(yazi.getFont().deriveFont(Font.BOLD, 25));
        add(yazi);
        yazi.setForeground(Color.BLACK);
        JLabel yazi2 = new JLabel("Kullanıcı No = " + this.kullanici_no);
        yazi2.setVisible(true);
        yazi2.setBounds(200, 70, 220, 40);
        yazi2.setFont(yazi2.getFont().deriveFont(Font.ITALIC, 20));
        add(yazi2);
        yazi2.setForeground(Color.BLACK);
        tabloOlustur();

        jisletmeNo = new JTextField("İsletme No");
        jisletmeNo.setBounds(180, 710, 70, 30);
        jisletmeNo.addFocusListener(new FocusAdapter() {

            public void focusGained(FocusEvent e) {
                jisletmeNo.setText("");
            }
        });

        add(jisletmeNo);
        jisletmeNo.setVisible(true);

        iseGir.setBounds(300, 700, 50, 50);
        iseGir.setVisible(true);
        iseGir.setOpaque(false);
        iseGir.setContentAreaFilled(false);
        iseGir.setBorderPainted(false);

        add(iseGir);
        istenAyril.setVisible(true);
        istenAyril.setBounds(400, 700, 50, 50);
        add(istenAyril);

        istenAyril.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DatabaseConnect db = new DatabaseConnect();
                Connection conn = db.getConnection();

                try {
                    String silinecekKayıtVerileri = "Select * from kullanici_guncel_calisma WHERE Kullanici_no=" + kullanici_no;
                    Statement st = conn.createStatement();
                    ResultSet silinecelVeriler = st.executeQuery(silinecekKayıtVerileri);
                    silinecelVeriler.next();

                    String guncelTarihi = "Select oyun_guncel_tarihi from baslangic";
                    Statement x = conn.createStatement();
                    ResultSet guncelTarih = x.executeQuery(guncelTarihi);
                    guncelTarih.next();

                    String baslamaTarihFark = "SELECT DATEDIFF(day,?,?)";
                    PreparedStatement gunFarki = conn.prepareStatement(baslamaTarihFark);

                    gunFarki.setDate(1, silinecelVeriler.getDate(3));
                    gunFarki.setDate(2, guncelTarih.getDate(1));

                    ResultSet day = gunFarki.executeQuery();
                    day.next();

                    String kayıtlaraEkle = "INSERT Calisma_kayit VALUES (?,?,?,?,?,?)";
                    PreparedStatement pst = conn.prepareStatement(kayıtlaraEkle);
                    pst.setInt(1, kullanici_no);
                    pst.setInt(2, silinecelVeriler.getInt(1));
                    pst.setDate(3, silinecelVeriler.getDate(3));
                    pst.setDate(4, guncelTarih.getDate(1));
                    pst.setInt(5, day.getInt(1));
                    pst.setInt(6, silinecelVeriler.getInt(5));
                    pst.executeUpdate();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {
                    String silme = "DELETE FROM kullanici_guncel_calisma WHERE kullanici_no=" + kullanici_no;
                    Statement sil = conn.createStatement();
                    sil.executeUpdate(silme);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                istenAyril.setVisible(false);
                iseGir.setVisible(true);
                jisletmeNo.setVisible(true);

            }
        });

        iseGir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int islemBasari = 0;
                DatabaseConnect db = new DatabaseConnect();
                Connection conn = db.getConnection();

                try {

                    Statement st = conn.createStatement();
                    String sorgu = "select * from isletme where isletme_no=" + Integer.parseInt(jisletmeNo.getText()) + " AND isletme_Sahibi=" + kullanici_no;
                    ResultSet sonuc = st.executeQuery(sorgu);
                    if (sonuc.next()) {
                        System.out.println("İşletme Kendisinin");
                    } else {
                        Statement baslangic_tarihi = conn.createStatement();
                        String bas = "Select oyun_guncel_tarihi from baslangic";
                        ResultSet basTarih = baslangic_tarihi.executeQuery(bas);
                        basTarih.next();

                        Statement maas = conn.createStatement();
                        String maasSorgu = "select ucret,calisma_saati from is_ilan where ilan_veren_isletme_no=" + Integer.parseInt(jisletmeNo.getText());
                        ResultSet maasi = maas.executeQuery(maasSorgu);
                        maasi.next();
                        // saati ekliyicem
                        PreparedStatement stm = conn.prepareStatement("INSERT kullanici_guncel_calisma VALUES(?,?,?,?,?)");
                        stm.setInt(1, Integer.parseInt(jisletmeNo.getText()));
                        stm.setInt(2, kullanici_no);
                        stm.setDate(3, basTarih.getDate(1));
                        stm.setInt(4, maasi.getInt(1));
                        stm.setInt(5, maasi.getInt(2));

                        stm.executeUpdate();
                        islemBasari = 1;
                        if (!(Integer.parseInt(jisletmeNo.getText()) == 1 || Integer.parseInt(jisletmeNo.getText()) == 2 || Integer.parseInt(jisletmeNo.getText()) == 3)) {
                            String silme = "DELETE FROM is_ilan WHERE ilan_veren_isletme_no=" + Integer.parseInt(jisletmeNo.getText());
                            Statement sil = conn.createStatement();
                            sil.executeUpdate(silme);
                        } else {
                            System.out.println("Yöneticinin Yanında İlan Sayısı Sınırsız");
                        }
                    }

                } catch (Exception ex) {
                    System.out.println("hata");
                    ex.printStackTrace();
                }

                if (islemBasari == 1) {
                    iseGir.setVisible(false);
                    jisletmeNo.setVisible(false);
                    istenAyril.setVisible(true);
                }
                remove(sc);
                tabloOlustur();
                repaint();
            }
        });

        DatabaseConnect db = new DatabaseConnect();
        Connection conn = db.getConnection();

        try {

            PreparedStatement st = conn.prepareStatement("SELECT x.kullanici_no FROM kullanici_guncel_calisma AS x WHERE x.kullanici_no=?");
            //   System.out.println(kullanici_no);
            st.setInt(1, kullanici_no);
            ResultSet sonuc = st.executeQuery();
            if (sonuc.next()) {
                iseGir.setVisible(false);
                jisletmeNo.setVisible(false);
            } else {
                istenAyril.setVisible(false);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void tabloOlustur() {
        DatabaseConnect db = new DatabaseConnect();
        Connection conn = db.getConnection();

        try {
            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("SELECT y.ilan_veren_isletme_no, x.turu, y.ucret, y.calisma_saati FROM isletme AS x, is_ilan AS y WHERE x.isletme_no=y.ilan_veren_isletme_no");
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
            table.setBounds(50, 200, 500, 400);
            JScrollPane sp = new JScrollPane(table);
            sp.setBounds(50, 200, 500, 400);

            add(sp);
            sc = sp;
            sp.setVisible(true);
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
