package db;

import util.Util;
import db.value.Value;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
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
     * Spaltenbezeichner des Primärschlüssels
     */
    private final String primaryKeyColId;

    /**
     * Spaltenbezeichner in der Reihenfolge der Tabelle
     */
    private final List<String> columnIds;

    /**
     * Zuordnung von Spaltenbezeichnern zu ihren Indizes
     */
    private final Map<String, Integer> columnIndexMap;

    /**
     * Index des Primärschlüssels
     */
    private final int primaryKeyIndex;

    /**
     * Zeilen dieser Tabelle, Schlüssel ist der Primärschlüsselwert
     */
    private final Map<Value, List<Value>> rows;


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

        assert Util.isValidIdentifier(id) : "id is invalid";
        assert Util.areValidIdentifiers(colIds) : "colIds contain invalid identifiers";
        assert Util.areOnlyUniqueIdentifiers(colIds) : "colIds contain duplicates";
        assert colIds.contains(primaryKeyColId) : "primary key not part of colIds";

        this.id = id;
        this.primaryKeyColId = primaryKeyColId;
        this.columnIds = new ArrayList<>(colIds);
        this.columnIndexMap = new HashMap<>();
        for (int i = 0; i < this.columnIds.size(); i++) {
            this.columnIndexMap.put(this.columnIds.get(i), i);
        }
        this.primaryKeyIndex = this.columnIndexMap.get(primaryKeyColId);
        this.rows = new LinkedHashMap<>();
    }

    /**
     * Liefert den Bezeichner dieser Tabelle.
     *
     * @return Bezeichner dieser Tabelle
     */
    public String getId() {
        return this.id;
    }

    /**
     * Liefert den Spaltenbezeichner des Primärschlüssels.
     *
     * @return Spaltenbezeichner des Primärschlüssels
     */
    public String getPrimaryKeyColumnId() {
        return this.primaryKeyColId;
    }

    /**
     * Liefert die Spaltenanzahl dieser Tabelle.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der Spaltenbezeichner dieser Tabelle und f(N) = 1.
     *
     * @return Spaltenanzahl dieser Tabelle
     */
    public int getNumOfColumns() {
        return this.columnIds.size();
    }

    /**
     * Liefert die Zeilenanzahl dieser Tabelle.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Zeilen in dieser Tabelle und f(N) = 1.
     *
     * @return Zeilenanzahl dieser Tabelle
     */
    public int getNumOfRows() {
        return this.rows.size();
    }

    /**
     * Liefert alle Spaltenbezeichner dieser Tabelle. Die Reihenfolge entspricht dabei der Reihenfolge der Spalten in
     * der Tabelle.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der Spaltenbezeichner dieser Tabelle und f(N) = N.
     *
     * @return Spaltenbezeichner dieser Tabelle
     */
    public List<String> getColumnIds() {
        return new ArrayList<>(this.columnIds);
    }

    /**
     * Liefert die Zeile mit dem übergebenen Primärschlüssel.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Zeilen in dieser Tabelle und f(N) = 1.
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
        return this.rows.get(primaryKey);
    }

    /**
     * Liefert den Wert in der Zeile mit dem übergebenen Primärschlüssel, der in der Spalte mit dem übergebenen
     * Spaltenbezeichner steht.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Zeilen in dieser Tabelle und f(N) = 1.
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
        Integer index = this.columnIndexMap.get(colId);
        assert index != null : "colId not part of this table";

        List<Value> row = this.getRowByPrimaryKey(primaryKey);
        if (row == null) {
            return null;
        }
        return row.get(index);
    }

    /**
     * Prüft, ob diese Tabelle eine Spalte mit dem übergebenen Spaltenbezeichner hat.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der Spaltenbezeichner dieser Tabelle und f(N) = 1.
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
        assert Util.isValidIdentifier(colId) : "invalid colId";
        return this.columnIndexMap.containsKey(colId);
    }

    /**
     * Prüft, ob mindestens ein Spaltenbezeichner aus der übergebenen Liste ein Spaltenbezeichner dieser Tabelle ist.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der Spaltenbezeichner der übergebenen Liste und f(N) =
     * N.
     *
     * @param colIds Spaltenbezeichner, die geprüft werden
     *
     * @return Angabe, ob mindestens ein Element aus colIds vorhanden ist. false, wenn colIds leer ist.
     *
     * @pre colIds != null
     */
    public boolean hasAnyColumn(List<String> colIds) {
        assert colIds != null : "colIds is null";
        for (String colId : colIds) {
            if (this.columnIndexMap.containsKey(colId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prüft, ob alle Spaltenbezeichner aus der übergebenen Liste Spaltenbezeichner dieser Tabelle sind.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der Spaltenbezeichner dieser Tabelle und f(N) = N.
     *
     * @param colIds Spaltenbezeichner, die geprüft werden
     *
     * @return Angabe, ob alle Elemente aus colIds vorhanden sind. true, wenn colIds leer ist.
     *
     * @pre colIds != null
     */
    public boolean hasAllColumns(List<String> colIds) {
        assert colIds != null : "colIds is null";
        for (String colId : colIds) {
            if (!this.columnIndexMap.containsKey(colId)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Hängt die Werte der übergebenen Liste in der angegebenen Reihenfolge als neue Zeile in diese Tabelle an. Sofern
     * bereits eine Zeile in dieser Tabelle existiert, die im Primärschlüssel identisch zu der neuen Zeile ist, passiert
     * nichts.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Zeilen in dieser Tabelle und f(N) = 1.
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
        assert row.size() == this.getNumOfColumns() : "row size does not match column count";

        Value primaryKey = row.get(this.primaryKeyIndex);
        if (this.rows.containsKey(primaryKey)) {
            return this;
        }

        List<Value> storedRow = new ArrayList<>(row);
        this.rows.put(primaryKey, storedRow);
        return this;
    }

    /**
     * Entfernt alle Zeilen aus dieser Tabelle, bei denen die übergebene Bedingung erfüllt ist.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der vorhandenen Zeilen in dieser Tabelle und f(N) = N.
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
        assert this.columnIndexMap.containsKey(whereParam.colId()) : "colId not part of table";

        int colIndex = this.columnIndexMap.get(whereParam.colId());
        var iterator = this.rows.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Value, List<Value>> entry = iterator.next();
            Value value = entry.getValue().get(colIndex);
            if (whereParam.predicate().test(value)) {
                iterator.remove();
            }
        }
        return this;
    }

    /**
     * Entfernt alle Zeilen aus dieser Tabelle.
     *
     * @post Diese Tabelle enthält keine Zeilen
     */
    public void removeAllRows() {
        this.rows.clear();
    }

    /**
     * Erstellt eine neue Tabelle mit den übergebenen Spaltenbezeichnern in gegebener Reihenfolge.
     * <p>
     * Es werden nur Zeilen in die neue Tabelle übernommen, bei denen mindestens eine der übergebenen Bedingungen
     * erfüllt ist. Wenn eine leere Liste an Bedingungen übergeben wurde, enthält die neue Tabelle alle Zeilen. Wenn
     * keine Spaltenbezeichner übergeben wurden, entspricht dies einem "SELECT *", es werden also alle Spalten
     * übernommen.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der Spaltenbezeichner dieser Tabelle und f(N) =
     * N + R, wobei R = Anzahl der vorhandenen Zeilen in dieser Tabelle.
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
        assert Util.isValidIdentifier(newTableId) : "newTableId invalid";

        List<String> selectedCols;
        if (colIds == null) {
            selectedCols = new ArrayList<>(this.columnIds);
        } else {
            assert Util.areOnlyUniqueIdentifiers(colIds) : "colIds not unique";
            assert this.hasAllColumns(colIds) : "colIds not part of table";
            assert colIds.contains(this.primaryKeyColId) : "primary key missing";
            selectedCols = new ArrayList<>(colIds);
        }

        DBTable newTable = new DBTable(newTableId, this.primaryKeyColId, selectedCols);

        boolean hasConditions = !whereParams.isEmpty();
        for (Map.Entry<Value, List<Value>> entry : this.rows.entrySet()) {
            List<Value> row = entry.getValue();

            boolean matches = !hasConditions;
            if (hasConditions) {
                for (WhereParameter where : whereParams) {
                    assert this.columnIndexMap.containsKey(where.colId()) : "where column missing";
                    Value value = row.get(this.columnIndexMap.get(where.colId()));
                    if (where.predicate().test(value)) {
                        matches = true;
                        break;
                    }
                }
            }

            if (matches) {
                List<Value> newRow = new ArrayList<>();
                for (String colId : selectedCols) {
                    newRow.add(row.get(this.columnIndexMap.get(colId)));
                }
                newTable.appendRow(newRow);
            }
        }

        return newTable;
    }

    /**
     * Aktualisiert Werte in der Spalte mit dem übergebenen Spaltenbezeichner.
     * <p>
     * Der Wert wird in allen Zeilen aktualisiert, in denen alle übergebenen Bedingungen erfüllt sind. Wenn eine leere
     * Liste an Bedingungen übergeben wurde, wird der Wert in allen Zeilen aktualisiert.
     * <p>
     * Diese Methode arbeitet in O(f(N)), dabei ist N = Anzahl der Spaltenbezeichner dieser Tabelle und f(N) =
     * N + R, wobei R = Anzahl der vorhandenen Zeilen in dieser Tabelle.
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
        assert this.columnIndexMap.containsKey(colId) : "colId not part of table";
        assert !this.primaryKeyColId.equals(colId) : "colId is primary key";

        int targetIndex = this.columnIndexMap.get(colId);
        boolean hasConditions = !whereParams.isEmpty();

        for (Map.Entry<Value, List<Value>> entry : this.rows.entrySet()) {
            List<Value> row = entry.getValue();

            boolean matches = true;
            if (hasConditions) {
                for (WhereParameter where : whereParams) {
                    assert this.columnIndexMap.containsKey(where.colId()) : "where column missing";
                    Value value = row.get(this.columnIndexMap.get(where.colId()));
                    if (!where.predicate().test(value)) {
                        matches = false;
                        break;
                    }
                }
            }

            if (matches) {
                row.set(targetIndex, newValue);
            }
        }

        return this;
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
     * R, wobei R = Anzahl der Zeilen dieser Tabelle.
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

        assert !fkColId.equals(this.primaryKeyColId) : "fkColId is primary key";
        assert this.columnIndexMap.containsKey(fkColId) : "fkColId missing";
        assert Util.isValidIdentifier(newTableId) : "newTableId invalid";

        String newPrimaryKey = this.id + "_" + this.primaryKeyColId;

        List<String> newColIds = new ArrayList<>();
        for (String colId : this.columnIds) {
            if (!colId.equals(fkColId)) {
                newColIds.add(this.id + "_" + colId);
            }
        }
        for (String colId : other.columnIds) {
            if (!colId.equals(other.primaryKeyColId)) {
                newColIds.add(other.id + "_" + colId);
            }
        }

        DBTable newTable = new DBTable(newTableId, newPrimaryKey, newColIds);

        int fkIndex = this.columnIndexMap.get(fkColId);
        int otherPkIndex = other.columnIndexMap.get(other.primaryKeyColId);

        for (Map.Entry<Value, List<Value>> entry : this.rows.entrySet()) {
            List<Value> thisRow = entry.getValue();
            Value fkValue = thisRow.get(fkIndex);
            List<Value> otherRow = other.getRowByPrimaryKey(fkValue);
            if (otherRow == null) {
                continue;
            }

            List<Value> newRow = new ArrayList<>();
            for (int i = 0; i < this.columnIds.size(); i++) {
                if (i == fkIndex) {
                    continue;
                }
                newRow.add(thisRow.get(i));
            }
            for (int i = 0; i < other.columnIds.size(); i++) {
                if (i == otherPkIndex) {
                    continue;
                }
                newRow.add(otherRow.get(i));
            }

            newTable.appendRow(newRow);
        }

        return newTable;
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
        StringBuilder builder = new StringBuilder();
        builder.append("Tabellenbezeichner: ").append(this.id).append('\n');
        builder.append("Primärschlüssel: ").append(this.primaryKeyColId).append('\n');
        builder.append('\n');

        int columnCount = this.columnIds.size();
        int[] widths = new int[columnCount];
        for (int i = 0; i < columnCount; i++) {
            widths[i] = this.columnIds.get(i).length();
        }

        for (List<Value> row : this.rows.values()) {
            for (int i = 0; i < columnCount; i++) {
                String valueStr = sanitizeValue(row.get(i).toString());
                if (valueStr.length() > widths[i]) {
                    widths[i] = valueStr.length();
                }
            }
        }

        builder.append(formatRow(this.columnIds, widths)).append('\n');
        builder.append(formatSeparator(widths)).append('\n');

        for (List<Value> row : this.rows.values()) {
            List<String> values = new ArrayList<>();
            for (Value value : row) {
                values.add(sanitizeValue(value.toString()));
            }
            builder.append(formatRow(values, widths)).append('\n');
        }

        return builder.toString();
    }

    private static String sanitizeValue(String value) {
        return value.replace("\r", " ").replace("\n", " ");
    }

    private static String formatRow(List<String> values, int[] widths) {
        StringBuilder rowBuilder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            rowBuilder.append("| ");
            rowBuilder.append(padRight(values.get(i), widths[i]));
            rowBuilder.append(' ');
        }
        rowBuilder.append('|');
        return rowBuilder.toString();
    }

    private static String formatSeparator(int[] widths) {
        StringBuilder builder = new StringBuilder();
        for (int width : widths) {
            builder.append('|');
            for (int i = 0; i < width + 2; i++) {
                builder.append('-');
            }
        }
        builder.append('|');
        return builder.toString();
    }

    private static String padRight(String value, int width) {
        StringBuilder builder = new StringBuilder(value);
        while (builder.length() < width) {
            builder.append(' ');
        }
        return builder.toString();
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
