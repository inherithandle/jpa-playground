package domain;

import javax.persistence.*;
import java.io.Serializable;


/**
 * An immutable class representing one bid.
 * <p>
 * If the "successful" property is used in a legacy situation
 * (see book chapter 8), it is no longer an immutable class with
 * consequences for second-level caching.
 * <p>
 * Note: This legacy mapping isn't currently possible with annotations.
 *
 * @see Item
 * @see User
 * @author Christian Bauer
 */
@Entity
@Table(name = "BID")
public class Bid implements Serializable {

    @Id @GeneratedValue
    @Column(name = "BID_ID")
    private Long id = null;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", nullable = false, updatable = false, insertable = false)
	private Item item;

    @ManyToOne
    @JoinColumn(name = "BIDDER_ID", nullable = false, updatable = false)
    private User bidder;

	@Column(name = "AMOUNT")
	private int amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getBidder() {
		return bidder;
	}

	public void setBidder(User bidder) {
		this.bidder = bidder;
	}

	@Override
	public String toString() {
		return "Bid{" +
				"id=" + id +
				", bidder=" + bidder +
				'}';
	}
}