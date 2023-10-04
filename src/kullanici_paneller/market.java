package kullanici_paneller;

import main.DatabaseConnect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;

public class market extends JPanel {
    JScrollPane sc;
    DatabaseConnect dtbc = new DatabaseConnect();
    Connection conn = dtbc.getConnection();
    JTextField jisletmeNo;
    JTextField jMiktar;
    Icon buyicon2 =new ImageIcon(this.getClass().getResource("/resim/buyicon2.png"));

    Icon tlicon =new ImageIcon(this.getClass().getResource("/resim/tl.png"));

    JButton satinAlma=new JButton(buyicon2);
    public market(int no) throws SQLException {
        int kullaniciNo=no;
        setLayout(null);
        setBounds(0,0,620,1080);
        setBackground(Color.decode("#334671"));
        JLabel yazi=new JLabel("Market Alışverişi ");
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
        ResultSet res=myStat.executeQuery("select x.isletme_no as No,y.yiyecek_ucret as Ucret from isletme x,market y where x.isletme_no=y.isletme_no AND x.turu='market'");
        Object[] sutunisim = {"isletme NO","Miktar"};

        //Statement myStat3 = conn.createStatement();
       // ResultSet res2 = myStat3.executeQuery("select COUNT(*) from Kullanici");
        //ResultSetMetaData rsMetaData=res.getMetaData();
        //int satirSayisi = rsMetaData.getColumnCount();
        DefaultTableModel model = new DefaultTableModel(sutunisim, 0);

        //Object[][] veriler = new Object[2][satirSayisi];
        while (res.next()) {
                int isletme=res.getInt("No");
                int yiyecek=res.getInt("Ucret");
                Object[] satir = {isletme,yiyecek};
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

        //frame 600 1080lik;

        jisletmeNo = new JTextField("İsletme No");
        jisletmeNo.setBounds(100, 620, 70, 30);
        jisletmeNo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                jisletmeNo.setText("");
            }
        });
        add(jisletmeNo);
        jisletmeNo.setVisible(true);

        jMiktar=new JTextField("Miktar");
        jMiktar.setBounds(200,620,70,30);
        jMiktar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                jMiktar.setText("");
            }
        });
        add(jMiktar);
        jMiktar.setVisible(true);

        add(satinAlma);
        satinAlma.setVisible(true);
        satinAlma.setBounds(300,610,50,50);



        Statement myStatCopy5 = conn.createStatement();
        ResultSet res5= myStatCopy5.executeQuery("select kullanici_para_miktari as MMM from Kullanici where kullanici_no='" + kullaniciNo + "';");
        res5.next();
        int kullaniciPara=res5.getInt("MMM");

        satinAlma.addActionListener(e -> {
            int miktar= Integer.parseInt(jMiktar.getText());
            int isletmeNo= Integer.parseInt(jisletmeNo.getText());
            try {
                //Market satın alım
                     Statement myStatCopy = conn.createStatement();
                    ResultSet resCopy=myStatCopy.executeQuery("select x.isletme_sahibi as Sahip,y.yiyecek_ucret as Ucret,x.isletme_no as No from isletme x,market y where x.isletme_no=y.isletme_no AND x.turu='market'");
                    while(resCopy.next()) {
                        System.out.println(resCopy.getInt("No"));
                        System.out.println(isletmeNo+"  2.");
                        if (isletmeNo == resCopy.getInt("No")) {
                            System.out.println("if1");
                            int sahibi=resCopy.getInt("Sahip");
                            int marketucret=resCopy.getInt("Ucret");
                            Statement myStatCopy2 = conn.createStatement();
                            ResultSet res3= myStatCopy2.executeQuery("select kullanici_para_miktari as MMM from Kullanici where kullanici_no='" + kullaniciNo + "';");
                            res3.next();
                            if (marketucret*miktar<=res3.getInt("MMM")) {
                                System.out.println("if2");
                                int toplamMiktar=marketucret*miktar;
                                //para azaltıp yemek ekleme
                                String query=("UPDATE Kullanici set kullanici_para_miktari=kullanici_para_miktari-?,kullanici_yemek_miktari=kullanici_yemek_miktari+"+miktar+" WHERE kullanici_no=?");
                                PreparedStatement stat= conn.prepareStatement(query);
                                stat.setInt(1,toplamMiktar);
                                stat.setInt(2,kullaniciNo);
                                stat.executeUpdate();
                                //işletme sahibine para ekleme
                                Statement myStat2 = conn.createStatement();
                                ResultSet res4=myStat2.executeQuery("select isletme_sahibi from isletme where isletme_no='"+isletmeNo+"';");
                                res4.next();
                                String query2=("UPDATE Kullanici set kullanici_para_miktari=kullanici_para_miktari+"+toplamMiktar+" WHERE kullanici_no="+sahibi);
                                PreparedStatement stat2= conn.prepareStatement(query2);
                                stat2.executeUpdate();

                                Statement myStat6=conn.createStatement();
                                ResultSet res6=myStat6.executeQuery("select oyun_guncel_tarihi from Baslangic");
                                res6.next();
                                Date tarih=res6.getDate("oyun_guncel_tarihi");

                                PreparedStatement stater=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                                stater.setInt(1,kullaniciNo);
                                stater.setString(2,"Market yiyecek Alımı");
                                stater.setInt(3,miktar);
                                stater.setDate(4,tarih);
                                stater.executeUpdate();

                                PreparedStatement stater2=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                                stater2.setInt(1,kullaniciNo);
                                stater2.setString(2,"Market Gideri");
                                stater2.setInt(3,toplamMiktar*-1);
                                stater2.setDate(4,tarih);
                                stater2.executeUpdate();

                                PreparedStatement stater3=conn.prepareStatement("INSERT kullanici_gider (kullanici_no, gider_turu_gelir_turu, miktari, tarihi) VALUES (?,?,?,?)");
                                stater3.setInt(1,sahibi);
                                stater3.setString(2,"Market geliri");
                                stater3.setInt(3,toplamMiktar);
                                stater3.setDate(4,tarih);
                                stater3.executeUpdate();


                                JLabel islemBasarili=new JLabel("İşlem Başarılı.");
                                islemBasarili.setBounds(200,850,200,40);
                                add(islemBasarili);
                                islemBasarili.setVisible(true);
                                islemBasarili.setForeground(Color.GREEN);
                                islemBasarili.setFont(islemBasarili.getFont().deriveFont(Font.ITALIC,15));

                                revalidate();
                                repaint();



                                break;
                            }
                            else{
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
        revalidate();
        repaint();
    }

}
