package com.ellisonalves.thehotel.application.usecases.room;

import java.math.BigDecimal;
import java.util.Currency;

public record Money(
        BigDecimal amount,
        Currency currency) {
}
