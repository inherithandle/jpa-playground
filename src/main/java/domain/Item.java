package domain;



import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * An item for auction.
 *
 * @author Christian Bauer
 */
@Entity
@Table(name = "ITEM")
public class Item implements Serializable {

    @Id
    private Long id = null;

    private String name;
//    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELLER_ID", nullable = false)
    private User seller;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
//    @BatchSize(size = 3)
    @Fetch(FetchMode.SUBSELECT)
    private List<Bid> bids;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false, updatable = false, insertable = false)
    private Category category;

    /**
     * No-arg constructor for JavaBean tools.
     */
    public Item() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seller=" + seller +
                ", bids=" + bids +
                '}';
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
}
