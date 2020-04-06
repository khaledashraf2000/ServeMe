package file.handling.tables;

import java.util.*;

public class TablesList {

    static List<Table> tables = new ArrayList();

    public static List<Table> getTables() {
        return tables;
    }

    public static void loadTableData(Tables tables) {
        for (Table table : tables.getTables()) {
            TablesList.tables.add(table);
        }
    }
}
