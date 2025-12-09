package db;

import db.value.BooleanValue;
import db.value.DoubleValue;
import db.value.StringValue;
import db.value.Value;
import java.util.ArrayList;
import java.util.List;


/**
 * Diese Klasse stellt Hilfsmethoden fürs Testen zur Verfügung.
 * <p>
 * Diese Datei darf nicht verändert werden.
 *
 * @author aan, avh, mhe, tti
 */
public final class TestUtils {


    /**
     * Erstellt ein {@link Value}-Objekt mit dem übergebenen Wert.
     *
     * @param value Wert
     *
     * @return Value-Objekt
     *
     * @pre value != null
     */
    public static Value cV(Object value) {
        assert value != null : "value is null";

        final Value newValue;

        if (value instanceof String vString) {
            newValue = new StringValue(vString);
        } else if (value instanceof Integer vInteger) {
            newValue = new DoubleValue(vInteger);
        } else if (value instanceof Long vLong) {
            newValue = new DoubleValue(vLong);
        } else if (value instanceof Float vFloat) {
            newValue = new DoubleValue(vFloat);
        } else if (value instanceof Double vDouble) {
            newValue = new DoubleValue(vDouble);
        } else if (value instanceof Boolean vBoolean) {
            newValue = new BooleanValue(vBoolean);
        } else {
            throw new IllegalArgumentException("Unsupported value type " + value.getClass() + " for " + value);
        }

        return newValue;
    }

    /**
     * Erstellt eine Liste an {@link Value}-Objekten mit den übergebenen Werten.
     *
     * @param values Werte
     *
     * @return Liste an Value-Objekten
     *
     * @pre value != null
     */
    public static List<Value> cVs(Object... values) {
        assert values != null : "values is null";

        List<Value> result = new ArrayList<>();

        for (Object value : values) {
            result.add(cV(value));
        }
        return result;
    }
}
