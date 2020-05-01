package com.carservice.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
//@Embeddable
@Table(name = "operations")

public class Operation
{

    @Id
    @Column(name = "operationid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int operationid;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;


   

    public int getOperationid() {
        return operationid;
    }

    public void setOperationid(int operationid) {
        this.operationid = operationid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return "Opertation{ " +
                "opertationid='" + operationid +'\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operation)) return false;
        Operation opertation = (Operation) o;
        return Objects.equals(getOperationid(), opertation.getOperationid());
    }
/*
    @Override
    public int hashCode() {
        int result = opertationid.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

 */
}

