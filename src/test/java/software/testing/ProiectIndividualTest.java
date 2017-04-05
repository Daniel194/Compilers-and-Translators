package software.testing;


import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static software.testing.ProiectIndividual.*;

/**
 * Created by daniellungu on 02/04/2017.
 */
public class ProiectIndividualTest {

    @Test
    public void equivalencePartitioning() {
        assertTrue(existPairNumbers(4, 5, new int[]{3, 1, 5, 0, 7}, 1));
        assertFalse(existPairNumbers(4, 5, new int[]{3, 10, 5, 0, 7}, 1));
        assertFalse(existPairNumbers(4, 5, new int[]{3, 1, 5, 0, 7}, -1));
        assertFalse(existPairNumbers(4, 5, new int[]{3, 1, 5, 0, 7}, 5));
        assertFalse(existPairNumbers(4, 5, new int[]{3, 1, 5, 0}, 1));
        assertFalse(existPairNumbers(4, 5, new int[]{3, 1, 5, 0, 7, 9}, 1));
        assertFalse(existPairNumbers(4, 1, new int[]{3, 1, 5, 0, 7}, 1));
        assertFalse(existPairNumbers(4, 200, new int[]{3, 1, 5, 0, 7}, 1));
        assertFalse(existPairNumbers(2000, 5, new int[]{3, 1, 5, 0, 7}, 1));
        assertFalse(existPairNumbers(-2000, 5, new int[]{3, 1, 5, 0, 7}, 1));
    }

    @Test
    public void boundaryValueAnalysis() {
        int[] c112 = ArrayUtils.addAll(new int[]{3, 2}, IntStream.rangeClosed(1, 98).map(x -> x = 1).toArray());
        int[] c114 = IntStream.rangeClosed(1, 101).map(x -> x = 1).toArray();

        assertTrue(existPairNumbers(5, 2, new int[]{3, 2}, 1));
        assertTrue(existPairNumbers(5, 100, c112, 1));
        assertFalse(existPairNumbers(5, 1, new int[]{3}, 0));
        assertFalse(existPairNumbers(5, 101, c114, 0));

        assertTrue(existPairNumbers(5, 2, new int[]{2, 2}, 0));
        assertFalse(existPairNumbers(5, 2, new int[]{2, 2}, -1));
        assertTrue(existPairNumbers(5, 6, new int[]{2, 3, 1, 4, 0, 5}, 3));
        assertFalse(existPairNumbers(5, 6, new int[]{2, 3, 1, 4, 0, 5}, 4));

        assertTrue(existPairNumbers(-1000, 2, new int[]{-500, -500}, 1));
        assertTrue(existPairNumbers(1000, 2, new int[]{500, 500}, 1));
        assertFalse(existPairNumbers(-1001, 2, new int[]{-500, -501}, 1));
        assertFalse(existPairNumbers(1001, 2, new int[]{500, 501}, 1));
    }

    @Test
    public void categoryPartitioning() {
        int[] c13 = ArrayUtils.addAll(new int[]{2, 3}, IntStream.rangeClosed(1, 99).map(x -> x = 1).toArray());

        assertTrue(existPairNumbers(5, 3, new int[]{1, 2, 3}, 1));
        assertFalse(existPairNumbers(5, 3, new int[]{1, 2, 3}, -1));
        assertFalse(existPairNumbers(5, 3, new int[]{1, 2, 3}, 2));
        assertFalse(existPairNumbers(5, 4, new int[]{1, 2, 3}, 1));
        assertFalse(existPairNumbers(5, 1, new int[]{5}, 1));
        assertFalse(existPairNumbers(5, 101, c13, 1));
        assertFalse(existPairNumbers(-1001, 2, new int[]{-500, -501}, 1));
        assertFalse(existPairNumbers(1001, 2, new int[]{500, 501}, 1));

    }

    @Test
    public void statementCoverage() {
        assertFalse(existPairNumbers(1001, 1, new int[]{}, 2));
        assertFalse(existPairNumbers(1001, 2, new int[]{3, 2}, 1));
        assertFalse(existPairNumbers(5, 3, new int[]{3, 2}, 1));
        assertTrue(existPairNumbers(5, 2, new int[]{3, 2}, 1));
    }

    @Test
    public void branchCoverage() {
        assertFalse(existPairNumbers(5, 0, new int[]{}, 0));
        assertFalse(existPairNumbers(2000, 2, new int[]{1000, 1000}, 1));
        assertFalse(existPairNumbers(2000, 3, new int[]{1000, 1000}, 1));
        assertFalse(existPairNumbers(5, 2, new int[]{3, 2}, 0));
    }

    @Test
    public void conditionCoverage() {
        int[] c2 = ArrayUtils.addAll(new int[]{2, 3}, IntStream.rangeClosed(1, 99).map(x -> x = 1).toArray());

        assertFalse(existPairNumbers(1, 1, new int[]{1}, 1));
        assertFalse(existPairNumbers(5, 101, c2, 1));
        assertFalse(existPairNumbers(1, 1, new int[]{1}, -1));
        assertFalse(existPairNumbers(5, 2, new int[]{3, 2}, 2));
        assertFalse(existPairNumbers(2000, 2, new int[]{1000, 1000}, 1));
        assertFalse(existPairNumbers(-2000, 2, new int[]{-1000, -1000}, 1));
        assertFalse(existPairNumbers(5, 3, new int[]{3, 2}, 1));
        assertTrue(existPairNumbers(5, 2, new int[]{3, 2}, 1));
    }

    @Test
    public void pathCoverage() {
        assertFalse(existPairNumbers(1, 1, new int[]{1}, 1));
        assertFalse(existPairNumbers(-2000, 2, new int[]{-1000, -1000}, 1));
        assertFalse(existPairNumbers(5, 3, new int[]{3, 2}, 1));
        assertTrue(existPairNumbers(5, 2, new int[]{3, 2}, 1));
    }

}
