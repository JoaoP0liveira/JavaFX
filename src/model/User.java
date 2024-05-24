package model;

public class User {

	private long id;
	
	private String nomeSv;
	private int membrosSv;
	
	public User(long id, String nomeSv, int membrosSv) {
		super();
		this.id = id;
		this.nomeSv = nomeSv;
		this.membrosSv = membrosSv;
	}
	
	public User() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeSv() {
		return nomeSv;
	}

	public void setNomeSv(String nomeSv) {
		this.nomeSv = nomeSv;
	}

	public int getMembrosSv() {
		return membrosSv;
	}

	public void setMembrosSv(int membrosSv) {
		this.membrosSv = membrosSv;
	}
	
	
	
}