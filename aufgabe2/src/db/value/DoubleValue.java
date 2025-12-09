package db.value;

import java.util.Objects;

/**
 * Diese Klasse repräsentiert einen Gleitkommawert.
 * <p>
 * Diese Datei darf nicht verändert werden.
 *
 * @author aan, avh, mhe, tti
 */
public class DoubleValue extends Value {

    /**
     * Wert
     */
    private final double value;

    /**
     * Konstruktor
     *
     * @param value Wert
     */
    public DoubleValue(double value) {
        this.value = value;
    }

    @Override
    public double getAsDouble() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value % 1L == 0 ? Long.toString((long) this.value) : Double.toString(this.value);
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof DoubleValue other)) {
            return false;
        }

        return this.value == other.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }
}
