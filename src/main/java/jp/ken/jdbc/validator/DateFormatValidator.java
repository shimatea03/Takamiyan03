package jp.ken.jdbc.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.ken.jdbc.annotation.DateFormat;

public class DateFormatValidator implements ConstraintValidator<DateFormat,String> {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

	@Override
	public void initialize(DateFormat constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			Date date = dateFormat.parse(value);
			String dateString	= dateFormat.format(date);
			return dateString.equals(value);
		} catch (ParseException e) {
			return false;

		}
	}
}
