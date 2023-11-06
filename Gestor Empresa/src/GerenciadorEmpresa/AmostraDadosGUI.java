package GerenciadorEmpresa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author João Varela
 */

@SuppressWarnings("serial")
public class AmostraDadosGUI extends JFrame {

    private final JPanel contentPane;
    private int xMouse, yMouse;
    private final JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AmostraDadosGUI frame = new AmostraDadosGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AmostraDadosGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(550, 300);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(255, 255, 255));
        contentPane.setBackground(new Color(93, 93, 93));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        //-----LABEL SALARIO MAIS BAIXO-----
        JLabel lblMin = new JLabel("Salário mais Baixo");
        lblMin.setHorizontalAlignment(SwingConstants.CENTER);
        lblMin.setBounds(409, 89, 120, 14);
        contentPane.add(lblMin);
        //-----TEXT FIELD SALARIO MAIS BAIXO-----
        JTextField txtFieldSalMin = new JTextField();
        txtFieldSalMin.setEditable(false);
        txtFieldSalMin.setBounds(425, 114, 86, 20);
        contentPane.add(txtFieldSalMin);
        txtFieldSalMin.setColumns(10);

        //-----LABEL SALARIO MAIS ALTO-----
        JLabel lblMax = new JLabel("Salário mais Alto");
        lblMax.setHorizontalAlignment(SwingConstants.CENTER);
        lblMax.setBounds(419, 145, 100, 14);
        contentPane.add(lblMax);
        //-----TEXT FIELD SALARIO MAIS ALTO-----
        JTextField txtFieldSalMax = new JTextField();
        txtFieldSalMax.setEditable(false);
        txtFieldSalMax.setBounds(425, 170, 86, 20);
        contentPane.add(txtFieldSalMax);
        txtFieldSalMax.setColumns(10);

        //-----LABEL TOTAL GASTO EM SALARIOS-----
        JLabel lblGastos = new JLabel("Total em Salários");
        lblGastos.setHorizontalAlignment(SwingConstants.CENTER);
        lblGastos.setBounds(419, 201, 100, 14);
        contentPane.add(lblGastos);
        //-----TEXT FIELD TOTAL GASTO-----
        JTextField txtFieldTotalGastos = new JTextField();
        txtFieldTotalGastos.setEditable(false);
        txtFieldTotalGastos.setBounds(425, 224, 86, 20);
        contentPane.add(txtFieldTotalGastos);
        txtFieldTotalGastos.setColumns(10);

        //-----TABELA DE DADOS-----
        table = new JTable();
        table.setName("");
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Nome", "Cargo", "Equipa", "Salário", "Meses"
                }
        ));
        table.setBounds(10, 31, 379, 249);
        table.setBorder(null);
        table.setDefaultEditor(Object.class, null);
        contentPane.add(table);
        
        //-----COMBO BOX DE ESCOLHAS DE FILTRAGEM-----
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBorder(null);
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"", "Apenas Funcionários", "Apenas Gerentes", "Sem Duplicados", "Mais de 3 meses", "Salário maior que 1000"}));
        comboBox.setBounds(399, 50, 141, 22);
        comboBox.addActionListener(e -> atualizarTabela(comboBox, table));
        contentPane.add(comboBox);

      //-----FECHAR O PROGRAMA-----
        JLabel lblFechar = new JLabel("X");
        lblFechar.setOpaque(true);
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
        lblFechar.setBounds(515, 0, 35, 20);
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

//-----AO INICIAR A APLICACAO-----
        //-----SALARIO MAIS BAIXO-----
        double salarioMin = Empresa.funcionarios.stream()
                .filter(f -> f.getSalario() > 0)
                .mapToDouble(Funcionario::getSalario)
                .min()
                .orElse(0);
        txtFieldSalMin.setText(String.valueOf(salarioMin));
        //-----SALARIO MAIS ALTO-----
        double salarioMax = Empresa.funcionarios.stream()
                .filter(f -> f.getSalario() > 0)
                .mapToDouble(Funcionario::getSalario)
                .max()
                .orElse(0);
        txtFieldSalMax.setText(String.valueOf(salarioMax));
        //-----TOTAL GASTO EM SALARIOS-----
        double totalGastoSalarios = Empresa.funcionarios.stream()
                .mapToDouble(Funcionario::getSalario)
                .reduce(0, Double::sum);
        txtFieldTotalGastos.setText(String.valueOf(totalGastoSalarios));
        
        //-----MOSTRAR TODOS OS FUNCIONARIOS-----
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        Empresa.funcionarios.stream()
        		.forEach(f -> {
        			Object[] dados = {f.getNome(), f.getCargo(), f.getEquipa(), f.getSalario(), f.getTempTrab()};
        			modelo.addRow(dados);
        		});
    }
//-----ESCOLHAS DE FILTRAGEM-----
    private void atualizarTabela(JComboBox<String> comboBoxFiltro, JTable tabela) {
        List<Funcionario> listaFiltrada = new ArrayList<>();
        String filtro = comboBoxFiltro.getSelectedItem().toString();
        switch(filtro) {
        	case "": //-----MENSAGEM DE ERRO AO TENTAR----- 
        		JOptionPane.showMessageDialog(null, "Por favor selecione uma Opção!", null, JOptionPane.WARNING_MESSAGE);
        		break;
        case "Apenas Funcionários": //-----LISTA APENAS FUNCIONARIOS COM CARGO DE "FUNCIONARIO"-----
        		listaFiltrada = Empresa.funcionarios.stream()
        			.skip(3)
        			.collect(Collectors.toList());
        		break;
        case "Apenas Gerentes": //-----LISTA APENAS FUNCIONARIOS COM CARGO DE "CEO"-----
        	listaFiltrada = Empresa.funcionarios.stream()
        		.limit(3)
        		.collect(Collectors.toList());
        	break;
        case "Sem Duplicados": //-----MOSTRA FUNCIONARIOS SEM REPETIR(POR ACABAR)-----
        	listaFiltrada = Empresa.funcionarios.stream()
        		.distinct()
        		.collect(Collectors.toList());
        	break;
        case "Mais de 3 meses": //-----LISTA FUNCIONARIOS COM MAIS DE 3 MESES DE TRABALHO NA EMPRESA-----
        	listaFiltrada = Empresa.funcionarios.stream()
        		.filter(f -> f.getTempTrab() > 3)
        		.collect(Collectors.toList());
        	break;
        case "Salário maior que 1000": //-----LISTA FUNCIONARIOS COM SALARIO SUPERIOR A 1000-----
        	listaFiltrada = Empresa.funcionarios.stream()
        		.filter(f -> f.getSalario() > 1000)
        		.collect(Collectors.toList());
        	break;
        }
        //-----LISTA A "listaFiltrada" SELECIONADA ACIMA DEPENDO DA ESCOLHA-----
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        listaFiltrada.stream().forEach(f -> {
        	Object[] dados = {f.getNome(), f.getCargo(), f.getEquipa(), f.getSalario(), f.getTempTrab()};
        	modelo.addRow(dados);
        });
    }
}