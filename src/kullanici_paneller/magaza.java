package kullanici_paneller;

import main.DatabaseConnect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;

public class magaza extends JPanel {
    JScrollPane sc;
    DatabaseConnect dtbc = new DatabaseConnect();
    Connection conn = dtbc.getConnection();
    JTextField jisletmeNo;
    JTextField jMiktar;
    Icon buyicon2 =new ImageIcon(this.getClass().getResource("/resim/buyicon2.png"));
    JButton satinAlma=new JButton(buyicon2);


    public magaza(int no) throws SQLException {
        int kullaniciNo=no;
        setLayout(null);
        setBounds(0,0,620,1080);
        setBackground(Color.decode("#334671"));


        JLabel yazi=new JLabel("Mağaza Alışverişi ");
        yazi.setVisible(true);
        yazi.setBounds(220,30,230,40);
        yazi.setFont(yazi.getFont().deriveFont(Font.BOLD,25));
        add(yazi);
        yazi.setForeground(Color.BLACK);
        JLabel yazi2=new JLabel("Kullanıcı No = "+ kullaniciNo);
        yazi2.setVisible(true);
        yazi2.setBounds(240,70,220,40);
        yazi2.setFont(yazi2.getFont().deriveFont(Font.ITALIC,20));
        add(yazi2);
        yazi2.setForeground(Color.BLACK);

        Statement myStat = conn.createStatement();
        ResultSet res=myStat.executeQuery("select x.isletme_no as No,y.esya_ucret as Ucret from isletme x,Magaza y where x.isletme_no=y.isletme_no AND x.turu='mağaza'");
        ResultSetMetaData rsMetaData=res.getMetaData();
        int satirSayaci = rsMetaData.getColumnCount();
        Object[] sutunisim = {"İşletme No","Miktar"};
        DefaultTableModel model = new DefaultTableModel(sutunisim, 0);

        while (res.next()) {
            int isletme=res.getInt("No");
            int esya=res.getInt("Ucret");
            Object[] satir = {isletme,esya};
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

        jisletmeNo = new JTextField("İşletme No");
        jisletmeNo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                jisletmeNo.setText("");
            }
        });
        jisletmeNo.setBounds(100, 620, 70, 30);

        jMiktar=new JTextField("Miktar");
        jMiktar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                jMiktar.setText("");
            }
        });
        jMiktar.setBounds(200,620,70,30);
        add(satinAlma);
        satinAlma.setVisible(true);
        satinAlma.setBounds(300,610,50,50);
        add(jisletmeNo);
        add(jMiktar);
        jMiktar.setVisible(true);
        jisletmeNo.setVisible(true);


        satinAlma.addActionListener(e -> {
            int miktar= Integer.parseInt(jMiktar.getText());
            int isletmeNo= Integer.parseInt(jisletmeNo.getText());
            try {
                //Magaza satın alım
                Statement myStatCopy = conn.createStatement();
                ResultSet resCopy=myStatCopy.executeQuery("select x.isletme_sahibi as Sahip ,x.isletme_no as No,y.esya_ucret as Ucret from isletme x,Magaza y where x.isletme_no=y.isletme_no AND x.turu='mağaza'");
                while(resCopy.next()) {
                    if (isletmeNo == resCopy.getInt("No")) {
                        int sahibi=resCopy.getInt("Sahip");
                        int marketucret=resCopy.getInt("Ucret");
                        ResultSet res3 = myStatCopy.executeQuery("select kullanici_para_miktari as MMM from Kullanici where kullanici_no='" + kullaniciNo + "';");
                        res3.next();
                        if (marketucret*miktar<=res3.getInt("MMM")) {
                            int toplamMiktar=marketucret*miktar;
                            //para azaltıp esya ekleme;
                            String query=("UPDATE Kullanici set kullanici_para_miktari=kullanici_para_miktari-?,kullanici_esya_miktari=Kullanici.kullanici_esya_miktari+"+miktar+" WHERE kullanici_no=?");
                            PreparedStatement stat= conn.prepareStatement(query);
                            stat.setInt(1,toplamMiktar);
                            stat.setInt(2,kullaniciNo);
                            stat.executeUpdate();
                            //market sahibine para ekleme;
                            Statement myStat2 = conn.createStatement();
                            ResultSet res4=myStat2.executeQuery("select isletme_sahibi from isletme where isletme_no='"+isletmeNo+"';");
                            res4.next();
                            String query2=("UPDATE Kullanici set kullanici_para_miktari=kullanici_para_miktari+"+toplamMiktar+" WHERE kullanici_no="+sahibi);
                            PreparedStatement stat2= conn.prepareStatement(query2);
                            stat2.executeUpdate();

                           Statement myStat5=conn.createStatement();
                           ResultSet res5=myStat5.executeQuery("select oyun_guncel_tarihi from Baslangic");
                           res5.next();
                           Date tarih=res5.getDate("oyun_guncel_tarihi");

                           PreparedStatement stater=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                           stater.setInt(1,kullaniciNo);
                           stater.setString(2,"Mağaza Eşya Alımı");
                           stater.setInt(3,miktar);
                           stater.setDate(4,tarih);
                           stater.executeUpdate();

                            PreparedStatement stater2=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                            stater2.setInt(1,kullaniciNo);
                            stater2.setString(2,"Mağaza Gideri");
                            stater2.setInt(3,toplamMiktar*-1);
                            stater2.setDate(4,tarih);
                            stater2.executeUpdate();

                            PreparedStatement stater3=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                            stater3.setInt(1,sahibi);
                            stater3.setString(2,"Mağaza geliri");
                            stater3.setInt(3,toplamMiktar);
                            stater3.setDate(4,tarih);
                            stater3.executeUpdate();

                            JLabel islemBasarili=new JLabel("İşlem Başarılı.");
                            islemBasarili.setBounds(200,850,200,40);
                            add(islemBasarili);
                            islemBasarili.setVisible(true);
                            islemBasarili.setForeground(Color.GREEN);
                            islemBasarili.setFont(islemBasarili.getFont().deriveFont(Font.ITALIC,15));

                            break;
                        }
                        else {
                            JLabel islemBasarili=new JLabel("Paranız Yetersiz.");
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
        });
    }
}
