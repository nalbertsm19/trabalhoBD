package entidades;


public class Agricultor 
{private int codAgricultor;
 private String areaServico;
 private String nome;
 private String dataNasc;
 private String cpf;

public Agricultor(String areaServico, String nome, String dataNasc, String cpf) 
{ this.areaServico = areaServico;
  this.nome=nome;
  this.dataNasc= dataNasc;
  this.cpf= cpf;
	
}
 
public int getCodAgricultor() 
{
	return codAgricultor;
}

public void setCodAgricultor(int codAgricultor) 
{
	this.codAgricultor = codAgricultor;
}

public String getAreaServico() 
{
	return areaServico;
}

public void setAreaServico(String areaServico) 
{
	this.areaServico = areaServico;
}

public String getNome() 
{
	return nome;
}

public void setNome(String nome) 
{
	this.nome = nome;
}

public String getDataNasc() 
{
	return dataNasc;
}

public void setDataNasc(String dataNasc) 
{
	this.dataNasc = dataNasc;
}

public String getCpf() 
{
	return cpf;
}

public void setCpf(String cpf) 
{
	this.cpf = cpf;
}
 

 

}