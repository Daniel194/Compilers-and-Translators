package software.testing;

/**
 * Created by daniellungu on 02/04/2017.
 */
public class ProiectIndividual {

    public static boolean existPairNumbers(int a, int n, int[] b, int x) {

        int count = 0;

        if (n < 2 || n > 100 || x < 0 || x > n / 2) {
            System.out.println("Conditions not met!");
            return false;
        }

        if (a > 1000 || a < -1000) {
            System.out.println("Conditions not met!");
            return false;
        }

        if (b.length != n) {
            System.out.println("Conditions not met!");
            return false;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (b[i] + b[j] == a) {
                    count++;
                }
            }
        }

        return count == x;
    }

}
