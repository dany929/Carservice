package com.carservice.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
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
   // @Temporal(TemporalType.DATE)
    private String datein;

    @Column(name = "dateout")

    private String dateout;

    @Column(name = "discount")
    private int discount;
    @Column(precision = 8,scale = 2)
    private double totalcost;

    public double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(double totalcost) {
        this.totalcost = totalcost;
    }

    //////////////////////////////////////////////////
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    private List<Toorder> toorders;

    public List<Toorder> getToorders() {
        return toorders;
    }

    public void setToorders(List<Toorder> toorders) {
        this.toorders = toorders;
    }


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="gosznak", referencedColumnName = "gosznak")
    @Cascade(org.hibernate.annotations.CascadeType.DETACH)
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
//////////////////////////////////////////////-----------

/*


    public String getGosznak() {
        return gosznak;
    }

    public void setGosznak(String gosznak) {
        this.gosznak = gosznak;
    }

*/

    public int getOrderid() {
        return orderid;
    }


    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }


    public String getDatein() {
        return datein;
    }

    public void setDatein(String datein) {
        this.datein = datein;
    }

    public String getDateout() {
        return dateout;
    }

    public void setDateout(String dateout) {
        this.dateout = dateout;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
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
                ", totalcost='" + totalcost + '\'' +
                ", gosznak='" + customer.getGosznak() + '\'' +
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

