/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        try
        {
            this.conn = new conectaDAO().connectDB();
             
            this.prep = this.conn.prepareStatement("INSERT INTO `produtos` (`nome`,`valor`,`status`) VALUES (?,?,?)");
            this.prep.setString(1, produto.getNome());
            this.prep.setInt(2, produto.getValor());
            this.prep.setString(3, produto.getStatus());
            
            this.prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            this.conn.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto");
            System.out.println(e);
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        this.listagem.clear();
        try
        {
            this.conn = new conectaDAO().connectDB();
            this.prep = this.conn.prepareStatement("SELECT * FROM `produtos`;");
            this.resultset = this.prep.executeQuery();
            while(this.resultset.next())
            {
                Integer _id = this.resultset.getInt("id");
                String _nome = this.resultset.getString("nome");
                Integer _valor = this.resultset.getInt("valor");
                String _status = this.resultset.getString("status");
                
                this.listagem.add(new ProdutosDTO(_id, _nome, _valor, _status));
            }
            this.conn.close();
            return listagem;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "erro ao se conectar ao banco de dados.");
            return null;
        }
    }  
}

