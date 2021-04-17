package com.narine.inventory;
import com.narine.entity.Inventory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Stateless // implement JPA management services using stateless
@Remote(InventoryService.class) // remote allows it to be passed from one project to another
public class InventoryServiceImpl implements InventoryService {
    @PersistenceContext // Inject PersistenceContext in this field (EntityManager)
    private EntityManager em; // EntityManager (responsible for establishing conversation in terms of objects and SQL terms in database) represents PersistenceContext
    /* NOTE (pertains to above line):
    - Entity Manager communicate with Persistence Context, done via @PersistenceContext
    - Persistence translates the Java terms to SQL Database terms
    - Analogy: acts like the adapter in Adapter Design Pattern
     */

    @Override
    public void clearList() {
        Query deleteFromInventory = em.createNamedQuery("Inventory.clearAll");
        deleteFromInventory.executeUpdate();
    }

    @Override
    public List<Inventory> getInventoryList() {
        List<Inventory> inventoryList =  em.createNamedQuery("Inventory.findAll", Inventory.class) // specifies name of query and the class (com.narine.entity) it is located in/that gets affected. Will avoid having to cast.
                .getResultList();
        return inventoryList.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public void addToList(Inventory inventory) {
        em.persist(inventory);
    }

    @Override
    public void removeFromList(Inventory inventory) {
        Inventory correspondingItem = em.createNamedQuery("Inventory.getByName", Inventory.class)
                .setParameter("name", inventory.getName())
                .getSingleResult();
        em.remove(correspondingItem);
    }
}
