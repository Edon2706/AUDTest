package db;

import util.Util;
import db.value.Value;

import java.util.List;
import java.util.Objects;

/**
 * Diese Klasse repräsentiert eine Datenbanktabelle.
 * <p>
 * Eine Tabelle hat einen eindeutigen Bezeichner, der die Tabelle mit {@link #equals(Object)}, {@link #hashCode()},
 * {@link #compareTo(DBTable)} vergleichbar macht. Außerdem hat sie eine feste Spaltenanzahl, die ebenso wie die
 * Bezeichner der einzelnen Spalten und deren Reihenfolge bei der Erzeugung festgelegt werden. Sie verfügt über eine
 * flexible Anzahl von Zeilen, in denen jeweils genau so viele Werte ({@link Value}) stehen wie es Spalten gibt.
 *
 * @author aan, avh, mhe, tti, TODO: Namen ergänzen
 */
public final class DBTable implements Comparable<DBTable> {

    /**
     * Bezeichner dieser Tabelle
     **/
    private final String id;


    /**
     * Konstruktor
     *
     * @param id              Bezeichner dieser Tabelle
     * @param primaryKeyColId Spaltenbezeichner des Primärschlüssels
     * @param colIds          Spaltenbezeichner, in der Reihenfolge, in der sie in dieser Tabelle stehen sollen
     *
     * @pre id != null
     * @pre primaryKeyColId != null
     * @pre colIds != null
     * @pre id muss gemäß {@link Util#isValidIdentifier(String)} ein gültiger Tabellenbezeichner sein
     * @pre primaryKeyColId muss in colIds enthalten sein
     * @pre Alle Bezeichner aus colIds müssen gemäß {@link Util#areOnlyUniqueIdentifiers(List)} eindeutig sein
     * @pre Alle Bezeichner aus colIds müssen gemäß {@link Util#areValidIdentifiers(List)} gültig sein
     */
    public DBTable(String id, String primaryKeyColId, List<String> colIds) {
        assert id != null : "id is null";
        assert primaryKeyColId != null : "primaryKeyColId is null";
        assert colIds != null : "colIds is null";

        this.id = id;
        // TODO: Implementierung
    }

    /**
     * Liefert den Bezeichner dieser Tabelle.
     *
     * @return Bezeichner dieser Tabelle
     */
    public String getId() {
        // TODO: Implementierung
    }

    /**
     * Liefert den Spaltenbezeichner des Primärschlüssels.
     *
     * @return Spaltenbezeichner des Primärschlüssels
     */
    public String getPrimaryKeyColumnId() {
        // TODO: Implementierung
    }

    /**
     * Liefert die Spaltenanzahl dieser Tabelle.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der Spaltenbezeichner dieser Tabelle und f(N) = ???
     * TODO.
     *
     * @return Spaltenanzahl dieser Tabelle
     */
    public int getNumOfColumns() {
        // TODO: Implementierung
    }

    /**
     * Liefert die Zeilenanzahl dieser Tabelle.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Zeilen in dieser Tabelle und f(N) = ???
     * TODO.
     *
     * @return Zeilenanzahl dieser Tabelle
     */
    public int getNumOfRows() {
        // TODO: Implementierung
    }

    /**
     * Liefert alle Spaltenbezeichner dieser Tabelle. Die Reihenfolge entspricht dabei der Reihenfolge der Spalten in
     * der Tabelle.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der Spaltenbezeichner dieser Tabelle und f(N) = ???
     * TODO.
     *
     * @return Spaltenbezeichner dieser Tabelle
     */
    public List<String> getColumnIds() {
        // TODO: Implementierung
    }

    /**
     * Liefert die Zeile mit dem übergebenen Primärschlüssel.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Zeilen in dieser Tabelle und f(N) = ???
     * TODO.
     *
     * @param primaryKey Primärschlüssel
     *
     * @return Zeile mit dem übergebenen Primärschlüssel oder null, wenn der Primärschlüssel nicht in dieser Tabelle
     *         enthalten ist
     *
     * @pre primaryKey != null
     */
    public List<Value> getRowByPrimaryKey(Value primaryKey) {
        assert primaryKey != null : "primaryKey is null";
        // TODO: Implementierung
    }

