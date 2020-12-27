package at.sipovsven.GetIt.repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import at.sipovsven.GetIt.model.Customer;

public class CreateOrderRepositoryJPA {
	@SuppressWarnings("unused")
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("GetIt");
	
	CustomerRepositoryJPA customerRepo = new CustomerRepositoryJPA();
	
	
	public Customer getCustomerForOrder(int customer_id) {
		// TODO customerAuto-generated method stub
		
		return customerRepo.getCustomerById(customer_id);
	}


}
