package at.sipovsven.GetIt.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import at.sipovsven.GetIt.model.Order;
import at.sipovsven.GetIt.model.Owner;

public class OwnerRepositoryJPA {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("GetIt");
	
	
	public void addOwner(Owner owner) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			em.persist(owner);
			et.commit();

		} catch (Exception e) {

			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();

		} finally {
			em.close();
		}
	}
	
	public Owner getOwnerById(int id) {
		// Searches Customer db by id given
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		Owner owner = em.find(Owner.class, id);
		
		et.commit();
		em.close();
		return owner;

	}
	
	
	public int getLatestOwnerId() {
	
	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
	EntityTransaction et = em.getTransaction();

	System.out.println("GetOwner");
	et.begin();

	@SuppressWarnings("unchecked")
	List<Order> owner = (List<Order>) em.createQuery("select o.id from Owner o").getResultList();
	int owner_id = owner.size();
	et.commit();
	em.close();
	return owner_id;
	}
	
}
