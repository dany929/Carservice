package com.Carservice.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
//@Embeddable
@Table(name = "parts")

public class Part
{

    @Id
    @Column(name = "partid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partid;

    @Column(name = "category")
    private String category;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    public int getPartid() {
        return partid;
    }

    public void setPartid(int partid) {
        this.partid = partid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /*  public Customer(String firstname, String lastname, String tel) {
          this.firstname = firstname;
          this.lastname = lastname;
          this.tel = tel;
      }

   public Customer() {
   }
     */
    @Override
    public String toString() {
        return "Part{ " +
                "partid='" + partid +'\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Part)) return false;
        Part part = (Part) o;
        return Objects.equals(getPartid(), part.getPartid());
    }
/*
    @Override
    public int hashCode() {
        int result = partid.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

 */
}

