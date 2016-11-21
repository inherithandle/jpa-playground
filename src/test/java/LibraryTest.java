import domain.Bid;
import domain.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import javax.persistence.criteria.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/*
 * This Java source file was auto generated by running 'gradle init --type java-library'
 * by 'hello' at '9/3/16 7:01 PM' with Gradle 2.10
 *
 * @author hello, @date 9/3/16 7:01 PM
 */
public class LibraryTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
    EntityManager em;
    EntityTransaction tx;

    @Before
    public void boot() {
        this.emf = Persistence.createEntityManagerFactory("jpabook");
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
    }

    @After
    public void putOffBoots() {
        em.close();
        emf.close();
    }

    @Test
    public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

    @Test
    public void test1() {

        try {
            tx.begin();

            joinFetch(em);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    private static void typedCriteria(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery criteria = cb.createQuery();
        Root<Item> i = criteria.from(Item.class);
        criteria.select(i).where(cb.equal(i.get("id"), 1));
        TypedQuery<Item> query = em.createQuery(criteria);
        Item result = query.getSingleResult();
        System.out.println(result);
    }

    private static void catesianProductCriteria(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery criteria = cb.createQuery();
        criteria.multiselect(criteria.from(Item.class), criteria.from(Bid.class));

    }

    private static void implicitJoin(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery criteria = cb.createQuery();

        Root<Bid> b = criteria.from(Bid.class);
        criteria.select(b).where(
                cb.equal(
                        b.get("item").get("seller").get("username"), "johndoe")
        );

        TypedQuery<Item> query = em.createQuery(criteria);
        List<Item> results = query.getResultList();
    }

    private static void explicitJoin(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery criteria = cb.createQuery();
        Root<Item> i = criteria.from(Item.class);
        Join<Item, Bid> b = i.join("bids", JoinType.LEFT);

        b.on(cb.equal(b.<BigDecimal>get("amount"), new BigDecimal(100)));

        TypedQuery<Item> query = em.createQuery(criteria);
        List<Item> results = query.getResultList();
    }

    private static void jpqlTest(EntityManager em) {
        Query result =  em.createQuery("select i from Item i join i.bids b where b.amount > 100");
        List<Item> list = result.getResultList();
    }

    private static void singleJPQLProducesMultipleSQLs(EntityManager em) {
        Query result =  em.createQuery("select i from Item i");
        List<Item> list = result.getResultList();
        // 왜 user를 join해서 가져오는것이 아니라 each item들의 유저들을 각각 sql로 가져오는가 (N + 1 query problem)

    }

    private static void joinFetch(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery criteria = cb.createQuery();
        Root<Item> i = criteria.from(Item.class);
        i.fetch("bids", JoinType.LEFT);
        i.fetch("seller", JoinType.INNER);
        criteria.select(i).where(cb.equal(i.get("id"), 1));
        TypedQuery<Item> query = em.createQuery(criteria);
        List<Item> item = query.getResultList();
        Set<Item> distinctItem = new LinkedHashSet<Item>(item);
        System.out.println(distinctItem);
    }

    private static void joinFetch2(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery criteria = cb.createQuery();
        Root<Item> i = criteria.from(Item.class);
        i.fetch("bids", JoinType.LEFT);
        criteria.select(i);
        TypedQuery<Item> query = em.createQuery(criteria);
        List<Item> item = query.getResultList();
        Set<Item> distinctItem = new LinkedHashSet<Item>(item);
        System.out.println(distinctItem);
    }
}
