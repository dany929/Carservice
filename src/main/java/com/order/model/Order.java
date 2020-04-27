package com.order.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
//@Embeddable
@Table(name = "orders")

public class Order implements Serializable
{

    @Id
    @Column(name = "orderid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderid;

    @Column(name = "datein")
    @Temporal(TemporalType.DATE)
    private Date datein;

    @Column(name = "dateout")
    @Temporal(TemporalType.DATE)
    private Date dateout;

    @Column(name = "discount")
    private String discount;

    @Column(name = "gosznak")
    private String gosznak;

    public String getGosznak() {
        return gosznak;
    }

    public void setGosznak(String gosznak) {
        this.gosznak = gosznak;
    }

    public int getOrderid() {
        return orderid;
    }


    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }


    public Date getDatein() {
        return datein;
    }

    public void setDatein(Date datein) {
        this.datein = datein;
    }

    public Date getDateout() {
        return dateout;
    }

    public void setDateout(Date dateout) {
        this.dateout = dateout;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
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
        return "Order{ " +
                "orderid='" + orderid +'\'' +
                ", datein='" + datein + '\'' +
                ", dateout='" + dateout + '\'' +
                ", discount='" + discount + '\'' +
                ", gosznak='" + gosznak + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getOrderid(), order.getOrderid());
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

