/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.ConectionFactory;
import dominio.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaod
 */
public class EstadoDAO {
    private Connection conecta;  
    
    public EstadoDAO() {
        this.conecta = new ConectionFactory().conecta();
    }
    
    //o valor de gastos totais do municipio NÃO é inicializado com valor do usuário
    // Valor de gastos total é a soma dos valores dos municipios
    public void cadastrarEstado (Estado obj){
       
       try {
           String cmdsql = "insert into estado(nome, sigla, distritofederal, orcamentoTotal ,gastosTotais) values (?,?,?,?,?)";
           PreparedStatement stmt = conecta.prepareStatement(cmdsql);
           stmt.setString(1, obj.getNome());
           stmt.setString(2, obj.getSigla());
           stmt.setInt(3, obj.getDistritoFederal());
           stmt.setFloat(4, obj.getOrcamentoTotal());
           stmt.setFloat(5, obj.getGastosTotais());           
           stmt.execute();           
           stmt.close();
           
       } catch (SQLException erro) {
           throw new RuntimeException(erro);  
       }
    }
    
    public void excluirEstado(String sigla) {

        try {
            String sql = "delete from estado where sigla=?";
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, sigla);
            System.out.println("sigla");
            System.out.println("Deletando...");
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Estado>listarEstados(){
           try {
               List<Estado> lista =new ArrayList<Estado>();
               String SQL="SELECT  *FROM estado ORDER BY nome ASC";
               PreparedStatement stmt=conecta.prepareStatement(SQL);
               ResultSet rs=stmt.executeQuery();
             while(rs.next()){
                 Estado e=new Estado();                   
                   e.setNome(rs.getString("nome"));
                   e.setSigla(rs.getString("sigla"));
                   e.setDistritoFederal(rs.getInt("distritofederal")); 
                   e.setOrcamentoTotal(rs.getFloat("orcamentoTotal"));
                   e.setGastosTotais(rs.getFloat("gastosTotais"));
                   lista.add(e);
             }    
               return lista;
               
           } catch (SQLException e) {
               e.printStackTrace();
           }
         return null;
 
       }
    
    public List<Estado>listarEstadosDevedores(){
           try {
               List<Estado> lista =new ArrayList<Estado>();
               String SQL="select * from estado where (orcamentoTotal < gastosTotais )";
               PreparedStatement stmt=conecta.prepareStatement(SQL);
               ResultSet rs=stmt.executeQuery();
             while(rs.next()){
                 Estado e=new Estado();                   
                   e.setNome(rs.getString("nome"));
                   e.setSigla(rs.getString("sigla"));
                   e.setDistritoFederal(rs.getInt("distritofederal")); 
                   e.setOrcamentoTotal(rs.getFloat("orcamentoTotal"));
                   e.setGastosTotais(rs.getFloat("gastosTotais"));
                   lista.add(e);
             }    
               return lista;
               
           } catch (SQLException e) {
               e.printStackTrace();
           }
         return null;
 
       }
    
}
