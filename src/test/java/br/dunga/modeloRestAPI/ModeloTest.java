package br.dunga.modeloRestAPI;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.dunga.modeloRestAPI.model.Cor;
import br.dunga.modeloRestAPI.model.Produto;

@DisplayName("Teste dos modelos")
public class ModeloTest {

	@Test
	void testeCor () {
		Cor cor = new Cor(1, "vermelho");
		Assertions.assertEquals(1, cor.getId());
	}
	
	@Test
	public void testeProduto() {
		ArrayList<String> palavras = new ArrayList<String>();
		palavras.add("pé");
		palavras.add("sapato");
		palavras.add("chulé");
		Produto produto = new Produto(1, "meia", 2.0, new Cor(1, "vemelho"), palavras);
		Assertions.assertEquals(2, produto.getPreco());
	}

}
