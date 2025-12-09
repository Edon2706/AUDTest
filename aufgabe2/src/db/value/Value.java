package db.value;

/**
 * Diese Klasse repräsentiert einen Wert.
 * <p>
 * Diese Datei darf nicht verändert werden.
 *
 * @author aan, avh, mhe, tti
 */
public abstract class Value {

    /**
     * Versteckter Konstruktor
     */
    Value() {
    }

    /**
     * Liefert den Wert als Zeichenkette.
     *
     * @return Wert als Zeichenkette
     */
    public String getAsString() {
        throw new IllegalStateException("Not a StringValue");
    }

    /**
     * Liefert den Wert als Gleitkommazahl.
     *
     * @return Wert als Gleitkommazahl
     */
    public double getAsDouble() {
        throw new IllegalStateException("Not a DoubleValue");
    }

    /**
     * Liefert den Wert als booleschen Wert.
     *
     * @return Wert als booleschen Wert
     */
    public boolean getAsBoolean() {
        throw new IllegalStateException("Not a BooleanValue");
    }

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}
