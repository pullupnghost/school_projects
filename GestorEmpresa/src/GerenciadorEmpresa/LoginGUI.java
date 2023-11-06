package GerenciadorEmpresa;

import java.awt.EventQueue;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

/**
 * @author João Varela
 */

@SuppressWarnings("serial")
public class LoginGUI extends JFrame {

    private JPanel contentPane;
    private JPasswordField passwordField;
    private JTextField txtFieldNome;
    private static Clip clip;
    private int xMouse, yMouse;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginGUI frame = new LoginGUI();
                File file = new File("MusicaFundo.wav");
                AudioInputStream audioInputStream=AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 460);
        setLocationRelativeTo(null);
        setUndecorated(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        //------TITULO-----
        JLabel lblTitulo = new JLabel("Gestor De Empresa");
        lblTitulo.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(93, 93, 93)));
        lblTitulo.setOpaque(true);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 30));
        lblTitulo.setBounds(525, 11, 300, 50);
        contentPane.add(lblTitulo);

        //-----BACKGROUND IMAGE-----
        JLabel lblBgImg = new JLabel("");
        lblBgImg.setBackground(new Color(93, 93, 93));
        lblBgImg.setHorizontalAlignment(SwingConstants.CENTER);
        lblBgImg.setIcon(new ImageIcon(LoginGUI.class.getResource("/Images/bg.jpg")));
        lblBgImg.setBounds(0, 0, 440, 412);
        contentPane.add(lblBgImg);
        
        //-----UTILIZADOR-----
        JLabel lblNome = new JLabel("Utilizador");
        lblNome.setIcon(new ImageIcon(LoginGUI.class.getResource("/Images/user-solid-24.png")));
        lblNome.setBounds(550, 102, 100, 25);
        contentPane.add(lblNome);
        //-----TEXT FIELD DO NOME-----
        txtFieldNome = new JTextField();
        txtFieldNome.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(93, 93, 93)));
        txtFieldNome.setOpaque(false);
        txtFieldNome.setBounds(550, 127, 225, 25);
        contentPane.add(txtFieldNome);
        txtFieldNome.setColumns(10);

        //-----PASSWORD-----
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setIcon(new ImageIcon(LoginGUI.class.getResource("/Images/lock-solid-24.png")));
        lblPassword.setBounds(550, 182, 100, 25);
        contentPane.add(lblPassword);
        //-----PASSWORD FIELD-----
        passwordField = new JPasswordField();
        passwordField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(93, 93, 93)));
        passwordField.setOpaque(false);
        passwordField.setBounds(550, 207, 225, 25);
        contentPane.add(passwordField);
        
        //-----MUSICA A TOCAR(label)-----
        JLabel lblMusica = new JLabel("Music is playing");
        lblMusica.setBackground(new Color(93, 93, 93));
        lblMusica.setIcon(new ImageIcon(LoginGUI.class.getResource("/Images/music.png")));
        lblMusica.setBounds(450, 382, 150, 25);
        contentPane.add(lblMusica);
        
        //-----BOTÃO DE PLAY-----
        JButton btnPlay = new JButton("");
        btnPlay.addActionListener(e -> {
            clip.start();
            lblMusica.setText("Music is playing");
        });
        btnPlay.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        btnPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPlay.setIcon(new ImageIcon(LoginGUI.class.getResource("/Images/play-regular-24.png")));
        btnPlay.setBounds(654, 377, 35, 35);
        contentPane.add(btnPlay);
        
        //------BOTÃO DE PAUSA-----
        JButton btnPause = new JButton("");
        btnPause.addActionListener(e -> {
            clip.stop();
            lblMusica.setText("Music is paused");
        });
        btnPause.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        btnPause.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPause.setIcon(new ImageIcon(LoginGUI.class.getResource("/Images/pause-regular-24.png")));
        btnPause.setBounds(619, 377, 35, 35);
        contentPane.add(btnPause);
        
        //-----BOTÃO DE STOP-----
        JButton btnStop = new JButton("");
        btnStop.addActionListener(e -> {
            clip.stop();
            clip.setFramePosition(0);
            lblMusica.setText("Music is stopped");
        });
        btnStop.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        btnStop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnStop.setIcon(new ImageIcon(LoginGUI.class.getResource("/Images/stop-regular-24.png")));
        btnStop.setBounds(688, 377, 35, 35);
        contentPane.add(btnStop);
        
        //-----BOTÃO ENTRAR-----
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setBackground(new Color(93, 93, 93));
        btnEntrar.setBorder(null);
        btnEntrar.addActionListener(e -> {
            String user = txtFieldNome.getText();
            String pass = String.valueOf(passwordField.getPassword());

            if(user.equals("admin") && pass.equals("1234")){
                JOptionPane.showMessageDialog(null, "Login com Sucesso!", null, JOptionPane.DEFAULT_OPTION);
                clip.stop();
                setVisible(false);
                MainGUI.main(null);
            }else if(user.equals("") || pass.equals("")){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos", null, JOptionPane.WARNING_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "Dados Incorretos!", null, JOptionPane.ERROR_MESSAGE);
            }
        });
        btnEntrar.setBounds(619, 291, 100, 25);
        contentPane.add(btnEntrar);
        
        //-----CANVAS-----
        Canvas canvas = new Canvas();
        canvas.setBackground(new Color(93,93,93));
        canvas.setBounds(0, 412, 900, 49);
        contentPane.add(canvas);
        
        //-----FECHAR O PROGRAMA-----
        JLabel lblFechar = new JLabel("X");
        lblFechar.setHorizontalAlignment(SwingConstants.CENTER);
        lblFechar.setHorizontalTextPosition(SwingConstants.CENTER);
        lblFechar.setOpaque(true);
        lblFechar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                lblFechar.setBackground(Color.RED);
                lblFechar.setForeground(Color.WHITE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                lblFechar.setBackground(new Color(240, 240, 240));
                lblFechar.setForeground(Color.BLACK);
            }
        });
        lblFechar.setBounds(860, 0, 40, 25);
        lblFechar.setForeground(Color.BLACK);
        contentPane.add(lblFechar);
        
        //-----MOVER PAINEL-----
        contentPane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen(), y = e.getYOnScreen();
                setLocation(x - xMouse, y - yMouse);
            }
        });
        contentPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
        
        //-----FUNCIONARIOS PREDEFENIDOS-----
   	 	Empresa.adicionarFuncionario("Joao", "CEO", "Programacao", 1500.00, 10);
   	 	Empresa.adicionarFuncionario("Matilde", "CEO", "Marketing", 1200.00, 6);
   	 	Empresa.adicionarFuncionario("Putxi", "CEO", "Consultor", 1400.45, 2);
   	 	Empresa.adicionarFuncionario("Nuno", "Funcionario", "Contabilista", 900, 8);
    }
}

