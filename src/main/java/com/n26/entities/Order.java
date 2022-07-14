package com.n26.entities;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Order {
    private int id;
    private int petId;
    private int quantity;
    private String shipmentDate;
    private String status;
    private boolean complete;

    @Override
    public String toString() {
        return "Order with ID " + id + " for " + shipmentDate;
    }
}