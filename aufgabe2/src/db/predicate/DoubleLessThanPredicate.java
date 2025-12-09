package db.predicate;

import db.value.Value;

import java.util.function.Predicate;

/**
 * Prädikat, welches prüft, ob der bei der {@link Predicate#test(Object)} übergebene Wert kleiner als der Vergleichswert
 * ist.
 * <p>
 * Diese Datei darf nicht verändert werden.
 *
 * @author aan, avh, mhe, tti
 */
public class DoubleLessThanPredicate implements Predicate<Value> {

    /**
     * Vergleichswert
     */
    private final double compareValue;

    /**
     * Konstruktor
     *
     * @param compareValue Vergleichswert
     */
    public DoubleLessThanPredicate(double compareValue) {
        this.compareValue = compareValue;
    }

    @Override
    public boolean test(Value value) {
        return value.getAsDouble() < compareValue;
    }
}

