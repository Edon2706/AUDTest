package db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.function.Predicate;

import static db.TestUtils.cV;
import static db.TestUtils.cVs;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Beispieltests
 *
 * @author aan, avh, mhe, tti
 *
 */
@Timeout(10)
public class ExampleTests {

    @Test
    public void test_DBTableToString() {

        final DBTable table = new DBTable("Teesorten", "ID", List.of("ID", "Name", "Herkunftsland", "KategorieID"));

        String expected = """
                Tabellenbezeichner: Teesorten
                Primärschlüssel: ID
                
                | ID | Name | Herkunftsland | KategorieID |
                |----|------|---------------|-------------|
                """;

        assertEquals(expected, table.toString(), "table.toString, no rows");

        table.appendRow(cVs(1, "Sencha", "Japan", 1));
        table.appendRow(cVs(16, "Darjeeling", "Indien", 2));
        table.appendRow(cVs(111, "Earl Grey", "Großbritannien", 2));
        table.appendRow(cVs(2000, "Rooibos Vanille", "Südafrika", 3));

        expected = """
                Tabellenbezeichner: Teesorten
                Primärschlüssel: ID
                
                | ID   | Name            | Herkunftsland  | KategorieID |
                |------|-----------------|----------------|-------------|
                | 1    | Sencha          | Japan          | 1           |
                | 16   | Darjeeling      | Indien         | 2           |
                | 111  | Earl Grey       | Großbritannien | 2           |
                | 2000 | Rooibos Vanille | Südafrika      | 3           |
                """;

        assertEquals(expected, table.toString(), "table.toString, five rows");
    }

    @Test
    public void test_DBToString() {

        final DB db = new DB("TassenFreudenDB");

        String expected = """
                Datenbankbezeichner: TassenFreudenDB
                
                """;

        assertEquals(expected, db.toString(), "db.toString, no tables");

        final DBTable categoryTable = new DBTable("Kategorie", "ID", List.of("ID", "Beschreibung", "EnthaeltKoffein"));
        categoryTable.appendRow(cVs(1, "Grüner Tee", true));
        categoryTable.appendRow(cVs(2, "Schwarzer Tee", true));
        categoryTable.appendRow(cVs(3, "Kräutertee", false));

        final DBTable teaTable = new DBTable("Teesorten", "ID", List.of("ID", "Name", "Herkunftsland", "KategorieID"));

        teaTable.appendRow(cVs(1, "Sencha", "Japan", 1));
        teaTable.appendRow(cVs(16, "Darjeeling", "Indien", 2));
        teaTable.appendRow(cVs(111, "Earl Grey", "Großbritannien", 2));
        teaTable.appendRow(cVs(2000, "Rooibos Vanille", "Südafrika", 3));

        db.addTable(categoryTable);
        db.addTable(teaTable);

        expected = """
                Datenbankbezeichner: TassenFreudenDB
                
                Tabellenbezeichner: Kategorie
                Primärschlüssel: ID
                
                | ID | Beschreibung  | EnthaeltKoffein |
                |----|---------------|-----------------|
                | 1  | Grüner Tee    | true            |
                | 2  | Schwarzer Tee | true            |
                | 3  | Kräutertee    | false           |
                
                Tabellenbezeichner: Teesorten
                Primärschlüssel: ID
                
                | ID   | Name            | Herkunftsland  | KategorieID |
                |------|-----------------|----------------|-------------|
                | 1    | Sencha          | Japan          | 1           |
                | 16   | Darjeeling      | Indien         | 2           |
                | 111  | Earl Grey       | Großbritannien | 2           |
                | 2000 | Rooibos Vanille | Südafrika      | 3           |
                
                """;

        assertEquals(expected, db.toString(), "db.toString, two tables");
    }

