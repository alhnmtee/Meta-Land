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
public class arsa_ilan extends JPanel {
    
    JScrollPane sc;
    JTextField arsaID;
    
    JTextField emlakci;
    JTextField arsa;
    JTextField satanKisi;
    JTextField ucret;
    
    
    public arsa_ilan() {
        super();
        setLayout(null);
        setSize(1400, 800);
        setBackground(Color.decode("#c62cfe"));
        JLabel x1 = new JLabel("İlanı Kaldırılacak Alan No:");
        x1.setBounds(200, 460, 200, 50);
        add(x1);
        x1.setVisible(true);
        
        this.arsaID = new JTextField();
        arsaID.setBounds(370, 470, 60, 30);
        add(arsaID);
        arsaID.setVisible(true);
        
        JLabel x2 = new JLabel("Emlakçı No:");
        x2.setBounds(200, 560, 200, 50);
        add(x2);
        x2.setVisible(true);
        
        this.emlakci = new JTextField();
        emlakci.setBounds(285, 570, 60, 30);
        add(emlakci);
        emlakci.setVisible(true);
        
        JLabel x3 = new JLabel("Arsa No:");
        x3.setBounds(380, 560, 200, 50);
        add(x3);
        x3.setVisible(true);
        
        this.arsa = new JTextField();
        arsa.setBounds(450, 570, 60, 30);
        add(arsa);
        arsa.setVisible(true);
        
        JLabel x4 = new JLabel("Satan Kişi No:");
        x4.setBounds(550, 560, 200, 50);
        add(x4);
        x4.setVisible(true);
        
        this.satanKisi = new JTextField();
        satanKisi.setBounds(650, 570, 60, 30);
        add(satanKisi);
        satanKisi.setVisible(true);
        
        JLabel x5 = new JLabel("Ücret:");
        x5.setBounds(750, 560, 200, 50);
        add(x5);
        x5.setVisible(true);
        
        this.ucret = new JTextField();
        ucret.setBounds(805, 570, 60, 30);
        add(ucret);
        ucret.setVisible(true);
        
        
        
        tabloOlustur();
        
        JButton ilanKaldir = new JButton("İlan Kaldır");
        ilanKaldir.setBounds(450, 470, 100, 30);
        add(ilanKaldir);
        ilanKaldir.setVisible(true);
        ilanKaldir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int kaldirilacakArsaNo = Integer.parseInt(arsaID.getText());
                
                try {
                    DatabaseConnect db = new DatabaseConnect();
                    Connection conn = db.getConnection();
                    
                    PreparedStatement state2 = conn.prepareStatement("DELETE FROM Arsa_ilan  WHERE satilan_arsa_no=?;");
                    state2.setInt(1, kaldirilacakArsaNo);
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
        ilanEkle.setBounds(880, 570, 100, 30);
        add(ilanEkle);
        ilanEkle.setVisible(true);
        ilanEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                try {
                    DatabaseConnect db = new DatabaseConnect();
                    Connection conn = db.getConnection();
                    
                    PreparedStatement state2 = conn.prepareStatement("INSERT Arsa_ilan VALUES(?,?,?,?)");
                    state2.setInt(1,Integer.parseInt(emlakci.getText()) );
                    state2.setInt(2, Integer.parseInt(arsa.getText()));
                    state2.setInt(3,Integer.parseInt(satanKisi.getText()) );
                    state2.setInt(4,Integer.parseInt(ucret.getText()) );
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
        
        String[] kolonlar = {"Emlakçı No", "Alan No", "Satan kişi No", " Ücret "};
        
        try {
            
            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select * from  Arsa_ilan ");
            
            Statement myStat2 = conn.createStatement();
            ResultSet res2 = myStat2.executeQuery("select COUNT(*) from  Arsa_ilan");
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
                        tabloDegerleri[k][2] = res.getInt(3);
                    } else if (i == 3) {
                        tabloDegerleri[k][3] = res.getInt(4);
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
