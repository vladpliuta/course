package javaCourse.periodicEdition.services;

import javax.servlet.http.HttpServletRequest;

import javaCourse.periodicEdition.entity.PeriodicEdition;



/**
 * business logic for create new periodic edition
 * 
 * @author Vladimir Pliuta
 *
 */
public class CreatePeriodicEdition {

	public static PeriodicEdition createPeriodicEdition (HttpServletRequest request){
	String idString = request.getParameter("ISSN");
	int id = Integer.valueOf(idString);

	String title = request.getParameter("title");

	String shortDescription = request.getParameter("shortDescription");

	String monthPeriodicityString = request.getParameter("monthPeriodicity");
	int monthPeriodicity = Integer.valueOf(monthPeriodicityString);

	String monthPriceString = request.getParameter("monthPrice");
	double monthPrice = Double.valueOf(monthPriceString);

	String discountQuarteryearString = request.getParameter("discountQuarteryear");
	int discountQuarteryear = Integer.valueOf(discountQuarteryearString);

	String discountHalfyearString = request.getParameter("discountHalfyear");
	int discountHalfyear = Integer.valueOf(discountHalfyearString);

	PeriodicEdition periodicEdition = new PeriodicEdition(id, title, shortDescription, monthPeriodicity, monthPrice,
			discountQuarteryear, discountHalfyear);
	
	return periodicEdition;
	}
}
