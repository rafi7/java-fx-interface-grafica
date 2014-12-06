package br.com.empresa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.empresa.modelo.Cliente;

public class ClienteDAO {
	private static final String INSERT = "insert into cliente"
			+ "(codigo, nome, cpf, telefone)"
			+ "values"
			+ "(sq_cd_cliente.nextval,?,?,?)";
	
	private static final String LIST = "select * from cliente";
	private static final String LIST_NOME = "select * from cliente where nome like ?";
	private static final String DELETE = "delete from cliente where codigo = ?";
	private static final String LIST_BY_ID = "select * from cliente where codigo = ?";
	private static final String UPDATE = " update cliente set "
										+" nome = ?, cpf = ?, telefone = ?"
										+" where codigo = ?";
	
	public void inserir (Cliente c){
		Connection con = null;
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(INSERT);
			/*adiciona os parametros*/
			pstm.setString(1, c.getNome());
			pstm.setString(2, c.getCpf());
			pstm.setString(3, c.getTelefone());
			
			pstm.execute();
			
			JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso!!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir um cliente na base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public List<Cliente> listar(){
		Connection con = null;
		List<Cliente> clientes = null;
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(LIST);
			ResultSet rs = pstm.executeQuery();
			clientes = new ArrayList<Cliente>();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setCodigo(rs.getInt("codigo"));
				c.setNome(rs.getString("nome"));
				c.setCpf(rs.getString("cpf"));
				c.setTelefone(rs.getString("telefone"));
				
				clientes.add(c);				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar Clientes da base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return clientes;
	}
	
	public List<Cliente> listarNome(String nome){
		Connection con = null;
		List<Cliente> clientes = null;
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(LIST_NOME);
			pstm.setString(1, "%"+ nome +"%");
			ResultSet rs = pstm.executeQuery();
			clientes = new ArrayList<Cliente>();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setCodigo(rs.getInt("codigo"));
				c.setNome(rs.getString("nome"));
				c.setCpf(rs.getString("cpf"));
				c.setTelefone(rs.getString("telefone"));
				
				clientes.add(c);				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar clientes da base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return clientes;
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
			
			JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!!!");
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir um cliente na base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	public Cliente getById(int codigo) {
		Connection con = null;
		Cliente cliente = new Cliente();
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(LIST_BY_ID);
			pstm.setInt(1, codigo);
			
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				cliente.setCodigo(rs.getInt("codigo"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar clientes da base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return cliente;		
	}

	public void editar(Cliente c) {
		Connection con = null;
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(UPDATE);
			/*adiciona os parametros*/
			pstm.setString(1, c.getNome());
			pstm.setString(2, c.getCpf());
			pstm.setString(3, c.getTelefone());
			pstm.setInt(4, c.getCodigo());
			
			pstm.execute();
			
			JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao editar um cliente na base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
