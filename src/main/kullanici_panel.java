package main;

import kullanici_paneller.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class kullanici_panel extends JPanel {
    JTable market=new JTable();
    ArrayList<JButton> arsaButonlar=new ArrayList<JButton>();
    ArrayList<JLabel> jLabelArray =new ArrayList<JLabel>();
    int kullaniciNo;
    DefaultTableModel table=new DefaultTableModel();
    JTable emlak=new JTable();
    JTable magaza=new JTable();
    DatabaseConnect dtbc = new DatabaseConnect();
    Connection conn = dtbc.getConnection();
    JPopupMenu pop;
    Icon buyicon =new ImageIcon(this.getClass().getResource("/resim/buyicon.png"));
    Icon buyicon2 =new ImageIcon(this.getClass().getResource("/resim/buyicon2.png"));
    Icon kiraicon =new ImageIcon(this.getClass().getResource("/resim/kira.png"));
    Icon accounticon =new ImageIcon(this.getClass().getResource("/resim/account.png"));
    Icon isletmeicon =new ImageIcon(this.getClass().getResource("/resim/isletme.png"));
    Icon refreshGif=new ImageIcon(this.getClass().getResource("/resim/refresh.png"));
    Icon isAramaicon =new ImageIcon(this.getClass().getResource("/resim/isarama.png"));

    Icon magazaicon =new ImageIcon(this.getClass().getResource("/resim/magaza.png"));
    Icon marketicon =new ImageIcon(this.getClass().getResource("/resim/market.png"));
    Icon emlakicon =new ImageIcon(this.getClass().getResource("/resim/emlak.png"));
    Icon arsaicon =new ImageIcon(this.getClass().getResource("/resim/maviarsa.png"));

    Icon tlicon =new ImageIcon(this.getClass().getResource("/resim/tl.png"));
    Icon tarihicon =new ImageIcon(this.getClass().getResource("/resim/tarih.png"));
    Icon esyaicon =new ImageIcon(this.getClass().getResource("/resim/esya.png"));
    Icon yiyecekicon =new ImageIcon(this.getClass().getResource("/resim/yiyecek.png"));

    Icon profilicon =new ImageIcon(this.getClass().getResource("/resim/profil.png"));

    Icon kullaniciGidericon =new ImageIcon(this.getClass().getResource("/resim/kullanicigider.png"));
    Icon malVarligiicon =new ImageIcon(this.getClass().getResource("/resim/malvarlıgı.png"));
    Icon isGecmisiicon =new ImageIcon(this.getClass().getResource("/resim/isgecmisi.png"));
    Icon isilanivermeicon =new ImageIcon(this.getClass().getResource("/resim/isilani.png"));
    Icon isletmeilanicon =new ImageIcon(this.getClass().getResource("/resim/isletmeilan.png"));



    JButton satinAl=new JButton(buyicon);
    JButton refresh=new JButton(refreshGif);
  //  JButton profil=new JButton(accounticon);
    JButton satinAlma=new JButton(buyicon2);
    JButton kirala=new JButton(kiraicon);
    JButton isletmeKurma=new JButton(isletmeicon);
    JButton isArama=new JButton(isAramaicon);

    JTextField jisletmeNo;
    JTextField jMiktar;


    public kullanici_panel(int x)  {


        try {
            arsaOlusturma();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setLayout(null);
        setBounds(0,0,1300,1080);
        setVisible(true);
        kullaniciNo=x;

        JLabel yazi=new JLabel("İşletme No");
        yazi.setBounds(1420,380,570,20);

        JLabel yazi2=new JLabel("Ücret");
        yazi2.setBounds(1620,380,570,20);

        add(yazi);
        add(yazi2);
        yazi2.setVisible(false);
        yazi.setVisible(false);






        satinAl.setBounds(1200,150,50,50);
        add(satinAl);

       // profil.setBounds(1200,800,50,50);
        //add(profil);

        isletmeKurma.setBounds(1200,230,50,50);
        add(isletmeKurma);

        //isArama.setBounds(1170,310,48,48);
       // add(isArama);

        kirala.setBounds(1200,390,50,50);
        add(kirala);


        jisletmeNo = new JTextField("İsletme No");
        jisletmeNo.setBounds(1500, 850, 70, 30);

        jMiktar=new JTextField("Miktar");
        jMiktar.setBounds(1400,850,70,30);

        JTextField jSatilanArsa=new JTextField("Arsa No");
        jSatilanArsa.setBounds(1600,850,70,30);

        add(jisletmeNo);
        add(jMiktar);
        jMiktar.setVisible(false);
        jisletmeNo.setVisible(false);


        JPopupMenu popUp = new JPopupMenu();
        pop = popUp;
        add(pop);
        JMenuItem p1 = new JMenuItem("Market");
        JMenuItem p2 = new JMenuItem("Mağaza");
        JMenuItem p3 = new JMenuItem("Emlak");
        pop.add(p1);
        pop.add(p2);
        pop.add(p3);

        add(satinAlma);
        satinAlma.setBounds(1600,850,50,50);
        satinAlma.setVisible(false);

        refresh.setBounds(10,10,40,40);
        add(refresh);
        refresh.setOpaque(false);
        refresh.setContentAreaFilled(false);
        refresh.setBorderPainted(false);

        labelGuncelle();

        refresh.addActionListener(e -> {
            for (int i = 0; i < jLabelArray.size(); i++) {
                remove(jLabelArray.get(i));
            }
            for (int j = 0; j <arsaButonlar.size() ; j++) {
                remove(arsaButonlar.get(j));
            }
            try {
                arsaOlusturma();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            labelGuncelle();
            repaint();

        });



        satinAl.addActionListener(e -> {
            pop.show(satinAl,50,50);
        });
        //Market pop up butonu
        p1.addActionListener(e1 -> {
            try {
                JFrame marketFrame=new JFrame("Market");
                marketFrame.setBounds(1300,0,600,1080);
                marketFrame.setVisible(true);
                market a= new market(kullaniciNo);
                marketFrame.add(a);
                //setLayout(null);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        // Magaza pop up butonu
        p2.addActionListener(e12 -> {
            try {
                JFrame magazaFrame=new JFrame("Mağaza");
                magazaFrame.setBounds(1300,0,600,1080);
                magazaFrame.setVisible(true);
                magaza b=new magaza(kullaniciNo);
                magazaFrame.add(b);
                setLayout(null);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
//emlak için pop buton
        p3.addActionListener(e13 -> {//Emlakçı arsa listeleme
            try {
                JFrame emlakFrame=new JFrame("Emlak");
                emlakFrame.setBounds(1300,0,600,1080);
                emlakFrame.setVisible(true);
                emlakSatis c= new emlakSatis(kullaniciNo);
                emlakFrame.add(c);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });


        isletmeKurma.addActionListener(e22 -> {
            JFrame isletme=new JFrame("İsletme kurma");
            setLayout(null);
            isletme.setBounds(1300,0,620,1080);
            isletme.setVisible(true);
            isletme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            isletmeKurma isletme1= null;
            try {
                isletme1 = new isletmeKurma(kullaniciNo);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            isletme.add(isletme1);
        });

/////




        JButton giderTablosu = new JButton(kullaniciGidericon);
        giderTablosu.setBounds(1100, 230, 50, 50);
        giderTablosu.setVisible(true);
        add(giderTablosu);
        giderTablosu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame giderFrame=new JFrame("Emlak");
                giderFrame.setBounds(1300,0,600,1080);
                giderFrame.setVisible(true);
                kullanici_gider c= new kullanici_gider(kullaniciNo);
                giderFrame.add(c);
            }
        });

        JButton iseGirme = new JButton(isAramaicon);
        iseGirme.setBounds(1200, 310, 50, 50);
        iseGirme.setVisible(true);
        add(iseGirme);
        iseGirme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame giderFrame=new JFrame("ise girme");
                giderFrame.setBounds(1300,0,600,1080);
                giderFrame.setVisible(true);
                ise_girme c= new ise_girme(kullaniciNo);
                giderFrame.add(c);
            }
        });

        JButton sahipOlma = new JButton(malVarligiicon);
        sahipOlma.setBounds(1100, 310, 50, 50);
        sahipOlma.setVisible(true);
        add(sahipOlma);
        sahipOlma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame giderFrame=new JFrame("Kullanıcı Varlıkları");
                giderFrame.setBounds(1300,0,600,1080);
                giderFrame.setVisible(true);
                kullanici_varliklari c= new kullanici_varliklari(kullaniciNo);
                giderFrame.add(c);
            }
        });

        JButton emlak_is_gecmis = new JButton(isGecmisiicon);
        emlak_is_gecmis.setBounds(1100, 390, 50, 50);
        emlak_is_gecmis.setVisible(true);
        add(emlak_is_gecmis);
        emlak_is_gecmis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame giderFrame=new JFrame("Emlak - İş Geçmişi");
                giderFrame.setBounds(1300,0,600,1080);
                giderFrame.setVisible(true);
                kullanici_emlak_is_gecmis c= new kullanici_emlak_is_gecmis(kullaniciNo);
                giderFrame.add(c);
            }
        });

        JButton is_ilani_verme = new JButton(isilanivermeicon);
        is_ilani_verme.setBounds(1200, 470, 50, 50);
        is_ilani_verme.setVisible(true);
        add(is_ilani_verme);
        is_ilani_verme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame giderFrame=new JFrame("İş İlanı Verme");
                giderFrame.setBounds(1300,0,600,1080);
                giderFrame.setVisible(true);
                is_ilani_verme c= new is_ilani_verme(kullaniciNo);
                giderFrame.add(c);
            }
        });

        JButton işletme_ilan_verme = new JButton(isletmeilanicon);
        işletme_ilan_verme.setBounds(1200, 550, 50, 50);
        işletme_ilan_verme.setVisible(true);
        add(işletme_ilan_verme);
        işletme_ilan_verme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame giderFrame=new JFrame("İşletme İlanı Verme");
                giderFrame.setBounds(1300,0,600,1080);
                giderFrame.setVisible(true);
                isletme_ilan_verme c= new isletme_ilan_verme(kullaniciNo);
                giderFrame.add(c);
            }
        });


        kirala.addActionListener(e23 -> {
            JFrame kirala=new JFrame("İşletme kirala");
            kirala.setBounds(1280,0,640,1080);
            kirala.setVisible(true);
            kirala.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            try {
                kiralama kira1= new kiralama(kullaniciNo);
                kirala.add(kira1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }



        });



    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawLine(0, 70, 1920, 70);
    }


    public void arsaOlusturma() throws SQLException{
        Statement myStat = conn.createStatement();
        ResultSet res=myStat.executeQuery("select oyun_alan_boyutu from Baslangic ");
        res.next();
        String oyunAlan=res.getString("oyun_alan_boyutu");
        System.out.println(oyunAlan);


        Statement myStat3=conn.createStatement();
        ResultSet res3=myStat3.executeQuery("select alan_turu,alan_no from Alan");


        int satir=Character.getNumericValue(oyunAlan.charAt(0));
        int sutun=Character.getNumericValue(oyunAlan.charAt(2));
        System.out.println("deneme"+satir + "test "+ sutun);
        //Verileri çektikten sonra bu değerleri ona göre ayarlayacaz
        int basX=100;
        int basY=60;
        int butonGenislik=600/sutun;
        int butonYukseklik=600/satir;
        int dikeyBosluk=25;
        int yatatBosluk=25;
        int i=0;

        Font font = new Font("Sylfaen", Font.BOLD, 16);


        int alanSira=1;
        while(res3.next()){
            String a=res3.getString("alan_turu");
            a=a.trim();
            if(a.equalsIgnoreCase("işletme")){
                Statement myStat2=conn.createStatement();
                ResultSet res2=myStat2.executeQuery("select turu ,isletme_no from isletme where arsa_no="+alanSira+" ");
                res2.next();
                String x=res2.getString("turu");
                x = x.trim();
                System.out.println(x);
                if (x.equalsIgnoreCase("emlak")){
                    //System.out.println(res2.getString("turu"));
                    JButton button=new JButton(emlakicon);
                    button.setBounds(basX,basY,butonGenislik,butonYukseklik);
                    add(button);
                    button.setVisible(true);
                    button.setOpaque(false);
                    button.setContentAreaFilled(false);
                    button.setBorderPainted(false);
                    arsaButonlar.add(button);
                    System.out.println("emlak");
                }
                if (x.equalsIgnoreCase("market")){
                    // System.out.println(res2.getString("turu"));
                    JButton button=new JButton(marketicon);
                    button.setBounds(basX,basY,butonGenislik,butonYukseklik);
                    add(button);
                    button.setVisible(true);
                    button.setOpaque(false);
                    button.setContentAreaFilled(false);
                    button.setBorderPainted(false);
                    arsaButonlar.add(button);
                    System.out.println("market");
                }
                if (x.equalsIgnoreCase("mağaza")){
                    //System.out.println(res2.getString("turu"));
                    JButton button=new JButton(magazaicon);
                    button.setBounds(basX,basY,butonGenislik,butonYukseklik);
                    add(button);
                    button.setVisible(true);
                    button.setOpaque(false);
                    button.setContentAreaFilled(false);
                    button.setBorderPainted(false);
                    arsaButonlar.add(button);
                    System.out.println("magaza");
                }
                int isletmeNo=res2.getInt("isletme_no");
                JLabel label=new JLabel("<html>Arsa No: "+alanSira+"<br/>İşletme No:"+ isletmeNo+"<html>");
                label.setBounds(basX+5,basY+butonYukseklik+1,butonGenislik,35);
                add(label);
                label.setVisible(true);
                jLabelArray.add(label);
                label.setForeground(Color.BLACK);
                label.setFont(font);

            }
            else{
                JButton button=new JButton(arsaicon);
                button.setBounds(basX,basY,butonGenislik,butonYukseklik);
                add(button);
                button.setVisible(true);
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                arsaButonlar.add(button);
                System.out.println("bos");
                JLabel label=new JLabel("Arsa No: "+alanSira);
                label.setBounds(basX+5,basY+butonYukseklik+1,butonGenislik,35);
                add(label);
                label.setVisible(true);
                label.setForeground(Color.BLACK);
                jLabelArray.add(label);
                label.setFont(font);
            }
            basX+=butonGenislik+yatatBosluk;

            if(alanSira%sutun==0){
                basX=100;
                basY+=butonYukseklik+dikeyBosluk;
            }
            alanSira++;
        }

    }
    public void labelGuncelle(){

        Font font = new Font("Sylfaen", Font.PLAIN, 17);
        try{
            Statement myStat = conn.createStatement();
            ResultSet res =myStat.executeQuery("select kullanici_para_miktari,kullanici_esya_miktari,kullanici_yemek_miktari ,kullanici_adi from Kullanici where kullanici_no="+kullaniciNo+" ");
            res.next();
            int kullaniciPara= res.getInt("kullanici_para_miktari");
            int kullaniciEsya=res.getInt("kullanici_esya_miktari");
            int kullaniciYemek=res.getInt("kullanici_yemek_miktari");
            String kullaniciAdi=res.getString("kullanici_adi");

            JLabel tlLabel=new JLabel(tlicon);
            tlLabel.setVisible(true);
            add(tlLabel);
            tlLabel.setBounds(800,10,50,50);
            jLabelArray.add(tlLabel);

            JLabel tl=new JLabel("= "+kullaniciPara);
            tl.setVisible(true);
            add(tl);
            tl.setBounds(850,15,100,50);
            jLabelArray.add(tl);
            tl.setForeground(Color.BLACK);
            tl.setFont(font);


            JLabel esyaLabel=new JLabel(esyaicon);
            esyaLabel.setVisible(true);
            add(esyaLabel);
            esyaLabel.setBounds(950,10,50,50);
            jLabelArray.add(esyaLabel);

            JLabel esya=new JLabel("= "+kullaniciEsya);
            esya.setVisible(true);
            add(esya);
            esya.setBounds(1000,15,100,50);
            jLabelArray.add(esya);
            esya.setForeground(Color.BLACK);
            esya.setFont(font);

            JLabel yiyecekLabel =new JLabel(yiyecekicon);
            yiyecekLabel.setVisible(true);
            add(yiyecekLabel);
            yiyecekLabel.setBounds(1100,10,50,50);
            jLabelArray.add(yiyecekLabel);

            JLabel yiyecek =new JLabel("= "+kullaniciYemek);
            yiyecek.setVisible(true);
            add(yiyecek);
            yiyecek.setBounds(1150,15,100,50);
            jLabelArray.add(yiyecek);
           yiyecek.setForeground(Color.BLACK);
           yiyecek.setFont(font);

            Statement stat=conn.createStatement();
            ResultSet res2=stat.executeQuery("select oyun_guncel_tarihi from Baslangic");
            res2.next();
            JLabel tarihLabel=new JLabel(tarihicon);
            tarihLabel.setVisible(true);
            tarihLabel.setBounds(400,10,50,50);
            add(tarihLabel);
            jLabelArray.add(tarihLabel);

            JLabel tarih=new JLabel(res2.getString("oyun_guncel_tarihi"));
            tarih.setVisible(true);
            tarih.setBounds(450,15,150,50);
            add(tarih);
            jLabelArray.add(tarih);
            tarih.setForeground(Color.BLACK);
            tarih.setFont(font);

            JLabel ppLabel=new JLabel(profilicon);
            add(ppLabel);
            ppLabel.setVisible(true);
            ppLabel.setBounds(70,10,50,50);
            jLabelArray.add(ppLabel);

            JLabel pp=new JLabel(kullaniciAdi+"No= " +kullaniciNo);
            pp.setVisible(true);
            add(pp);
            pp.setBounds(120,20,180,30);
            jLabelArray.add(pp);
            pp.setForeground(Color.BLACK);
            pp.setFont(font);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
