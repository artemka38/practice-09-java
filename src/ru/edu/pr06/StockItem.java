package ru.edu.pr06;
public class StockItem {
 private final String code; private final String name; private final Category category; private int quantity;
 public StockItem(String code,String name,Category category,int quantity) { if(quantity<0) throw new IllegalArgumentException("quantity must be >= 0"); this.code=code;this.name=name;this.category=category;this.quantity=quantity; }
 public void increase(int q){
    if (q <= 0) {
        throw new IllegalArgumentException(
                "q must be > 0"
        );
    }

    this.quantity += q;
}
 public void decrease(int q){
    if (q <= 0) {
        throw new IllegalArgumentException(
                "q must be > 0"
        );
    }

    // Вариант 25:
    // после списания должна остаться минимум 1 единица
    if (quantity - q < 1) {
        throw new IllegalArgumentException(
                "minimum reserve is 1"
        );
    }

    this.quantity -= q;
}
 public String getCode(){return code;} public Category getCategory(){return category;} public int getQuantity(){return quantity;}

 public String getName() {
    return name;
}

}
