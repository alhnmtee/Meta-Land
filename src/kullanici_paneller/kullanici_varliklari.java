/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kullanici_paneller;

import main.DatabaseConnect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author kerem
 */
public class kullanici_varliklari extends JPanel {

    int kullanici_no;
    JScrollPane sc;

    public kullanici_varliklari(int no) {
        super();
        setSize(620, 1080);
        this.kullanici_no = no;
        setLayout(null);
        setBackground(Color.decode("#334671"));

        JLabel yazi = new JLabel("Sahibi Olunan Arsalar");
        yazi.setVisible(true);
        yazi.setBounds(180, 70, 310, 40);
        yazi.setFont(yazi.getFont().deriveFont(Font.BOLD, 25));
        add(yazi);
        yazi.setForeground(Color.BLACK);
        JLabel yazi2 = new JLabel("Kullanıcı No = " + this.kullanici_no);
        yazi2.setVisible(true);
        yazi2.setBounds(240, 30, 220, 40);
        yazi2.setFont(yazi2.getFont().deriveFont(Font.ITALIC, 20));
        add(yazi2);
        yazi2.setForeground(Color.BLACK);
         JLabel yazi21 = new JLabel("Sahibi Olunan İşletmeler");
        yazi21.setVisible(true);
        yazi21.setBounds(180, 450, 310, 40);
        yazi21.setFont(yazi21.getFont().deriveFont(Font.BOLD, 25));
        add(yazi21);
        yazi21.setForeground(Color.BLACK);
      //  System.out.println("a");
        tabloOlustur();
        tabloOlustur2();

    }

    public void tabloOlustur() {
        DatabaseConnect db = new DatabaseConnect();
        Connection conn = db.getConnection();

        try {
            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select * from alan where alan_sahibi="+this.kullanici_no);
            Object[] sutunisim = {"Alan NO", "Alan Sahibi","Alan Türü"};

            //Statement myStat3 = conn.createStatement();
            // ResultSet res2 = myStat3.executeQuery("select COUNT(*) from Kullanici");
            //ResultSetMetaData rsMetaData=res.getMetaData();
            //int satirSayisi = rsMetaData.getColumnCount();
            DefaultTableModel model = new DefaultTableModel(sutunisim, 0);

            //Object[][] veriler = new Object[2][satirSayisi];
            while (res.next()) {
                
                Object[] satir = {res.getInt(1), res.getInt(2),res.getString(3)};
                model.addRow(satir);
            }

            JTable table = new JTable(model);
            table.setBounds(40, 120, 520, 300);
            JScrollPane sp = new JScrollPane(table);
            sp.setBounds(40, 120, 520, 300);

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
            ResultSet res = myStat.executeQuery("select * from isletme where isletme_sahibi="+this.kullanici_no);
            Object[] sutunisim = {"İşletme NO", "Alan No","Alan Türü","Seviyesi","Seviye Başlangıç Tarihi"};

            //Statement myStat3 = conn.createStatement();
            // ResultSet res2 = myStat3.executeQuery("select COUNT(*) from Kullanici");
            //ResultSetMetaData rsMetaData=res.getMetaData();
            //int satirSayisi = rsMetaData.getColumnCount();
            DefaultTableModel model = new DefaultTableModel(sutunisim, 0);

            //Object[][] veriler = new Object[2][satirSayisi];
            while (res.next()) {
                
                Object[] satir = {res.getInt(1), res.getInt(2),res.getString(4),res.getInt(5),res.getDate(10)};
                model.addRow(satir);
            }

            JTable table = new JTable(model);
            table.setBounds(40, 520, 520, 300);
            JScrollPane sp = new JScrollPane(table);
            sp.setBounds(40, 520, 520, 300);

            add(sp);
            sc = sp;
            sp.setVisible(true);
            setVisible(true);
        } catch (Exception e) {
        }
    }

}
