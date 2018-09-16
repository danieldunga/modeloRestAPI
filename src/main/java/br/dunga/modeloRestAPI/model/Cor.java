package br.dunga.modeloRestAPI.model;

public class Cor {

	private int id;
	private String nome;
	
	public Cor(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	
}
