package at.sipovsven.GetIt.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import at.sipovsven.GetIt.model.Customer;
import at.sipovsven.GetIt.model.Order;
import at.sipovsven.GetIt.model.Owner;

public class OrderRepositoryJPA {

	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("GetIt");

	public void addOrder(Order order) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			em.persist(order);
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

	public List<Order> getAllOrders() {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		System.out.println("Read all Orders");
		et.begin();

		@SuppressWarnings("unchecked")
		List<Order> order = (List<Order>) em.createQuery("select o from Order o").getResultList();
		et.commit();
		em.close();
		return order;
	}
	
	public List<Order> getAllOrderDates() {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		System.out.println("Read all Orders for Dates");
		et.begin();

		@SuppressWarnings("unchecked")
		List<Order> orderDates = (List<Order>) em.createQuery("select o.date from Order o").getResultList();
		et.commit();
		em.close();
		
		System.out.println("Success");
		return orderDates;
		}
	
	public int getLatestOrderId() {
	
	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
	EntityTransaction et = em.getTransaction();

	System.out.println("Read all Orders");
	et.begin();

	@SuppressWarnings("unchecked")
	List<Order> order = (List<Order>) em.createQuery("select o.orderID from Order o").getResultList();
	int order_id = order.size() + 1;
	et.commit();
	em.close();
	return order_id;
	}
	
	public Order getOrderById(int id) {
		// Searches Customer db by id given
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		Order order = em.find(Order.class, id);
		
		et.commit();
		em.close();
		return order;

	}
	
	public Order updateOrder(Order order) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			order = em.merge(order);
			et.commit();

			return order;
			
		} catch (Exception e) {

			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();

		} finally {
			em.close();
		}
		
		return null;
	}
}
