package at.sipovsven.GetIt.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import at.sipovsven.GetIt.model.Customer;

public class CustomerRepositoryJPA {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("GetIt");

	public String ToString(Customer customer) {
		return customer.getCustomer_id() + " " + customer.getAddress() + " " + customer.getEmail() + " "
				+ customer.getLastname() + " " + customer.getName();

	}

	public void addCustomers(Customer customer) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			em.persist(customer);
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
	
	public void removeCustomer(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();
			Customer customer = new Customer();
			customer = em.find(Customer.class, id);

			em.remove(customer);
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
	
	public Customer updateCustomer(Customer customer) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			customer = em.merge(customer);
			et.commit();

			return customer;
			
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
	
	public Customer getCustomerById(int customer_id) {
		// Searches Customer db by id given
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		Customer customer = em.find(Customer.class, customer_id);
		
		et.commit();
		em.close();
		return customer;

	}
	
	public Customer getCustomerByAddress(String address) {
		// Searches Customer db by id given
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")
		List<Customer> customerList = (List<Customer>) em.createQuery("Select c.customer_id from Customer c where c.address = :address").setParameter("address", address);
		
		
		Customer customer = em.find(Customer.class, customerList);
		System.out.println(customer);
		et.commit();
		em.close();
		return  customer;

	}
	
	public List<Customer> getAllCustomersName() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")
		List<Customer> customerName = (List<Customer>) em.createQuery("select name from Customer").getResultList();
		
		et.commit();
		em.close();
		return customerName;
	}
	


	
	public List<Customer> getAllCustomerAddress() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")
		List<Customer> firstName = (List<Customer>) em.createQuery("select address from Customer").getResultList();
		
		
		
		et.commit();
		em.close();
		return firstName;
	}
	
	public List<Customer> getAllCustomers() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")
		List<Customer> customers = (List<Customer>) em.createQuery("select c from Customer c").getResultList();
		et.commit();
		em.close();
		return customers;
	}
}
