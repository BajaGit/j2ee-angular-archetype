#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum DataManager {
	INSTANCE;
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	
	public EntityManager getEntityManager(){
		if ( this.em == null){
			this.emf = Persistence.createEntityManagerFactory("${symbol_dollar}objectdb/db/default.odb");
	        this.em = this.emf.createEntityManager();
		}
		return this.em;
	}
	
	public void destroy(){
		if (this.em != null){
			this.em.close();
			this.emf.close();
		}
	}
	
}
