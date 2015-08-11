package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.Cardinality;

import org.junit.Assert;
import org.junit.Test;



/**
 * Created by JJB on 8/9/15.
 */
public class CardinalityTest {

    @Test
    public void testOneToOne() {
        Cardinality c = Cardinality.fromRange("1", "1");
        Assert.assertEquals(c.getAsString(), Cardinality.ONE_TO_ONE, c);
        c = Cardinality.fromRange("1", null);
        Assert.assertEquals(c.getAsString(), Cardinality.ONE_TO_ONE, c);
        c = Cardinality.fromRange("1", "");
        Assert.assertEquals(c.getAsString(), Cardinality.ONE_TO_ONE, c);
        Assert.assertTrue(Cardinality.ONE_TO_ONE.isSingle());
        Assert.assertFalse(Cardinality.ONE_TO_ONE.isOptional());
    }

    @Test
    public void testOneToMany() {
        Assert.assertEquals(Cardinality.ONE_TO_MANY, Cardinality.fromRange("1", "7"));
        Assert.assertEquals(Cardinality.ONE_TO_MANY, Cardinality.fromRange("1", "*"));
        Assert.assertFalse(Cardinality.ONE_TO_MANY.isSingle());
        Assert.assertFalse(Cardinality.ONE_TO_MANY.isOptional());
    }

    @Test
    public void testZeroToOne() {
        Assert.assertEquals(Cardinality.ZERO_TO_ONE, Cardinality.fromRange("0", "1"));
        Assert.assertTrue(Cardinality.ZERO_TO_ONE.isSingle());
        Assert.assertTrue(Cardinality.ZERO_TO_ONE.isOptional());
    }

    @Test
    public void testAnyToMany() {
        Assert.assertEquals(Cardinality.ANY_TO_MANY, Cardinality.fromRange("*", "*"));
        Assert.assertFalse(Cardinality.ANY_TO_MANY.isSingle());
        Assert.assertTrue(Cardinality.ANY_TO_MANY.isOptional());
    }


    @Test
    public void testManyToMany() {
        Assert.assertEquals(Cardinality.MANY_TO_MANY, Cardinality.fromRange("2", "*"));
        Assert.assertEquals(Cardinality.MANY_TO_MANY, Cardinality.fromRange("2", "8"));
        Assert.assertFalse(Cardinality.MANY_TO_MANY.isSingle());
        Assert.assertFalse(Cardinality.MANY_TO_MANY.isOptional());
    }
}
