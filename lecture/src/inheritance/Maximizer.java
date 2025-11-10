package inheritance;

import java.util.Comparator;

/**
 * ClassName: Maximizer
 * Package: inheritance
 * Description:
 *
 * @Author harkjeans
 * @Create 2025/11/10 21:15
 * @Version 1.0
 */
public class Maximizer {
    public static OurComparable max(OurComparable[] items) {
        int maxDex = 0;
        for (int i = 0; i < items.length; i++) {
            int cmp = items[i].compareTo(items[maxDex]);
            if (cmp > 0) {
                maxDex = i;
            }
        }
        return items[maxDex];
    }

    public static void main(String[] args) {
        Dog[] dogs = new Dog[]{new Dog("Jack", 10), new Dog("Hark", 12),
                new Dog("Lucky", 15)};

        Dog maxDog = (Dog) Maximizer.max(dogs);
        maxDog.bark();

        Comparator<Dog> nc = Dog.getNameComparator();
        int cmp = nc.compare(new Dog("Jack", 10), new Dog("Hark", 12));
        System.out.println(cmp);
    }
}
