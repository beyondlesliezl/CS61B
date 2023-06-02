package hw3.hash;

import org.junit.Test;
import org.omg.CORBA.BAD_PARAM;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.Random;


import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        //meaning no two SimpleOomages should EVER have the same
        //hashCode UNLESS they have the same red, blue, and green values!
        //value that is a multiple of 5 between 0 and 255.
        int[] arr = new int[52];
        int sub = 0;
        for (int i = 0; i < 256; i++) {
            if (i % 5 == 0) {
                arr[sub] = i;
                sub++;
            }
        }
        Random random = new Random();
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(30, 45, 50);
        SimpleOomage ooC = new SimpleOomage(60, 75, 85);
        SimpleOomage ooD = new SimpleOomage(200, 215, 225);
        SimpleOomage ooE = new SimpleOomage(90, 105,115);
        SimpleOomage ooF = new SimpleOomage(190, 175, 125);
        SimpleOomage ooG = new SimpleOomage(180, 165, 155);
        for (int j = 0; j < 10000; j++) {
            int red = arr[random.nextInt(52)];
            int blue = arr[random.nextInt(52)];
            int green = arr[random.nextInt(52)];
            SimpleOomage test = new SimpleOomage(red, green, blue);
            if (! (red == 5 && blue == 10 && green == 20)) {
                assertNotEquals(ooA.hashCode(), test.hashCode());
            }
            if (! (red == 30 && blue == 45 && green == 50)) {
                assertNotEquals(ooB.hashCode(), test.hashCode());
            }
            if (! (red == 60 && blue == 75 && green == 85)) {
                assertNotEquals(ooC.hashCode(), test.hashCode());
            }
            if (! (red == 200 && blue == 215 && green == 225)) {
                assertNotEquals(ooD.hashCode(), test.hashCode());
            }
            if (! (red == 90 && blue == 105 && green == 115)) {
                assertNotEquals(ooE.hashCode(), test.hashCode());
            }
            if (! (red == 190 && blue == 175 && green == 125)) {
                assertNotEquals(ooF.hashCode(), test.hashCode());
            }
            if (! (red == 180 && blue == 165 && green == 155)) {
                assertNotEquals(ooG.hashCode(), test.hashCode());
            }
        }
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