    @Test
    public void test_select() {

        final DBTable teaTable = new DBTable("Tee", "ID", List.of("ID", "Name", "Herkunftsland", "KategorieID"));

        teaTable.appendRow(cVs(1, "Sencha", "Japan", 1));
        teaTable.appendRow(cVs(16, "Darjeeling", "Indien", 2));
        teaTable.appendRow(cVs(111, "Earl Grey", "Großbritannien", 2));
        teaTable.appendRow(cVs(2000, "Rooibos Vanille", "Südafrika", 3));

        DBTable newTable = teaTable.select(List.of("Name", "ID"),
                List.of(new WhereParameter("KategorieID", Predicate.isEqual(cV(2))),
                        new WhereParameter("KategorieID", Predicate.isEqual(cV(4)))), "Tee_Selected");

        String expected = """
                Tabellenbezeichner: Tee_Selected
                Primärschlüssel: ID
                
                | Name       | ID  |
                |------------|-----|
                | Darjeeling | 16  |
                | Earl Grey  | 111 |
                """;

        assertEquals(expected, newTable.toString(),
                "newTable.toString, after select([Name, ID], [KategorieID == 2, KategorieID == 4]), two columns, switched order");
    }

    @Test
    public void test_update() {

        final DBTable teaTable = new DBTable("Tee", "ID", List.of("ID", "Name", "Herkunftsland", "KategorieID"));

        teaTable.appendRow(cVs(1, "Sencha", "Japan", 1));
        teaTable.appendRow(cVs(16, "Darjeeling", "Indien", 2));
        teaTable.appendRow(cVs(111, "Earl Grey", "Großbritannien", 2));
        teaTable.appendRow(cVs(2000, "Rooibos Vanille", "Südafrika", 3));

        DBTable newTable = teaTable.update("Name", cV("Darjeeling Gartentee"),
                List.of(new WhereParameter("KategorieID", Predicate.isEqual(cV(2))),
                        new WhereParameter("Herkunftsland", Predicate.isEqual(cV("Indien")))));

        String expected = """
                Tabellenbezeichner: Tee
                Primärschlüssel: ID
                
                | ID   | Name                 | Herkunftsland  | KategorieID |
                |------|----------------------|----------------|-------------|
                | 1    | Sencha               | Japan          | 1           |
                | 16   | Darjeeling Gartentee | Indien         | 2           |
                | 111  | Earl Grey            | Großbritannien | 2           |
                | 2000 | Rooibos Vanille      | Südafrika      | 3           |
                """;

        assertEquals(expected, newTable.toString(),
                "newTable.toString, after update(Name, Darjeeling Gartentee, [KategorieID == 2, Herkunftsland == Indien]");
    }

    @Test
    public void test_equijoin() {

        final DBTable teaTable = new DBTable("Tee", "ID", List.of("ID", "Name", "Herkunftsland", "KategorieID"));

        teaTable.appendRow(cVs(1, "Sencha", "Japan", 1));
        teaTable.appendRow(cVs(16, "Darjeeling", "Indien", 2));
        teaTable.appendRow(cVs(111, "Earl Grey", "Großbritannien", 2));
        teaTable.appendRow(cVs(2000, "Rooibos Vanille", "Südafrika", 3));

        final DBTable categoryTable = new DBTable("Kategorie", "ID", List.of("ID", "Beschreibung", "EnthaeltKoffein"));
        categoryTable.appendRow(cVs(1, "Grüner Tee", true));
        categoryTable.appendRow(cVs(2, "Schwarzer Tee", true));
        categoryTable.appendRow(cVs(3, "Kräutertee", false));

        final DBTable newTable = teaTable.equijoin(categoryTable, "KategorieID", "Tee_Kategorie");

        String expectedNew = """
                Tabellenbezeichner: Tee_Kategorie
                Primärschlüssel: Tee_ID
                
                | Tee_ID | Tee_Name        | Tee_Herkunftsland | Kategorie_Beschreibung | Kategorie_EnthaeltKoffein |
                |--------|-----------------|-------------------|------------------------|---------------------------|
                | 1      | Sencha          | Japan             | Grüner Tee             | true                      |
                | 16     | Darjeeling      | Indien            | Schwarzer Tee          | true                      |
                | 111    | Earl Grey       | Großbritannien    | Schwarzer Tee          | true                      |
                | 2000   | Rooibos Vanille | Südafrika         | Kräutertee             | false                     |
                """;

        assertEquals(expectedNew, newTable.toString(),
                "newTable.toString, after teaTable.equijoin(categoryTable, KategorieID, ...)");
    }

}