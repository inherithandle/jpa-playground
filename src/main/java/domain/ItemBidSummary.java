package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.Immutable
@org.hibernate.annotations.Subselect(value = "select i.ID as ITEMID, i.NAME as NAME, " +
                                            "count(b.BID_ID) as NUMBEROFBIDS " +
                                            "from ITEM i left outer join BID b on i.ID = b.ITEM_ID " +
                                            "group by i.ID, i.NAME" )
@org.hibernate.annotations.Synchronize({"Item", "Bid"})
public class ItemBidSummary {

    @Id
    protected int itemId;

    protected String name;

    protected long numberOfBids;

    public ItemBidSummary() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(long numberOfBids) {
        this.numberOfBids = numberOfBids;
    }
}