package entidades;

public class AreaPlantada 
{private int codArea;
 private double tamArea;
 private String tipoArea;
 private String descricaoQualidade;
 private String espSemente;
 private int codAgricultor;

 

public AreaPlantada(int codArea, double tamArea, String tipoArea, String descricaoQualidade, String espSemente, int codAgricultor) {
    this.codArea = codArea;
    this.tamArea = tamArea;
    this.tipoArea = tipoArea;
    this.descricaoQualidade = descricaoQualidade;
    this.espSemente = espSemente;
    this.codAgricultor = codAgricultor;
}

public AreaPlantada( double tamArea, String tipoArea, String descricaoQualidade, String espSemente, int codAgricultor) {


    this.tamArea = tamArea;
    this.tipoArea = tipoArea;
    this.descricaoQualidade = descricaoQualidade;
    this.espSemente = espSemente;
    this.codAgricultor = codAgricultor;
}
 
 
public int getCodArea() 
{
	return codArea;
}
public void setCodArea(int codArea) 
{
	this.codArea = codArea;
}
public double getTamArea() 
{
	return tamArea;
}
public void setTamArea(double tamArea) 
{
	this.tamArea = tamArea;
}
public String getTipoArea() 
{
	return tipoArea;
}
public void setTipoArea(String tipoArea) 
{
	this.tipoArea = tipoArea;
}
public String getDescricaoQualidade() 
{
	return descricaoQualidade;
}
public void setDescricaoQualidade(String descricaoQualidade) 
{
	this.descricaoQualidade = descricaoQualidade;
}
public String getEspSemente() 
{
	return espSemente;
}
public void setEspSemente(String espSemente) 
{
	this.espSemente = espSemente;
}
public int getCodAgricultor() 
{
	return codAgricultor;
}
public void setCodAgricultor(int codAgricultor) 
{
	this.codAgricultor = codAgricultor;
}
interface TratamentoSolo 
{
    String getTipo();
    int getNivel();
}

}


