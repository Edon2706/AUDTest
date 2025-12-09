package db.value;

/**
 * Diese Klasse repräsentiert einen booleschen Wert.
 * <p>
 * Diese Datei darf nicht verändert werden.
 *
 * @author aan, avh, mhe, tti
 */
public class BooleanValue extends Value {

    /**
     * Wert
     */
    private final boolean value;

    /**
     * Konstruktor
     *
     * @param value Wert
     */
    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean getAsBoolean() {
        return this.value;
    }

    @Override
    public String toString() {
        return Boolean.toString(this.value);
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof BooleanValue other)) {
            return false;
        }

        return this.value == other.value;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(this.value);
    }
}
