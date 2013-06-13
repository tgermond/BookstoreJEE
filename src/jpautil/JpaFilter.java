package jpautil;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JpaFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(JpaFilter.class);

    private static EntityManagerFactory emf;
    private static ThreadLocal<EntityManager> currentEntityManager = new ThreadLocal<EntityManager>();


    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
          EntityManager em = emf.createEntityManager();
          log.debug("creating EntityManager"+em.toString());
        currentEntityManager.set(em);
        try {
            em.getTransaction().begin();
            chain.doFilter(request, response);
            em.getTransaction().commit();
         } catch (Throwable ex) {
          em.getTransaction().rollback();
        }
        currentEntityManager.set(null);
        em.close();
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Initializing filter...");
        String persistenceUnit = filterConfig.getInitParameter("persistence-unit");
        emf = Persistence.createEntityManagerFactory(persistenceUnit);
    }

    public void destroy() {
      emf.close();
    }
    
    public static EntityManager getEntityManager(){
      return currentEntityManager.get();
    }

}