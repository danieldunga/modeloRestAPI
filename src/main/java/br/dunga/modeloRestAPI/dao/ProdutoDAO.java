package br.dunga.modeloRestAPI.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.dunga.modeloRestAPI.model.Cor;
import br.dunga.modeloRestAPI.model.Produto;

public class ProdutoDAO {

	private static Map<Long, Produto> banco = new HashMap<Long, Produto>();
	private static AtomicLong contador = new AtomicLong(1);
	
	static {
		long id = contador.getAndIncrement();
		ArrayList<String> palavras = new ArrayList<String>();
		palavras.add("pé");
		palavras.add("sapato");
		palavras.add("chulé");
		banco.put(id, new Produto(id, "meia", 2.0, new Cor(1, "vemelho"), palavras));
	}
		
	public Produto buscar(long id) {
		return banco.get(id);
	}
	
	public void inserir(Produto produto) {
		long id = contador.getAndIncrement();
		produto.setId(id);
		banco.put(id, produto);
	}
	
	public List<Produto> buscarTodos() {
		ArrayList<Produto> lista = new ArrayList<Produto>(banco.values());
		return lista;
	}

	public void atualizar(Produto produto) {
		banco.put(produto.getId(), produto);
	}
}
