package inheritance;

import java.util.function.Function;

/**
 * ClassName: HigherOrderFunction
 * Package: inheritance
 * Description: how to implement higher-order functions in Java
 * like in Python:
 * def tenX(x):
 *     return 10*x
 * def do_twice(f, x):
 *     return f(f(x))
 *
 * >>> print(do_twice(tenX, 2)):
 * >>> 200
 *
 * @Author harkjeans
 * @Create 2025/11/10 20:39
 * @Version 1.0
 */
public class HigherOrderFunction {

    // Java 7-
    public static int doTwice(IntUnaryFunction f, int x) {
        return f.apply(f.apply(x));
    }

    // Java 8+
    public static int tenX(int x) {
        return 10 * x;
    }

    public static int doTwice(Function<Integer, Integer> f, int x) {
        return f.apply(f.apply(x));
    }

    public static void main(String[] args) {
        int result1 = doTwice(new TenX(), 2);
        System.out.println(result1);

        //int result2 = doTwice(HigherOrderFunction::tenX, 2);
        //System.out.println(result2);
    }
}
