package db.value;

import java.util.Objects;

/**
 * Diese Klasse repräsentiert einen Zeichenkettenwert.
 * <p>
 * Diese Datei darf nicht verändert werden.
 *
 * @author aan, avh, mhe, tti
 */
public class StringValue extends Value {

    /**
     * Wert
     */
    private final String value;

    /**
     * Konstruktor
     *
     * @param value Wert
     *
     * @pre value != null
     */
    public StringValue(String value) {
        assert value != null : "value is null";
        this.value = value;
    }

    @Override
    public String getAsString() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof StringValue other)) {
            return false;
        }

        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }
}
