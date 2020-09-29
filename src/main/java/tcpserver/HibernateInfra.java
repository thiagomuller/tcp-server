package tcpserver;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import lombok.extern.java.Log;

@Log
public class HibernateInfra {
	private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session getSession() {
    	if(session != null) {
    		return session;
    	}
        if (sessionFactory == null) {
            try {
                registry = new StandardServiceRegistryBuilder().configure().build();
                MetadataSources sources = new MetadataSources(registry);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
            	log.info("Coundn't handle database necessary infra, please see message below");
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory.getCurrentSession();
    }
    
    public static void commitAndCloseSession() {
    	session.getTransaction().commit();
    	session.close();
    }
    

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
