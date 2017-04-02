package software.testing;

import org.junit.Test;

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

}
