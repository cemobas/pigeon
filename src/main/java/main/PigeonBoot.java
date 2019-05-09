package main;

import response.BadRequestMapper;
import response.NoResultMapper;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import rest.service.AccountService;
import rest.service.CustomerService;
import rest.service.PigeonEntry;
import rest.service.TransferService;

public class PigeonBoot {

    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                        CustomerService.class.getCanonicalName()+";"+";"+
                        AccountService.class.getCanonicalName()+";"+
                        TransferService.class.getCanonicalName()+";"+
                        PigeonEntry.class.getCanonicalName()+";"+
                        BadRequestMapper.class.getCanonicalName()+";"+
                        NoResultMapper.class.getCanonicalName());

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}