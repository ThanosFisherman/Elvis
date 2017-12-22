package com.thanosfisherman.elvis;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ElvisTest {
    @Test
    public void primitive_boolean_isPresent() throws Exception {
        BigBox box = new BigBox(new LilBox());
        final boolean shouldBeFalse = Elvis.of(box).next(BigBox::getLilBox).next(LilBox::isBoolean).isPresent();
        assertEquals(shouldBeFalse, true);
    }

    @Test
    public void primitive_double_isPresent() throws Exception {
        BigBox box = new BigBox(new LilBox());
        final boolean shouldBeTrue = Elvis.of(box).next(BigBox::getLilBox).next(LilBox::getIamDouble).isPresent();
        assertEquals(shouldBeTrue, true);
    }

    @Test
    public void primitive_double_is0() throws Exception {
        BigBox box = new BigBox(new LilBox());
        final double doublepls = Elvis.of(box).next(BigBox::getLilBox).next(LilBox::getIamDouble).getDouble();
        assertEquals(doublepls, 0, 1e-15);
    }

    @Test
    public void primitive_double_isNot0() throws Exception {
        BigBox box = new BigBox(new LilBox(true, 2.6));
        final double doublepls = Elvis.of(box).next(BigBox::getLilBox).next(LilBox::getIamDouble).getDouble();
        assertEquals(doublepls, 2.6, 1e-15);
    }

    @Test
    public void primitive_double_isNotPresent() throws Exception {
        BigBox box = new BigBox();
        final boolean shouldBeFalse = Elvis.of(box).next(BigBox::getLilBox).next(LilBox::getIamDouble).isPresent();
        assertEquals(shouldBeFalse, false);
    }
}