package db;

import util.Util;

import java.util.List;

/**
 * Diese Klasse repräsentiert eine Datenbank.
 * <p>
 * Eine Datenbank besitzt einen eindeutigen Bezeichner und verwaltet eine Reihe von Datenbanktabellen
 * ({@link DBTable}).
 *
 * @author aan, avh, mhe, tti, TODO: Namen ergänzen
 */
public final class DB {

    /**
     * Bezeichner dieser Datenbank
     */
    private final String id;


    /**
     * Konstruktor
     *
     * @param id Bezeichner dieser Datenbank
     *
     * @pre id != null
     * @pre id muss gemäß {@link Util#isValidIdentifier(String)} ein gültiger Bezeichner sein
     */
    public DB(String id) {
        assert id != null : "id is null";

        this.id = id;
        // TODO: Implementierung
    }

    /**
     * Liefert den Bezeichner dieser Datenbank.
     *
     * @return Bezeichner dieser Datenbank
     */
    public String getId() {
        // TODO: Implementierung
    }

    /**
     * Liefert die Anzahl der Tabellen in dieser Datenbank.
     *
     * @return Anzahl der Tabellen in dieser Datenbank
     */
    public int getNumOfTables() {
        // TODO: Implementierung
    }

    /**
     * Liefert die Tabelle mit dem übergebenen Tabellenbezeichner oder null, wenn es keine gibt.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Tabellen in dieser Datenbank und f(N) =
     * ??? TODO.
     *
     * @param tableId Tabellenbezeichner der Tabelle, die zurückgegeben werden soll
     *
     * @return Tabelle mit dem übergebenen Tabellenbezeichner oder null, wenn keine passende Tabelle existiert
     *
     * @pre tableId != null
     * @pre tableId muss gemäß {@link Util#isValidIdentifier(String)} ein gültiger Tabellenbezeichner sein
     */
    public DBTable getTable(String tableId) {
        assert tableId != null : "tableId is null";
        // TODO: Implementierung
    }

    /**
     * Liefert aller Tabellenbezeichner aus dieser Datenbank. Die Bezeichner sind dabei aufsteigend sortiert.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Tabellen in dieser Datenbank und f(N) =
     * ??? TODO.
     *
     * @return aufsteigend sortierte Liste der Tabellenbezeichner
     */
    public List<String> getTableIds() {
        // TODO: Implementierung
    }

    /**
     * Liefert aller Tabellenbezeichner dieser Datenbank zurück, die im übergebenen Bereich liegen. Das Ergebnis ist
     * aufsteigend (gemäß compareTo) nach den Tabellenbezeichnern sortiert.
     * <p>
     * Beispiel: Wenn die Datenbank Tabellen mit den Namen "Bestellung", "Tee_Kategorie", "Tee_Sorte" und "Verkauf"
     * enthält, liefert die Anfrage mit from="T" to="V" die Namen "Tee_Sorte" und "Tee_Kategorie".
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Tabellen in dieser Datenbank und f(N) =
     * ??? TODO.
     *
     * @param from Untere Grenze des Suchbereiches (inklusive)
     * @param to   Obere Grenze des Suchbereiches (exklusive)
     *
     * @return Alle Tabellenbezeichner in dem übergebenen Bereich
     *
     * @pre from != null
     * @pre to != null
     * @pre from muss gemäß {@link Util#isValidIdentifier(String)} ein gültiger Tabellenbezeichner sein
     * @pre to muss gemäß {@link Util#isValidIdentifier(String)} ein gültiger Tabellenbezeichner sein
     * @pre from muss gemäß {@link String#compareTo(String)} kleiner als to sein
     */
    public List<String> getTableIdsBetween(String from, String to) {
        assert from != null : "from is null";
        assert to != null : "to is null";
        // TODO: Implementierung
    }

    /**
     * Liefert die erste Tabelle zurück, bei der der Tabellenbezeichner mit dem übergebenen Präfix beginnt. Das Präfix
     * kann auch einem vollständigen Tabellenbezeichner entsprechen,
     * <p>
     * Beispiel: Wenn die Datenbanktabellen "Bestellung", "Tee_Kategorie" und "Tee_Sorte" enthält, liefert die Anfrage
     * nach "Tee_" die Tabelle mit dem Bezeichner "Tee_Kategorie".
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Tabellen in dieser Datenbank und f(N) =
     * ??? TODO.
     *
     * @param prefix Präfix des Tabellenbezeichners
     *
     * @return Tabelle, die einen Bezeichner mit dem übergebenen Präfix hat oder null, wenn keine passende Tabelle
     *         existiert
     *
     * @pre prefix != null
     * @pre prefix muss gemäß {@link Util#isValidIdentifier(String)} ein gültiges Präfix eines
     *         Tabellenbezeichners sein
     */
    public DBTable getFirstTableWithPrefix(String prefix) {
        assert prefix != null : "prefix is null";
        // TODO: Implementierung
    }

