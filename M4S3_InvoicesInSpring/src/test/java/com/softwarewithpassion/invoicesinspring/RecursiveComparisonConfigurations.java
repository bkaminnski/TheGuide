package com.softwarewithpassion.invoicesinspring;

import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;

import java.math.BigDecimal;

import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

public class RecursiveComparisonConfigurations {

    public static RecursiveComparisonConfiguration comparingBigDecimalsWithComparator() {
        RecursiveComparisonConfiguration recursiveComparisonConfiguration = new RecursiveComparisonConfiguration();
        recursiveComparisonConfiguration.registerComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class);
        return recursiveComparisonConfiguration;
    }
}
