package br.dunga.modeloRestAPI;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {

	public static void main(String[] args) throws IOException {
		HttpServer server = startServer();
		System.out.println("Servidor rodando");
		System.in.read();
		stopServer(server);
		System.out.println("Servidor parado");
	}

	public static void stopServer(HttpServer server) {
		server.shutdown();
	}

	public static HttpServer startServer() {
		ResourceConfig config = new ResourceConfig().packages("br.dunga.modeloRestAPI.resource");
		URI uri = URI.create("http://localhost:8081/");
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		return server;
	}

}
