package ua.petproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    @NotNull
    private BigDecimal price;

    @Column(name = "calories", nullable = false)
    private double calories;

    public Dish(){}

    public Dish(Integer id, String name, BigDecimal price, double calories) {
        super(id,name);
        this.price = price;
        this.calories=calories;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "dish{" +
                "price=" + price +
                ", calories=" + calories +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