    /**
     * Liefert den Wert in der Zeile mit dem übergebenen Primärschlüssel, der in der Spalte mit dem übergebenen
     * Spaltenbezeichner steht.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Zeilen in dieser Tabelle und f(N) = ???
     * TODO.
     *
     * @param primaryKey Primärschlüssel
     * @param colId      Spaltenbezeichner
     *
     * @return Wert der Zelle oder null, wenn der Primärschlüssel nicht in dieser Tabelle enthalten ist
     *
     * @pre primaryKey != null
     * @pre colId != null
     * @pre colId muss ein Spaltenbezeichner dieser Tabelle sein
     * @pre colId muss gemäß {@link Util#isValidIdentifier(String)} ein gültiger Spaltenbezeichner sein
     */
    public Value getValueByPrimaryKey(Value primaryKey, String colId) {
        assert primaryKey != null : "primaryKey is null";
        assert colId != null : "colId is null";
        // TODO: Implementierung
    }

    /**
     * Prüft, ob diese Tabelle eine Spalte mit dem übergebenen Spaltenbezeichner hat.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der Spaltenbezeichner dieser Tabelle und f(N) = ???
     * TODO.
     *
     * @param colId Spaltenbezeichner, der geprüft wird
     *
     * @return Angabe, ob diese Tabelle eine Spalte mit dem übergebenen Spaltenbezeichner hat
     *
     * @pre colId != null
     * @pre colId muss gemäß {@link Util#isValidIdentifier(String)} ein gültiger Spaltenbezeichner sein
     */
    public boolean hasColumn(String colId) {
        assert colId != null : "colId is null";
        // TODO: Implementierung
    }

    /**
     * Prüft, ob mindestens ein Spaltenbezeichner aus der übergebenen Liste ein Spaltenbezeichner dieser Tabelle ist.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der Spaltenbezeichner der übergebenen Liste und f(N) =
     * ??? TODO.
     *
     * @param colIds Spaltenbezeichner, die geprüft werden
     *
     * @return Angabe, ob mindestens ein Element aus colIds vorhanden ist. false, wenn colIds leer ist.
     *
     * @pre colIds != null
     */
    public boolean hasAnyColumn(List<String> colIds) {
        assert colIds != null : "colIds is null";
        // TODO: Implementierung
    }

    /**
     * Prüft, ob alle Spaltenbezeichner aus der übergebenen Liste Spaltenbezeichner dieser Tabelle sind.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der Spaltenbezeichner dieser Tabelle und f(N) = ???
     * TODO.
     *
     * @param colIds Spaltenbezeichner, die geprüft werden
     *
     * @return Angabe, ob alle Elemente aus colIds vorhanden sind. true, wenn colIds leer ist.
     *
     * @pre colIds != null
     */
    public boolean hasAllColumns(List<String> colIds) {
        assert colIds != null : "colIds is null";
        // TODO: Implementierung
    }

    /**
     * Hängt die Werte der übergebenen Liste in der angegebenen Reihenfolge als neue Zeile in diese Tabelle an. Sofern
     * bereits eine Zeile in dieser Tabelle existiert, die im Primärschlüssel identisch zu der neuen Zeile ist, passiert
     * nichts.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Zeilen in dieser Tabelle und f(N) = ???
     * TODO.
     *
     * @param row Werte, für die neue Zeile
     *
     * @return Diese Tabelle
     *
     * @pre row != null
     * @pre Anzahl Werte von row muss der Spaltenanzahl dieser Tabelle entsprechen
     */
    public DBTable appendRow(List<Value> row) {
        assert row != null : "row is null";
        // TODO: Implementierung
    }

    /**
     * Entfernt alle Zeilen aus dieser Tabelle, bei denen die übergebene Bedingung erfüllt ist.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Zeilen in dieser Tabelle und f(N) = ???
     * TODO.
     *
     * @param whereParam Bedingung
     *
     * @return Diese Tabelle
     *
     * @pre whereParam != null
     * @pre whereParam.colId() muss ein Spaltenbezeichner dieser Tabelle sein
     */
    public DBTable removeRows(WhereParameter whereParam) {
        assert whereParam != null : "whereParam is null";
        // TODO: Implementierung
    }

    /**
     * Entfernt alle Zeilen aus dieser Tabelle.
     *
     * @post Diese Tabelle enthält keine Zeilen
     */
    public void removeAllRows() {
        // TODO: Implementierung
    }

