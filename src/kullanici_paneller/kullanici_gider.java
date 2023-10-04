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
public class kullanici_gider extends JPanel {

    int kullanici_no;
    JScrollPane sc;

    public kullanici_gider(int no) {
        super();
        setSize(620, 1080);
        this.kullanici_no = no;
        setLayout(null);
        setBackground(Color.decode("#334671"));

        JLabel yazi = new JLabel("Kullanıcı Gider Tablosu");
        yazi.setVisible(true);
        yazi.setBounds(180, 30, 310, 40);
        yazi.setFont(yazi.getFont().deriveFont(Font.BOLD, 25));
        add(yazi);
        yazi.setForeground(Color.BLACK);
        JLabel yazi2 = new JLabel("Kullanıcı No = " + this.kullanici_no);
        yazi2.setVisible(true);
        yazi2.setBounds(240, 70, 220, 40);
        yazi2.setFont(yazi2.getFont().deriveFont(Font.ITALIC, 20));
        add(yazi2);
        yazi2.setForeground(Color.BLACK);
        tabloOlustur();

    }

    public void tabloOlustur() {
        DatabaseConnect db = new DatabaseConnect();
        Connection conn = db.getConnection();

        try {
            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select * from kullanici_gider where kullanici_no="+this.kullanici_no+" ORDER BY tarihi");
            Object[] sutunisim = {"Kullanıcı NO", "Açıklama","Miktar","Tarih"};

            //Statement myStat3 = conn.createStatement();
            // ResultSet res2 = myStat3.executeQuery("select COUNT(*) from Kullanici");
            //ResultSetMetaData rsMetaData=res.getMetaData();
            //int satirSayisi = rsMetaData.getColumnCount();
            DefaultTableModel model = new DefaultTableModel(sutunisim, 0);

            //Object[][] veriler = new Object[2][satirSayisi];
            while (res.next()) {
                
                Object[] satir = {res.getInt(1), res.getString(2),res.getInt(3),res.getDate(4)};
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
        }
    }

}
