package at.sipovsven.GetIt.service;
import java.awt.Font;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.net.MalformedURLException;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.util.List;

	import com.itextpdf.text.BaseColor;
	import com.itextpdf.text.Chunk;
	import com.itextpdf.text.Document;
	import com.itextpdf.text.DocumentException;
	import com.itextpdf.text.Element;
	import com.itextpdf.text.FontFactory;
	import com.itextpdf.text.Image;
	import com.itextpdf.text.PageSize;
	import com.itextpdf.text.Paragraph;
	import com.itextpdf.text.Phrase;
	import com.itextpdf.text.pdf.BaseFont;
	import com.itextpdf.text.pdf.PdfPCell;
	import com.itextpdf.text.pdf.PdfPTable;
	import com.itextpdf.text.pdf.PdfWriter;
	import com.itextpdf.text.pdf.draw.LineSeparator;

	import at.sipovsven.GetIt.model.Customer;
	import at.sipovsven.GetIt.model.Owner;
	import at.sipovsven.GetIt.model.Product;

public class PdfService {

	

	
		private com.itextpdf.text.Font font1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 10, Font.PLAIN);
		private com.itextpdf.text.Font font2 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 14, Font.BOLD);
		private com.itextpdf.text.Font font3 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 14, Font.BOLD);

		
		public static final String TOTAL = "Total";
		public static final String SUBTOTAL = "Subtotal";
		public static final String TAX = "Tax";
		
		BaseColor color = new BaseColor(20, 165, 255);

	
/*
 * Creates an Invoice PDF
 * Uses information from orders/customers/products etc.
 * 
 * Sets the data inside Cells which renders the data as a viewable Pdf
 * Careful, missinputs or wrong datatype will produce a fragmented Pdf
 */

		public void createPdf(String savePath,String filename,int orderNumber, Customer customer, Owner owner,List<Product> productSet) throws DocumentException, MalformedURLException, IOException {

			try {

				
				
				Document document = new Document(PageSize.A4, 40, 20, 0, 0);
				PdfWriter.getInstance(document, new FileOutputStream(savePath + "/" + filename + orderNumber + ".pdf"));
				document.open();

		
				
				Image img = Image.getInstance(
						"C:/Users/svens/eclipse-workspace/GetIt/src/main/resources/images/GetItLogo_Redux.png");
				img.setAlignment(Element.ALIGN_BASELINE);

				PdfPTable table = new PdfPTable(3);
				table.setWidthPercentage(100);
				table.setSpacingBefore(150f);
				table.setWidths(new int[] { 2, 1, 2 });
				PdfPCell cell = new PdfPCell(
						new Phrase(owner.getCompanyName() +  "\n" + owner.getFirstName() +"\n"+ owner.getLastname() + 
								"\n"+ owner.getAddress() , font1));
				cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				cell.setBorder(0);
				table.addCell(cell);

				PdfPCell cellrg = new PdfPCell(new Phrase("Rechnung" + orderNumber, font2));
				cellrg.setBorder(0);
				cellrg.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
				cellrg.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				table.addCell(cellrg);

				PdfPCell cell2 = new PdfPCell();
				cell2.setBorder(0);
				cell2.setHorizontalAlignment(PdfPCell.RIGHT);
				table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

				table.addCell(cell2);
				document.add(table);

				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
				StringBuilder dateBuilder = new StringBuilder(dateFormat.format(date));
				Paragraph leftDate = new Paragraph();
				leftDate.setAlignment(Element.ALIGN_RIGHT);
				leftDate.setFont(font1);
				leftDate.add("" + dateBuilder);

				document.add(new LineSeparator());

				document.add(leftDate);
				document.add(new Paragraph(""));

				// Adress
				PdfPTable adressTable = new PdfPTable(2);
				adressTable.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
				adressTable.setSpacingBefore(40f);
				adressTable.setWidthPercentage(96f);
				cell = new PdfPCell(
						new Phrase("Herr\n"+ customer.getName() + " " + customer.getLastname()+"\n" + customer.getAddress(), font2));
				cell.setBorder(0);
				adressTable.addCell(cell);
				Chunk chunk = new Chunk("Rechnungsnummer: "+ orderNumber + "\nKundennummer: " +
				customer.getCustomer_id() + "\nRechnungsdatum: " + dateBuilder, font1);
				cell = new PdfPCell(new Phrase(chunk));
				cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
				cell.setBorder(0);
				adressTable.addCell(cell);

				document.add(adressTable);

				// Products
				PdfPTable productTable = new PdfPTable(4);
				productTable.setPaddingTop(200);
				productTable.setSpacingBefore(50);
				productTable.addCell("QTY");
				productTable.addCell("Name");
				productTable.addCell("Price");
				productTable.addCell("Amount");

				double total = 0;
				int tax = 20;

				// Iterate over Set to display different Products
				for (int i = 0; i < productSet.size(); i++) {

					productTable.addCell(productSet.get(i).intToString(productSet.get(i).getQuantity()));
					productTable.addCell(productSet.get(i).getName());
					productTable.addCell(productSet.get(i).doubleToString(productSet.get(i).getPrice()));
					productTable.addCell(
							productSet.get(i).doubleToString(productSet.get(i).getPrice() * productSet.get(i).getQuantity()));

					total = total + productSet.get(i).getPrice() * productSet.get(i).getQuantity();

				}
				double subTotal = total / 100 * (100 - tax);

				cell = new PdfPCell(new Phrase(" "));
				cell.setBorder(0);
				productTable.addCell(cell);
				productTable.addCell(cell);
				productTable.addCell(cell);
				productTable.addCell(cell);

				
				font3.setColor(255, 255, 255);
				PdfPCell cellSubtotal = new PdfPCell(new Phrase("Subtotal",font3));
				cellSubtotal.setBackgroundColor(color);

				productTable.addCell(cell);
				productTable.addCell(cellSubtotal);
				productTable.addCell("Tax 20%");
				productTable.addCell("Total");

				productTable.addCell(cell);
				productTable.addCell(Double.toString(subTotal));
				productTable.addCell(Double.toString(total - total / 100 * (100 - tax)));
				productTable.addCell(Double.toString(total));
				
				productTable.setSpacingAfter(500);
				document.add(productTable);

				//BUSINESS OWNER INFORMATION
				PdfPTable businessTable = new PdfPTable(2);

				cell = new PdfPCell(
						new Phrase(owner.getBankName() +"\nBLZ: " + owner.getBankCode() + " " + owner.getAccountNumber(), font1));
				cell.setVerticalAlignment(PdfPCell.BOTTOM);
				cell.setBorder(0);

				businessTable.addCell(cell);

				cell = new PdfPCell(
						new Phrase("\n BIC: " + owner.getBic() + "\n UID: " + owner.getUid(),font1));
				cell.setVerticalAlignment(PdfPCell.BOTTOM);
				cell.setBorder(0);
				businessTable.addCell(cell);
				
				document.add(businessTable);


				document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

