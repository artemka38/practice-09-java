package ru.edu.pr06;

public class StockItem {

    private final String code;
    private final String name;
    private final Category category;
    private int quantity;

    public StockItem(
            String code,
            String name,
            Category category,
            int quantity
    ) {

        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException(
                    "code is null or blank"
            );
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "name is null or blank"
            );
        }

        if (category == null) {
            throw new IllegalArgumentException(
                    "category is null"
            );
        }

        if (quantity < 0) {
            throw new IllegalArgumentException(
                    "quantity must be >= 0"
            );
        }

        this.code = code;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
    }

    public void increase(int q) {

        if (q <= 0) {
            throw new IllegalArgumentException(
                    "q must be > 0"
            );
        }

        this.quantity += q;
    }

    public void decrease(int q) {

        if (q <= 0) {
            throw new IllegalArgumentException(
                    "q must be > 0"
            );
        }

        // Вариант 25:
        // после списания должна остаться
        // минимум 1 единица
        if (quantity - q < 1) {
            throw new IllegalArgumentException(
                    "minimum reserve is 1"
            );
        }

        this.quantity -= q;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null
                || getClass() != o.getClass()) {
            return false;
        }

        StockItem other =
                (StockItem) o;

        return code.equals(other.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}