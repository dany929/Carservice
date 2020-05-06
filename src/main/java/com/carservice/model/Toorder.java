package com.carservice.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Embeddable
@Table(name = "toorder")

public class Toorder implements Serializable
{
    @Id
    @Column(name = "toorderid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int toorderid;

    public int getToorderid() {
        return toorderid;
    }

    public void setToorderid(int toorderid) {
        this.toorderid = toorderid;
    }

    /*
            @Id
            @Column(name = "orderid")
            private int orderid;

            @Id
            @Column(name = "partid")
            private int partid;

            @Id
            @Column(name = "operationid")
            private int operationid;
        */

    @Column(name = "numofparts")
    private int numofparts;

///////////////////////////////////////////Гибер отн
 @ManyToOne (fetch = FetchType.LAZY)
@JoinColumn (name="partid")
 @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
private Part part;

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="operationid")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Operation operation;

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn (name="orderid")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    ///////////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~     ~~~~~
    /*
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
*/
    public Toorder() {}
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
        return "ToOrder{ " +
                "toorderid='" + toorderid +'\'' +
                ", orderid='" + order.getOrderid() +'\'' +
                ", partid='" + part.getPartid() + '\'' +
                ", operationid='" + operation.getOperationid() + '\'' +
                ", numofparts='" + numofparts + '\'' +
                '}';
    }
/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Toorder)) return false;
        Toorder toorder = (Toorder) o;
        return Objects.equals(getOrderid(), toorder.getOrderid());
    }


*/


/*
    public int hashCode(int orderid, int operationid,int partid) {
        int result = orderid;
        result = 31 * result + operationid;
        result = 31 * result +partid;

        return result;
    }
*/

}

