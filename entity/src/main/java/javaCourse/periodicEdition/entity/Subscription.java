package javaCourse.periodicEdition.entity;

public class Subscription extends Entity {
	private int idReader;
	private int ISSN;
	private int period;

	public Subscription() {
	}
	
	public Subscription(int idReader, int ISSN, int period) {
		this.idReader = idReader;
		this.ISSN = ISSN;
		this.period = period;
	}
	
	public int getIdReader() {
		return idReader;
	}

	public void setIdReader(int idReader) {
		this.idReader = idReader;
	}

	public int getISSN() {
		return ISSN;
	}

	public void setISSN(int ISSN) {
		this.ISSN = ISSN;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", id reader=" + idReader + ", ISSN=" + ISSN + ", period=" + period + "]";

	}
}
