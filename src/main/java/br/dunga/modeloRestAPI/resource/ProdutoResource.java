package br.dunga.modeloRestAPI.resource;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.dunga.modeloRestAPI.dao.ProdutoDAO;
import br.dunga.modeloRestAPI.model.Produto;

@Path("produto")
public class ProdutoResource {

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response obterProduto(@PathParam("id") long id) {
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = dao.buscar(id);
		
		if (produto == null) {
			return Response.noContent().build();
		} else {
			return Response.ok(produto.toJson()).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response obterTodosProduto() {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> lista = dao.buscarTodos();
		
		if (lista == null || lista.isEmpty()) {
			return Response.noContent().build();
		} else {
			Type listType = new TypeToken<List<Produto>>() {}.getType();
			String json =  new Gson().toJson(lista, listType);
			return Response.ok(json).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response criarProduto(String conteudo) {
		
		Produto produto = new Gson().fromJson(conteudo, Produto.class);
		ProdutoDAO dao = new ProdutoDAO();
		dao.inserir(produto);
		
		URI location = URI.create("/produto/" + produto.getId());
		return Response.created(location).build();
		
	}
	
	@PATCH
	@Path("{id}/palavra/{palavra}")
	public Response adicionaPalavra(@PathParam("id") long id, @PathParam("palavra") String palavra) {
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = dao.buscar(id);
		produto.adicionaPalavra(palavra);
		dao.atualizar(produto);
		return Response.ok().build();
	}
}
