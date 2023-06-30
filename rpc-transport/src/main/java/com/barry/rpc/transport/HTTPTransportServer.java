package com.barry.rpc.transport;

import jakarta.servlet.http.HttpServlet;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author : Luyu Zhang
 * @create 2023-06-30 17:05
 */
@Slf4j
public class HTTPTransportServer implements TransportServer{
    private RequestHandler handler;
    private Server server;

    @Override
    public void init(int port, RequestHandler handler) {
        this.handler = handler;
        this.server = new Server(port);

        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);

        ServletHolder holder = new ServletHolder(new RequestServlet());
        ctx.addServlet(holder, "/*");

    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws jakarta.servlet.ServletException, java.io.IOException {
            log.info("client connect");

            System.out.println("client connect");
            InputStream in = req.getInputStream();
            OutputStream out = resp.getOutputStream();

            if (handler != null) {
                handler.onRequest(in, out);
            }

            out.flush();
        }
    }
}
