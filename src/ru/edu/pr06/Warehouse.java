package ru.edu.pr06;
import java.util.*;
public class Warehouse {
 private final Map<String,StockItem> items=new HashMap<>(); private final List<StockMovement> movements=new ArrayList<>();
 public void addItem(StockItem item){
    if (item == null) {
        throw new IllegalArgumentException(
                "item is null"
        );
    }

    if (items.containsKey(item.getCode())) {
        throw new IllegalArgumentException(
                "code already exists"
        );
    }

    items.put(item.getCode(), item);
}
 public void receive(String code,int q){
    StockItem item = items.get(code);

    if (item == null) {
        throw new IllegalArgumentException(
                "code not found"
        );
    }

    item.increase(q);

    movements.add(
            new StockMovement(
                    code,
                    MovementType.RECEIVE,
                    q
            )
    );
}
 public void issue(String code,int q) {
    StockItem item = items.get(code);

    if (item == null) {
        throw new IllegalArgumentException(
                "code not found"
        );
    }

    item.decrease(q);

    movements.add(
            new StockMovement(
                    code,
                    MovementType.ISSUE,
                    q
            )
    );
}
 public List<StockItem> findByCategory(Category c) {
    List<StockItem> result =
            new ArrayList<>();

    for (StockItem item : items.values()) {
        if (item.getCategory() == c) {
            result.add(item);
        }
    }

    return result;
}
 public Map<Category,Integer> totalsByCategory(){
    Map<Category, Integer> result =
            new HashMap<>();

    for (StockItem item : items.values()) {
        Category category =
                item.getCategory();

        if (result.containsKey(category)) {
            result.put(
                    category,
                    result.get(category)
                            + item.getQuantity()
            );
        } else {
            result.put(
                    category,
                    item.getQuantity()
            );
        }
    }

    return result;
}

public int itemsCount() {
    return items.size();
}

public int movementsCount() {
    return movements.size();
}

// Вариант 25, часть Б
public List<StockItem> addAlmostEmptyItems(
        Warehouse wh
) {
    List<StockItem> result =
            new ArrayList<>();

    for (StockItem item
            : wh.items.values()) {

        if (item.getQuantity() == 1) {
            result.add(item);
        }
    }

    return result;
}

}
