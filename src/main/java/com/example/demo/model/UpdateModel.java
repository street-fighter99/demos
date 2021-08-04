package com.example.demo.model;


import lombok.Data;

@Data
public class UpdateModel {
    private int id;
    private String name;
    private int device_model;
    private double actual_price;
    private double sell_price;
    private int is_active;
    private String category;
    private double qty;
}
