package at.sipovsven.GetIt.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import at.sipovsven.GetIt.model.Customer;
import at.sipovsven.GetIt.model.Product;
import at.sipovsven.GetIt.repository.CustomerRepositoryJPA;
import at.sipovsven.GetIt.repository.ProductRepositoryJPA;

public class ExportImportService {

	ProductRepositoryJPA prodRepo = new ProductRepositoryJPA();
	CustomerRepositoryJPA customerRepo = new CustomerRepositoryJPA();
	
	//Standard csv export as comma seperated List
	public void exportProducts() throws SQLException, IOException {

		String csvFilePath = "C:/Users/svens/Desktop/Products.csv";
		List<Product> products = prodRepo.getAllProducts();

		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
		fileWriter.write("id,category,name,price,p_price,quantity,tax,weight");

		for (Product p : products) {

			String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s", p.getId(), p.getCategory(), p.getName(),
					p.getPrice(), p.getPurchasePrice(), p.getQuantity(), p.getTax(), p.getWeight());

			fileWriter.newLine();
			fileWriter.write(line);

		}

		fileWriter.close();

	}

	
	//Standard csv export as comma seperated List
	public void exportCustomers(String csv) throws SQLException, IOException {
		String csvFilePath = csv + "/Customers.csv";

		List<Customer> customers = customerRepo.getAllCustomers();

		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
		fileWriter.write("customer_id,address,email,lastname,name,phone");

		for (Customer c : customers) {
			String line = String.format("%s,%s,%s,%s,%s,%s", c.getCustomer_id(), c.getAddress(), c.getEmail(),
					c.getLastname(), c.getName(), c.getPhone());

			fileWriter.newLine();
			fileWriter.write(line);
		}

		fileWriter.close();
	}

	
	/* 
	 * imports Customers from given user csv data
	 * Best used with FileChooser to give the user controll over which file to import
	 * Duplicates will be ignored
	 */
	public void importCustomers(String csvFilePath) throws SQLException, IOException {
		// TODO Auto-generated method stub

		List<Customer> customerList = customerRepo.getAllCustomers();

		BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
		String lineText = null;

		lineReader.readLine(); // skip header line

		Customer customer = new Customer();

		// go throuh the input stream line by line
		while ((lineText = lineReader.readLine()) != null) {
			String[] data = lineText.split(",");

			String id = data[0];
			String address = data[1];
			String email = data[2];
			String lastname = data[3];
			String name = data[4];
			String phone = data[5];

			int iId = Integer.parseInt(id);
			customer = new Customer(iId, name, lastname, address, email, phone, null);

			System.out.println(iId);
			System.out.println(customerList.size());
			// IF CUSTOMER ID IS ALREADY IN DATABASE => ITERATE++ AND SKIP THIS INDEX

			if (!customerList.contains(customer)) {

				customerRepo.updateCustomer(customer);

			}
		}
		lineReader.close();

	}

	/* 
	 * imports Products from given user csv data
	 * Best used with FileChooser to give the user controll over which file to import
	 * Filters Duplicate Products --> Only quantity of Product gets updated
	 * New Products will be imported as new Entry to DB
	 */
	public void importProducts(String csvFilePath) throws SQLException, IOException {

		List<Product> productList = prodRepo.getAllProducts();

		BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
		String lineText = null;

		lineReader.readLine(); // skip header line

		while ((lineText = lineReader.readLine()) != null) {

			String[] data = lineText.split(",");

			String id = data[0];
			String category = data[1];
			String name = data[2];
			double price = Double.parseDouble(data[3]);
			double purchasePrice = Double.parseDouble(data[4]);
			int quantity = Integer.parseInt(data[5]);
			double tax = Double.parseDouble(data[6]);
			double weight = Double.parseDouble(data[7]);

			int iId = Integer.parseInt(id);
			Product product = new Product(iId, name, quantity, weight, price, purchasePrice, category, tax);

			Optional<Product> productFound = productList.stream().filter(p -> p.getId() == iId).findFirst();

			if (productFound.isPresent()) {

				// update quantity
				product = productFound.get();
				product.setQuantity(quantity);
			}

			prodRepo.updateProduct(product);
		}
		lineReader.close();

	}
}
