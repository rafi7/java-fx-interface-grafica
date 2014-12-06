package br.com.empresa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.empresa.modelo.Fornecedor;

public class FornecedorDAO {
	private static final String INSERT = "insert into fornecedor"
			+ "(codigo, nome_fantasia, razao_social, inscricao_estadual, telefone, cnpj)"
			+ "values"
			+ "(sq_cd_fornecedor.nextval,?,?,?,?,?)";
	
	private static final String LIST = "select * from fornecedor";
	private static final String LIST_NOME = "select * from fornecedor where nome_fantasia like ?";
	private static final String DELETE = "delete from fornecedor where codigo = ?";
	private static final String LIST_BY_ID = "select * from fornecedor where codigo = ?";
	private static final String UPDATE = " update fornecedor set "
										+" nome_fantasia = ?, razao_social = ?, inscricao_estadual = ?, telefone = ?, cnpj = ?"
										+" where codigo = ?";
	
	public void inserir (Fornecedor f){
		Connection con = null;
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(INSERT);
			/*adiciona os parametros*/
			pstm.setString(1, f.getNomeFantasia());
			pstm.setString(2, f.getRazaSocial());
			pstm.setString(3, f.getInscricaoEstadual());
			pstm.setString(4, f.getTelefone());
			pstm.setString(5, f.getCnpj());
			
			pstm.execute();
			
			JOptionPane.showMessageDialog(null, "Fornecedor inserido com sucesso!!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir um fornecedor na base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public List<Fornecedor> listar(){
		Connection con = null;
		List<Fornecedor> fornecedores = null;
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(LIST);
			ResultSet rs = pstm.executeQuery();
			fornecedores = new ArrayList<Fornecedor>();
			while (rs.next()) {
				Fornecedor f = new Fornecedor();
				f.setCodigo(rs.getInt("codigo"));
				f.setNomeFantasia(rs.getString("nome_fantasia"));
				f.setRazaSocial(rs.getString("razao_social"));
				f.setInscricaoEstadual(rs.getString("inscricao_estadual"));
				f.setTelefone(rs.getString("telefone"));
				f.setCnpj(rs.getString("cnpj"));
				
				fornecedores.add(f);				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar fornecedores da base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return fornecedores;
	}
	
	public List<Fornecedor> listarNome(String nome){
		Connection con = null;
		List<Fornecedor> fornecedores = null;
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(LIST_NOME);
			pstm.setString(1, "%"+ nome +"%");
			ResultSet rs = pstm.executeQuery();
			fornecedores = new ArrayList<Fornecedor>();
			while (rs.next()) {
				Fornecedor f = new Fornecedor();
				f.setCodigo(rs.getInt("codigo"));
				f.setNomeFantasia(rs.getString("nome_fantasia"));
				f.setRazaSocial(rs.getString("razao_social"));
				f.setInscricaoEstadual(rs.getString("inscricao_estadual"));
				f.setTelefone(rs.getString("telefone"));
				f.setCnpj(rs.getString("cnpj"));
				
				fornecedores.add(f);				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar fornecedores da base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return fornecedores;
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
			
			JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso!!!");
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir um fornecedor na base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	public Fornecedor getById(int codigo) {
		Connection con = null;
		Fornecedor fornecedor = new Fornecedor();
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(LIST_BY_ID);
			pstm.setInt(1, codigo);
			
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				fornecedor.setCodigo(rs.getInt("codigo"));
				fornecedor.setNomeFantasia(rs.getString("nome_fantasia"));
				fornecedor.setRazaSocial(rs.getString("razao_social"));
				fornecedor.setInscricaoEstadual(rs.getString("inscricao_estadual"));
				fornecedor.setTelefone(rs.getString("telefone"));
				fornecedor.setCnpj(rs.getString("cnpj"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar fornecedores da base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return fornecedor;		
	}

	public void editar(Fornecedor f) {
		System.out.println("Editando...");
		Connection con = null;
		try {
			/*Busca a conexao*/
			con = FabricaConexao.getConexao();
			PreparedStatement pstm = con.prepareStatement(UPDATE);
			/*adiciona os parametros*/
			pstm.setString(1, f.getNomeFantasia());
			pstm.setString(2, f.getRazaSocial());
			pstm.setString(3, f.getInscricaoEstadual());
			pstm.setString(4, f.getTelefone());
			pstm.setString(5, f.getCnpj());
			pstm.setInt(6, f.getCodigo());
			
			pstm.execute();
			
			JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso!!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao editar um fornecedor na base de dados.\n"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
