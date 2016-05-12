#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class DataAccessor<T> {
	
	private final Class<T> dataClass;
	
	public DataAccessor(Class<T> dataClass){
		
		this.dataClass = dataClass;
	}
	
	public CriteriaBuilder createCriteraBuilder(){
		return DataManager.INSTANCE.getEntityManager().getCriteriaBuilder();
	}
	
	public List<T> query (CriteriaQuery<T> query){
		TypedQuery<T> result =
				DataManager.INSTANCE.getEntityManager().createQuery(query);
		return result.getResultList();
	}
	
	public Long getCount(){
		Query q1 = DataManager.INSTANCE.getEntityManager().createQuery("SELECT COUNT(p) FROM "+ this.dataClass.getSimpleName() +" p");
        return (Long)q1.getSingleResult();
	}
	
	public List<T> getAll(){
		TypedQuery<T> query =
				DataManager.INSTANCE.getEntityManager().createQuery("SELECT p FROM "+ this.dataClass.getSimpleName() +" p", this.dataClass);
          return query.getResultList();
	}
	
	public void save(T toSave){
		EntityManager em = DataManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		em.persist(toSave);
		em.getTransaction().commit();
	}
	
	public void delete(T toDelete){
		EntityManager em = DataManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		em.remove(toDelete);
		em.getTransaction().commit();
	}
	
}
