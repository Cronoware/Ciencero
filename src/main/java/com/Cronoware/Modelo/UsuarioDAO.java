package com.Cronoware.Modelo;

import com.Cronoware.Mapeo.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;

public class UsuarioDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
        	this.sessionFactory = sessionFactory;
    	}
  	
  	public void guardar(Usuario c) {
    
	        Session session = sessionFactory.openSession();
        	Transaction tx = null;
	        try {
			tx = session.beginTransaction();
         
           		session.persist(c);
           		tx.commit();
        	}catch (Exception e) {
           		if (tx!=null){ 
               			tx.rollback();
           		}
           		e.printStackTrace(); 
        	}finally {
           		session.close();
        	}
	}
        
        public Usuario getUsuario(String correo, String psswd){
            Usuario u = null;
            Session session = sessionFactory.openSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                String hql = "from usuario where correo = :correo";
                Query q = session.createQuery(hql);
                q.setParameter("correo", correo);
                u = (Usuario)q.uniqueResult();
                tx.commit();
                if(u.getPsswd().equals(psswd)){
                    return u;
                }else{
                    return null;
                }
            }catch(Exception e){
                if (tx!=null){ 
                    tx.rollback();
                }
                e.printStackTrace();
                return null;
            }finally{
                session.close();
            }
        }
	
	public void eliminar(Usuario c) {
    
        	Session session = sessionFactory.openSession();
        	Transaction tx = null;
        	try {
        	   	tx = session.beginTransaction();
	           	session.delete(c);
           		tx.commit();
        	}catch (Exception e) {
	        	if (tx!=null){ 
        	       		tx.rollback();
           		}
           		e.printStackTrace(); 
        	}finally {
           		session.close();
        	}
	}
}
