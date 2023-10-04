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
import java.sql.*;

/**
 *
 * @author kerem
 */
public class isletme_ilan_verme extends JPanel {

    int kullanici_no;
    JScrollPane sc;
    JTextField isletme_no;
    JTextField arsaNo;
    JTextField ucret;
    JTextField emlakci_alan_no;
    JComboBox<String> islem_tipi;
    JTextField kira_gun_sayisi;

    DatabaseConnect db = new DatabaseConnect();
    Connection conn = db.getConnection();

    public isletme_ilan_verme(int no) {
        super();
        setSize(620, 1080);
        this.kullanici_no = no;
        setLayout(null);
        setBackground(Color.decode("#334671"));

        JButton arsailan=new JButton("Arsa İlanı");
        arsailan.setBounds(100,400,200,50);
        add(arsailan);

        JButton isletmeilan=new JButton("İşletme İlanı");
        isletmeilan.setBounds(350,400,200,50);
        add(isletmeilan);


        isletmeilan.addActionListener(e -> {
            isletmeilan.setVisible(false);
            arsailan.setVisible(false);
            tabloOlustur();
            islem_tipi = new JComboBox<>();
            islem_tipi.addItem("Kira");
            islem_tipi.addItem("Satış");
            islem_tipi.setSelectedItem("Kira");
            islem_tipi.setBounds(370, 540, 80, 30);
            add(islem_tipi);
            JLabel yazi = new JLabel("Sahip Olduğunuz İşletmeler");
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

            isletme_no = new JTextField("İşletme No");
            isletme_no.setBounds(50, 540, 80, 30);
            isletme_no.setVisible(true);
            add(isletme_no);

            isletme_no.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    isletme_no.setText("");
                }
            });

            ucret = new JTextField("Ücret");
            ucret.setBounds(150, 540, 80, 30);
            ucret.setVisible(true);
            add(ucret);

            JButton ilanVer = new JButton("İlan Ver");
            ilanVer.setBounds(470, 540, 80, 30);
            add(ilanVer);

            ilanVer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int isletmeN = Integer.parseInt(isletme_no.getText());

                    try {

                        Statement st = conn.createStatement();
                        ResultSet set = st.executeQuery("Select * from isletme where isletme_sahibi=" + kullanici_no + " AND isletme_no=" + isletmeN);
                        if (set.next()) {
                            System.out.println("Kullanici No "+kullanici_no);
                            PreparedStatement stm = conn.prepareStatement("INSERT isletme_ilan VALUES(?,?,?,?,?)");
                            int emlakci_no=Integer.parseInt(emlakci_alan_no.getText());
                            stm.setInt(1,emlakci_no);
                            stm.setInt(2, isletmeN);
                            stm.setInt(3, kullanici_no);
                            String tip = (String)islem_tipi.getSelectedItem();
                            stm.setString(4,tip );
                            stm.setInt(5, Integer.parseInt(ucret.getText()));
                            stm.executeUpdate();
                            JLabel islemBasarili=new JLabel("İşlem Başarılı.");
                            islemBasarili.setBounds(200,850,200,40);
                            add(islemBasarili);
                            islemBasarili.setVisible(true);
                            islemBasarili.setForeground(Color.GREEN);
                            islemBasarili.setFont(islemBasarili.getFont().deriveFont(Font.ITALIC,15));

                        } else {
                            JLabel islemBasarili=new JLabel("İşlem Başarısız.");
                            islemBasarili.setBounds(200,850,200,40);
                            add(islemBasarili);
                            islemBasarili.setVisible(true);
                            islemBasarili.setForeground(Color.RED);
                            islemBasarili.setFont(islemBasarili.getFont().deriveFont(Font.ITALIC,15));
                            System.out.println("işletme kendsinini değil");
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            });

