package com.mysmartfridge.infrastructure.persistence;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.mysmartfridge.domain.recipes.Quantity;
import com.mysmartfridge.domain.recipes.Quantity.Unit;

/**
 * Converter between a Role domain object and a String stored in databse.
 * 
 * This class is used by JPA when storing and retrieving a user in the database.
 */
@Converter(autoApply = true)
public class QuantityConverter implements AttributeConverter<Quantity, String> {

	@Override
	public String convertToDatabaseColumn(Quantity attribute) {
		
		return attribute.getValue()+"|"+attribute.getUnit().name();
	}

	@Override
	//TODO error handling
	public Quantity convertToEntityAttribute(String dbData) {
		
		String dbValue = dbData.split("\\|")[0];
		String dbUnit = dbData.split("\\|")[1];
		
		Unit unit = null;
		
		for (Unit u : Unit.class.getEnumConstants()) {
			if (u.name().equals(dbUnit))
				unit = u;
		}
		
		Double value = Double.parseDouble(dbValue);
		
		return new Quantity(value, unit);
	}

}
