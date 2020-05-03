package com.carservice.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Embeddable
@Table(name = "customers")

public class Customer
{

    @Id
    @Column(name = "gosznak")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String gosznak;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "tel")
    private String tel;


    //////////////////////////////////////////////////
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
//////////////////////////////////////////////-----------

    public String getGosznak() {
        return gosznak;
    }

    public void setGosznak(String gosznak) {
        this.gosznak = gosznak;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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
        return "Customer{ " +
                "gosznak='" + gosznak +'\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getGosznak(), customer.getGosznak());
    }

    @Override
    public int hashCode() {
        int result = gosznak.hashCode();
        result = 31 * result + firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + tel.hashCode();
        return result;
    }
}

