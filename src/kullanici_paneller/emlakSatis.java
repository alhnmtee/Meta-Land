package kullanici_paneller;

import main.DatabaseConnect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;
import java.time.LocalDate;

public class emlakSatis extends JPanel {
    JScrollPane sc;
    JScrollPane sc2;
    DatabaseConnect dtbc = new DatabaseConnect();
    Connection conn = dtbc.getConnection();
    JTextField jisletmeNo;
    JTextField jSatanEmlak;

    JTextField jMiktar;
    Icon buyicon2 =new ImageIcon(this.getClass().getResource("/resim/buyicon2.png"));
    JButton satinAlma=new JButton(buyicon2);
    JButton satinAlma2=new JButton(buyicon2);

    JButton arsaSatis=new JButton("Arsalar İçin");
    JButton isletmeSatis=new JButton("İşletme İçin");
    public emlakSatis(int no) throws SQLException {


        int kullaniciNo=no;
        setLayout(null);
        setBounds(0,0,620,1080);
        setBackground(Color.decode("#334671"));

        arsaSatis.setBounds(100,400,200,50);
        add(arsaSatis);

        isletmeSatis.setBounds(350,400,200,50);
        add(isletmeSatis);



        JLabel yazi3=new JLabel("İşletme Kurulmamış 2'den fazla Arsanız var.Lütfen önce işletme kurunuz");
        yazi3.setVisible(false);
        yazi3.setBounds(20,750,550,40);
        yazi3.setForeground(Color.ORANGE);
        yazi3.setFont(yazi3.getFont().deriveFont(Font.BOLD,15));
        add(yazi3);

        String query16 = ("select oyun_guncel_tarihi FROM Baslangic");
        Statement stat16 = conn.createStatement();
        ResultSet baslangicGun=stat16.executeQuery(query16);
        baslangicGun.next();
        Date tarih=baslangicGun.getDate("oyun_guncel_tarihi");




        arsaSatis.addActionListener(e -> {
            JLabel yazi=new JLabel("Arsa Satış");
            yazi.setVisible(true);
            yazi.setBounds(220,30,230,40);
            yazi.setFont(yazi.getFont().deriveFont(Font.BOLD,25));
            add(yazi);
            yazi.setForeground(Color.BLACK);
            JLabel yazi2=new JLabel("Kullanıcı No = "+ kullaniciNo);
            yazi2.setVisible(true);
            yazi2.setBounds(220,70,220,40);
            yazi2.setFont(yazi2.getFont().deriveFont(Font.ITALIC,20));
            add(yazi2);
            yazi2.setForeground(Color.BLACK);

            revalidate();
            repaint();


            arsaSatis.setVisible(false);
            isletmeSatis.setVisible(false);
            try {
                tabloOlusturma(kullaniciNo);

                jisletmeNo = new JTextField("İsletme No");
                jisletmeNo.setBounds(100, 620, 70, 30);
                jisletmeNo.setVisible(false);
                jisletmeNo.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        super.focusGained(e);
                        jisletmeNo.setText("");
                    }
                });
                add(jisletmeNo);

                add(satinAlma);
                satinAlma.setBounds(400, 610, 50, 50);
                satinAlma.setVisible(false);

                JTextField jSatilanArsa = new JTextField("Arsa No");
                jSatilanArsa.setBounds(200, 620, 70, 30);
                jSatilanArsa.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        super.focusGained(e);
                        jSatilanArsa.setText("");
                    }
                });
                add(jSatilanArsa);
                jSatilanArsa.setVisible(false);

                Statement stat4 = conn.createStatement();
                ResultSet res4 = stat4.executeQuery("select count(*) as Sayi  from Alan WHERE alan_sahibi=" + kullaniciNo + " AND alan_turu='arsa'");
                res4.next();
                int arsaSayisi = res4.getInt("Sayi");

                if (arsaSayisi <= 2){
                    satinAlma.setVisible(true);
                    jisletmeNo.setVisible(true);
                    jSatilanArsa.setVisible(true);
                    satinAlma.addActionListener(e2 -> {
                        int isletmeNo3 = Integer.parseInt(jisletmeNo.getText());
                        int satilanArsaNo = Integer.parseInt(jSatilanArsa.getText());
                        try {
                            Statement myStatCopy = conn.createStatement();
                            ResultSet resCopy = myStatCopy.executeQuery("select ucret as Ucret ,emlakci_no as No,satan_kisi_no as SatanNo from Arsa_ilan where satilan_arsa_no="+satilanArsaNo+" AND satan_kisi_no<>" + no + " AND emlakci_no="+isletmeNo3+" ");
                            while (resCopy.next()) {

                                System.out.println("arsaNo: " + satilanArsaNo);
                               // Statement myStatCopy3 = conn.createStatement();
                                //ResultSet resCopy3 = myStatCopy3.executeQuery("select x.isletme_no as No,y.ucret as Ucret ,y.satilan_arsa_no as Arsa,y.satan_kisi_no as SatanNo from isletme x,Arsa_ilan y where x.isletme_no=y.emlakci_no AND x.turu='emlak' AND y.satan_kisi_no<>" + no + " ");
                                //resCopy3.next();
                                if (isletmeNo3 == resCopy.getInt("No")) {
                                    System.out.println("test1");
                                    int emlakUcret = resCopy.getInt("Ucret");
                                    int satanKisiNo=resCopy.getInt("SatanNo");
                                    System.out.println("test24: "+ satanKisiNo);
                                    Statement myStat2 = conn.createStatement();
                                    ResultSet res21 = myStat2.executeQuery("select kullanici_para_miktari as MMM from Kullanici where kullanici_no='" + kullaniciNo + "';");
                                    res21.next();
                                    int kullaniciPara = res21.getInt("MMM");
                                    if (emlakUcret <= kullaniciPara) {
                                        System.out.println("test7");
                                        String query2 = ("UPDATE Kullanici set kullanici_para_miktari=kullanici_para_miktari-" + emlakUcret + " WHERE kullanici_no=" + kullaniciNo + ";");
                                        PreparedStatement stat = conn.prepareStatement(query2);
                                        stat.executeUpdate();
                                        String query3 = ("UPDATE Alan set alan_sahibi=" + kullaniciNo + " WHERE alan_no=" + satilanArsaNo + ";");
                                        PreparedStatement stat2 = conn.prepareStatement(query3);
                                        stat2.executeUpdate();


                                        System.out.println("emlakçı = "+ isletmeNo3);

                                        String query4 = ("DELETE FROM Arsa_ilan WHERE satilan_arsa_no=" + satilanArsaNo + ";");
                                        PreparedStatement stat3 = conn.prepareStatement(query4);
                                        stat3.executeUpdate();
                                        System.out.println(satilanArsaNo);



                                        LocalDate gunumuz=LocalDate.now();
                                        PreparedStatement stater=conn.prepareStatement("INSERT Emlak_islem_kayit (emlakci_isletme_no, islem_yapan_kisi_id, satilan_yer_no,islem_tipi, satis_tarihi, kiralama_tarihi, kira_bitis_tarihi) VALUES (?,?,?,?,?,?,?)");
                                        stater.setInt(1,isletmeNo3);
                                        stater.setInt(2,kullaniciNo);
                                        stater.setInt(3,satilanArsaNo);
                                        stater.setString(4,"satış");
                                        stater.setDate(5,tarih);
                                        stater.setDate(6,null);
                                        stater.setDate(7,null);

                                        stater.executeUpdate();

                                        Statement stat17=conn.createStatement();
                                        ResultSet res17=stat17.executeQuery("select komisyon from Emlak where isletme_no="+isletmeNo3+";");
                                        res17.next();
                                        int komisyonOranı=res17.getInt("komisyon");
                                        int komisyon=(emlakUcret*komisyonOranı)/100;
                                        System.out.println("komisyon ücreti: "+komisyon);

                                        PreparedStatement stater3=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                                        stater3.setInt(1,kullaniciNo);
                                        stater3.setString(2,"Arsa Satın Alım");
                                        stater3.setInt(3,emlakUcret*-1);
                                        stater3.setDate(4,tarih);
                                        stater3.executeUpdate();


                                       Statement stat9=conn.createStatement();
                                       ResultSet res9=stat9.executeQuery("select isletme_sahibi from isletme where isletme_no="+isletmeNo3+";");
                                       res9.next();
                                       int emlakciSahibi=res9.getInt("isletme_sahibi");

                                        String query52 = ("UPDATE Kullanici set kullanici_para_miktari=kullanici_para_miktari+" + komisyon + " WHERE kullanici_no=" + emlakciSahibi + ";");
                                        PreparedStatement stat13 = conn.prepareStatement(query52);
                                        stat13.executeUpdate();

                                        PreparedStatement stater4=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                                        stater4.setInt(1,emlakciSahibi);
                                        stater4.setString(2,"Komisyon");
                                        stater4.setInt(3,(komisyon));
                                        stater4.setDate(4,tarih);
                                        stater4.executeUpdate();

                                        PreparedStatement stater5=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                                        stater5.setInt(1,satanKisiNo);
                                        stater5.setString(2,"Arsa Satış");
                                        stater5.setInt(3,(emlakUcret-komisyon));
                                        stater5.setDate(4,tarih);
                                        stater5.executeUpdate();


                                        String query5 = ("UPDATE Kullanici set kullanici_para_miktari=kullanici_para_miktari+" +(emlakUcret-komisyon)+" WHERE kullanici_no=" +satanKisiNo+ ";");
                                        PreparedStatement stat5 = conn.prepareStatement(query5);
                                        stat5.executeUpdate();

                                        JLabel islemBasarili=new JLabel("İşlem Başarılı.");
                                        islemBasarili.setBounds(200,850,200,40);
                                        add(islemBasarili);
                                        islemBasarili.setVisible(true);
                                        islemBasarili.setForeground(Color.GREEN);
                                        islemBasarili.setFont(islemBasarili.getFont().deriveFont(Font.ITALIC,13));

                                        break;
                                    } else {
                                        JLabel islemBasarili=new JLabel("İşlem Başarısız.");
                                        islemBasarili.setBounds(200,850,200,40);
                                        add(islemBasarili);
                                        islemBasarili.setVisible(true);
                                        islemBasarili.setForeground(Color.RED);
                                        islemBasarili.setFont(islemBasarili.getFont().deriveFont(Font.ITALIC,15));
                                    }

                                }
                            }

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        remove(sc);
                        revalidate();
                        repaint();
                        try {
                            tabloOlusturma(kullaniciNo);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            Statement stat8 = conn.createStatement();
                            ResultSet res8 = stat8.executeQuery("select count(*) as Sayi  from Alan WHERE alan_sahibi=" + kullaniciNo + " AND alan_turu='arsa'");
                            res8.next();
                            int arsaSayisiX = res8.getInt("Sayi");
                            if(arsaSayisiX>=2){
                                satinAlma.setVisible(false);
                                yazi3.setVisible(true);
                                jSatilanArsa.setVisible(false);
                                jisletmeNo.setVisible(false);
                            }



                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        revalidate();
                        repaint();
                    });
            }
                else if(arsaSayisi>2){
                    yazi3.setVisible(true);

                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        isletmeSatis.addActionListener(e -> {
            JLabel yazi=new JLabel("İşletme Satış");
            yazi.setVisible(true);
            yazi.setBounds(220,30,230,40);
            yazi.setFont(yazi.getFont().deriveFont(Font.BOLD,25));
            add(yazi);
            yazi.setForeground(Color.BLACK);
            JLabel yazi2=new JLabel("Kullanıcı No = "+ kullaniciNo);
            yazi2.setVisible(true);
            yazi2.setBounds(220,70,220,40);
            yazi2.setFont(yazi2.getFont().deriveFont(Font.ITALIC,20));
            add(yazi2);
            yazi2.setForeground(Color.BLACK);

            satinAlma.setVisible(false);
            arsaSatis.setVisible(false);
            isletmeSatis.setVisible(false);

            revalidate();
            repaint();
            try {
                tabloOlusturmaisletme(kullaniciNo);
                jSatanEmlak = new JTextField("Emlakçı No");
                jSatanEmlak.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        super.focusGained(e);
                        jSatanEmlak.setText("");
                    }
                });
                jSatanEmlak.setBounds(100, 620, 70, 30);
                jSatanEmlak.setVisible(true);
                add(jSatanEmlak);

                jisletmeNo = new JTextField("Alınacak işletme");
                jisletmeNo.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        super.focusGained(e);
                        jisletmeNo.setText("");
                    }
                });
                jisletmeNo.setBounds(190, 620, 100, 30);
                jisletmeNo.setVisible(true);
                add(jisletmeNo);

                add(satinAlma2);
                satinAlma2.setBounds(400, 610, 50, 50);
                satinAlma2.setVisible(true);

                satinAlma2.addActionListener(e1 -> {
                    //buradaki işletme no satın alınan işletme no
                    int isletmeNo4 = Integer.parseInt(jisletmeNo.getText());
                    int satanEmlak = Integer.parseInt(jSatanEmlak.getText());

                    try {
                        Statement myStatCopy = conn.createStatement();
                        ResultSet resisletme = myStatCopy.executeQuery("select ucret,islem_tipi,satan_emlakci_no,islem_yapilan_isletme_no,islem_yapilan_kullanici_no from isletme_ilan where islem_tipi='satış' AND satan_emlakci_no="+satanEmlak+" AND islem_yapilan_isletme_no="+isletmeNo4+" ");
                        while(resisletme.next()){
                            System.out.println("Satılan İşletme No:"+isletmeNo4);
                            int isletmeUcret=resisletme.getInt("ucret");
                            int satanKisiNo2=resisletme.getInt("islem_yapilan_kullanici_no");

                            Statement stat10=conn.createStatement();
                            ResultSet res10=stat10.executeQuery("select kullanici_para_miktari from Kullanici where kullanici_no="+kullaniciNo+";");
                            res10.next();
                            int kullaniciParasi=res10.getInt("kullanici_para_miktari");
                            if(isletmeUcret<=kullaniciParasi){
                                System.out.println("Yeterli para var işletme için");
                                String query11 = ("UPDATE Kullanici set kullanici_para_miktari=kullanici_para_miktari-" + isletmeUcret + " WHERE kullanici_no=" + kullaniciNo + ";");
                                PreparedStatement stat11 = conn.prepareStatement(query11);
                                stat11.executeUpdate();

                                String query12 = ("UPDATE isletme set isletme_sahibi=" + kullaniciNo + " WHERE isletme_no=" + isletmeNo4 + ";");
                                PreparedStatement stat12 = conn.prepareStatement(query12);
                                stat12.executeUpdate();


                                String query14 = ("DELETE FROM isletme_ilan WHERE islem_yapilan_isletme_no=" + isletmeNo4 + ";");
                                PreparedStatement stat14 = conn.prepareStatement(query14);
                                stat14.executeUpdate();

                                Statement stat15=conn.createStatement();
                                ResultSet res15=stat15.executeQuery("select komisyon from Emlak where isletme_no="+satanEmlak+";");
                                res15.next();
                                int komisyonOranı=res15.getInt("komisyon");
                                int a=(isletmeUcret*komisyonOranı)/100;
                                System.out.println("komisyon ücreti: "+a);

                                String query13 = ("UPDATE Kullanici set kullanici_para_miktari=kullanici_para_miktari+"+(isletmeUcret-a)+ " WHERE kullanici_no=" +satanKisiNo2+ ";");
                                PreparedStatement stat13 = conn.prepareStatement(query13);
                                stat13.executeUpdate();
                              //  String query16=("")
                              //  PreparedStatement stat16=conn.prepareStatement(query16);

                                LocalDate gunumuz=LocalDate.now();
                                PreparedStatement stater=conn.prepareStatement("INSERT Emlak_islem_kayit (emlakci_isletme_no, islem_yapan_kisi_id, satilan_yer_no,islem_tipi, satis_tarihi, kiralama_tarihi, kira_bitis_tarihi,kira_suresi) VALUES (?,?,?,?,?,?,?,?)");
                                stater.setInt(1,satanEmlak);
                                stater.setInt(2,kullaniciNo);
                                stater.setInt(3,isletmeNo4);
                                stater.setString(4,"satış");
                                stater.setDate(5, tarih);
                                stater.setDate(6,null);
                                stater.setDate(7,null);
                                stater.setInt(8,99999999);
                                stater.executeUpdate();

                                Statement stat30= conn.createStatement();
                                ResultSet res30=stat30.executeQuery("select arsa_no from isletme where isletme_no="+isletmeNo4+";");
                                res30.next();

                                PreparedStatement stater2=conn.prepareStatement(" UPDATE isletme_guncel_kullanim set isleten_no=?, isletilecek_gun_sayisi=?  where alan_no="+res30.getInt(1)+";");
                                stater2.setInt(1,kullaniciNo);
                                stater2.setInt(2,999999);
                                stater2.executeUpdate();

                                PreparedStatement stater3=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                                stater3.setInt(1,kullaniciNo);
                                stater3.setString(2,"İşletme Satın Alım");
                                stater3.setInt(3,isletmeUcret*-1);
                                stater3.setDate(4,tarih);
                                stater3.executeUpdate();


                                Statement stat9=conn.createStatement();
                                ResultSet res9=stat9.executeQuery("select isletme_sahibi from isletme where isletme_no="+satanEmlak+";");
                                res9.next();
                                int emlakciSahibi=res9.getInt("isletme_sahibi");

                                PreparedStatement stater6=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                                stater6.setInt(1,emlakciSahibi);
                                stater6.setString(2,"Komisyon");
                                stater6.setInt(3,(a));
                                stater6.setDate(4,tarih);
                                stater6.executeUpdate();

                                PreparedStatement stater5=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                                stater5.setInt(1,satanKisiNo2);
                                stater5.setString(2,"İşletme Satış");
                                stater5.setInt(3,(isletmeUcret-a));
                                stater5.setDate(4,tarih);
                                stater5.executeUpdate();

                                PreparedStatement stater21=conn.prepareStatement(" UPDATE Alan set alan_sahibi=? where alan_no="+res30.getInt(1)+";");
                                stater21.setInt(1,kullaniciNo);
                                stater21.executeUpdate();

                                String query41 = ("UPDATE Kullanici set kullanici_para_miktari=kullanici_para_miktari+" + a + " WHERE kullanici_no=" + emlakciSahibi + ";");
                                PreparedStatement stat41 = conn.prepareStatement(query41);
                                stat41.executeUpdate();

                                JLabel islemBasarili=new JLabel("İşlem Başarılı.");
                                islemBasarili.setBounds(200,850,200,40);
                                add(islemBasarili);
                                islemBasarili.setVisible(true);
                                islemBasarili.setForeground(Color.GREEN);
                                islemBasarili.setFont(islemBasarili.getFont().deriveFont(Font.ITALIC,13));


                            }else{
                                JLabel islemBasarili=new JLabel("İşlem Başarısız.");
                                islemBasarili.setBounds(200,850,200,40);
                                add(islemBasarili);
                                islemBasarili.setVisible(true);
                                islemBasarili.setForeground(Color.RED);
                                islemBasarili.setFont(islemBasarili.getFont().deriveFont(Font.ITALIC,13));
                                System.out.println("Yeterli para yok");
                            }


                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    remove(sc2);
                    revalidate();
                    repaint();
                    try {
                        tabloOlusturmaisletme(kullaniciNo);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    revalidate();
                    repaint();

                });




            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }



        });

       // Statement myStat = conn.createStatement();
        //ResultSet res=myStat.executeQuery("select x.isletme_no as No,y.ucret as Ucret ,y.satilan_arsa_no as Arsa,y.satan_kisi_no as SatanNo from isletme x,Arsa_ilan y where x.isletme_no=y.emlakci_no AND x.turu='emlak' AND y.satan_kisi_no<>"+kullaniciNo+" ");


        /*Object[] sutunisim = {"isletme NO","Ucret","Satılan Arsa No","Satan kisi"};
        DefaultTableModel model = new DefaultTableModel(sutunisim, 0);
        while (res.next()) {
            int isletme=res.getInt("No");
            int yiyecek=res.getInt("Ucret");
            int arsa=res.getInt("Arsa");
            int satanNo=res.getInt("SatanNo");
            Object[] satir = {isletme,yiyecek,arsa,satanNo};
            model.addRow(satir);
        }

        JTable table = new JTable(model);
        table.setBounds(100, 200, 400, 400);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(100, 200, 400, 400);
        add(sp);
        sc = sp;
        sp.setVisible(true);
        setVisible(true);*/

        //frame 600 1080lik;


    }
    public void tabloOlusturma(int no) throws SQLException{

        Statement myStat = conn.createStatement();
        ResultSet res=myStat.executeQuery("select x.isletme_no as No,y.ucret as Ucret ,y.satilan_arsa_no as Arsa,y.satan_kisi_no as SatanNo from isletme x,Arsa_ilan y where x.isletme_no=y.emlakci_no AND x.turu='emlak' AND y.satan_kisi_no<>"+no+" ");

        Statement myStat2 = conn.createStatement();
        ResultSet res2=myStat2.executeQuery("select x.isletme_no as No,y.ucret as Ucret ,y.satilan_arsa_no as Arsa,y.satan_kisi_no as SatanNo from isletme x,Arsa_ilan y where x.isletme_no=y.emlakci_no AND x.turu='emlak' AND y.satan_kisi_no<>"+no+" ");

        Object[] sutunisim = {"İşletme No","Ücret","Satılan Arsa No","Satan Kişi"};
        DefaultTableModel model = new DefaultTableModel(sutunisim, 0);

        while (res2.next()) {
            res.next();
            int isletmeNo=res.getInt("No");
            int ucret=res.getInt("Ucret");
            int tur=res.getInt("Arsa");
            int satanKisi=res.getInt("SatanNo");
            Object[] satir = {isletmeNo,ucret,tur,satanKisi};
            model.addRow(satir);
        }
        JTable table = new JTable(model);
        table.setBounds(100, 200, 400, 400);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(100, 200, 400, 400);
        add(sp);
        sc = sp;
        sp.setVisible(true);
        setVisible(true);
    }
    public void tabloOlusturmaisletme(int no) throws SQLException{

        Statement myStat = conn.createStatement();
        ResultSet res=myStat.executeQuery("select satan_emlakci_no as No,islem_yapilan_isletme_no as SatilanNo,islem_yapilan_kullanici_no as SatanId,ucret as ucret from isletme_ilan where islem_tipi='satış' AND islem_yapilan_kullanici_no<>"+no+" ");

        Statement myStat2 = conn.createStatement();
        ResultSet res2=myStat2.executeQuery("select satan_emlakci_no as No,islem_yapilan_isletme_no as SatilanNo,islem_yapilan_kullanici_no as SatanId,ucret as ucret from isletme_ilan where islem_tipi='satış' AND islem_yapilan_kullanici_no<>"+no+" ");

        Object[] sutunisim = {"Emlakçı No","Satılan İşletme No","Satan Kişi No","Ücret"};
        DefaultTableModel model = new DefaultTableModel(sutunisim, 0);

        while (res2.next()) {
            res.next();
            int isletmeNo=res.getInt("No");
            int satilanNo=res.getInt("SatilanNo");
            int satanId=res.getInt("SatanId");
            int ucret=res.getInt("ucret");
            Object[] satir = {isletmeNo,satilanNo,satanId,ucret};
            model.addRow(satir);
        }
        JTable table = new JTable(model);
        table.setBounds(100, 200, 400, 400);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(100, 200, 400, 400);
        add(sp);
        sc2 = sp;
        sp.setVisible(true);
        setVisible(true);
    }

}
