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
public class Municipio {

    private String nome;
    private int populacao;
    private String clima;
    private float gastos;
    private String fk_id_estado;

    public Municipio(String nome, int populacao, String clima, float gastos, String fk_id_estado) {

        this.nome = nome;
        this.populacao = populacao;
        this.clima = clima;
        this.gastos = gastos;
        this.fk_id_estado = fk_id_estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getFk_id_estado() {
        return fk_id_estado;
    }

    public void setFk_id_estado(String fk_id_estado) {
        this.fk_id_estado = fk_id_estado;
    }

   

    public float getGastos() {
        return gastos;
    }

    public void setGastos(float gastos) {
        this.gastos = gastos;
    }

    public Municipio() {
    }

}
