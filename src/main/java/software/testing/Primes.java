package software.testing;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.lang.Math.sqrt;

public class Primes {

    private static boolean isPrime(int n) {
        return n == 2 || !(n < 2 || n % 2 == 0) && IntStream.rangeClosed(3, (int) sqrt(n))
                .filter(x -> x % 2 != 0)
                .noneMatch(x -> n % x == 0);
    }

    public static boolean solve(int n, int[] a, int i) {

        if (n < 1 || n > 50 || i < 1 || i > n) {
            System.out.println("Conditions not met!");
            return false;
        }

        if (Arrays.stream(a).anyMatch(x -> x < 0)) {
            System.out.println("Conditions not met!");
            return false;
        }

        return Arrays.stream(a).filter(Primes::isPrime).count() >= i;

    }


}
