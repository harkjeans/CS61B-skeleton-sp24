import deque.*;

import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/**
 * ClassName: Deque61BTest
 * Package: PACKAGE_NAME
 * Description: Deque61BTest
 *
 * @Author harkjeans
 * @Create 2025/11/12 11:06
 * @Version 1.0
 */
public class Deque61BTest {
    /* Flags for iterator tests **/
    @Test
    public void iteratorTestForArrayDeck() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addLast("front");
        ad1.addLast("middle");
        ad1.addLast("back");

        assertThat(ad1).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void forEachTestForArrayDeck() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addLast("front");
        ad1.addLast("middle");
        ad1.addLast("back");

        List<String> result = new ArrayList<>();
        for (String item : ad1) {
            result.add(item);
        }

        assertThat(result).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void circularArrDeckIteratorTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.addFirst(1);
        ad1.addFirst(0);

        assertThat(ad1).containsExactly(0, 1, 2, 3).inOrder();
    }

    @Test
    public void IteratorTestAfterArrDeckResize() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        for (int i = 0; i < 9; i++) {
            ad1.addLast(i);
        }

        assertThat(ad1).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8).inOrder();
    }

    @Test
    public void iteratorTestForLinkedListDeck() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void forEachTestForLLDeck() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        List<String> result = new ArrayList<>();
        for (String item : lld1) {
            result.add(item);
        }

        assertThat(result).containsExactly("front", "middle", "back").inOrder();
    }

    /* Flags for equals tests **/
    @Test
    public void testEqualLinkedListDeque() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    public void testEqualsLLD_withNewStringObjects() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();

        lld1.addLast(new String("front"));
        lld1.addLast(new String("middle"));
        lld1.addLast(new String("back"));

        lld2.addLast(new String("front"));
        lld2.addLast(new String("middle"));
        lld2.addLast(new String("back"));

        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    public void testEqualsLLD_withNullElements() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        LinkedListDeque61B<String> lld2 = new LinkedListDeque61B<>();

        lld1.addLast(null);
        lld1.addLast("normal");

        lld2.addLast(null);
        lld2.addLast("normal");

        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    public void testEqualArrayDeque() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        Deque61B<String> ad2 = new ArrayDeque61B<>();

        ad1.addLast("front");
        ad1.addLast("middle");
        ad1.addLast("back");

        ad2.addLast("front");
        ad2.addLast("middle");
        ad2.addLast("back");

        assertThat(ad1).isEqualTo(ad2);
    }

    @Test
    public void testEqualsAD_withNewStringObjects() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        Deque61B<String> ad2 = new ArrayDeque61B<>();

        ad1.addLast(new String("front"));
        ad1.addLast(new String("middle"));
        ad1.addLast(new String("back"));

        ad2.addLast(new String("front"));
        ad2.addLast(new String("middle"));
        ad2.addLast(new String("back"));

        assertThat(ad1).isEqualTo(ad2);
    }

    @Test
    public void testEqualsAD_withNullElements() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        Deque61B<String> ad2 = new ArrayDeque61B<>();

        ad1.addLast(null);
        ad1.addLast("normal");

        ad2.addLast(null);
        ad2.addLast("normal");

        assertThat(ad1).isEqualTo(ad2);
    }

    /* Flags for toString tests **/
    @Test
    public void toStringTestForLLD() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<Integer> lld2 = new LinkedListDeque61B<>();
        lld1.addLast("A");
        lld1.addLast("B");
        lld1.addLast("C");
        lld2.addLast(1);
        lld2.addLast(2);
        lld2.addLast(3);

        String res1 = lld1.toString();
        String res2 = lld2.toString();

        assertThat(res1).isEqualTo("[A, B, C]");
        assertThat(res2).isEqualTo("[1, 2, 3]");
    }

    @Test
    public void toStringTestLLD_withEmpty() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        String res1 = lld1.toString();

        assertThat(res1).isEqualTo("[]");
    }

    @Test
    public void toStringTestLLD_withNull() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("A");
        lld1.addLast(null);
        lld1.addLast("C");

        String res1 = lld1.toString();

        assertThat(res1).isEqualTo("[A, null, C]");
    }

    @Test
    public void toStringTestForAD() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        Deque61B<Integer> ad2 = new ArrayDeque61B<>();
        ad1.addLast("A");
        ad1.addLast("B");
        ad1.addLast("C");
        ad2.addLast(1);
        ad2.addLast(2);
        ad2.addLast(3);

        String res1 = ad1.toString();
        String res2 = ad2.toString();

        assertThat(res1).isEqualTo("[A, B, C]");
        assertThat(res2).isEqualTo("[1, 2, 3]");
    }

    @Test
    public void toStringTestAD_withEmpty() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        String res1 = ad1.toString();

        assertThat(res1).isEqualTo("[]");
    }

    @Test
    public void toStringTestAD_withNull() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addLast("A");
        ad1.addLast(null);
        ad1.addLast("C");

        String res1 = ad1.toString();

        assertThat(res1).isEqualTo("[A, null, C]");
    }

}
