package com.toorder.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
//@Embeddable
@Table(name = "toorder")

public class Toorder implements Serializable
{

    @Id
    @Column(name = "orderid")
    private int orderid;
    @Id
    @Column(name = "partid")
    private int partid;
    @Id
    @Column(name = "operationid")
    private int operationid;

    @Column(name = "numofparts")
    private int numofparts;

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getOrderid() {
        return orderid;
    }

    public int getPartid() {
        return partid;
    }

    public void setPartid(int partid) {
        this.partid = partid;
    }

    public int getOperationid() {
        return operationid;
    }

    public void setOperationid(int operationid) {
        this.operationid = operationid;
    }

    public int getNumofparts() {
        return numofparts;
    }

    public void setNumofparts(int numofparts) {
        this.numofparts = numofparts;
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
                "orderid='" + orderid +'\'' +
                ", partid='" + partid + '\'' +
                ", operationid='" + operationid + '\'' +
                ", numofparts='" + numofparts + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Toorder)) return false;
        Toorder toorder = (Toorder) o;
        return Objects.equals(getPartid(), toorder.getPartid());
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

