package GerenciadorEmpresa;

/**
 * @author João Varela
 */

public class Funcionario {

	private String Nome;
	private String Cargo;
	private String Equipa;
	private double Salario;
	private int TempTrab;
	
	//-----CONSTRUTOR-----
	public Funcionario(String Nome, String Cargo, String Equipa, double Salario, int TempTrab){
		this.Nome = Nome;
		this.Cargo = Cargo;
		this.Equipa = Equipa;
		this.Salario = Salario;
		this.TempTrab = TempTrab;
	}

//-----GETTERS E SETTERS-----
	
	//-----NOME-----
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}

	//-----CARGO-----
	public String getCargo() {
		return Cargo;
	}
	public void setCargo(String cargo) {
		Cargo = cargo;
	}

	//-----EQUIPA-----
	public String getEquipa() {
		return Equipa;
	}
	public void setEquipa(String equipa) {
		Equipa = equipa;
	}

	//-----SALARIO-----
	public double getSalario() {
		return Salario;
	}
	public void setSalario(double salario) {
		Salario = salario;
	}
	
	//-----TEMPO DE TRABALHO-----
	public int getTempTrab() {
		return TempTrab;
	}
	public void setTempTrab(int tempTrab) {
		TempTrab = tempTrab;
	}	
}
