package entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class User {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;    

	@Column(name = "email")
    private String email;
    
    @Column(name = "username")
    private String username;

	@Column(name = "pass")
    private String pass;
	
	@Column(name = "fullname")
    private String fullname;
	
	@Column(name = "identificationId")
    private String identificationId;
	
	@Column(name = "age")
    private int age;
	
	@Column(name = "address")
    private String address;
	
	@Column(name = "phone")
    private String phone;
	
	@Column(name = "dob")
    private Date dob;
	
	@Column(name = "verifylink")
    private String verifylink;    
	
	@ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
	
	@ManyToOne( cascade = CascadeType.REMOVE)
	@JoinColumn(name="fav_id")
	private Favourite fav;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getVerifylink() {
		return verifylink;
	}

	public void setVerifylink(String verifylink) {
		this.verifylink = verifylink;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Favourite getFav() {
		return fav;
	}

	public void setFav(Favourite fav) {
		this.fav = fav;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getIdentificationId() {
		return identificationId;
	}

	public void setIdentificationId(String identificationId) {
		this.identificationId = identificationId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
}
