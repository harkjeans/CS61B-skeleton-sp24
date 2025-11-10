package inheritance;

/**
 * ClassName: OurComparable
 * Package: inheritance
 * Description:
 *
 * @Author harkjeans
 * @Create 2025/11/10 21:22
 * @Version 1.0
 */
public interface OurComparable {

    /**
     * return < 0 if I am less than o
     * return 0 if I am equal to o
     * return > 0 if I am greater than 0
     */
    int compareTo(Object o);
}
