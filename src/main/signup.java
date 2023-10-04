package main;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class signup extends JPanel {

    DatabaseConnect dtbc = new DatabaseConnect();
    Connection conn = dtbc.getConnection();
    Icon icon =new ImageIcon(this.getClass().getResource("/resim/ok.png"));
JButton ok;
    public signup(){
        setSize(1920, 1080);
        setLayout(null);

        Font font = new Font("Sylfaen", Font.BOLD, 23);


       JLabel lUsername = new JLabel("Kullanıcı Adı:");
        lUsername.setBounds(900, 250, 150, 40);
        lUsername.setForeground(Color.decode("#ffaec8"));
        lUsername.setFont(font);
        lUsername.setVisible(true);

        JTextField   jTextUsername = new JTextField();
        jTextUsername.setBounds(900, 300, 90, 40);

        JLabel lPassword = new JLabel("Şifre: ");
        lPassword.setBounds(900, 350, 150, 40);
        lPassword.setForeground(Color.decode("#ffaec8"));
        lPassword.setFont(font);

        JPasswordField jPassword = new JPasswordField();
        jPassword.setBounds(900, 400, 90, 30);


        JLabel lisim = new JLabel("İsim:");
        lisim.setBounds(900, 450, 130, 40);
        lisim.setForeground(Color.decode("#ffaec8"));
        lisim.setFont(font);
        lisim.setVisible(true);

        JTextField isimText=new JTextField();
        isimText.setBounds(900,500,90,40);

        JLabel lsoyisim = new JLabel("Soyisim: ");
        lsoyisim.setBounds(900, 550, 130, 40);
        lsoyisim.setForeground(Color.decode("#ffaec8"));
        lsoyisim.setFont(font);

        JTextField soyisimText=new JTextField();
        soyisimText.setBounds(900,600,90,40);

        add(lisim);
        add(lsoyisim);
        add(jTextUsername);
        add(jPassword);
        add(lUsername);
        add(lPassword);
        add(isimText);
        add(soyisimText);
        ok=new JButton(icon);
        add(ok);
        ok.setBounds(920,650,50,50);



        JLabel yazi = new JLabel("Kullanıcı Adı boş bırakılamaz");
        yazi.setVisible(false);
        yazi.setBounds(810, 800, 500, 60);
        yazi.setFont(yazi.getFont().deriveFont(Font.BOLD, 25));
        add(yazi);
        yazi.setForeground(Color.white);

        JLabel yazi2 = new JLabel("Kullanıcı Adı daha önce alınmış");
        yazi2.setVisible(false);
        yazi2.setBounds(800, 800, 500, 60);
        yazi2.setFont(yazi2.getFont().deriveFont(Font.BOLD, 25));
        add(yazi2);
        yazi2.setForeground(Color.white);

        JLabel yazi3 = new JLabel("Şifre boş bırakılamaz");
        yazi3.setVisible(false);
        yazi3.setBounds(820, 800, 500, 60);
        yazi3.setFont(yazi3.getFont().deriveFont(Font.BOLD, 25));
        add(yazi3);
        yazi3.setForeground(Color.white);

        JLabel yazi4 = new JLabel("Başarılı Şekilde Kayıt Oldunuz");
        yazi4.setVisible(false);
        yazi4.setBounds(810, 800, 500, 60);
        yazi4.setFont(yazi4.getFont().deriveFont(Font.BOLD, 25));
        add(yazi4);
        yazi4.setForeground(Color.white);

        ok.setOpaque(false);
        ok.setContentAreaFilled(false);
        ok.setBorderPainted(false);

        ok.addActionListener(e14 -> {
            String username=jTextUsername.getText();
            String password = new String(jPassword.getPassword());
            String isim=isimText.getText();
            String soyisim=soyisimText.getText();

            try {
                Statement stat = conn.createStatement();
                ResultSet res = stat.executeQuery("SELECT * FROM Kullanici WHERE kullanici_giris_adi = '" + username + "';");
                if (username.isEmpty()) {
                    System.out.println("deneme");
                    yazi.setVisible(true);
                    yazi2.setVisible(false);
                    yazi3.setVisible(false);
                }
                else if(res.next()){
                    yazi.setVisible(false);
                    yazi2.setVisible(true);
                    yazi3.setVisible(false);
                }
                else if (password.isEmpty()) {
                    yazi.setVisible(false);
                    yazi2.setVisible(false);
                    yazi3.setVisible(true);
                }
                else {
                    yazi.setVisible(false);
                    yazi2.setVisible(false);
                    yazi3.setVisible(false);
                    yazi4.setVisible(true);
                    Statement stat2 = conn.createStatement();
                    ResultSet res2 = stat2.executeQuery("select baslangic_esya_miktari,baslangic_para_miktari,baslangic_yemek_miktarı from Baslangic");
                    res2.next();
                    int esya = res2.getInt(1);
                    int para = res2.getInt(2);
                    int yemek = res2.getInt(3);

                    PreparedStatement stater3 = conn.prepareStatement("INSERT Kullanici (kullanici_adi, kullanici_soyadi, kullanici_giris_adi, kullanici_sifre, kullanici_yemek_miktari, kullanici_para_miktari, kullanici_esya_miktari) VALUES (?,?,?,?,?,?,?)");
                    stater3.setString(1, isim);
                    stater3.setString(2, soyisim);
                    stater3.setString(3, username);
                    stater3.setString(4, password);
                    stater3.setInt(5, yemek);
                    stater3.setInt(6, para);
                    stater3.setInt(7, esya);
                    stater3.executeUpdate();
                }

            } catch (SQLException e13) {
                throw new RuntimeException(e13);
            }

        });



    }
}
