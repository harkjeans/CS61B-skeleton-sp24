/**
 * ClassName: Dessert
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author harkjeans
 * @Create 2025/11/6 11:59
 * @Version 1.0
 */
public class Dessert {

    private int flavor;
    private int price;
    private static int numDessert;

    public Dessert(int flavor, int price) {
        this.flavor = flavor;
        this.price = price;
        numDessert++;
    }

    public void printDessert() {
        System.out.println(flavor + " " + price + " " + numDessert);
    }

    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }

}
