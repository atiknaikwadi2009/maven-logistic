package at.sipovsven.GetIt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_owner")
public class Owner {
	private static final long serialVersionUID = 1L;
	private int id;
	private String companyName;
	private String firstName;
	private String lastname;
	private String address;
	private String email;
	private String phone;
	private String uid;
	private String bankName;
	private String bankCode;
	private String accountNumber;
	private String bic;
	private String companybookNum;

	public Owner() {

	}

	public Owner(String companyName, String firstName, String lastname, String address, String email, String phone,
			String uid, String bankName,String bankCode, String accountNumber, String bic, String companyBookNum) {

		this.companyName = companyName;
		this.firstName = firstName;
		this.lastname = lastname;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.uid = uid;
		this.bankName = bankName;
		this.bankCode = bankCode;
		this.accountNumber = accountNumber;
		this.bic = bic;
		this.companybookNum = companyBookNum;
	}

	// Autoincrement reset?
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "company_name")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone_number")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "uid_code")
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Column(name = "bank_name")
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "bank_code")
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Column(name = "account_number")
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name = "bic")
	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	@Column(name = "companybook_number")
	public String getCompanybookNum() {
		return companybookNum;
	}

	public void setCompanybookNum(String companybookNum) {
		this.companybookNum = companybookNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
