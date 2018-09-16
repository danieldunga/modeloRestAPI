package br.dunga.modeloRestAPI.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

public class Produto {

	private long id;
	private String nome;
	private double preco;
	private Cor cor;
	private List<String> palavrasChave;
	
	public Produto(long id, String nome, double preco, Cor cor, List<String> palavrasChave) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.cor = cor;
		this.palavrasChave = palavrasChave;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	public Cor getCor() {
		return cor;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

	public String toXML() {
		return new XStream().toXML(this);
	}

	public List<String> getPalavrasChave() {
		return palavrasChave;
	}

	public void setPalavrasChave(List<String> palavrasChave) {
		this.palavrasChave = palavrasChave;
	}

	public void adicionaPalavra(String palavra) {
		if (palavrasChave == null) {
			palavrasChave = new ArrayList<String>();
		}
		palavrasChave.add(palavra);
	}
}
