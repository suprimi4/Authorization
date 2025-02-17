package HW01.main;

import HW01.servlets.LoginServlet;
import HW01.servlets.MirrorServlet;
import HW01.servlets.RegistrationServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import java.util.logging.Logger;

public class Main {
    private  static  final Logger logger = Logger.getLogger("Main");
    public static void main(String[] args) throws Exception {
        RegistrationServlet registrationServlet = new RegistrationServlet();
        LoginServlet loginServlet = new LoginServlet();
        MirrorServlet mirrorServlet = new MirrorServlet();

        ServletContextHandler servletContextHandler =new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(registrationServlet), "/");
        servletContextHandler.addServlet(new ServletHolder(loginServlet), "/login");
        servletContextHandler.addServlet(new ServletHolder(mirrorServlet), "/mirror");
        Server server = new Server(8080);
        server.setHandler(servletContextHandler);

        server.start();
        logger.info("Server started");
        server.join();
    }
}
