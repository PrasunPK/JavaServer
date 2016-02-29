/**
 * Created by prasunkp on 29/02/16.
 */
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class HttpServerDemo {
    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress(8080);
        HttpServer server = HttpServer.create(address, 2);

        server.createContext("/", new MyHandler());
        server.start();
        System.out.println("Server is listening on port 8080" );
    }
}

class MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        System.out.println(requestMethod);
        if (requestMethod.equalsIgnoreCase("GET")) {
            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, 0);

            OutputStream responseBody = exchange.getResponseBody();
            String s = "Hello I am prasun. Wellcome to my page";
            responseBody.write(s.getBytes());
            responseBody.close();
        }
    }
}