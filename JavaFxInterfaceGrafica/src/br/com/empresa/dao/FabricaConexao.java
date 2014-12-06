package br.com.empresa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
	private static final String USUARIO = "jorge";
	private static final String SENHA = "123456";
	private static final String STR_CONEXAO = "jdbc:oracle:thin:@localhost:1521:xe";

	public static Connection getConexao() throws SQLException{
		try {
			return DriverManager.getConnection(STR_CONEXAO, USUARIO, SENHA);
		} catch (SQLException e) {
			throw new SQLException();
		}
	}
}
