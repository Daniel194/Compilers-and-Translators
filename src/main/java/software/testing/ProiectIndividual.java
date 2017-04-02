package software.testing;

/**
 * Created by daniellungu on 02/04/2017.
 */
public class ProiectIndividual {

    public boolean existPairNumber(int a, int[] b) {

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (i != j && b[i] + b[j] == a) {
                    return true;
                }
            }
        }

        return false;
    }

}
