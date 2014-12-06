package br.com.empresa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.empresa.modelo.Produto;

public class ProdutoDAO {
	private static final String INSERT = "insert into produto"
			+ "(codigo, nome, preco)"
			+ "values"
			+ "(sq_cd_produto.nextval,?,?)";
	
	private static final String LIST = "select * from produto";
	private static final String LIST_NOME = "select * from produto where nome like ?";
	private static final String DELETE = "delete from produto where codigo = ?";
	private static final String LIST_BY_ID = "select * from produto where codigo = ?";
	private static final String UPDATE = " update produto set "
										+" nome = ?, preco = ?"
										+" where codigo = ?";
	
	public void inserir (Produto p){
		Connection con = null;
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(INSERT);
			/*adiciona os parametros*/
			pstm.setString(1, p.getNome());
			pstm.setDouble(2, p.getPreco());
			
			pstm.execute();
			
			JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir um produto na base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public List<Produto> listar(){
		Connection con = null;
		List<Produto> produtos = null;
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(LIST);
			ResultSet rs = pstm.executeQuery();
			produtos = new ArrayList<Produto>();
			while (rs.next()) {
				Produto p = new Produto();
				p.setCodigo(rs.getInt("codigo"));
				p.setNome(rs.getString("nome"));
				p.setPreco(rs.getDouble("preco"));
				produtos.add(p);				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar Produtos da base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return produtos;
	}
	
	public List<Produto> listarNome(String nome){
		Connection con = null;
		List<Produto> produtos = null;
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(LIST_NOME);
			pstm.setString(1, "%"+ nome +"%");
			ResultSet rs = pstm.executeQuery();
			produtos = new ArrayList<Produto>();
			while (rs.next()) {
				Produto p = new Produto();
				p.setCodigo(rs.getInt("codigo"));
				p.setNome(rs.getString("nome"));
				p.setPreco(rs.getDouble("preco"));
				
				produtos.add(p);				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar produtos da base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return produtos;
	}
	
	public boolean excluir (int codigo){
		Connection con = null;
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(DELETE);
			/*adiciona os parametros*/
			pstm.setInt(1, codigo);			
			pstm.execute();
			
			JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!!!");
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir um produto na base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	public Produto getById(int codigo) {
		Connection con = null;
		Produto produto = new Produto();
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(LIST_BY_ID);
			pstm.setInt(1, codigo);
			
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				produto.setCodigo(rs.getInt("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getDouble("preco"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar produtos da base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return produto;		
	}

	public void editar(Produto p) {
		Connection con = null;
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(UPDATE);
			/*adiciona os parametros*/
			pstm.setString(1, p.getNome());
			pstm.setDouble(2, p.getPreco());
			pstm.setInt(3, p.getCodigo());
			
			pstm.execute();
			
			JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao editar um produto na base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
