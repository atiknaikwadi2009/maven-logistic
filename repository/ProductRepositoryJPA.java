package at.sipovsven.GetIt.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import at.sipovsven.GetIt.model.Product;

public class ProductRepositoryJPA {

	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("GetIt");

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public void addProduct(Product product) {
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

	public void removeProduct(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();
			Product product = new Product();
			product = em.find(Product.class, id);

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

	public Product updateProduct(Product product) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		product = em.merge(product);

		et.commit();

		em.close();
		return product;

	}

	public List<Product> getAllProductNames() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")
		List<Product> productNames = (List<Product>) em.createQuery("select name from Product").getResultList();

		et.commit();
		em.close();
		return productNames;
	}

	public List<Product> getAllProducts() {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")

		// CASE SENSITIVE HQL QUERY!!!!
		List<Product> products = (List<Product>) em.createQuery("select p from Product p").getResultList();
		et.commit();
		em.close();
		return products;
	}

	// Sets the product quantity in DB to - orderedQuantity
	public void setOrderedQuantity(List<Product> productList, List<Integer> idList, List<Integer> quantityList) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		for (int i = 0; i < productList.size(); i++) {
			et.begin();

			Product product = new Product();

			product.setId(idList.get(i));
			product = em.find(Product.class, product.getId());
			product.setQuantity(product.getQuantity() - quantityList.get(i));

			et.commit();
		}
		em.close();
	}

	public Product getProductByName(String name) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) em.createQuery("select p from Product p where p.name in :name")
				.setParameter("name", name).getResultList();

		Product product = products.get(0);

		return product;
	}

	@SuppressWarnings("unused")
	private List<Product> getProduct() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")
		List<Product> items = (List<Product>) em.createQuery("select p from Product p").getResultList();
		et.commit();
		em.close();
		return items;

	}

	public void persistNewQuantity(int id, int quantity) {
		// TODO Auto-generated method stub

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		Product product = new Product();

		product = em.find(Product.class, id);

		product.setQuantity(quantity);

		et.commit();

		em.close();
	}

}
