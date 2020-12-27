package at.sipovsven.GetIt.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import at.sipovsven.GetIt.model.OrderedProduct;

public class OrderedProductRepositoryJPA {

	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("GetIt");

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public void addOrderedProduct(OrderedProduct product) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			em.persist(product);
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

	
	public OrderedProduct find(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		OrderedProduct product = new OrderedProduct();
		try {
			et = em.getTransaction();
			et.begin();
			
			product = em.find(OrderedProduct.class, id);
			
			
			System.out.println(product.getId());
			et.commit();
			
			return product;
		} catch (Exception e) {

			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();

		} finally {
			em.close();
		}
	
		return product;


	}
	
	
	
	public void removeOrderedProduct(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();
			OrderedProduct product = new OrderedProduct();
			product = em.find(OrderedProduct.class, id);

			em.remove(product);
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

	public List<OrderedProduct> getAllOrderedProductNames() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")
		List<OrderedProduct> productNames = (List<OrderedProduct>) em.createQuery("select name from OrderedProduct").getResultList();

		et.commit();
		em.close();
		return productNames;
	}

	@SuppressWarnings("unchecked")
	public List<OrderedProduct> getAllOrderedProducts() {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();


		// CASE SENSITIVE HQL QUERY!!!!
		List<OrderedProduct> products = (List<OrderedProduct>) em.createQuery("select o from OrderedProduct o").getResultList();
		et.commit();
		em.close();
		return products;
	}

	
	// Sets the product quantity in DB to - orderedQuantity
	public void setOrderedQuantity(List<OrderedProduct> productList, List<Integer> idList, List<Integer> quantityList) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		for (int i = 0; i < productList.size(); i++) {
			et.begin();

			OrderedProduct product = new OrderedProduct();

			product.setId(idList.get(i));
			product = em.find(OrderedProduct.class, product.getId());
			product.setQuantity(product.getQuantity() - quantityList.get(i));

			et.commit();
		}
		em.close();
	}

	
	public OrderedProduct getProductByName(String name) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		@SuppressWarnings("unchecked")
		List<OrderedProduct> products = (List<OrderedProduct>) em.createQuery("select o from OrderedProduct o where o.name in :name")
				.setParameter("name", name).getResultList();

		OrderedProduct product = products.get(0);

		return product;
	}

}
