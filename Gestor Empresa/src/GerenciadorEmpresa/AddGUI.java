package GerenciadorEmpresa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author João Varela
 */

@SuppressWarnings("serial")
public class AddGUI extends JFrame {

    private final JPanel contentPane;
    private int xMouse, yMouse;
    private final JTextField txtFieldNome;
    private final JTextField txtFieldCargo;
    private final JTextField txtFieldEquipa;
    private final JTextField txtFieldSalario;
    private final JTextField txtFieldMeses;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddGUI frame = new AddGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AddGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(280, 300);
        setUndecorated(true);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(255, 255, 255));
        contentPane.setBackground(new Color(93, 93, 93));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //-----LABEL NOME-----
        JLabel lblNome = new JLabel("Nome");
        lblNome.setHorizontalAlignment(SwingConstants.CENTER);
        lblNome.setBounds(90, 22, 100, 14);
        contentPane.add(lblNome);
        //-----TEXT FIELD NOME-----
        txtFieldNome = new JTextField();
        txtFieldNome.setBorder(null);
        txtFieldNome.setBounds(90, 38, 100, 20);
        contentPane.add(txtFieldNome);
        txtFieldNome.setColumns(10);
        
        //-----LABEL CARGO-----
        JLabel lblCargo = new JLabel("Cargo");
        lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
        lblCargo.setBounds(90, 69, 100, 14);
        contentPane.add(lblCargo);
        //-----TEXT FIELD CARGO-----
        txtFieldCargo = new JTextField();
        txtFieldCargo.setBorder(null);
        txtFieldCargo.setBounds(90, 85, 100, 20);
        contentPane.add(txtFieldCargo);
        txtFieldCargo.setColumns(10);

        //-----LABEL EQUIPA-----
        JLabel lblEquipa = new JLabel("Equipa");
        lblEquipa.setHorizontalAlignment(SwingConstants.CENTER);
        lblEquipa.setBounds(90, 116, 100, 14);
        contentPane.add(lblEquipa);
        //-----TEXT FIELD EQUIPA-----
        txtFieldEquipa = new JTextField();
        txtFieldEquipa.setBorder(null);
        txtFieldEquipa.setBounds(90, 133, 100, 20);
        contentPane.add(txtFieldEquipa);
        txtFieldEquipa.setColumns(10);
        
        //-----LABEL SALARIO-----
        JLabel lblSalario = new JLabel("Salário");
        lblSalario.setHorizontalAlignment(SwingConstants.CENTER);
        lblSalario.setBounds(90, 164, 100, 14);
        contentPane.add(lblSalario);
        //-----TEXT FIELD SALARIO-----
        txtFieldSalario = new JTextField();
        txtFieldSalario.setBorder(null);
        txtFieldSalario.setBounds(90, 180, 100, 20);
        contentPane.add(txtFieldSalario);
        txtFieldSalario.setColumns(10);

        //-----LABEL MESES DE TRABALHO-----
        JLabel lblMesesTrab = new JLabel("Meses em Trabalho");
        lblMesesTrab.setHorizontalAlignment(SwingConstants.CENTER);
        lblMesesTrab.setBounds(90, 211, 100, 14);
        contentPane.add(lblMesesTrab);
        //-----TEXT FIELD MESES-----
        txtFieldMeses = new JTextField();
        txtFieldMeses.setBorder(null);
        txtFieldMeses.setBounds(90, 227, 100, 20);
        contentPane.add(txtFieldMeses);
        txtFieldMeses.setColumns(10);
        
        //-----BOTÃO ADICIONAR-----
        JButton btnAdicionar = new JButton("Adicionar Funcionário");
        btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAdicionar.addActionListener(e -> {
            int i = 0;
            try {
                String Nome = txtFieldNome.getText();
                String Cargo = txtFieldCargo.getText();
                String Equipa = txtFieldEquipa.getText();
                double Salario = Double.parseDouble(txtFieldSalario.getText());
                int MesesTrab = Integer.parseInt(txtFieldMeses.getText());

                if(Nome.equals("")||Cargo.equals("")||Equipa.equals("")||Salario == '\0'||MesesTrab == '\0') {
                    throw new Exception();
                }else {
                    Empresa.adicionarFuncionario(Nome, Cargo, Equipa, Salario, MesesTrab);
                    i = 1;
                }
            }catch(Exception ne) {
                JOptionPane.showMessageDialog(null, "Dados Incorretos ou Não Preenchidos!", null, JOptionPane.WARNING_MESSAGE);
            }
            if(i == 1) {
                setVisible(false);
                MainGUI.main(null);
            }
        });
        btnAdicionar.setForeground(new Color(255, 255, 255));
        btnAdicionar.setBackground(Color.decode("#393e46"));
        btnAdicionar.setBorder(null);
        btnAdicionar.setBounds(10, 258, 125, 30);
        contentPane.add(btnAdicionar);
        
        //-----BOTÃO REMOVER FUNCIONARIOS
        JButton btnRemover = new JButton("Remover Funcionário");
        btnRemover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRemover.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		boolean temp = false;
        		String nome = JOptionPane.showInputDialog("Nome do Funcionário que deseja apagar:");
        		for (Funcionario funcionario : Empresa.funcionarios) {
        		    if (funcionario.getNome().equalsIgnoreCase(nome)) {
        		    	Empresa.funcionarios.remove(funcionario);
        		    	JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso!");
        		    	temp = true;
        		        break;
        		    }
        		}
        		 if(temp == false) {
     		    	JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
                }
        	}
        });
        btnRemover.setForeground(Color.WHITE);
        btnRemover.setBorder(null);
        btnRemover.setBackground(new Color(57, 62, 70));
        btnRemover.setBounds(145, 258, 125, 30);
        contentPane.add(btnRemover);
        
        //-----FECHAR O PROGRAMA-----
        JLabel lblFechar = new JLabel("X");
        lblFechar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                contentPane.removeAll();
                setVisible(false);
                MainGUI.main(null);
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
        lblFechar.setHorizontalAlignment(SwingConstants.CENTER);
        lblFechar.setBackground(Color.decode("#c1c0b9"));
        lblFechar.setForeground(Color.BLACK);
        lblFechar.setOpaque(true);
        lblFechar.setBounds(245, 0, 35, 20);
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
    }
}
