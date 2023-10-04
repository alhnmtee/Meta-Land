package main;

import admin_paneller.*;

import javax.swing.*;
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
public class admin_panel extends JPanel {

    JTextField gunArtisMiktari;

    admin_panel() {
        super();
        setBounds(0, 0, 1920, 1080);
        this.setLayout(null);
        JButton baslangicDegerAtama = new JButton("Başlangıç Değerleri Atama");
        baslangicDegerAtama.setBounds(200, 200, 200, 40);
        add(baslangicDegerAtama);
        baslangicDegerAtama.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ekran = new JFrame("Başlanıç Ayarlama");
                ekran.setSize(1000, 800);
                ekran.setVisible(true);
                BaslangicAtama x = new BaslangicAtama();
                ekran.add(x);

            }
        });

        JButton kullanici_islem = new JButton("Oyuncu İşlemleri");
        kullanici_islem.setBounds(450, 200, 200, 40);
        add(kullanici_islem);

        kullanici_islem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ekran = new JFrame("Kullanici İşlem");
                ekran.setSize(1400, 800);
                ekran.setVisible(true);
                kullanici_islem x = new kullanici_islem();
                ekran.add(x);
            }
        });

        JButton arsa_islem = new JButton("Arsa İşlemleri");
        arsa_islem.setBounds(700, 200, 200, 40);
        add(arsa_islem);

        arsa_islem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ekran = new JFrame("Arsa İşlem");
                ekran.setSize(1400, 800);
                ekran.setVisible(true);
                arsa_islem x = new arsa_islem();
                ekran.add(x);
            }
        });

        JButton arsa_ilanları = new JButton("Arsa İlanları");
        arsa_ilanları.setBounds(950, 200, 200, 40);
        add(arsa_ilanları);

        arsa_ilanları.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ekran = new JFrame("Arsa İlan");
                ekran.setSize(1400, 800);
                ekran.setVisible(true);
                arsa_ilan x = new arsa_ilan();
                ekran.add(x);
            }
        });

        JButton isletme_ilanları = new JButton("İşletme İlanları");
        isletme_ilanları.setBounds(950, 280, 200, 40);
        add(isletme_ilanları);

        isletme_ilanları.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ekran = new JFrame("İşletme İlanları");
                ekran.setSize(1400, 800);
                ekran.setVisible(true);
                isletme_ilanlari x = new isletme_ilanlari();
                ekran.add(x);
            }
        });

        JButton işletme_işlemleri = new JButton("İşletme İşlemleri");
        işletme_işlemleri.setBounds(700, 280, 200, 40);
        add(işletme_işlemleri);

        işletme_işlemleri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ekran = new JFrame("İşletme İşlemleri");
                ekran.setSize(1400, 800);
                ekran.setVisible(true);
                isletme_islem x = new isletme_islem();
                ekran.add(x);
            }
        });

        JButton is_ilan = new JButton("İş İlanları");
        is_ilan.setBounds(450, 280, 200, 40);
        add(is_ilan);

        is_ilan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ekran = new JFrame("İlan İşlem");
                ekran.setSize(1400, 800);
                ekran.setVisible(true);
                is_ilan x = new is_ilan();
                ekran.add(x);
            }
        });

        JButton emlakTabloGoruntuler = new JButton("Emlak Geçmiş Görüntüle");
        emlakTabloGoruntuler.setBounds(200, 280, 200, 40);
        add(emlakTabloGoruntuler);
        emlakTabloGoruntuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ekran = new JFrame("Emlak Geçmiş Görüntüle");
                ekran.setSize(1400, 800);
                ekran.setVisible(true);
                emlak_goruntule x = new emlak_goruntule();
                ekran.add(x);

            }
        });

        JButton guncel_isletme = new JButton("Güncel İşletme Görüntüle");
        guncel_isletme.setBounds(200, 360, 200, 40);
        add(guncel_isletme);
        guncel_isletme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ekran = new JFrame("Güncel İşletme Kullamı Görüntüle");
                ekran.setSize(1400, 800);
                ekran.setVisible(true);
                güncel_isletme x = new güncel_isletme();
                ekran.add(x);

            }
        });

        JButton kullanici_gider = new JButton("Kullanıcı Gider Tablosu");
        kullanici_gider.setBounds(450, 360, 200, 40);
        add(kullanici_gider);
        kullanici_gider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ekran = new JFrame("Kullanıcı Gider Tablosu");
                ekran.setSize(1400, 800);
                ekran.setVisible(true);
                kullanici_gider x = new kullanici_gider();
                ekran.add(x);

            }
        });

        JButton kullanici_calisma = new JButton("Kullanıcı Güncel Çalışma");
        kullanici_calisma.setBounds(700, 360, 200, 40);
        add(kullanici_calisma);
        kullanici_calisma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ekran = new JFrame("Kullanıcı Güncel Çalışma");
                ekran.setSize(1400, 800);
                ekran.setVisible(true);
                kullanici_guncel_calisma x = new kullanici_guncel_calisma();
                ekran.add(x);

            }
        });

        JButton kullanici_calisma_kayit = new JButton("Kullanıcı Çalışma Kaydı");
        kullanici_calisma_kayit.setBounds(950, 360, 200, 40);
        add(kullanici_calisma_kayit);
        kullanici_calisma_kayit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ekran = new JFrame("Kullanıcı Çalışma Kaydı");
                ekran.setSize(1400, 800);
                ekran.setVisible(true);
                kullanici_calisma_kayit x = new kullanici_calisma_kayit();
                ekran.add(x);

            }
        });

        JButton gunilerlet = new JButton("Gün İlerlet");
        gunilerlet.setBounds(200, 440, 200, 40);
        add(gunilerlet);
        gunilerlet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Günlük Azalma Kullanıcı için
                // güncel gün tarihini 1 azaltmayı unutma

                DatabaseConnect db = new DatabaseConnect();
                Connection conn = db.getConnection();

                int gunMiktari = Integer.parseInt(gunArtisMiktari.getText());
                for (int i = 0; i < gunMiktari; i++) {

                    try {
                        Statement gunIlerlet = conn.createStatement();
                        gunIlerlet.executeUpdate("UPDATE Baslangic SET oyun_guncel_tarihi=DATEADD(DAY,1,oyun_guncel_tarihi)");
                    } catch (Exception es) {
                        System.out.println("Gün ilerletilemedi");
                    }

                    try {
                        String sorgu = "SELECT kullanici_gunluk_yemek_gideri, kullanici_gunluk_para_gideri, kullanici_gunluk_esya_gideri, oyun_guncel_tarihi FROM baslangic";
                        Statement state = conn.createStatement();
                        ResultSet sonuc = state.executeQuery(sorgu);
                        sonuc.next();
                        //System.out.println("Yemek:"+sonuc.getInt(1)+" - Para"+sonuc.getInt(2)+" - Esya"+sonuc.getInt(3)+" Tarih:"+sonuc.getDate(4));
                        Statement paraGuncelle = conn.createStatement();
                        paraGuncelle.executeUpdate("UPDATE Kullanici SET kullanici_para_miktari=kullanici_para_miktari-" + sonuc.getInt(2) + ",kullanici_yemek_miktari=kullanici_yemek_miktari-" + sonuc.getInt(1) + ",kullanici_esya_miktari=kullanici_esya_miktari-" + sonuc.getInt(3));

                        Statement tumKullaniciNolar = conn.createStatement();
                        ResultSet kullaniciNolar = tumKullaniciNolar.executeQuery("Select kullanici_no from Kullanici");

                        while (kullaniciNolar.next()) {
                            PreparedStatement giderlereKaydetPara = conn.prepareStatement("INSERT Kullanici_gider VALUES(?,?,?,?)");
                            giderlereKaydetPara.setInt(1, kullaniciNolar.getInt(1));
                            giderlereKaydetPara.setString(2, "Günlük Para Gideri");
                            giderlereKaydetPara.setInt(3, sonuc.getInt(2) * -1);
                            giderlereKaydetPara.setDate(4, sonuc.getDate(4));
                            giderlereKaydetPara.executeUpdate();

                            PreparedStatement giderlereKaydetEsya = conn.prepareStatement("INSERT Kullanici_gider VALUES(?,?,?,?)");
                            giderlereKaydetEsya.setInt(1, kullaniciNolar.getInt(1));
                            giderlereKaydetEsya.setString(2, "Günlük Eşya Gideri");
                            giderlereKaydetEsya.setInt(3, sonuc.getInt(3) * -1);
                            giderlereKaydetEsya.setDate(4, sonuc.getDate(4));
                            giderlereKaydetEsya.executeUpdate();

                            PreparedStatement giderlereKaydeYemek = conn.prepareStatement("INSERT Kullanici_gider VALUES(?,?,?,?)");
                            giderlereKaydeYemek.setInt(1, kullaniciNolar.getInt(1));
                            giderlereKaydeYemek.setString(2, "Günlük Yemek Gideri");
                            giderlereKaydeYemek.setInt(3, sonuc.getInt(1) * -1);
                            giderlereKaydeYemek.setDate(4, sonuc.getDate(4));
                            giderlereKaydeYemek.executeUpdate();
                        }

                    } catch (Exception ex) {
                        System.out.println("Hata");
                    }

                    try {

                        String sorgu = "SELECT kullanici_gunluk_yemek_gideri, kullanici_gunluk_para_gideri, kullanici_gunluk_esya_gideri, oyun_guncel_tarihi FROM baslangic";
                        Statement state = conn.createStatement();
                        ResultSet sonuc = state.executeQuery(sorgu);
                        sonuc.next();
                        //System.out.println("Yemek:"+sonuc.getInt(1)+" - Para"+sonuc.getInt(2)+" - Esya"+sonuc.getInt(3)+" Tarih:"+sonuc.getDate(4));
                        Statement calisanKullanicilar = conn.createStatement();
                        ResultSet calisanKullanicilarSorgu = calisanKullanicilar.executeQuery("Select X.kullanici_no,y.turu From Kullanici_guncel_calisma AS X, isletme AS Y WHERE x.isletme_no = y.isletme_no");
                        while (calisanKullanicilarSorgu.next()) {
                            System.out.println(calisanKullanicilarSorgu.getInt(1) + " " + calisanKullanicilarSorgu.getString(2));
                            String turu = calisanKullanicilarSorgu.getString(2);

                            System.out.println("-" + turu + "-");
                            turu = turu.trim();

                            if (turu.equalsIgnoreCase("market")) {
                                System.out.println("test1");
                                Statement marketEkle = conn.createStatement();
                                marketEkle.executeUpdate("UPDATE Kullanici SET kullanici_yemek_miktari=kullanici_yemek_miktari+" + sonuc.getInt(1) + "WHERE kullanici_no=" + calisanKullanicilarSorgu.getInt(1));
                                System.out.println("test2");
                                PreparedStatement giderlereKaydetPara = conn.prepareStatement("INSERT Kullanici_gider VALUES(?,?,?,?)");
                                giderlereKaydetPara.setInt(1, calisanKullanicilarSorgu.getInt(1));
                                giderlereKaydetPara.setString(2, "Market Çalışma Yemek İadesi");
                                giderlereKaydetPara.setInt(3, sonuc.getInt(1));
                                giderlereKaydetPara.setDate(4, sonuc.getDate(4));
                                giderlereKaydetPara.executeUpdate();
                                System.out.println("test3");
                            }
                            if (turu.equalsIgnoreCase("Mağaza")) {
                                System.out.println("test1");
                                Statement marketEkle = conn.createStatement();
                                marketEkle.executeUpdate("UPDATE Kullanici SET kullanici_esya_miktari=kullanici_esya_miktari+" + sonuc.getInt(3) + "WHERE kullanici_no=" + calisanKullanicilarSorgu.getInt(1));
                                System.out.println("test2");
                                PreparedStatement giderlereKaydetPara = conn.prepareStatement("INSERT Kullanici_gider VALUES(?,?,?,?)");
                                giderlereKaydetPara.setInt(1, calisanKullanicilarSorgu.getInt(1));
                                giderlereKaydetPara.setString(2, "Mağaza Çalışma Eşya İadesi");
                                giderlereKaydetPara.setInt(3, sonuc.getInt(3));
                                giderlereKaydetPara.setDate(4, sonuc.getDate(4));
                                giderlereKaydetPara.executeUpdate();
                                System.out.println("test3");

                            }
                            if (turu.equalsIgnoreCase("Emlak")) {
                                System.out.println("test1");
                                Statement marketEkle = conn.createStatement();
                                marketEkle.executeUpdate("UPDATE Kullanici SET kullanici_para_miktari=kullanici_para_miktari+" + sonuc.getInt(2) + "WHERE kullanici_no=" + calisanKullanicilarSorgu.getInt(1));
                                System.out.println("test2");
                                PreparedStatement giderlereKaydetPara = conn.prepareStatement("INSERT Kullanici_gider VALUES(?,?,?,?)");
                                giderlereKaydetPara.setInt(1, calisanKullanicilarSorgu.getInt(1));
                                giderlereKaydetPara.setString(2, "Emlak Çalışma Para İadesi");
                                giderlereKaydetPara.setInt(3, sonuc.getInt(2));
                                giderlereKaydetPara.setDate(4, sonuc.getDate(4));
                                giderlereKaydetPara.executeUpdate();
                                System.out.println("test3");

                            }
                        }
                    } catch (Exception es) {
                        es.printStackTrace();
                    }

                    try {
                        String sorgu = "SELECT kullanici_no,calisan_maas  FROM Kullanici_guncel_calisma   ";
                        Statement state = conn.createStatement();
                        ResultSet sonuc = state.executeQuery(sorgu);

                        String sorgux = "SELECT kullanici_gunluk_yemek_gideri, kullanici_gunluk_para_gideri, kullanici_gunluk_esya_gideri, oyun_guncel_tarihi FROM baslangic";
                        Statement statex = conn.createStatement();
                        ResultSet baslangicDegerler = statex.executeQuery(sorgux);
                        baslangicDegerler.next();
                        //sonuc.next();
                        //System.out.println(sonuc.getInt(1)+" "+sonuc.getInt(2));
                        while (sonuc.next()) {
                            Statement maasYatir = conn.createStatement();
                            maasYatir.executeUpdate("UPDATE Kullanici SET Kullanici_para_miktari=kullanici_para_miktari+" + sonuc.getInt(2) + " WHERE kullanici_no=" + sonuc.getInt(1));

                            PreparedStatement giderlereKaydetPara = conn.prepareStatement("INSERT Kullanici_gider VALUES(?,?,?,?)");
                            giderlereKaydetPara.setInt(1, sonuc.getInt(1));
                            giderlereKaydetPara.setString(2, "Günlük Maaş");
                            giderlereKaydetPara.setInt(3, sonuc.getInt(2));
                            giderlereKaydetPara.setDate(4, baslangicDegerler.getDate(4));
                            giderlereKaydetPara.executeUpdate();
                            System.out.println("test3");

                        }
                    } catch (Exception es) {
                        System.out.println("Maaşlar Yatmadı");
                    }

                    try {

                        String sorgu = "SELECT kullanici_gunluk_yemek_gideri, kullanici_gunluk_para_gideri, kullanici_gunluk_esya_gideri, oyun_guncel_tarihi FROM baslangic";
                        Statement state = conn.createStatement();
                        ResultSet sonuc = state.executeQuery(sorgu);
                        sonuc.next();
                        String guncelKullanim = "SELECT x.isleten_no, y.sabit_gelir_miktari,y.sabit_gelir_orani FROM isletme_guncel_kullanim AS x, isletme AS y WHERE x.alan_no=y.arsa_no";
                        Statement kullanici_gelir_miktari_gelir_orani = conn.createStatement();
                        ResultSet noMiktarOran = kullanici_gelir_miktari_gelir_orani.executeQuery(guncelKullanim);

                        while (noMiktarOran.next()) {
                            // System.out.println("no:"+noMiktarOran.getInt(1)+"miktar"+noMiktarOran.getInt(2)+"oran"+noMiktarOran.getInt(3));
                            Statement isletmeGeliri = conn.createStatement();
                            int eklenecek = (noMiktarOran.getInt(2) + (noMiktarOran.getInt(2) * noMiktarOran.getInt(3)) / 100);
                            isletmeGeliri.executeUpdate("UPDATE Kullanici SET Kullanici_para_miktari=kullanici_para_miktari+" + eklenecek + " WHERE kullanici_no=" + noMiktarOran.getInt(1));
                            System.out.println(eklenecek);
                            PreparedStatement giderlereKaydetPara = conn.prepareStatement("INSERT Kullanici_gider VALUES(?,?,?,?)");
                            giderlereKaydetPara.setInt(1, noMiktarOran.getInt(1));
                            giderlereKaydetPara.setString(2, "Günlük İşletme Geliri");
                            giderlereKaydetPara.setInt(3, eklenecek);
                            giderlereKaydetPara.setDate(4, sonuc.getDate(4));
                            giderlereKaydetPara.executeUpdate();

                        }

                    } catch (Exception es) {
                        System.out.println("hata");
                    }
                    try {

                        String sorgu = "SELECT kullanici_gunluk_yemek_gideri, kullanici_gunluk_para_gideri, kullanici_gunluk_esya_gideri, oyun_guncel_tarihi FROM baslangic";
                        Statement state = conn.createStatement();
                        ResultSet sonuc = state.executeQuery(sorgu);
                        sonuc.next();

                        String kiraGunAzaltma = "UPDATE isletme_guncel_kullanim SET isletilecek_gun_sayisi=isletilecek_gun_sayisi-1";
                        Statement stat2 = conn.createStatement();
                        stat2.executeUpdate(kiraGunAzaltma);

                        String gunSayisiBitenler = "SELECT x.alan_no,y.isletme_sahibi FROM isletme_guncel_kullanim AS x,isletme AS y WHERE x.isletilecek_gun_sayisi=0 AND x.alan_no=y.arsa_no";
                        Statement stat3 = conn.createStatement();
                        ResultSet sonucBitenler = stat3.executeQuery(gunSayisiBitenler);

                        while (sonucBitenler.next()) {
                            //   System.out.println("alan no ="+sonucBitenler.getInt(1)+" eski sahibi="+sonucBitenler.getInt(2));
                            String eskiSahibeAktarma = "UPDATE isletme_guncel_kullanim SET isleten_no=" + sonucBitenler.getInt(2) + ", isletilecek_gun_sayisi=999999999" + " WHERE alan_no=" + sonucBitenler.getInt(1);
                            Statement st = conn.createStatement();
                            st.executeUpdate(eskiSahibeAktarma);
                        }

                    } catch (Exception es) {
                        System.out.println("hatacık");
                    }

                    try {

                        String sorgu = "SELECT kullanici_gunluk_yemek_gideri, kullanici_gunluk_para_gideri, kullanici_gunluk_esya_gideri, oyun_guncel_tarihi FROM baslangic";
                        Statement state = conn.createStatement();
                        ResultSet sonuc = state.executeQuery(sorgu);
                        sonuc.next();

                        String isletmeTarihleri = "select arsa_no,mevcut_seviye_baslangic_tarihi,seviyesi from isletme";
                        Statement isletmeArsaNo_tarih = conn.createStatement();
                        ResultSet sonuc_isletmeArsaNo_tarih = isletmeArsaNo_tarih.executeQuery(isletmeTarihleri);

                        while (sonuc_isletmeArsaNo_tarih.next()) {
                            System.out.println("Arsa No: " + sonuc_isletmeArsaNo_tarih.getInt(1) + " Tarih: " + sonuc_isletmeArsaNo_tarih.getDate(2));
                            String isletmeBaslangıcFark = "SELECT DATEDIFF(day,?,?)";
                            PreparedStatement gunFarki = conn.prepareStatement(isletmeBaslangıcFark);

                            gunFarki.setDate(1, sonuc_isletmeArsaNo_tarih.getDate(2));
                            gunFarki.setDate(2, sonuc.getDate(4));
                            ResultSet day = gunFarki.executeQuery();
                            day.next();
                            System.out.println("Gün Farkı = " + day.getInt(1));
                            if (day.getInt(1) >= 7) {
                                if (sonuc_isletmeArsaNo_tarih.getInt(3) == 1) {
                                    System.out.println("deneme1");
                                    String guncelleme = "UPDATE isletme SET seviyesi = seviyesi + 1, kapasitesi = kapasitesi + 20, calisan_sayisi = calisan_sayisi + 3, sabit_gelir_miktari = sabit_gelir_miktari + 1000, sabit_gelir_orani = 20, mevcut_Seviye_baslangic_tarihi = ? WHERE arsa_no = ?";
                                    PreparedStatement statement = conn.prepareStatement(guncelleme);
                                    statement.setDate(1, sonuc.getDate(4));
                                    statement.setInt(2, sonuc_isletmeArsaNo_tarih.getInt(1));
                                    statement.executeUpdate();
                                }
                                if (sonuc_isletmeArsaNo_tarih.getInt(3) == 2) {
                                    System.out.println("deneme2");
                                    String guncelleme = "UPDATE isletme SET seviyesi = seviyesi+1 , kapasitesi=kapasitesi+20,calisan_sayisi=calisan_sayisi+6,sabit_gelir_miktari=sabit_gelir_miktari+1000,sabit_gelir_orani=30,mevcut_Seviye_baslangic_tarihi=? WHERE arsa_no=?";
                                    PreparedStatement statement = conn.prepareStatement(guncelleme);
                                    statement.setDate(1, sonuc.getDate(4));
                                    statement.setInt(2, sonuc_isletmeArsaNo_tarih.getInt(1));
                                    statement.executeUpdate();
                                }
                            }

                        }

                    } catch (Exception es) {
                        System.out.println("Hata");
                    }

                    try {
                        Statement st = conn.createStatement();
                        ResultSet stSet = st.executeQuery("select kullanici_no from kullanici where kullanici_yemek_miktari<=0 OR kullanici_esya_miktari<=0");
                        while(stSet.next()){
                            System.out.println("k_no="+stSet.getInt(1));
                            Statement x = conn.createStatement();
                            x.executeUpdate("DELETE FROM Kullanici_guncel_calisma where kullanici_no="+stSet.getInt(1));

                            Statement y = conn.createStatement();
                            y.executeUpdate("UPDATE isletme_guncel_kullanim SET isleten_no=1 where isleten_no="+stSet.getInt(1));

                            Statement z = conn.createStatement();
                            z.executeUpdate("UPDATE isletme SET isletme_sahibi=1 where isletme_sahibi="+stSet.getInt(1));

                            Statement p = conn.createStatement();
                            z.executeUpdate("UPDATE Alan SET alan_sahibi=1 where alan_sahibi="+stSet.getInt(1));



                            Statement x1 = conn.createStatement();
                            x1.executeUpdate("DELETE FROM Arsa_ilan where satan_kisi_no="+stSet.getInt(1));

                            Statement x2 = conn.createStatement();
                            x2.executeUpdate("DELETE FROM isletme_ilan where islem_yapilan_kullanici_no="+stSet.getInt(1));


                            //Statement siliyozArtık = conn.createStatement();
                            //siliyozArtık.executeUpdate("DELETE FROM Kullanici where kullanici_no="+stSet.getInt(1));

                            System.out.println("Bitti");

                        }
                    } catch (Exception exa) {
                        exa.printStackTrace();
                    }

                    System.out.println("Gün Sayısı="+i);
                }

            }
        });
        this.gunArtisMiktari = new JTextField();
        gunArtisMiktari.setBounds(450, 445, 60, 30);
        add(gunArtisMiktari);
        gunArtisMiktari.setVisible(true);

    }
}