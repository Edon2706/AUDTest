package db;

import db.value.Value;

import java.util.function.Predicate;

/**
 * Dieser Record repräsentiert einen Ausdruck für die WHERE-Bedingung bei Anfragen in einer Datenbanktabelle
 * ({@link DBTable}).
 * <p>
 * Ein Ausdruck besitzt einen Spaltenbezeichner und eine Bedingung ({@link Predicate}), die für Werte der Spalte mit
 * diesem Spaltenbezeichner erfüllt sein muss.
 * <p>
 * Diese Datei darf nicht verändert werden.
 *
 * @param colId     Spaltenbezeichner in einer Datenbanktabelle
 * @param predicate Bedingung für Werte in der Spalte mit dem Bezeichner
 *
 * @author aan, avh, mhe, tti
 */
public record WhereParameter(String colId, Predicate<Value> predicate) {

    /**
     * Konstruktor.
     *
     * @param colId     Spaltenbezeichner in einer Datenbanktabelle
     * @param predicate Bedingung für Werte in der Spalte mit dem Bezeichner
     *
     * @pre colId != null
     * @pre predicate != null
     */
    public WhereParameter {
        assert colId != null : "colId is null";
        assert predicate != null : "predicate is null";
    }
}