            ucret.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    ucret.setText("");
                }
            });

            emlakci_alan_no = new JTextField("Emlakçı İşletme No");
            emlakci_alan_no.setBounds(250, 540, 100, 30);
            emlakci_alan_no.setVisible(true);
            add(emlakci_alan_no);

            emlakci_alan_no.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    emlakci_alan_no.setText("");
                }
            });

        });
        JButton ilanVer = new JButton("İlan Ver");
        ilanVer.setBounds(470, 540, 80, 30);
        add(ilanVer);
        ilanVer.setVisible(false);
        ilanVer.addActionListener(e1 -> {
            int arsaNum = Integer.parseInt(arsaNo.getText());
            int ucreti=Integer.parseInt(ucret.getText());

            try{
                Statement st = conn.createStatement();
                ResultSet set = st.executeQuery("Select * from Alan where alan_sahibi=" + kullanici_no + " AND alan_no=" + arsaNum+" AND alan_turu='arsa';");
                if (set.next()) {

                    PreparedStatement stm = conn.prepareStatement("INSERT Arsa_ilan VALUES(?,?,?,?)");
                    int emlakci_no=Integer.parseInt(emlakci_alan_no.getText());
                    stm.setInt(1,emlakci_no);
                    stm.setInt(2, arsaNum);
                    stm.setInt(3, kullanici_no);
                    stm.setInt(4,ucreti );
                    stm.executeUpdate();

                    JLabel islemBasarili=new JLabel("İşlem Başarılı.");
                    islemBasarili.setBounds(200,850,200,40);
                    add(islemBasarili);
                    islemBasarili.setVisible(true);
                    islemBasarili.setForeground(Color.GREEN);
                    islemBasarili.setFont(islemBasarili.getFont().deriveFont(Font.ITALIC,15));

                } else {
                    JLabel islemBasarili=new JLabel("İşlem Başarısız.");
                    islemBasarili.setBounds(200,850,200,40);
                    add(islemBasarili);
                    islemBasarili.setVisible(true);
                    islemBasarili.setForeground(Color.RED);
                    islemBasarili.setFont(islemBasarili.getFont().deriveFont(Font.ITALIC,15));
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });

        arsailan.addActionListener(e -> {
            arsailan.setVisible(false);
            isletmeilan.setVisible(false);
            tabloOlusturArsa();
            ilanVer.setVisible(true);
            JLabel yazi = new JLabel("Sahip Olduğunuz Arsalar");
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

            arsaNo = new JTextField("Arsa No");
            arsaNo.setBounds(50, 540, 80, 30);
            arsaNo.setVisible(true);
            add(arsaNo);

            arsaNo.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    arsaNo.setText("");
                }
            });

            ucret = new JTextField("Ücret");
            ucret.setBounds(150, 540, 80, 30);
            ucret.setVisible(true);
            add(ucret);
            ucret.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    ucret.setText("");
                }
            });
            emlakci_alan_no = new JTextField("Emlakçı İşletme No");
            emlakci_alan_no.setBounds(250, 540, 100, 30);
            emlakci_alan_no.setVisible(true);
            add(emlakci_alan_no);

            emlakci_alan_no.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    emlakci_alan_no.setText("");
                }
            });



        });

    }

    public void tabloOlustur() {

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

    public void tabloOlusturArsa() {

        try {
            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select * from Alan where alan_turu='arsa' AND alan_sahibi="+kullanici_no+";");
            Object[] sutunisim = {"Alan No", "Alan Sahibi", "Türü"};

            //Statement myStat3 = conn.createStatement();
            // ResultSet res2 = myStat3.executeQuery("select COUNT(*) from Kullanici");
            //ResultSetMetaData rsMetaData=res.getMetaData();
            //int satirSayisi = rsMetaData.getColumnCount();
            DefaultTableModel model = new DefaultTableModel(sutunisim, 0);

            //Object[][] veriler = new Object[2][satirSayisi];
            while (res.next()) {

                Object[] satir = {res.getInt(1), res.getInt(2), res.getString(3)};
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
}
