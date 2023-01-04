/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.ConectionFactory;
import dominio.Estado;
import dominio.Municipio;
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
public class MunicipioDAO {

    private Connection conecta;

    public MunicipioDAO() {
        this.conecta = new ConectionFactory().conecta();
    }

    public void cadastrarMunicipio(Municipio obj) throws InterruptedException {

        try {
            String sqlVerific = "select *from estado where sigla=?";
            PreparedStatement stmtVer = conecta.prepareStatement(sqlVerific);
            stmtVer.setString(1, obj.getFk_id_estado());
            ResultSet rss = stmtVer.executeQuery();
            float orccamentoTotal = 0;
            float gastoTotalComparativo = 0;
            float gastoMunicipio = obj.getGastos();
            boolean controle = true;
            if (rss != null && rss.next()) {
                gastoTotalComparativo = (float) rss.getFloat("gastosTotais");
                orccamentoTotal = (float) rss.getFloat("orcamentototal");
                if (gastoMunicipio > (orccamentoTotal - gastoTotalComparativo)) {
                    controle = false;
                }
                System.out.println(gastoTotalComparativo);
            }

            if ((gastoTotalComparativo <= orccamentoTotal) && controle) {
                System.out.println("Entrou 1");
                String cmdsql = "insert into municipio(nome, populacao, clima ,gastos, fk_id_estado ) values (?,?,?,?,?)";
                PreparedStatement stmt = conecta.prepareStatement(cmdsql);
                stmt.setString(1, obj.getNome());
                stmt.setInt(2, obj.getPopulacao());
                stmt.setString(3, obj.getClima());
                stmt.setFloat(4, obj.getGastos());
                float gt = (float) obj.getGastos();
                stmt.setString(5, obj.getFk_id_estado());
                String sigla = obj.getFk_id_estado();
                stmt.execute();
                stmt.close();
                System.out.println("Erro 1");

                String sql = "select *from estado where sigla=?";
                PreparedStatement stmt2 = conecta.prepareStatement(sql);
                stmt2.setString(1, sigla);
                System.out.println("Erro 2");
                ResultSet rs = stmt2.executeQuery();
                System.out.println(rs);
                System.out.println("Erro 3");
                float v = 0;
                float valorAtualizadoEstado;
                if (rs != null && rs.next()) {
                    v = (float) rs.getFloat("gastosTotais");

                }
                System.out.println("Erro 4");
                System.out.println("Gasto total do estado antes " + v);
                //Alteração do valor total de gastos do estado, somando com o valor do municipio + valor atual
                valorAtualizadoEstado = v + gt;
                System.out.println(valorAtualizadoEstado);
                stmt2.close();

                String cmd2sql = "update estado set gastosTotais=? where sigla=?";
                PreparedStatement stmt3 = conecta.prepareStatement(cmd2sql);
                stmt3.setFloat(1, valorAtualizadoEstado);
                stmt3.setString(2, sigla);
                stmt3.execute();
                stmt3.close();

                new Thread().sleep(10000);
                String sql4 = "select *from estado where sigla=?";
                PreparedStatement stmt4 = conecta.prepareStatement(sql4);
                stmt4.setString(1, sigla);
                ResultSet rst = stmt4.executeQuery();
                System.out.println("erro 8");
                if (rst != null && rst.next()) {
                    System.out.println("erro 9");
                    float vt = rst.getFloat("gastosTotais");
                    System.out.println("Gasto total do estado depois " + v);

                }
            } else {
                System.out.println("ESTADO EXCEDEU O GASTO");
            }
            stmtVer.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public List<Municipio> listarMunicipiosSigla(String sigla) {
        try {
            List<Municipio> lista = new ArrayList<Municipio>();
            String SQL = "select * from municipio where fk_id_estado = ?";
            PreparedStatement stmt = conecta.prepareStatement(SQL);
            stmt.setString(1, sigla);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Municipio e = new Municipio();
                e.setNome(rs.getString("nome"));
                e.setPopulacao(rs.getInt("populacao"));
                e.setClima(rs.getString("clima"));
                e.setGastos(rs.getFloat("gastos"));
                e.setFk_id_estado(rs.getString("fk_id_estado"));
                lista.add(e);
            }
            return lista;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Municipio> listarMunicipios() {
        try {
            List<Municipio> lista = new ArrayList<Municipio>();
            String SQL = "SELECT  *FROM municipio ORDER BY nome ASC";
            PreparedStatement stmt = conecta.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Municipio e = new Municipio();
                e.setNome(rs.getString("nome"));
                e.setPopulacao(rs.getInt("populacao"));
                e.setClima(rs.getString("clima"));
                e.setGastos(rs.getFloat("gastos"));
                e.setFk_id_estado(rs.getString("fk_id_estado"));
                lista.add(e);
            }
            return lista;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Municipio> listarMunicipiosClima(String clima) {
        try {
            List<Municipio> lista = new ArrayList<Municipio>();
            String SQL = "select * from municipio where clima=?";
            PreparedStatement stmt = conecta.prepareStatement(SQL);
            stmt.setString(1, clima);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Municipio e = new Municipio();
                e.setNome(rs.getString("nome"));
                e.setPopulacao(rs.getInt("populacao"));
                e.setClima(rs.getString("clima"));
                e.setGastos(rs.getFloat("gastos"));
                e.setFk_id_estado(rs.getString("fk_id_estado"));
                lista.add(e);
            }
            return lista;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Municipio> buscarMunicipio(String nome, String sigla) {
        try {
            List<Municipio> lista = new ArrayList<Municipio>();
            String SQL = "select * from municipio where nome=? and fk_id_estado=?";
            PreparedStatement stmt = conecta.prepareStatement(SQL);
            stmt.setString(1, nome);
            stmt.setString(2, sigla);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Municipio e = new Municipio();
                e.setNome(rs.getString("nome"));
                e.setPopulacao(rs.getInt("populacao"));
                e.setClima(rs.getString("clima"));
                e.setGastos(rs.getFloat("gastos"));
                e.setFk_id_estado(rs.getString("fk_id_estado"));
                lista.add(e);
            }
            return lista;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void AdicionarGasto(String silgaEstado, String nomeMunicipio, float valorGasto) {
        try {
            //Valor gasto atual            
            String sql = "select *from municipio where nome=? and fk_id_estado=?";
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, nomeMunicipio);
            stmt.setString(2, silgaEstado);
            ResultSet rs = stmt.executeQuery();
            float valorAtualGastoMunicipio = 0;
            if (rs != null && rs.next()) {
                valorAtualGastoMunicipio = (float) rs.getFloat("gastos");
                System.out.println(valorAtualGastoMunicipio);

            }
            stmt.close();

            //Inserir valor atual acrescido do valor de parâmetro
            String sqlScript = "update municipio set gastos=? where (fk_id_estado=?) and (nome = ?)";
            PreparedStatement stmt1 = conecta.prepareStatement(sqlScript);
            float valorAtualizadoMunicipio = valorAtualGastoMunicipio + valorGasto;
            stmt1.setFloat(1, valorAtualizadoMunicipio);
            stmt1.setString(2, silgaEstado);
            stmt1.setString(3, nomeMunicipio);
            stmt1.execute();
            stmt1.close();

            String sql2 = "select *from estado where sigla=?";
            PreparedStatement stmt2 = conecta.prepareStatement(sql2);
            stmt2.setString(1, silgaEstado);
            ResultSet rst = stmt2.executeQuery();
            float valorAtualEstadoGasto = 0;
            if (rst != null && rst.next()) {
                valorAtualEstadoGasto = (float) rst.getFloat("gastosTotais");
                System.out.println(valorAtualEstadoGasto);
            }
            stmt2.close();

            String cmd2sql = "update estado set gastosTotais=? where sigla=?";
            PreparedStatement stmt3 = conecta.prepareStatement(cmd2sql);
            float valorAtualizadoEstado = valorAtualEstadoGasto + valorGasto;
            stmt3.setFloat(1, valorAtualizadoEstado);
            stmt3.setString(2, silgaEstado);
            stmt3.execute();
            stmt3.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public void excluirMunicipio(String nome) {

        try {
            String sql = "delete from municipio where nome=?";
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
