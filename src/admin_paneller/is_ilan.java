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
public class is_ilan extends JPanel {
    
    JScrollPane sc;
    JTextField isletme_no;
    
    JTextField eklenecek_no;
    JTextField yeni_ucret;
    JTextField yeni_saat;
    JTextField ilanEkle;
    public is_ilan() {
        super();
        setLayout(null);
        setSize(1400, 800);
        setBackground(Color.decode("#c62cfe"));
        tabloOlustur();
        
        JLabel x2 = new JLabel("İlanı Silinecek İşletme No:");
        x2.setBounds(200, 460, 200, 50);
        add(x2);
        x2.setVisible(true);
        
        this.isletme_no = new JTextField();
        isletme_no.setBounds(350, 470, 60, 30);
        add(isletme_no);
        isletme_no.setVisible(true);
        
        JLabel x3 = new JLabel("İlana Eklenecek İşletme No:");
        x3.setBounds(190, 560, 200, 50);
        add(x3);
        x3.setVisible(true);
        
        this.eklenecek_no = new JTextField();
        eklenecek_no.setBounds(350, 570, 60, 30);
        add(eklenecek_no);
        eklenecek_no.setVisible(true);
        
        JLabel x4 = new JLabel("İlana Eklenecek Çalışan Ücret:");
        x4.setBounds(430, 560, 200, 50);
        add(x4);
        x4.setVisible(true);
        
        this.yeni_ucret = new JTextField();
        yeni_ucret.setBounds(610, 570, 60, 30);
        add(yeni_ucret);
        yeni_ucret.setVisible(true);
        
        JLabel x5 = new JLabel("İlana Eklenecek Çalışma Saati:");
        x5.setBounds(710, 560, 200, 50);
        add(x5);
        x5.setVisible(true);
        
        this.yeni_saat = new JTextField();
        yeni_saat.setBounds(890, 570, 60, 30);
        add(yeni_saat);
        yeni_saat.setVisible(true);
        
        JButton ilanEkle = new JButton("İlan Ekle");
        ilanEkle.setBounds(990, 570, 180, 30);
        add(ilanEkle);
        ilanEkle.setVisible(true);
        ilanEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseConnect db = new DatabaseConnect();
                Connection conn = db.getConnection();
                
                 try {
                    PreparedStatement state = conn.prepareStatement("INSERT is_ilan VALUES(?,?,?)");
                    state.setInt(1, Integer.parseInt(eklenecek_no.getText()));
                    state.setInt(2, Integer.parseInt(yeni_ucret.getText()));
                    state.setInt(3, Integer.parseInt(yeni_saat.getText()));
                   state.executeUpdate();
                } catch (Exception ex) {
                }
                   
                   remove(sc);
                    revalidate();
                    repaint();
                    tabloOlustur();
                    revalidate();
                    repaint();
            }
        });
        JButton ilanSil = new JButton("İlan Sil");
        ilanSil.setBounds(420, 470, 180, 30);
        add(ilanSil);
        ilanSil.setVisible(true);
        ilanSil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                DatabaseConnect db = new DatabaseConnect();
                Connection conn = db.getConnection();
                
                
                
                try {
                   
                   PreparedStatement state = conn.prepareStatement("DELETE FROM is_ilan WHERE ilan_veren_isletme_no=" + Integer.parseInt(isletme_no.getText()));
                   state.executeUpdate();
                   
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
        
        String[] kolonlar = {"İlan Veren İşletme No", "Ucret", "Çalışma Saati"};
        
        try {
            
            Statement myStat = conn.createStatement();
            ResultSet res = myStat.executeQuery("select * from is_ilan");
            
            Statement myStat2 = conn.createStatement();
            ResultSet res2 = myStat2.executeQuery("select COUNT(*) from  is_ilan");
            res2.next();
            int satir_sayisi = res2.getInt(1);
            //System.out.println("satir sayisi = "+satir_sayisi);          
            Object[][] tabloDegerleri = new Object[50][3];
            int k = 0;
            while (res.next()) {
                
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        tabloDegerleri[k][0] = res.getInt(1);
                    } else if (i == 1) {
                        tabloDegerleri[k][1] = res.getInt(2);
                    } else if (i == 2) {
                        tabloDegerleri[k][2] = res.getInt(3);
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