    /**
     * Gibt an, ob eine Tabelle mit dem übergebenen Tabellenbezeichner in dieser Datenbank existiert.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Tabellen in dieser Datenbank und f(N) =
     * ??? TODO.
     *
     * @param tableId Tabellenbezeichner der Tabelle, deren Existenz geprüft werden soll
     *
     * @return Angabe, ob eine Tabelle mit dem Tabellenbezeichner existiert
     *
     * @pre tableId != null
     * @pre tableId muss gemäß {@link Util#isValidIdentifier(String)} ein gültiger Tabellenbezeichner sein
     */
    public boolean hasTable(String tableId) {
        assert tableId != null : "tableId is null";
        // TODO: Implementierung
    }

    /**
     * Fügt die übergebene Tabelle in diese Datenbank ein.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Tabellen in dieser Datenbank und f(N) =
     * ??? TODO.
     *
     * @param table Tabelle, die eingefügt werden soll
     *
     * @pre table != null
     * @pre Es darf keine Tabelle mit dem Bezeichner der übergebenen Tabelle in dieser Datenbank existieren
     */
    public void addTable(DBTable table) {
        assert table != null : "table is null";
        // TODO: Implementierung
    }

    /**
     * Entfernt die Tabelle mit dem übergebenen Tabellenbezeichner aus dieser Datenbank.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Tabellen in dieser Datenbank und f(N) =
     * ??? TODO.
     *
     * @param tableId Tabellenbezeichner der zu entfernenden Tabelle
     *
     * @pre tableId != null
     * @pre tableId muss gemäß {@link Util#isValidIdentifier(String)} ein gültiger Tabellenbezeichner sein
     * @post Es darf keine Tabelle mit dem übergebenen Bezeichner in dieser Datenbank existieren
     */
    public void removeTable(String tableId) {
        assert tableId != null : "tableId is null";
        // TODO: Implementierung
    }

    /**
     * Entfernt alle Tabellen aus dieser Datenbank.
     */
    public void removeAllTables() {
        // TODO: Implementierung
    }

    /**
     * Liefert eine Übersicht über die gesamte Datenbank.
     * <p>
     * Die Stringrepräsentation besteht aus dem Namen dieser Datenbank sowie der Stringrepräsentation der enthaltenen
     * Tabellen aufsteigend sortiert nach ihren Tabellenbezeichnern.
     * <ul>
     * <li>In der ersten Zeile steht "Datenbankbezeichner: " gefolgt von dem Bezeichner dieser Datenbank.</li>
     * <li>Die zweite Zeile ist eine Leerzeile.</li>
     * <li>Anschließend folgen alle Tabellen aufsteigend sortiert nach ihren Bezeichnern.</li>
     * <li>Die Darstellung jeder Tabelle erfolgt dabei gemäß {@link DBTable#toString()}.</li>
     * <li>Nach jeder Tabelle folgt eine Leerzeile.</li>
     * </ul>
     * <p>
     * Es werden stets Unix-Zeilenumbrüche (\n) in der Ausgabe verwendet.
     * <p>
     * Beispiel für eine Datenbank mit zwei Tabellen:
     * <pre>
     *     Datenbankbezeichner: TassenFreudenDB
     *
     *     Tabellenbezeichner: Kategorie
     *     Primärschlüssel: ID
     *
     *     | ID | Beschreibung  | EnthaeltKoffein |
     *     |----|---------------|-----------------|
     *     | 1  | Grüner Tee    | true            |
     *     | 2  | Schwarzer Tee | true            |
     *     | 3  | Kräutertee    | false           |
     *
     *     Tabellenbezeichner: Teesorten
     *     Primärschlüssel: ID
     *
     *     | ID   | Name            | Herkunftsland  | KategorieID |
     *     |------|-----------------|----------------|-------------|
     *     | 1    | Sencha          | Japan          | 1           |
     *     | 16   | Darjeeling      | Indien         | 2           |
     *     | 111  | Earl Grey       | Großbritannien | 2           |
     *     | 2000 | Rooibos Vanille | Südafrika      | 3           |
     *
     * </pre>
     *
     * @return die Stringrepräsentation dieser Datenbank
     */
    @Override
    public String toString() {
        // TODO: Implementierung
    }
}
