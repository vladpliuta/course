package javaCourse.periodicEdition.entity;

public class PeriodicEdition extends Entity {
	private String title;
	private String shortDescription;
	private int monthPeriodicity;
	private double monthPrice;
	private int discountQuarteryear;
	private int discountHalfyear;

	public PeriodicEdition() {
	}
	
	public PeriodicEdition(int id, String title, String shortDescription, int monthPeriodicity, double monthPrice,
			int discountQuarteryear, int discountHalfyear) {
		super(id);
		this.title = title;
		this.shortDescription = shortDescription;
		this.monthPeriodicity = monthPeriodicity;
		this.monthPrice = monthPrice;
		this.discountQuarteryear = discountQuarteryear;
		this.discountHalfyear = discountHalfyear;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public int getMonthPeriodicity() {
		return monthPeriodicity;
	}

	public void setMonthPeriodicity(int monthPeriodicity) {
		this.monthPeriodicity = monthPeriodicity;
	}

	public double getMonthPrice() {
		return monthPrice;
	}

	public void setMonthPrice(double monthPrice) {
		this.monthPrice = monthPrice;
	}

	public int getDiscountQuarteryear() {
		return discountQuarteryear;
	}

	public void setDiscountQuarteryear(int discountQuarteryear) {
		this.discountQuarteryear = discountQuarteryear;
	}

	public int getDiscountHalfyear() {
		return discountHalfyear;
	}

	public void setDiscountHalfyear(int discountHalfyear) {
		this.discountHalfyear = discountHalfyear;
	}

	@Override
	public String toString() {
		return "Periodic edition [ISSN=" + id + ", title=" + title + ", short description=" + shortDescription
				+ ", month periodicity=" + monthPeriodicity + ", month price=" + monthPrice + ", discount quarteryear="
				+ discountQuarteryear + ", discount halfyear=" + discountHalfyear + "]";
	}
}
