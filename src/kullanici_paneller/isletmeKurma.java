package kullanici_paneller;

import main.DatabaseConnect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;

public class isletmeKurma extends JPanel {
    JScrollPane sc=new JScrollPane();
    DatabaseConnect dtbc = new DatabaseConnect();
    Connection conn = dtbc.getConnection();
    Icon okicon =new ImageIcon(this.getClass().getResource("/resim/ok.png"));
    JButton islem=new JButton(okicon);
    JTextField jurunFiyat;
    JTextField jisletmeTuru;
    JTextField jArsaNO;
    public isletmeKurma(int no) throws SQLException {

        int kullaniciNo=no;
        tabloOlusturma(kullaniciNo);
        setLayout(null);
        setBounds(0,0,620,1080);
        setBackground(Color.decode("#334671"));

        JLabel yazi=new JLabel("İşletme Kurma ");
        yazi.setVisible(true);
        yazi.setBounds(220,30,200,40);
        yazi.setFont(yazi.getFont().deriveFont(Font.BOLD,25));
        add(yazi);
        yazi.setForeground(Color.BLACK);
        JLabel yazi2=new JLabel("Kullanıcı No = "+ kullaniciNo);
        yazi2.setVisible(true);
        yazi2.setBounds(220,70,220,40);
        yazi2.setFont(yazi2.getFont().deriveFont(Font.ITALIC,20));
        add(yazi2);
        yazi2.setForeground(Color.BLACK);

        JLabel yetersizBakiye=new JLabel("Paranız yetersizdir.");
        yetersizBakiye.setBounds(200,800,200,40);
        add(yetersizBakiye);
        yetersizBakiye.setVisible(false);
        yetersizBakiye.setForeground(Color.RED);
        yetersizBakiye.setFont(yetersizBakiye.getFont().deriveFont(Font.ITALIC,15));

        JLabel islemBasarili=new JLabel("İşlem Başarılı.");
        islemBasarili.setBounds(200,850,200,40);
        add(islemBasarili);
        islemBasarili.setVisible(false);
        islemBasarili.setForeground(Color.GREEN);
        islemBasarili.setFont(islemBasarili.getFont().deriveFont(Font.ITALIC,15));


        Statement myStat = conn.createStatement();
        ResultSet res=myStat.executeQuery("select * from Alan where alan_sahibi= '" + no + "' AND alan_turu='arsa';");

        if(res.next()){
            Statement myStat2 = conn.createStatement();
            ResultSet res2=myStat2.executeQuery("select * from Alan where alan_sahibi= '" + no + "' AND alan_turu='arsa' ;");

            Statement myStat3=conn.createStatement();
            ResultSet res3=myStat3.executeQuery("select magaza_kurma_ucreti,market_kurma_ucreti,emlak_kurma_ucreti from Baslangic");
            res3.next();
            int magazaKurmaUcreti=res3.getInt("magaza_kurma_ucreti");
            int marketKurmaUcreti=res3.getInt("market_kurma_ucreti");
            int emlakKurmaUcreti=res3.getInt("emlak_kurma_ucreti");

            JLabel marketlabel=new JLabel("Market Kurma ücreti = "+ marketKurmaUcreti);
            marketlabel.setVisible(true);
            marketlabel.setBounds(30,600,200,30);
            marketlabel.setFont(marketlabel.getFont().deriveFont(Font.ITALIC,13));
            add(marketlabel);
            marketlabel.setForeground(Color.BLACK);
            JLabel magazaLabel=new JLabel("Mağaza Kurma Ücreti = "+ magazaKurmaUcreti);
            magazaLabel.setVisible(true);
            magazaLabel.setBounds(230,600,200,30);
            magazaLabel.setFont(magazaLabel.getFont().deriveFont(Font.ITALIC,13));
            add(magazaLabel);
            magazaLabel.setForeground(Color.BLACK);
            JLabel emlakLabel=new JLabel("Emlak Kurma Ücreti = "+ emlakKurmaUcreti);
            emlakLabel.setVisible(true);
            emlakLabel.setBounds(430,600,200,30);
            emlakLabel.setFont(emlakLabel.getFont().deriveFont(Font.ITALIC,13));
            add(emlakLabel);
            emlakLabel.setForeground(Color.BLACK);

           /* Object[] sutunisim = {"Alan No","Alan Sahibi","Alan Türü"};
            ResultSetMetaData rsMetaData=res.getMetaData();
            int satirSayisi = rsMetaData.getColumnCount();
            DefaultTableModel model = new DefaultTableModel(sutunisim, 0);
            //Object[][] veriler = new Object[2][satirSayisi];
            while (res2.next()) {
                int alanNo=res.getInt("alan_no");
                int alanSahibi=res.getInt("alan_sahibi");
                String tur=res.getString("alan_turu");
                Object[] satir = {alanNo,alanSahibi,tur};
                model.addRow(satir);
            }
            JTable table = new JTable(model);
            //tablo.setPreferredScrollableViewportSize(new Dimension(800, 400));
            table.setBounds(100, 150, 400, 400);
            JScrollPane sp = new JScrollPane(table);
            sp.setBounds(100, 150, 400, 400);
            add(sp);
            sc = sp;
            sp.setVisible(true);
            setVisible(true);*/

            jisletmeTuru = new JTextField("İşletme Türü");
            jisletmeTuru.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    super.focusGained(e);
                    jisletmeTuru.setText("");
                }
            });
            jisletmeTuru.setBounds(100, 750, 70, 30);

            jArsaNO=new JTextField("Arsa No");
            jArsaNO.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    super.focusGained(e);
                    jArsaNO.setText("");
                }
            });
            jArsaNO.setBounds(200,750,70,30);
            add(jisletmeTuru);
            add(jArsaNO);
            jArsaNO.setVisible(true);
            jisletmeTuru.setVisible(true);

            jurunFiyat=new JTextField("Ürün Fiyatı veya Komisyon");
            jurunFiyat.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    super.focusGained(e);
                    jurunFiyat.setText("");
                }
            });
            add(jurunFiyat);
            jurunFiyat.setBounds(300,750,160,30);

            add(islem);
            islem.setBounds(490,740,50,50);
            islem.setVisible(true);
            islem.setOpaque(false);
            islem.setContentAreaFilled(false);
            islem.setBorderPainted(false);

            Statement myStat4=conn.createStatement();
            ResultSet res4=myStat4.executeQuery("select kullanici_para_miktari from Kullanici WHERE kullanici_no="+kullaniciNo+" ");
            res4.next();


            Statement myStat5=conn.createStatement();
            ResultSet res5=myStat5.executeQuery("select oyun_guncel_tarihi from Baslangic");
            res5.next();
            Date tarih=res5.getDate("oyun_guncel_tarihi");



            int kullaniciPara=res4.getInt("kullanici_para_miktari");

            islem.addActionListener(e ->  {
                int arsaNo=Integer.parseInt(jArsaNO.getText());
                String isletmeTuru=jisletmeTuru.getText();
                int esyaUcret=Integer.parseInt(jurunFiyat.getText());

                try{
                    Statement kontrol=conn.createStatement();
                    ResultSet resKontrol=kontrol.executeQuery("select * from Alan where alan_sahibi="+kullaniciNo+" AND alan_no="+arsaNo+";");
                    if(resKontrol.next()){
                        if(kullaniciPara>=magazaKurmaUcreti&& isletmeTuru.equalsIgnoreCase("mağaza")) {
                            yetersizBakiye.setVisible(false);
                            islemBasarili.setVisible(true);

                            try {
                                String query2=("UPDATE Kullanici set kullanici_para_miktari=kullanici_para_miktari-"+magazaKurmaUcreti+" WHERE kullanici_no="+kullaniciNo+" ");
                                PreparedStatement stat2= conn.prepareStatement(query2);

                                String query=("UPDATE Alan set alan_turu=? WHERE alan_no="+arsaNo+" ");
                                PreparedStatement stat= conn.prepareStatement(query);
                                stat.setString(1,"işletme");
                                stat.executeUpdate();
                                stat2.executeUpdate();

                                isletmeEkle(arsaNo,kullaniciNo,isletmeTuru);

                                Statement myStat12 = conn.createStatement();
                                ResultSet res12=myStat12.executeQuery("select isletme_no from isletme where arsa_no="+arsaNo+";");
                                res12.next();
                                int isletmeNo=res12.getInt("isletme_no");


                                PreparedStatement stater=conn.prepareStatement("INSERT Magaza (isletme_no, esya_ucret) VALUES (?,?)");
                                stater.setInt(1,isletmeNo);
                                stater.setInt(2,esyaUcret);
                                stater.executeUpdate();

                                PreparedStatement stater3=conn.prepareStatement("INSERT isletme_guncel_kullanim (alan_no, isleten_no,isletilecek_gun_sayisi) VALUES (?,?,?)");
                                stater3.setInt(1,arsaNo);
                                stater3.setInt(2,kullaniciNo);
                                stater3.setInt(3,999999);
                                stater3.executeUpdate();

                                PreparedStatement stater7=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                                stater7.setInt(1,kullaniciNo);
                                stater7.setString(2,"Mağaza Kurma Ücreti");
                                stater7.setInt(3,magazaKurmaUcreti*-1);
                                stater7.setDate(4,tarih);
                                stater7.executeUpdate();


                            }catch (SQLException ex){
                                throw  new RuntimeException(ex);
                            }
                        }
                        else if(kullaniciPara>=marketKurmaUcreti&& isletmeTuru.equalsIgnoreCase("market")){
                            yetersizBakiye.setVisible(false);
                            islemBasarili.setVisible(true);
                            try {
                                String query3=("UPDATE Kullanici set kullanici_para_miktari=kullanici_para_miktari-"+marketKurmaUcreti+" WHERE kullanici_no="+kullaniciNo+" ");
                                PreparedStatement stat3= conn.prepareStatement(query3);
                                String query=("UPDATE Alan set alan_turu=? WHERE alan_no="+arsaNo+" ");
                                PreparedStatement stat= conn.prepareStatement(query);
                                stat.setString(1,"işletme");
                                stat.executeUpdate();
                                stat3.executeUpdate();

                                isletmeEkle(arsaNo,kullaniciNo,isletmeTuru);

                                Statement myStat12 = conn.createStatement();
                                ResultSet res12=myStat12.executeQuery("select isletme_no from isletme where arsa_no="+arsaNo+";");
                                res12.next();
                                int isletmeNo=res12.getInt("isletme_no");


                                PreparedStatement stater2=conn.prepareStatement("INSERT Market (isletme_no,yiyecek_ucret) VALUES (?,?)");
                                stater2.setInt(1,isletmeNo);
                                stater2.setInt(2,esyaUcret);
                                stater2.executeUpdate();



                                PreparedStatement stater4=conn.prepareStatement("INSERT isletme_guncel_kullanim (alan_no, isleten_no,isletilecek_gun_sayisi) VALUES (?,?,?)");
                                stater4.setInt(1,arsaNo);
                                stater4.setInt(2,kullaniciNo);
                                stater4.setInt(3,999999);
                                stater4.executeUpdate();

                                PreparedStatement stater6=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                                stater6.setInt(1,kullaniciNo);
                                stater6.setString(2,"Market Kurma Ücreti");
                                stater6.setInt(3,marketKurmaUcreti*-1);
                                stater6.setDate(4,tarih);
                                stater6.executeUpdate();


                            }catch (SQLException ex){
                                throw  new RuntimeException(ex);
                            }
                        }
                        else if(kullaniciPara>=emlakKurmaUcreti &&isletmeTuru.equalsIgnoreCase("emlak")){
                            yetersizBakiye.setVisible(false);
                            islemBasarili.setVisible(true);
                            try {
                                String query=("UPDATE Alan set alan_turu=? WHERE alan_no="+arsaNo+" ");
                                PreparedStatement stat= conn.prepareStatement(query);
                                stat.setString(1,"işletme");
                                stat.executeUpdate();

                                isletmeEkle(arsaNo,kullaniciNo,isletmeTuru);

                                Statement myStat12 = conn.createStatement();
                                ResultSet res12=myStat12.executeQuery("select isletme_no from isletme where arsa_no="+arsaNo+";");
                                res12.next();
                                int isletmeNo=res12.getInt("isletme_no");

                                PreparedStatement stater3=conn.prepareStatement("INSERT Emlak (isletme_no,komisyon) VALUES (?,?)");
                                stater3.setInt(1,isletmeNo);
                                stater3.setInt(2,esyaUcret);
                                stater3.executeUpdate();

                                PreparedStatement stater4=conn.prepareStatement("INSERT isletme_guncel_kullanim (alan_no, isleten_no,isletilecek_gun_sayisi) VALUES (?,?,?)");
                                stater4.setInt(1,arsaNo);
                                stater4.setInt(2,kullaniciNo);
                                stater4.setInt(3,999999);
                                stater4.executeUpdate();


                                PreparedStatement stater5=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                                stater5.setInt(1,kullaniciNo);
                                stater5.setString(2,"Emlak Kurma Ücreti");
                                stater5.setInt(3,emlakKurmaUcreti*-1);
                                stater5.setDate(4,tarih);
                                stater5.executeUpdate();


                            }catch (SQLException ex){
                                throw  new RuntimeException(ex);
                            }
                        }
                        else
                            yetersizBakiye.setVisible(true); System.out.println("3");
                        remove(sc);
                        revalidate();
                        repaint();
                        try {
                            tabloOlusturma(kullaniciNo);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        revalidate();
                        repaint();
                    }
                    else
                        System.out.println("bura senin değil gardaş");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }



            });
        }
    }

    public void tabloOlusturma(int no) throws SQLException{

        Statement myStat = conn.createStatement();
        ResultSet res=myStat.executeQuery("select * from Alan where alan_sahibi= '" + no + "' AND alan_turu='arsa';");

        Statement myStat2 = conn.createStatement();
        ResultSet res2=myStat2.executeQuery("select * from Alan where alan_sahibi= '" + no + "' AND alan_turu='arsa' ;");

        Object[] sutunisim = {"Alan No","Alan Sahibi","Alan Türü"};
        DefaultTableModel model = new DefaultTableModel(sutunisim, 0);

        while (res2.next()) {
            res.next();
            int alanNo=res.getInt("alan_no");
            int alanSahibi=res.getInt("alan_sahibi");
            String tur=res.getString("alan_turu");
            Object[] satir = {alanNo,alanSahibi,tur};
            model.addRow(satir);
        }
        JTable table = new JTable(model);
        table.setBounds(100, 150, 400, 400);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(100, 150, 400, 400);
        add(sp);
        sc = sp;
        sp.setVisible(true);
        setVisible(true);
    }
    public void isletmeEkle(int arsaNo,int kNo,String tur) throws SQLException{

             String query1 = ("select oyun_guncel_tarihi FROM Baslangic");
             Statement stat1 = conn.createStatement();
             ResultSet baslangicGun=stat1.executeQuery(query1);
             baslangicGun.next();
             Date tarih=baslangicGun.getDate("oyun_guncel_tarihi");
            //başlangıç değerleri atanacak
            PreparedStatement stater2 = conn.prepareStatement("INSERT isletme(arsa_no, isletme_sahibi, turu, seviyesi, kapasitesi, calisan_sayisi, sabit_gelir_miktari, sabit_gelir_orani, mevcut_seviye_baslangic_tarihi) VALUES(?,?,?,?,?,?,?,?,?)");
            stater2.setInt(1,arsaNo);
            stater2.setInt(2, kNo);
            stater2.setString(3,tur);
            stater2.setInt(4,1);
            stater2.setInt(5, 20);
            stater2.setInt(6, 3);
            // stater2.setInt(7, Integer.parseInt(yeni_calisan_maasi.getText()));
            stater2.setInt(7, 1000);
            stater2.setInt(8, 10);
            stater2.setDate(9, tarih);
            stater2.executeUpdate();
    }
}
