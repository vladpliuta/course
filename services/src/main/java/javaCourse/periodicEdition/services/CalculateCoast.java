package javaCourse.periodicEdition.services;

import javaCourse.periodicEdition.entity.PeriodicEdition;

/**
 * business logic for calculate coast of payment
 * 
 * @author Vladimir Pliuta
 *
 */
public class CalculateCoast {

	public static double calculate (PeriodicEdition periodicEdition, int period) {
		int discount = 0;
		switch (period) {
		case 1:
			discount = 0;
			break;
		case 3:
			discount = periodicEdition.getDiscountQuarteryear();
			break;
		case 6:
			discount = periodicEdition.getDiscountHalfyear();
			break;
		case 12:
			discount = periodicEdition.getDiscountHalfyear();
			break;
		}
		double coast = (double) (periodicEdition.getMonthPrice() * period * (100 - discount) / 100);

		return coast;
	}
}
