package br.com.empresa.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TabelaCliente {
	private Cliente cliente;
	private IntegerProperty codigo;
	private StringProperty nome;
	private StringProperty telefone;
	private StringProperty cpf;
	public TabelaCliente(Cliente cliente) {
		this.cliente = cliente;
		codigo = new SimpleIntegerProperty();
		codigo.set(cliente.getCodigo());
		
		nome = new SimpleStringProperty();
		nome.set(cliente.getNome());
		
		telefone = new SimpleStringProperty();
		telefone.set(cliente.getTelefone());
		
		cpf = new SimpleStringProperty();
		cpf.set(cliente.getCpf());
	}
	public Cliente getCliente() {
		return cliente;
	}
	public IntegerProperty codigoProperty() {
		return codigo;
	}
	public StringProperty nomeProperty() {
		return nome;
	}
	public StringProperty telefoneProperty() {
		return telefone;
	}
	public StringProperty cpfProperty() {
		return cpf;
	}
}
