package it.gds.point.persistence;

import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class HibernateUtil {
    private static final SessionFactory sessionFactory;
	 
	static {
       try {
    	   sessionFactory = new Configuration().configure().buildSessionFactory();   
       } catch (Throwable e) {
	            System.err.println("Initial SessionFactory creation failed." + e);
	            throw new ExceptionInInitializerError(e);
       }
	}
	 
	public static SessionFactory getSessionFactory() {	
		return sessionFactory;
	}
	
	public static void shutdown() {
		getSessionFactory().close();
	}

	public synchronized static void saveOrUpdate(Object o) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().setTimeout(10);
			session.getTransaction().begin();
			session.saveOrUpdate(o);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public synchronized static void delete(Object o) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().setTimeout(10);
			session.getTransaction().begin();
			session.delete(o);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}