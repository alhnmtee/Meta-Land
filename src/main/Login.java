package main;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// kullanici_tipi = 1    admin
// kullanici_tipi = 2    oyuncu

public class Login {

    public static int yükseklik = 1080;
    public static int genislik = 1920;
    private JFrame loginFrame;
    private JPanel panel;
    protected JButton loginButton;
    protected JTextField jTextUsername;
    protected JPasswordField jPassword;
    protected JLabel lUsername, lPassword;
    DatabaseConnect dtbc = new DatabaseConnect();
    Connection conn = dtbc.getConnection();
    ImageIcon kayıtOl = new ImageIcon(this.getClass().getResource("/resim/kayıtol.png"));
    int kullaniciNo;
    JFrame oyunEkran;
    kullanici_panel kullanici_panel;

    public Login() {
        loginFrame = new JFrame("Login Ekranı");
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // loginFrame.setSize(yükseklik, genislik);
        loginFrame.setLayout(new BorderLayout());
        loginFrame.setBounds(0,0,genislik,yükseklik);



        JLabel backgroundLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/resim/loginekran.png"));
        backgroundLabel.setIcon(imageIcon);
        backgroundLabel.setBounds(0, 0, loginFrame.getWidth(), loginFrame.getHeight());


        loginFrame.add(backgroundLabel);


        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(1920, 1080);
        System.out.println(this.getClass().getResource("/resim"));

        backgroundLabel.add(panel);
        Icon icongif = new ImageIcon(this.getClass().getResource("/resim/gificon.gif"));
       // Icon icongif = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("C:\\Users\\ASUS\\Desktop\\Klasörler\\java\\test2\\src\\resim\\gificon.gif")));
        JLabel resimLabel = new JLabel(icongif);
        //resimLabel.setBounds(650, 40, 48, 48);

        Font font = new Font("Sylfaen", Font.PLAIN, 19);


        lUsername = new JLabel("Kullanıcı Adı:");
        lUsername.setBounds(600, 150, 130, 30);
        lUsername.setForeground(Color.decode("#ffaec8"));
        lUsername.setFont(font);

        lPassword = new JLabel("Şifre: ");
        lPassword.setBounds(600, 200, 130, 30);
        lPassword.setForeground(Color.decode("#ffaec8"));
        lPassword.setFont(font);

        jPassword = new JPasswordField();
        jPassword.setBounds(730, 200, 90, 30);

        jTextUsername = new JTextField();
        jTextUsername.setBounds(730, 150, 90, 30);





        Icon icon =new ImageIcon(this.getClass().getResource("/resim/login2.png"));
        loginButton = new JButton(icon);
        loginButton.setBounds(850, 165, 64, 64);
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        // loginButton.setFocusPainted(false);
        loginButton.addActionListener(e -> {
            String username = jTextUsername.getText();
            String password = new String(jPassword.getPassword());
            if (loginCheck(username, password) == 1 ) {
                JOptionPane.showMessageDialog(loginFrame, "Admin olarak Giriş Başarılı");
                JFrame yeni_ekran = new JFrame("Admin Panel");
                yeni_ekran.setSize(1920,1080);
                yeni_ekran.setVisible(true);
                yeni_ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                admin_panel x = new admin_panel();

                JLabel backgroundLabel2 = new JLabel();
                ImageIcon imageIcon2 = new ImageIcon(this.getClass().getResource("/resim/admin1.jpg"));
                backgroundLabel2.setIcon(imageIcon2);
                backgroundLabel2.setBounds(0, 0, yeni_ekran.getWidth(),yeni_ekran.getHeight());
                yeni_ekran.add(backgroundLabel2);
                backgroundLabel2.add(x);
                x.setOpaque(false);


                loginFrame.setVisible(false);

            }
            else if(loginCheck(username, password) != -1){


                try{
                    Statement stat11=conn.createStatement();
                    ResultSet res11= stat11.executeQuery("select kullanici_yemek_miktari,kullanici_esya_miktari from Kullanici where kullanici_no="+loginCheck(username,password)+" ");
                    res11.next();
                    if(res11.getInt(1)>0 && res11.getInt(2)>0){

                        JOptionPane.showMessageDialog(loginFrame, "Kullanıcı olarak Giriş Başarılı");
                        JFrame kullanici_ekran = new JFrame("Kullanici Panel");
                        kullanici_ekran.setSize(1300+15,1080);
                        kullanici_ekran.setVisible(true);
                        kullanici_ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        kullanici_panel x2 =new kullanici_panel(loginCheck(username,password));
                        // kullanici_ekran.add(x2);

                        JLabel backgroundLabel2 = new JLabel();
                        ImageIcon imageIcon2 = new ImageIcon(this.getClass().getResource("/resim/kullanicipanel.jpg"));
                        backgroundLabel2.setIcon(imageIcon2);
                        backgroundLabel2.setBounds(0, 0, kullanici_ekran.getWidth(),kullanici_ekran.getHeight());
                        kullanici_ekran.add(backgroundLabel2);
                        backgroundLabel2.add(x2);
                        x2.setOpaque(false);
                        oyunEkran=kullanici_ekran;
                        kullanici_panel=x2;
                        loginFrame.setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(loginFrame,"Kullanıcı Oyunu Kaybetti.Yeniden üye olun.");
                    }


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
            else {
                JOptionPane.showMessageDialog(loginFrame, "Kullanıcı adı veya şifre yanlış");
            }

        });
        JButton kayit;
        kayit = new JButton(kayıtOl);
        kayit.setBounds(740, 300, 100, 100);
        kayit.setOpaque(false);
        kayit.setContentAreaFilled(false);
        kayit.setBorderPainted(false);
        kayit.addActionListener(e2-> {
            JFrame kayitEkran=new JFrame();
            kayitEkran.setBounds(0,0,1920,1080);
            kayitEkran.setVisible(true);
            kayitEkran.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            kayitEkran.setLayout(null);

            signup x3=new signup();

            JLabel backgroundLabel3 = new JLabel();
            ImageIcon imageIcon3 = new ImageIcon(this.getClass().getResource("/resim/kayit.jpg"));
            backgroundLabel3.setIcon(imageIcon3);
            backgroundLabel3.setBounds(0, 0, kayitEkran.getWidth(),kayitEkran.getHeight());
           kayitEkran.add(backgroundLabel3);
            backgroundLabel3.add(x3);
            x3.setOpaque(false);


        });

        //loginFrame.add(panel);
        panel.add(lUsername);
        panel.add(lPassword);
        panel.add(jPassword);
        panel.add(jTextUsername);
        panel.add(loginButton);
        panel.add(resimLabel);
        panel.add(kayit);
        loginFrame.setVisible(true);
        panel.setVisible(true);
        panel.setOpaque(false);
        //panel.setBackground(null);


    }

    public int loginCheck(String username, String password) {
        DatabaseConnect dtbc = new DatabaseConnect();
        Connection conn = dtbc.getConnection();
        int kullanici_tipi = -1;
        if (conn != null) {
            try {

                Statement myStat = conn.createStatement();
                ResultSet res = myStat.executeQuery("select kullanici_no from Kullanici where kullanici_giris_adi='" + username + "' AND kullanici_sifre='" + password + "';");
                if (res.next()) {
                    //System.out.println(res.getString("k_tipi"));
                    kullanici_tipi = res.getInt("kullanici_no");

                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                dtbc.closeConnection();
            }
        }
        this.kullaniciNo=kullanici_tipi;
        return kullanici_tipi;
    }
}