    /**
     * Erstellt eine neue Tabelle mit den übergebenen Spaltenbezeichnern in gegebener Reihenfolge.
     * <p>
     * Es werden nur Zeilen in die neue Tabelle übernommen, bei denen mindestens eine der übergebenen Bedingungen
     * erfüllt ist. Wenn eine leere Liste an Bedingungen übergeben wurde, enthält die neue Tabelle alle Zeilen. Wenn
     * keine Spaltenbezeichner übergeben wurden, entspricht dies einem "SELECT *", es werden also alle Spalten
     * übernommen.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der Spaltenbezeichner dieser Tabelle und f(N) = ???
     * TODO.
     *
     * @param colIds      Spaltenbezeichner der Spalten, die selektiert werden sollen oder null, wenn alle Spalten
     *                    selektiert werden sollen
     * @param whereParams Bedingungen
     * @param newTableId  Bezeichner der Tabelle, die erzeugt wird
     *
     * @return Tabelle mit allen selektierten Spalten
     *
     * @pre newTableId != null
     * @pre whereParams != null
     * @pre newTableId muss gemäß {@link Util#isValidIdentifier(String)} ein gültiger Tabellenbezeichner sein
     * @pre Wenn colIds nicht null, müssen alle Bezeichner aus colIds gemäß
     *         {@link Util#areOnlyUniqueIdentifiers(List)} eindeutig sein
     * @pre Wenn colIds nicht null, müssen alle Bezeichner aus colIds Spaltenbezeichner dieser Tabelle sein
     * @pre Wenn colIds nicht null, muss der Spaltenbezeichner des Primärschlüssels enthalten sein
     * @pre Alle Bezeichner aus whereParams müssen Spaltenbezeichner dieser Tabelle sein
     */
    public DBTable select(List<String> colIds, List<WhereParameter> whereParams, String newTableId) {
        assert whereParams != null : "whereParams is null";
        assert newTableId != null : "newTableId is null";
        // TODO: Implementierung
    }

    /**
     * Aktualisiert Werte in der Spalte mit dem übergebenen Spaltenbezeichner.
     * <p>
     * Der Wert wird in allen Zeilen aktualisiert, in denen alle übergebenen Bedingungen erfüllt sind. Wenn eine leere
     * Liste an Bedingungen übergeben wurde, wird der Wert in allen Zeilen aktualisiert.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der Spaltenbezeichner dieser Tabelle und f(N) = ???
     * TODO.
     *
     * @param colId       Spaltenbezeichner der Spalte, in der der Wert aktualisiert werden soll
     * @param newValue    Neuer Wert
     * @param whereParams Bedingungen
     *
     * @return Diese Tabelle
     *
     * @pre colId != null
     * @pre newValue != null
     * @pre whereParams != null
     * @pre colId muss ein Spaltenbezeichner dieser Tabelle sein
     * @pre colId darf nicht der Spaltenbezeichner des Primärschlüssels sein
     * @pre Alle Bezeichner aus whereParams müssen Spaltenbezeichner dieser Tabelle sein
     */
    public DBTable update(String colId, Value newValue, List<WhereParameter> whereParams) {
        assert colId != null : "colId is null";
        assert newValue != null : "newValue is null";
        assert whereParams != null : "whereParams is null";
        // TODO: Implementierung
    }


    /**
     * Führt eine join-Operation mit dieser und der übergebenen Tabelle durch.
     * <p>
     * Die beiden Tabellen werden anhand des übergebenen Spaltenbezeichners zusammengefügt, welcher den Fremdschlüssel
     * der übergebenen Tabelle in dieser Tabelle darstellt. In der neuen Tabelle befinden sich alle Zeilen, in denen die
     * Werte (an der Position des Fremdschlüssels in dieser Tabelle und des Primärschlüssels in der Übergebenen)
     * übereinstimmen. Es bleibt die Reihenfolge der Zeilen aus dieser Tabelle erhalten.
     * <p>
     * Die neue Tabelle enthält alle Spaltenbezeichner beider Tabellen, außer die zwei Bezeichner, nach denen gejoint
     * wird: zunächst die Spalten dieser Tabelle und danach die Spalten der übergebenen Tabelle. Sie sind nach folgendem
     * Schema aufgebaut: Der Bezeichner beginnt mit dem Namen der Ursprungstabelle gefolgt von einem Unterstrich und dem
     * vorhandenen Spaltenbezeichner. Der neue Spaltenbezeichner des Primärschlüssels ergibt sich analog dazu aus dem
     * Namen dieser Tabelle, einem Unterstrich und dem Spaltenbezeichner des Primärschlüssels dieser Tabelle.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der Zeilen der übergebenen Tabelle und f(N) = ???
     * TODO.
     *
     * @param other      Tabelle, die mit dieser Tabelle gejoint werden soll
     * @param fkColId    Spaltenbezeichner des Fremdschlüssels dieser Tabelle
     * @param newTableId Tabellenbezeichner der Tabelle, die erzeugt wird
     *
     * @return neu erzeugte Tabelle
     *
     * @pre other != null
     * @pre fkColId != null
     * @pre newTableId != null
     * @pre fkColId darf nicht der Spaltenbezeichner des Primärschlüssels dieser Tabelle sein
     * @pre fkColId muss ein Spaltenbezeichner dieser Tabelle sein
     * @pre newTableId muss gemäß {@link Util#isValidIdentifier(String)} ein gültiger Tabellenbezeichner sein
     */
    public DBTable equijoin(DBTable other, String fkColId, String newTableId) {
        assert other != null : "other is null";
        assert fkColId != null : "fkColId is null";
        assert newTableId != null : "newTableId is null";

        // TODO: Implementierung
    }

