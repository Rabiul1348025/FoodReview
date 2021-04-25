package com.example.foodapp;

public class dataProvider {

    private  String food;
    private  String des;
    private  String prices;

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPrice() {
        return prices;
    }

    public void setPrice(String prices) {
        this.prices = prices;
    }

    public dataProvider(String food, String des, String prices){
        this.food= food;

        this.des= des;
    }
}
