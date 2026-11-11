package ru.edu.pr06;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SelfCheck {

    public static void main(String[] args) {

        // =========================================
        // 18 ОБЯЗАТЕЛЬНЫХ UNIT-ПРОВЕРОК
        // =========================================

        // 1. Корректное создание StockItem
        try {
            StockItem item = new StockItem(
                    "NB-01",
                    "Notebook",
                    Category.COMPUTER,
                    12
            );

            System.out.println(
                    item.getQuantity() == 12
                            ? "PASS 1"
                            : "FAIL 1"
            );

        } catch (Exception e) {
            System.out.println("FAIL 1");
        }


        // 2. quantity < 0
        try {
            new StockItem(
                    "X",
                    "Y",
                    Category.COMPUTER,
                    -1
            );

            System.out.println("FAIL 2");

        } catch (IllegalArgumentException e) {

            System.out.println(
                    e.getMessage() != null
                            && e.getMessage().contains(
                            "quantity must be >= 0"
                    )
                            ? "PASS 2"
                            : "FAIL 2"
            );

        } catch (Exception e) {
            System.out.println("FAIL 2");
        }


        // 3. increase(5): 12 -> 17
        try {
            StockItem item = new StockItem(
                    "NB-03",
                    "Notebook",
                    Category.COMPUTER,
                    12
            );

            item.increase(5);

            System.out.println(
                    item.getQuantity() == 17
                            ? "PASS 3"
                            : "FAIL 3"
            );

        } catch (Exception e) {
            System.out.println("FAIL 3");
        }


        // 4. increase(0) -> ошибка
        try {
            StockItem item = new StockItem(
                    "NB-04",
                    "Notebook",
                    Category.COMPUTER,
                    12
            );

            item.increase(0);

            System.out.println("FAIL 4");

        } catch (IllegalArgumentException e) {

            System.out.println(
                    e.getMessage() != null
                            && e.getMessage().contains(
                            "q must be > 0"
                    )
                            ? "PASS 4"
                            : "FAIL 4"
            );

        } catch (Exception e) {
            System.out.println("FAIL 4");
        }


        // 5. decrease(5): 12 -> 7
        try {
            StockItem item = new StockItem(
                    "NB-05",
                    "Notebook",
                    Category.COMPUTER,
                    12
            );

            item.decrease(5);

            System.out.println(
                    item.getQuantity() == 7
                            ? "PASS 5"
                            : "FAIL 5"
            );

        } catch (Exception e) {
            System.out.println("FAIL 5");
        }


        // 6. decrease(13) -> ошибка
        // Для варианта 25 минимальный резерв = 1
        try {
            StockItem item = new StockItem(
                    "NB-06",
                    "Notebook",
                    Category.COMPUTER,
                    12
            );

            item.decrease(13);

            System.out.println("FAIL 6");

        } catch (IllegalArgumentException e) {

            System.out.println(
                    e.getMessage() != null
                            && e.getMessage().contains(
                            "minimum reserve is 1"
                    )
                            ? "PASS 6"
                            : "FAIL 6"
            );

        } catch (Exception e) {
            System.out.println("FAIL 6");
        }


        // 7. addItem
        try {
            Warehouse warehouse =
                    new Warehouse();

            StockItem item =
                    new StockItem(
                            "NB-07",
                            "Notebook",
                            Category.COMPUTER,
                            12
                    );

            warehouse.addItem(item);

            System.out.println(
                    warehouse.itemsCount() == 1
                            ? "PASS 7"
                            : "FAIL 7"
            );

        } catch (Exception e) {
            System.out.println("FAIL 7");
        }


        // 8. Дубликат кода
        try {
            Warehouse warehouse =
                    new Warehouse();

            warehouse.addItem(
                    new StockItem(
                            "NB-08",
                            "Notebook",
                            Category.COMPUTER,
                            12
                    )
            );

            warehouse.addItem(
                    new StockItem(
                            "NB-08",
                            "Another",
                            Category.COMPUTER,
                            5
                    )
            );

            System.out.println("FAIL 8");

        } catch (IllegalArgumentException e) {

            System.out.println(
                    e.getMessage() != null
                            && e.getMessage().contains(
                            "code already exists"
                    )
                            ? "PASS 8"
                            : "FAIL 8"
            );

        } catch (Exception e) {
            System.out.println("FAIL 8");
        }


        // 9. addItem(null)
        try {
            Warehouse warehouse =
                    new Warehouse();

            warehouse.addItem(null);

            System.out.println("FAIL 9");

        } catch (IllegalArgumentException e) {

            System.out.println(
                    e.getMessage() != null
                            && e.getMessage().contains(
                            "item is null"
                    )
                            ? "PASS 9"
                            : "FAIL 9"
            );

        } catch (Exception e) {
            System.out.println("FAIL 9");
        }


        // 10. receive
        try {
            Warehouse warehouse =
                    new Warehouse();

            StockItem item =
                    new StockItem(
                            "NB-10",
                            "Notebook",
                            Category.COMPUTER,
                            12
                    );

            warehouse.addItem(item);

            warehouse.receive(
                    "NB-10",
                    5
            );

            boolean ok =
                    item.getQuantity() == 17
                            && warehouse.movementsCount() == 1;

            System.out.println(
                    ok
                            ? "PASS 10"
                            : "FAIL 10"
            );

        } catch (Exception e) {
            System.out.println("FAIL 10");
        }


        // 11. receive с неизвестным кодом
        try {
            Warehouse warehouse =
                    new Warehouse();

            warehouse.receive(
                    "XX-99",
                    5
            );

            System.out.println("FAIL 11");

        } catch (IllegalArgumentException e) {

            System.out.println(
                    e.getMessage() != null
                            && e.getMessage().contains(
                            "code not found"
                    )
                            ? "PASS 11"
                            : "FAIL 11"
            );

        } catch (Exception e) {
            System.out.println("FAIL 11");
        }


        // 12. issue
        try {
            Warehouse warehouse =
                    new Warehouse();

            StockItem item =
                    new StockItem(
                            "NB-12",
                            "Notebook",
                            Category.COMPUTER,
                            12
                    );

            warehouse.addItem(item);

            warehouse.issue(
                    "NB-12",
                    3
            );

            boolean ok =
                    item.getQuantity() == 9
                            && warehouse.movementsCount() == 1;

            System.out.println(
                    ok
                            ? "PASS 12"
                            : "FAIL 12"
            );

        } catch (Exception e) {
            System.out.println("FAIL 12");
        }


        // 13. issue больше остатка
        // Для варианта 25 сработает минимальный резерв
        try {
            Warehouse warehouse =
                    new Warehouse();

            StockItem item =
                    new StockItem(
                            "NB-13",
                            "Notebook",
                            Category.COMPUTER,
                            12
                    );

            warehouse.addItem(item);

            warehouse.issue(
                    "NB-13",
                    100
            );

            System.out.println("FAIL 13");

        } catch (IllegalArgumentException e) {

            System.out.println(
                    e.getMessage() != null
                            && e.getMessage().contains(
                            "minimum reserve is 1"
                    )
                            ? "PASS 13"
                            : "FAIL 13"
            );

        } catch (Exception e) {
            System.out.println("FAIL 13");
        }


        // 14. issue с неизвестным кодом
        try {
            Warehouse warehouse =
                    new Warehouse();

            warehouse.issue(
                    "XX-99",
                    3
            );

            System.out.println("FAIL 14");

        } catch (IllegalArgumentException e) {

            System.out.println(
                    e.getMessage() != null
                            && e.getMessage().contains(
                            "code not found"
                    )
                            ? "PASS 14"
                            : "FAIL 14"
            );

        } catch (Exception e) {
            System.out.println("FAIL 14");
        }


        // 15. findByCategory:
        // две позиции COMPUTER
        try {
            Warehouse warehouse =
                    new Warehouse();

            warehouse.addItem(
                    new StockItem(
                            "NB-15",
                            "Notebook",
                            Category.COMPUTER,
                            12
                    )
            );

            warehouse.addItem(
                    new StockItem(
                            "PC-15",
                            "Computer",
                            Category.COMPUTER,
                            5
                    )
            );

            warehouse.addItem(
                    new StockItem(
                            "MN-15",
                            "Monitor",
                            Category.DISPLAY,
                            7
                    )
            );

            List<StockItem> result =
                    warehouse.findByCategory(
                            Category.COMPUTER
                    );

            System.out.println(
                    result.size() == 2
                            ? "PASS 15"
                            : "FAIL 15"
            );

        } catch (Exception e) {
            System.out.println("FAIL 15");
        }


        // 16. findByCategory:
        // категория отсутствует
        try {
            Warehouse warehouse =
                    new Warehouse();

            warehouse.addItem(
                    new StockItem(
                            "NB-16",
                            "Notebook",
                            Category.COMPUTER,
                            12
                    )
            );

            List<StockItem> result =
                    warehouse.findByCategory(
                            Category.NETWORK
                    );

            System.out.println(
                    result.isEmpty()
                            ? "PASS 16"
                            : "FAIL 16"
            );

        } catch (Exception e) {
            System.out.println("FAIL 16");
        }


        // 17. Изменение найденного списка
        // не влияет на Warehouse
        try {
            Warehouse warehouse =
                    new Warehouse();

            warehouse.addItem(
                    new StockItem(
                            "NB-17",
                            "Notebook",
                            Category.COMPUTER,
                            12
                    )
            );

            warehouse.addItem(
                    new StockItem(
                            "PC-17",
                            "Computer",
                            Category.COMPUTER,
                            5
                    )
            );

            List<StockItem> result =
                    warehouse.findByCategory(
                            Category.COMPUTER
                    );

            result.clear();

            boolean ok =
                    result.isEmpty()
                            && warehouse.itemsCount() == 2
                            && warehouse
                            .findByCategory(
                                    Category.COMPUTER
                            )
                            .size() == 2;

            System.out.println(
                    ok
                            ? "PASS 17"
                            : "FAIL 17"
            );

        } catch (Exception e) {
            System.out.println("FAIL 17");
        }


        // 18. totalsByCategory
        try {
            Warehouse warehouse =
                    new Warehouse();

            warehouse.addItem(
                    new StockItem(
                            "NB-18",
                            "Notebook",
                            Category.COMPUTER,
                            12
                    )
            );

            warehouse.addItem(
                    new StockItem(
                            "MN-18",
                            "Monitor",
                            Category.DISPLAY,
                            7
                    )
            );

            warehouse.addItem(
                    new StockItem(
                            "KB-18",
                            "Keyboard",
                            Category.PERIPHERAL,
                            20
                    )
            );

            Map<Category, Integer> totals =
                    warehouse.totalsByCategory();

            boolean ok =
                    totals.size() == 3
                            && totals.get(
                            Category.COMPUTER
                    ) == 12
                            && totals.get(
                            Category.DISPLAY
                    ) == 7
                            && totals.get(
                            Category.PERIPHERAL
                    ) == 20;

            System.out.println(
                    ok
                            ? "PASS 18"
                            : "FAIL 18"
            );

        } catch (Exception e) {
            System.out.println("FAIL 18");
        }


        // =========================================
        // ВАРИАНТ 25
        // =========================================

        // 19. Остаток ровно 1 разрешён
        try {
            StockItem item =
                    new StockItem(
                            "V-19",
                            "Item",
                            Category.COMPUTER,
                            5
                    );

            item.decrease(4);

            System.out.println(
                    item.getQuantity() == 1
                            ? "PASS 19"
                            : "FAIL 19"
            );

        } catch (Exception e) {
            System.out.println("FAIL 19");
        }


        // 20. Остаток 0 запрещён
        try {
            StockItem item =
                    new StockItem(
                            "V-20",
                            "Item",
                            Category.COMPUTER,
                            5
                    );

            item.decrease(5);

            System.out.println("FAIL 20");

        } catch (IllegalArgumentException e) {

            System.out.println(
                    e.getMessage() != null
                            && e.getMessage().contains(
                            "minimum reserve is 1"
                    )
                            ? "PASS 20"
                            : "FAIL 20"
            );

        } catch (Exception e) {
            System.out.println("FAIL 20");
        }


        // 21. addAlmostEmptyItems:
        // находит позицию с quantity == 1
        try {
            Warehouse warehouse =
                    new Warehouse();

            warehouse.addItem(
                    new StockItem(
                            "V-21-A",
                            "AlmostEmpty",
                            Category.COMPUTER,
                            1
                    )
            );

            warehouse.addItem(
                    new StockItem(
                            "V-21-B",
                            "Normal",
                            Category.DISPLAY,
                            5
                    )
            );

            List<StockItem> result =
                    warehouse.addAlmostEmptyItems(
                            warehouse
                    );

            boolean ok =
                    result.size() == 1
                            && result.get(0)
                            .getQuantity() == 1;

            System.out.println(
                    ok
                            ? "PASS 21"
                            : "FAIL 21"
            );

        } catch (Exception e) {
            System.out.println("FAIL 21");
        }


        // 22. Если quantity == 1 нет,
        // результат пустой
        try {
            Warehouse warehouse =
                    new Warehouse();

            warehouse.addItem(
                    new StockItem(
                            "V-22-A",
                            "Item1",
                            Category.COMPUTER,
                            5
                    )
            );

            warehouse.addItem(
                    new StockItem(
                            "V-22-B",
                            "Item2",
                            Category.DISPLAY,
                            7
                    )
            );

            List<StockItem> result =
                    warehouse.addAlmostEmptyItems(
                            warehouse
                    );

            System.out.println(
                    result.isEmpty()
                            ? "PASS 22"
                            : "FAIL 22"
            );

        } catch (Exception e) {
            System.out.println("FAIL 22");
        }


        // =========================================
        // DATA-DRIVEN
        // =========================================

        System.out.println();
        System.out.println("Data-driven:");

        try {
            List<String> lines =
                    Files.readAllLines(
                            Paths.get(
                                    "data/sample.csv"
                            )
                    );

            Warehouse warehouse =
                    new Warehouse();

            int passed = 0;
            int total = 0;

            for (int i = 1;
                 i < lines.size();
                 i++) {

                String line =
                        lines.get(i);

                if (line.isBlank()
                        || line.startsWith("#")) {
                    continue;
                }

                total++;

                String[] parts =
                        line.split(";", -1);

                try {
                    String code =
                            parts[0];

                    String name =
                            parts[1];

                    Category category =
                            Category.valueOf(
                                    parts[2]
                            );

                    int quantity =
                            Integer.parseInt(
                                    parts[3]
                            );

                    String expected =
                            parts.length > 4
                                    ? parts[4]
                                    : "OK:qty="
                                    + quantity;

                    StockItem item =
                            new StockItem(
                                    code,
                                    name,
                                    category,
                                    quantity
                            );

                    warehouse.addItem(item);

                    if (expected.startsWith(
                            "OK:qty="
                    )) {

                        int expectedQty =
                                Integer.parseInt(
                                        expected.substring(
                                                "OK:qty="
                                                        .length()
                                        )
                                );

                        if (item.getQuantity()
                                == expectedQty) {

                            passed++;

                            System.out.println(
                                    "PASS data/"
                                            + total
                            );

                        } else {

                            System.out.println(
                                    "FAIL data/"
                                            + total
                                            + ": quantity"
                            );
                        }

                    } else {

                        System.out.println(
                                "FAIL data/"
                                        + total
                                        + ": unknown expected"
                        );
                    }

                } catch (Exception e) {

                    String expected =
                            parts.length > 4
                                    ? parts[4]
                                    : "";

                    if (expected.startsWith(
                            "SKIP:"
                    )) {

                        passed++;

                        System.out.println(
                                "PASS data/"
                                        + total
                        );

                    } else {

                        System.out.println(
                                "FAIL data/"
                                        + total
                                        + ": "
                                        + e.getMessage()
                        );
                    }
                }
            }

            System.out.println(
                    "Data-driven: "
                            + passed
                            + "/"
                            + total
                            + " passed"
            );

        } catch (Exception e) {

            System.out.println(
                    "Data-driven error: "
                            + e.getMessage()
            );
        }


        // =========================================
        // INTERACTIVE MODE
        // =========================================

        if (args.length > 0
                && args[0].equals(
                "--interactive"
        )) {

            System.out.println();
            System.out.println(
                    "Interactive mode:"
            );

            Scanner scanner =
                    new Scanner(System.in);

            Warehouse warehouse =
                    new Warehouse();

            System.out.println(
                    "Доступные команды:"
            );

            System.out.println(
                    "add <code> <name> "
                            + "<category> <qty>"
            );

            System.out.println(
                    "receive <code> <qty>"
            );

            System.out.println(
                    "issue <code> <qty>"
            );

            System.out.println(
                    "find <category>"
            );

            System.out.println(
                    "totals"
            );

            System.out.println(
                    "exit"
            );

            while (true) {

                System.out.print("> ");

                String line =
                        scanner.nextLine()
                                .trim();

                if (line.equalsIgnoreCase(
                        "exit"
                )) {

                    System.out.println(
                            "Интерактивный режим завершён."
                    );

                    break;
                }

                if (line.isBlank()) {
                    continue;
                }

                String[] parts =
                        line.split("\\s+");

                try {

                    switch (
                            parts[0]
                                    .toLowerCase()
                    ) {

                        case "add": {

                            String code =
                                    parts[1];

                            String name =
                                    parts[2];

                            Category category =
                                    Category.valueOf(
                                            parts[3]
                                                    .toUpperCase()
                                    );

                            int quantity =
                                    Integer.parseInt(
                                            parts[4]
                                    );

                            StockItem item =
                                    new StockItem(
                                            code,
                                            name,
                                            category,
                                            quantity
                                    );

                            warehouse.addItem(
                                    item
                            );

                            System.out.println(
                                    "Позиция добавлена."
                            );

                            break;
                        }


                        case "receive": {

                            String code =
                                    parts[1];

                            int quantity =
                                    Integer.parseInt(
                                            parts[2]
                                    );

                            warehouse.receive(
                                    code,
                                    quantity
                            );

                            System.out.println(
                                    "Приход выполнен."
                            );

                            break;
                        }


                        case "issue": {

                            String code =
                                    parts[1];

                            int quantity =
                                    Integer.parseInt(
                                            parts[2]
                                    );

                            warehouse.issue(
                                    code,
                                    quantity
                            );

                            System.out.println(
                                    "Выдача выполнена."
                            );

                            break;
                        }


                        case "find": {

                            Category category =
                                    Category.valueOf(
                                            parts[1]
                                                    .toUpperCase()
                                    );

                            List<StockItem> result =
                                    warehouse
                                            .findByCategory(
                                                    category
                                            );

                            System.out.println(
                                    "Найдено: "
                                            + result.size()
                            );

                            for (StockItem item
                                    : result) {

                                System.out.println(
                                        item.getCode()
                                                + " qty="
                                                + item.getQuantity()
                                );
                            }

                            break;
                        }


                        case "totals": {

                            Map<Category, Integer> totals =
                                    warehouse
                                            .totalsByCategory();

                            for (Map.Entry<Category, Integer> entry
                                    : totals.entrySet()) {

                                System.out.println(
                                        entry.getKey()
                                                + ": "
                                                + entry.getValue()
                                );
                            }

                            break;
                        }


                        default:

                            System.out.println(
                                    "Неизвестная команда."
                            );
                    }

                } catch (Exception e) {

                    System.out.println(
                            "Ошибка: "
                                    + e.getMessage()
                    );
                }
            }
        }
    }
}