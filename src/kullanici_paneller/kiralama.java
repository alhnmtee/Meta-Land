package kullanici_paneller;

import main.DatabaseConnect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;
import java.time.LocalDate;

public class kiralama extends JPanel {
    JScrollPane sc2;
    DatabaseConnect dtbc = new DatabaseConnect();
    Connection conn = dtbc.getConnection();

    Icon okicon =new ImageIcon(this.getClass().getResource("/resim/ok.png"));
    JButton islem=new JButton(okicon);

    JTextField jkiraSure;
    JTextField jemlakNo;
    JTextField jisletmeNo;


    public kiralama(int no)throws SQLException{

        int kullaniciNo=no;
        setLayout(null);
        setBounds(0,0,640,1080);
        setBackground(Color.decode("#334671"));

        JLabel yazi=new JLabel("İşletme Kiralama");
        yazi.setVisible(true);
        yazi.setBounds(220,30,230,40);
        yazi.setFont(yazi.getFont().deriveFont(Font.BOLD,25));
        add(yazi);
        yazi.setForeground(Color.BLACK);
        JLabel yazi2=new JLabel("Kullanıcı No = "+ kullaniciNo);
        yazi2.setVisible(true);
        yazi2.setBounds(230,70,220,40);
        yazi2.setFont(yazi2.getFont().deriveFont(Font.ITALIC,20));
        add(yazi2);
        yazi2.setForeground(Color.BLACK);

        String query16 = ("select oyun_guncel_tarihi FROM Baslangic");
        Statement stat16 = conn.createStatement();
        ResultSet baslangicGun=stat16.executeQuery(query16);
        baslangicGun.next();
        Date tarih=baslangicGun.getDate("oyun_guncel_tarihi");

        tabloOlusturmaKira(kullaniciNo);

        jemlakNo=new JTextField("Emlak No");
        jemlakNo.setBounds(100,620,70,30);
        jemlakNo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                jemlakNo.setText("");
            }
        });
        add(jemlakNo);
        jemlakNo.setVisible(true);

       jkiraSure=new JTextField("Kira(Gün)");
        jkiraSure.setBounds(200,620,70,30);
        jkiraSure.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                jkiraSure.setText("");
            }
        });
        add(jkiraSure);
        jkiraSure.setVisible(true);

        jisletmeNo=new JTextField("İşletme No");
       jisletmeNo.setBounds(300,620,70,30);
        jisletmeNo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                jisletmeNo.setText("");
            }
        });
        add(jisletmeNo);
        jisletmeNo.setVisible(true);

        islem.setBounds(400,610,50,50);
        islem.setVisible(true);
        add(islem);

        islem.addActionListener(e -> {
            //buradaki işletme no satın alınan işletme no
            int isletmeNo=Integer.parseInt(jisletmeNo.getText());
            int emlakNo=Integer.parseInt(jemlakNo.getText());
            int kiraSure=Integer.parseInt(jkiraSure.getText());
            try {
                Statement myStat = conn.createStatement();
                ResultSet res = myStat.executeQuery("select ucret,islem_tipi,satan_emlakci_no,islem_yapilan_isletme_no,islem_yapilan_kullanici_no" +
                        " from isletme_ilan where islem_tipi='kira' AND satan_emlakci_no=" + emlakNo + " AND islem_yapilan_isletme_no=" + isletmeNo + " ");
            while(res.next()){
                int isletmeKiraUcret=res.getInt("ucret");
                int satanKisiNo=res.getInt("islem_yapilan_kullanici_no");

                Statement stat1=conn.createStatement();
                ResultSet res1=stat1.executeQuery("select kullanici_para_miktari from Kullanici where kullanici_no="+kullaniciNo+";");
                res1.next();
                int kullaniciParasi=res1.getInt("kullanici_para_miktari");
                if(isletmeKiraUcret*kiraSure<=kullaniciParasi){
                    String query2 = ("UPDATE Kullanici set kullanici_para_miktari=kullanici_para_miktari-" + isletmeKiraUcret*kiraSure + " WHERE kullanici_no=" + kullaniciNo + ";");
                    PreparedStatement stat2 = conn.prepareStatement(query2);
                    stat2.executeUpdate();


                    Statement stat5=conn.createStatement();
                    ResultSet res5=stat5.executeQuery("select komisyon from Emlak where isletme_no="+emlakNo+";");
                    res5.next();
                    int komisyonOranı=res5.getInt("komisyon");
                    int komisyon=(isletmeKiraUcret*komisyonOranı)/100;
                    System.out.println("komisyon ücreti: "+komisyon);

                    String query4 = ("UPDATE Kullanici set kullanici_para_miktari=kullanici_para_miktari+" + ((isletmeKiraUcret*kiraSure)-komisyon)+" WHERE kullanici_no=" +satanKisiNo+ ";");
                    PreparedStatement stat4 = conn.prepareStatement(query4);
                    stat4.executeUpdate();

                    String query6 = ("DELETE FROM isletme_ilan WHERE islem_yapilan_isletme_no=" + isletmeNo + ";");
                    PreparedStatement stat6 = conn.prepareStatement(query6);
                    stat6.executeUpdate();


                    LocalDate endDate=tarih.toLocalDate().plusDays(kiraSure);

                    PreparedStatement stater=conn.prepareStatement("INSERT Emlak_islem_kayit (emlakci_isletme_no, islem_yapan_kisi_id, satilan_yer_no,islem_tipi, satis_tarihi, kiralama_tarihi, kira_bitis_tarihi,kira_suresi) VALUES (?,?,?,?,?,?,?,?)");
                    stater.setInt(1,emlakNo);
                    stater.setInt(2,kullaniciNo);
                    stater.setInt(3,isletmeNo);
                    stater.setString(4,"kira");
                    stater.setDate(5, null);
                    stater.setDate(6,tarih);
                    stater.setDate(7, Date.valueOf(endDate));
                    stater.setInt(8,kiraSure);
                    stater.executeUpdate();

                    Statement stat11= conn.createStatement();
                    ResultSet res11=stat11.executeQuery("select arsa_no,isletme_sahibi from isletme where isletme_no="+isletmeNo);
                    res11.next();


                    PreparedStatement stater2=conn.prepareStatement("UPDATE isletme_guncel_kullanim set isleten_no=?,isletilecek_gun_sayisi=? where alan_no=?");
                    stater2.setInt(3,res11.getInt(1));
                    stater2.setInt(1,kullaniciNo);
                    stater2.setInt(2,kiraSure);
                    stater2.executeUpdate();

                    PreparedStatement stater3=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                    stater3.setInt(1,kullaniciNo);
                    stater3.setString(2,"İşletme Kira Gider");
                    stater3.setInt(3,isletmeKiraUcret*kiraSure*-1);
                    stater3.setDate(4,tarih);
                    stater3.executeUpdate();



                    PreparedStatement stater7=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                    stater7.setInt(1,res11.getInt(2));
                    stater7.setString(2,"İşletme Kira Geliri");
                    stater7.setInt(3,isletmeKiraUcret*kiraSure);
                    stater7.setDate(4,tarih);
                    stater7.executeUpdate();


                    Statement stat9=conn.createStatement();
                    ResultSet res9=stat9.executeQuery("select isletme_sahibi from isletme where isletme_no="+emlakNo+";");
                    res9.next();
                    int emlakciSahibi=res9.getInt("isletme_sahibi");

                    PreparedStatement stater6=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                    stater6.setInt(1,emlakciSahibi);
                    stater6.setString(2,"Komisyon");
                    stater6.setInt(3,(komisyon));
                    stater6.setDate(4,tarih);
                    stater6.executeUpdate();


                    String query18 = ("UPDATE Kullanici set kullanici_para_miktari=kullanici_para_miktari+" +komisyon+" WHERE kullanici_no=" +emlakciSahibi+ ";");
                    PreparedStatement stat14 = conn.prepareStatement(query18);
                    stat14.executeUpdate();


                    JLabel islemBasarili=new JLabel("İşlem Başarılı.");
                    islemBasarili.setBounds(200,850,200,40);
                    add(islemBasarili);
                    islemBasarili.setVisible(true);
                    islemBasarili.setForeground(Color.GREEN);
                    islemBasarili.setFont(islemBasarili.getFont().deriveFont(Font.ITALIC,15));


                } else{
                    JLabel islemBasarili=new JLabel("İşlem Başarısız");
                    islemBasarili.setBounds(200,850,200,40);
                    add(islemBasarili);
                    islemBasarili.setVisible(true);
                    islemBasarili.setForeground(Color.RED);
                    islemBasarili.setFont(islemBasarili.getFont().deriveFont(Font.ITALIC,15));
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
                tabloOlusturmaKira(kullaniciNo);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            revalidate();
            repaint();

        });



    }


    public void tabloOlusturmaKira(int no) throws SQLException {

        Statement myStat = conn.createStatement();
        ResultSet res=myStat.executeQuery("select satan_emlakci_no as No,islem_yapilan_isletme_no as SatilanNo,islem_yapilan_kullanici_no as SatanId,ucret as ucret from isletme_ilan where islem_tipi='kira' AND islem_yapilan_kullanici_no<>"+no+" ");

        Statement myStat2 = conn.createStatement();
        ResultSet res2=myStat2.executeQuery("select satan_emlakci_no as No,islem_yapilan_isletme_no as SatilanNo,islem_yapilan_kullanici_no as SatanId,ucret as ucret from isletme_ilan where islem_tipi='kira' AND islem_yapilan_kullanici_no<>"+no+" ");

        Object[] sutunisim = {"Emlak No","Satılan İşletme No","Satan Kişi No","Ücret(Gün)"};
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
