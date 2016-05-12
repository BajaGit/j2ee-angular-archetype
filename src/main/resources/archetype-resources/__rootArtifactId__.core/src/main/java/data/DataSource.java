#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data;

import javax.persistence.EntityManager;


public interface DataSource {
	
	default public void crup(){
		EntityManager em = DataManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		em.persist(this);
		em.getTransaction().commit();
	}
	
	default public void delete(){
		EntityManager em = DataManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		em.remove(this);
		em.getTransaction().commit();
	}

	
}
