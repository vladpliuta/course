package javaCourse.periodicEdition.entity;

public class Payment extends Entity {
	private int idReader;
	private double coast;

	public Payment() {
	}
	
	public Payment(int idReader, double coast) {
		this.idReader = idReader;
		this.coast = coast;
	}
	
	public int getIdReader() {
		return idReader;
	}

	public void setIdReader(int idReader) {
		this.idReader = idReader;
	}

	public double getCoast() {
		return coast;
	}

	public void setCoast(double coast) {
		this.coast = coast;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", id reader=" + idReader + ", coast=" + coast + "]";
	}
}
