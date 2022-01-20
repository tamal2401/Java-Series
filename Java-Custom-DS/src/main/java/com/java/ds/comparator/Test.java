package com.java.ds.comparator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        // category -> brand -> item
        // p1 (promotion) -> on category
        // p2 -> brand
        // p3 -> item (name identifier
        Item item = new Item();

        Set<Promotion> promotionSet = new HashSet<>();
        promotionSet.add((Promotion) item.getPromotions());

        Brand brandName  = item.getBrandName();
        promotionSet.add((Promotion) brandName.getPromotions());

        Category ct = brandName.getCategory();
        promotionSet.add((Promotion) ct.getPromotions());

        
    }
}

class Category{

//    @OneToMany
    private List<Brand> brands;

//    @OneToMany
    List<Promotion> promotions;

    public Category(List<Brand> brands) {
        this.brands = brands;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
}

class Brand{

//    @OneToMany
    List<Item> items;

    //ManyTOOne
    Category category;

    List<Promotion> promotions;

    public Brand(List<Item> items) {
        this.items = items;
    }


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
}

class Item{

    private String itemName;

    // ManyToOne
    private Brand brandName;

    List<Promotion> promotions;

    public Item() {
    }

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Brand getBrandName() {
        return brandName;
    }

    public void setBrandName(Brand brandName) {
        this.brandName = brandName;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
}

class Promotion{
    long promotionId;
    String promotionName;
    long categoryId;
    long itemId;
    long brandId;
}
