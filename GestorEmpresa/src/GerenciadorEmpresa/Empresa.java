package GerenciadorEmpresa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author João Varela
 */

public class Empresa {
    static List<Funcionario> funcionarios = new ArrayList<>();

    //-----ADICIONAR FUNCIONARIOS-----
    public static void adicionarFuncionario(String nome, String cargo, String equipe, double salario, int TempTrab) {
        Funcionario funcionario = new Funcionario(nome, cargo, equipe, salario, TempTrab);
        funcionarios.add(funcionario);
    }

    //-----NUMERO TOTAL DE FUNCIONARIOS-----
    public static long getNumeroFuncionarios() {
    	return funcionarios.stream()
    			.count();
    }
    
    //-----MEDIA DE SALARIOS-----
    public static double getMediaSalarios() {
        return funcionarios.stream()
                .mapToDouble(Funcionario::getSalario)
                .average()
                .orElse(0);
    }
    
    //-----MEDIA DE MESES NA EMPRESA-----
    public static double getMediaMesesNaEmpresa() {
        return funcionarios.stream()
                .mapToDouble(Funcionario::getTempTrab)
                .average()
                .orElse(0);
    }
}