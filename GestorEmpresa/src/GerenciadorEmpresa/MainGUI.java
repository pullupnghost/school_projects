package GerenciadorEmpresa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * @author João Varela
 */

@SuppressWarnings("serial")
public class MainGUI extends JFrame{

	private JPanel contentPane;
    private int xMouse, yMouse;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainGUI frame = new MainGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MainGUI() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setUndecorated(true);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(255, 255, 255));
        contentPane.setBackground(new Color(93, 93, 93));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //-----LABEL NUMERO FUNCIONARIOS-----
        JLabel lblNFunc = new JLabel("Número de Funcionários");
        lblNFunc.setForeground(new Color(255, 255, 255));
        lblNFunc.setBounds(10, 25, 150, 14);
        contentPane.add(lblNFunc);
        //-----TEXT FIELD NUMERO FUNCIONARIOS-----
        JTextField txtFieldFuncionarios = new JTextField();
        txtFieldFuncionarios.setBorder(null);
        txtFieldFuncionarios.setForeground(new Color(255, 255, 255));
        txtFieldFuncionarios.setBackground(Color.decode("#393e46"));
        txtFieldFuncionarios.setEditable(false);
        txtFieldFuncionarios.setBounds(10, 42, 86, 20);
        contentPane.add(txtFieldFuncionarios);
        txtFieldFuncionarios.setColumns(10);

        //-----LABEL MEDIA DE SALARIOS-----
        JLabel lblSalarioMedia = new JLabel("Média Salarial");
        lblSalarioMedia.setForeground(new Color(255, 255, 255));
        lblSalarioMedia.setBounds(10, 76, 130, 14);
        contentPane.add(lblSalarioMedia);
        //-----TEXT FIELD NUMERO FUNCIONARIOS-----
        JTextField txtFieldMediaSalario = new JTextField();
        txtFieldMediaSalario.setBorder(null);
        txtFieldMediaSalario.setBackground(Color.decode("#393e46"));
        txtFieldMediaSalario.setForeground(new Color(255, 255, 255));
        txtFieldMediaSalario.setEditable(false);
        txtFieldMediaSalario.setBounds(10, 94, 86, 20);
        contentPane.add(txtFieldMediaSalario);
        txtFieldMediaSalario.setColumns(10);

        //-----LABEL MEDIA DE MESES-----
        JLabel lblMesesMedia = new JLabel("Média Meses na Empresa");
        lblMesesMedia.setForeground(new Color(255, 255, 255));
        lblMesesMedia.setBounds(10, 128, 150, 14);
        contentPane.add(lblMesesMedia);
        //-----TEXT FIELD NUMERO FUNCIONARIOS-----
        JTextField txtFieldMediaMeses = new JTextField();
        txtFieldMediaMeses.setBorder(null);
        txtFieldMediaMeses.setForeground(new Color(255, 255, 255));
        txtFieldMediaMeses.setBackground(Color.decode("#393e46"));
        txtFieldMediaMeses.setEditable(false);
        txtFieldMediaMeses.setBounds(10, 146, 86, 20);
        contentPane.add(txtFieldMediaMeses);
        txtFieldMediaMeses.setColumns(10);

        //-----FILTRAGEM DE DADOS-----
        JButton btnAmostraDados = new JButton("Amostra de Dados");
        btnAmostraDados.addActionListener(e -> {
            setVisible(false);
            AmostraDadosGUI.main(null);
        });
        btnAmostraDados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAmostraDados.setForeground(new Color(255, 255, 255));
        btnAmostraDados.setBorder(null);
        btnAmostraDados.setBounds(184, 41, 140, 23);
        contentPane.add(btnAmostraDados);
        btnAmostraDados.setBackground(Color.decode("#393e46"));

        //-----BOTÃO ADICIONAR FUNCIONARIOS-----
        JButton btnAdicionar = new JButton("Adicionar Funcionários");
        btnAdicionar.addActionListener(e -> {
            setVisible(false);
            AddGUI.main(null);
        });
        btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAdicionar.setForeground(new Color(255, 255, 255));
        btnAdicionar.setBorder(null);
        btnAdicionar.setBounds(184, 124, 140, 23);
        contentPane.add(btnAdicionar);
        btnAdicionar.setBackground(Color.decode("#393e46"));

        //-----FECHAR O PROGRAMA-----
        JLabel lblFechar = new JLabel("X");
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
                lblFechar.setBackground(Color.decode("#c1c0b9"));
                lblFechar.setForeground(Color.BLACK);
            }
        });
        lblFechar.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblFechar.setOpaque(true);
        lblFechar.setBackground(Color.decode("#c1c0b9"));
        lblFechar.setForeground(Color.BLACK);
        lblFechar.setHorizontalAlignment(SwingConstants.CENTER);
        lblFechar.setBounds(315, 0, 35, 20);
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

        //-----ATULIZAR DADOS AO ENTRAR-----
        txtFieldFuncionarios.setText(String.valueOf(Empresa.getNumeroFuncionarios()));
        txtFieldMediaSalario.setText(String.format("%.2f", Empresa.getMediaSalarios()));
        txtFieldMediaMeses.setText(String.format("%.2f", Empresa.getMediaMesesNaEmpresa()));
    }
}

