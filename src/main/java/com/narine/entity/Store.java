package com.narine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data //lombok annotation. creates accessors and mutators along with the toString method
@AllArgsConstructor //lombok annotation. create constructor using all fields
@NoArgsConstructor //lombok annotation. create empty constructor
@Entity // denotes that this class is a table (like a table in SQL)
public class Store implements Serializable {
    @Id
    private Long id;

    private String name;

    private String location;

//    @OneToMany(mappedBy = "store", fetch = FetchType.EAGER)
    @OneToMany
//    @JoinColumn//(name = "id_inventory") //foreign key
    private List<Inventory> inventoryItems;
}
