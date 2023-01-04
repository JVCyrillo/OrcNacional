/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import conexao.ConectionFactory;
import dao.EstadoDAO;
import dao.MunicipioDAO;
import dominio.Estado;
import dominio.Municipio;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author joaod
 */
public class TesteDeCRUD {

    public static void main(String[] args) throws SQLException, InterruptedException {
        try {
            JOptionPane.showMessageDialog(null, "testando a conexao");
            Connection con = new ConectionFactory().conecta();
            JOptionPane.showMessageDialog(null, "conexão realizada com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }

//        EstadoDAO trDAO = new EstadoDAO();
//        Estado tr = new Estado("Minas Gerais", "MG",  0, (float)123.8, (float)124.8);
//        trDAO.cadastrarEstado(tr);
//
//        MunicipioDAO tlDAO = new MunicipioDAO();
//        Municipio tf = new Municipio("ooooooooo", 19987, "Equatorial", (float) 123.8, "MG");
//        tlDAO.cadastrarMunicipio(tf);
//        
//        tlDAO = new MunicipioDAO();
////        Municipio tf = new Municipio("cvcvc", 19987, "Equatorial", (float) 50.8, "MC");
//        tlDAO.AdicionarGasto("MC", "cvcvc", 50);
        
        
        
//          ArrayList<Municipio> lista =   tlDAO.listarMunicipios("AL");
//          for (Municipio m: lista){
//              System.out.println("nome" + m.getNome());
//              System.out.println("sigla" + m.getFk_id_estado());
//          }

//        List<Municipio> lista = tlDAO.listarMunicipiosComClima("Equatorial");
//        for (Municipio m : lista) {
//            System.out.println("nome" + m.getNome());
//            
//        }

        //trDAO.listarTurmas();
        //trDAO.alterarTurma(tr);
        //trDAO.excluirTurma(tr);
    }
}
