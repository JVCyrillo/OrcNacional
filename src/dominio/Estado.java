/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author joaod
 */
public class Estado {    
    private String nome;
    private String sigla;
    private int distritoFederal; 
    private float orcamentoTotal;
    private float gastosTotais;

    public float getOrcamentoTotal() {
        return orcamentoTotal;
    }

    public void setOrcamentoTotal(float orcamentoTotal) {
        this.orcamentoTotal = orcamentoTotal;
    }

    public Estado( String nome, String sigla,  int distritoFederal, float orcamentoTotal, float gastosTotais) {        
        this.nome = nome;
        this.sigla = sigla;
        this.distritoFederal = distritoFederal;        
        this.orcamentoTotal = orcamentoTotal; 
        this.gastosTotais = gastosTotais; 
    }    

    

    public Estado() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getDistritoFederal() {
        return distritoFederal;
    }

    public void setDistritoFederal(int distritoFederal) {
        this.distritoFederal = distritoFederal;
    }    

    public float getGastosTotais() {
        return gastosTotais;
    }

    public void setGastosTotais(float gastosTotais) {
        this.gastosTotais = gastosTotais;
    }
    
    
    
}
