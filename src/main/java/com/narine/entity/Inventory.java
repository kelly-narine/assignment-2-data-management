package com.narine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i")
@NamedQuery(name = "Inventory.getByName", query = "SELECT i FROM Inventory  i WHERE i.name = :name")
@NamedQuery(name = "Inventory.clearAll", query = "DELETE FROM Inventory ") // done via JPQL (Java Persistence Query Language). JPQL --> Java Persistence Query Language. Similar to SQL, but it is more Java Object Oriented.
public class Inventory implements Comparable<Inventory>, Serializable {
    @Id //primary key
    @GeneratedValue //auto generates and auto increments the primary key
    private Long id;

    private String name;

    private String sport;

    private int quantity;

    private double unitPrice;

    private Date dateUpdated;

    @PrePersist //sets generated fields.
    void createdAt() { //com.narine.entity listener method
        this.dateUpdated = new Date();
    }

    @PreUpdate //sets generated fields
    void updatedAt() { //com.narine.entity listener method
        this.dateUpdated = new Date();
    }

    @Override
    public int compareTo(Inventory o) {
        return dateUpdated.compareTo(o.dateUpdated);
    } //sort inventory by date updated
}
