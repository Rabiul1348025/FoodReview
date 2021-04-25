package com.example.foodapp;
// this class is used to provide data to the other classes
public class DataProvider {
//declaration of variables
    private  String food;
    private  String des;
    private  String Prices;
    private float ratings;

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
        return Prices;
    }

    public void setPrice(String prices) {
        this.Prices = prices;
    }

    public float getRating() {
        return ratings;
    }

    public void setRating(float rating) {
        this.ratings = rating;
    }



    public DataProvider(String food, String des, String Prices, float ratings){
        this.food= food;

        this.des= des;
        this.Prices=Prices;
        this.ratings= ratings;
    }
}
