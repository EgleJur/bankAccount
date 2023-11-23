package com.example.bankAccount.validationUnits;

import com.example.bankAccount.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidationUtilsPositive {
    private static final Logger logger = LogManager.getLogger(ValidationUtilsNotNull.class);

            public static void isValidByAmount(Double amount){
        if (amount.compareTo(0.00) == -1 || amount.compareTo(0.00) == 0) {
            logger.warn("Amount cannot be negative.");
            throw new ValidationException("Amount can not be negative", "amount",
                    "Negative amount", String.valueOf(amount));
        }
    }
}
