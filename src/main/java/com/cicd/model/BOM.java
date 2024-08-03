package com.cicd.model;

import java.util.ArrayList;
import java.util.List;

public class BOM {
    private List<BOMItem> items;

    public BOM() {
        this.items = new ArrayList<>();
    }

    public List<BOMItem> getItems() {
        return items;
    }

    public void setItems(List<BOMItem> items) {
        this.items = items;
    }

    public void addItem(BOMItem item) {
        this.items.add(item);
    }

    // Inner class to represent each item in the BOM
    public static class BOMItem {
        private String partNumber;
        private String description;
        private int quantity;

        public BOMItem(String partNumber, String description, int quantity) {
            this.partNumber = partNumber;
            this.description = description;
            this.quantity = quantity;
        }

        public String getPartNumber() {
            return partNumber;
        }

        public void setPartNumber(String partNumber) {
            this.partNumber = partNumber;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}

