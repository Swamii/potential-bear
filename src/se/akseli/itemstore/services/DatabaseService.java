package se.akseli.itemstore.services;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Swami
 * Base class for all the different services. 
 * Using an abstract class limits the number of persistencecontext's to one,
 * while still keeping many different functions for every entity without too much code.
 * @param <T> The entity the specific service will be used for
 */
public abstract class DatabaseService<T> {
	
	@PersistenceContext(unitName="ItemStore")
	EntityManager em;
		
	public DatabaseService() {}
	
	private Class<T> type;
	
	public DatabaseService(Class<T> type) {
		this.type = type;
	}
	
	public T persist(T t) {
		this.em.persist(t);
		// flush to force a commit
		this.em.flush();
		this.em.refresh(t);
		return t;
	}
	
	public void delete(T t) {
		this.em.remove(t);
	}
	
	public T update(T t) {
		return this.em.merge(t);
	}
	
	public T find(Object id) {
		return this.em.find(this.type, id);
	}
	
	public List<T> findWithQuery(String query) {
		return this.em.createQuery(query, this.type).getResultList();
	}
	
	public List<T> findWithQuery(String query, Map<String, Object> params) {
		Query q = this.em.createQuery(query, this.type);
		for (String key : params.keySet()) {
			q.setParameter(key, params.get(key));
		}
		return q.getResultList();
	}
	
}
