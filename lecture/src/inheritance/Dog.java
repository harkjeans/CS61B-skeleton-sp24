package inheritance;

import java.util.Comparator;

/**
 * ClassName: Dog
 * Package: inheritance
 * Description:
 *
 * @Author harkjeans
 * @Create 2025/11/10 21:24
 * @Version 1.0
 */
public class Dog implements OurComparable {
    public String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println(name + " say: bark");
    }

    @Override
    public int compareTo(Object o) {
        Dog otherDog = (Dog) o;
        return this.size - otherDog.size;
    }

    private static class NameComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog o1, Dog o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }
}