    /**
     * Liefert eine tabellarische Übersicht dieser Tabelle.
     * <p>
     * Die Stringrepräsentation besteht aus dem Tabellenbezeichner, dem Spaltenbezeichner des Primärschlüssels sowie
     * einer tabellarischen Übersicht des Tabelleninhalts. Dabei erfolgt die Repräsentation der Spalten blockweise in
     * der Reihenfolge, wie sie in der Tabelle stehen.
     * <ul>
     * <li>In der ersten Zeile steht "Tabellenbezeichner: " gefolgt von dem Bezeichner der Tabelle.</li>
     * <li>In der zweiten Zeile steht "Primärschlüssel: " gefolgt von dem Spaltenbezeichner des Primärschlüssels.</li>
     * <li>Die dritte Zeile ist eine Leerzeile.</li>
     * <li>Anschließend folgt der Tabelleninhalt nach folgendem Schema:
     * <ul>
     * <li>Ein Feld hat immer die Breite des längsten Eintrages, der in der jeweiligen Spalte vorkommt.
     * Dabei ist der Inhalt </li>
     * <li>Felder sind durch " | " (Leerzeichen, Pipe, Leerzeichen) voneinander getrennt. Es befinden sich zudem eine
     * Pipe und ein Leerzeichen am Anfang jeder Zeile. Auf die letzte Pipe in einer Zeile folgt kein Leerzeichen.</li>
     * <li>In der ersten Zeile stehen (entsprechend formatiert) die Spaltenbezeichner der Tabelle.</li>
     * <li>Es folgt eine Zeile, die die Trennung von Kopfzeile und Daten symbolisiert. Die Felder dieser Zeile sind
     * komplett mit Minuszeichen gefüllt (keine Leerzeichen an den Pipes).</li>
     * <li>Danach folgen die Zeilen der Tabelle in der Reihenfolge, in der sie gespeichert sind (in
     * entsprechender blockweiser Formatierung). Wenn es keine Zeilen in der Tabelle gibt, werden nur die ersten beiden
     * Zeilen ausgegeben.</li>
     * <li>Alle Zeilenumbrüche (Unix, DOS, macOS) innerhalb der Felder der Tabelle werden für eine bessere Lesbarkeit
     * jeweils durch ein einzelnes Leerzeichen ersetzt.</li>
     * </ul>
     * </li>
     * <li>Nach Ausgabe der Tabelle folgt ein Zeilenumbruch.</li>
     * </ul>
     * <p>
     * Es werden stets Unix-Zeilenumbrüche (\n) in der Ausgabe verwendet.
     * <p>
     * Beispiel für eine Tabelle mit drei Spalten:
     * <pre>
     *     Tabellenbezeichner: Tee
     *     Primärschlüssel: ID
     *
     *     | ID   | Name            | Herkunftsland  | KategorieID |
     *     |------|-----------------|----------------|-------------|
     *     | 1    | Sencha          | Japan          | 1           |
     *     | 16   | Darjeeling      | Indien         | 2           |
     *     | 111  | Earl Grey       | Großbritannien | 2           |
     *     | 2000 | Rooibos Vanille | Südafrika      | 3           |
     * </pre>
     *
     * @return Stringrepräsentation dieser Tabelle
     */
    @Override
    public String toString() {
        // TODO: Implementierung
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DBTable other)) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public int compareTo(DBTable o) {
        return this.id.compareTo(o.id);
    }
}
