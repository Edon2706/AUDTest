package util;

import java.util.HashSet;
import java.util.List;

/**
 * Diese Klasse stellt Hilfsmethoden für die Prüfung von Zeichenketten zur Verfügung.
 * <p>
 * Diese Datei darf nicht verändert werden.
 *
 * @author aan, avh, mhe, tti
 */
public final class Util {

    /**
     * Versteckter Konstruktor
     */
    private Util() {
    }

    /**
     * Prüft, ob die übergebene Zeichenkette ein gültiger Bezeichner ist. Ein gültiger Bezeichner besteht aus einem
     * Zeichen aus der Menge [a-zA-Z] gefolgt von einer beliebigen Anzahl von Zeichen aus der Menge [a-zA-Z0-9_].
     *
     * @param str Zeichenkette, die geprüft wird
     *
     * @return Angabe, ob die Zeichenkette ein gültiger Bezeichner ist
     *
     * @pre str != null
     */
    public static boolean isValidIdentifier(String str) {
        assert str != null : "str is null";
        return str.matches("^[a-zA-Z][a-zA-Z0-9_]*$");
    }

    /**
     * Prüft, ob in der übergebenen Liste nur gültige Bezeichner sind. Ein gültiger Bezeichner besteht aus einem Zeichen
     * aus der Menge [a-zA-Z] gefolgt von einer beliebigen Anzahl von Zeichen aus der Menge [a-zA-Z0-9_].
     *
     * @param strs Bezeichner, die geprüft werden
     *
     * @return Angabe, ob die übergebene Liste nur gültige Bezeichner enthält
     *
     * @pre strs != null
     */
    public static boolean areValidIdentifiers(List<String> strs) {
        assert strs != null : "strs is null";
        for (String str : strs) {
            if (!isValidIdentifier(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prüft, ob alle Zeichenketten der übergebenen Liste eindeutig sind. Sollte mindestens eine Zeichenkette doppelt
     * vorkommen, so gibt die Methode false zurück.
     *
     * @param strs Bezeichner, die geprüft werden
     *
     * @return Angabe, ob alle Zeichenketten einmalig sind
     *
     * @pre strs != null
     */
    public static boolean areOnlyUniqueIdentifiers(List<String> strs) {
        assert strs != null : "strs is null";
        return new HashSet<>(strs).size() == strs.size();
    }
}
