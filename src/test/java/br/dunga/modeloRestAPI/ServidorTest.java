package br.dunga.modeloRestAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.dunga.modeloRestAPI.model.Cor;
import br.dunga.modeloRestAPI.model.Produto;

public class ServidorTest {
	
	private static HttpServer servidor;
	private static Client client;
	private static WebTarget target;
	
	@BeforeAll
	static void startServidor() {
		servidor = Servidor.startServer();
		ClientConfig config = new ClientConfig();
		
		Logger logger = Logger.getLogger(ServidorTest.class.getName());
        Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
		
		config.register(feature);
		client = ClientBuilder.newClient(config);
		target = client.target("http://localhost:8081");
	}
	
	@AfterAll
	static void paraServidor() {
		Servidor.stopServer(servidor);
	}

	@Test
	void buscarProduto() {
		try {			
			String conteudo = target.path("/produto/1").request().get(String.class);

			assertTrue(conteudo.contains("meia"));
			
		} catch (Exception e) {
			fail(e);	
		}
	}
	
	@Test
	void inserirProduto() {
		
		ArrayList<String> palavras = new ArrayList<String>();
		palavras.add("jeans");
		Produto produto = new Produto(1, "calça", 24.0, new Cor(3, "azul"), palavras);
		
		String json = produto.toJson();
		Entity<String> entity = Entity.entity(json, MediaType.APPLICATION_JSON);
		Response resp = target.path("/produto/").request().post(entity);
		assertEquals(Status.CREATED.getStatusCode(), resp.getStatus());
		
		String location = resp.getHeaderString("Location");
		String conteudo = client.target(location).request().get(String.class);
		assertTrue(conteudo.contains("jeans"));
	}
}
