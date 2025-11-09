import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.(Arrange -> Act -> Assert)

    /* Flags for add tests **/
    @Test
    public void addFirstFromEmptyTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("first");

        assertThat(lld1.isEmpty()).isFalse();
        assertThat(lld1.size()).isEqualTo(1);
        assertThat(lld1.toList()).containsExactly("first");
    }

    @Test
    public void addLastFromEmptyTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("last");

        assertThat(lld1.isEmpty()).isFalse();
        assertThat(lld1.size()).isEqualTo(1);
        assertThat(lld1.toList()).containsExactly("last");
    }

    @Test
    public void addFirstNonEmptyTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();
        lld1.addLast("existing");
        lld2.addFirst("existing");

        lld1.addFirst("first");
        lld2.addFirst("first");

        assertThat(lld1.size()).isEqualTo(2);
        assertThat(lld2.size()).isEqualTo(2);
        assertThat(lld1.toList())
                .containsExactly("first", "existing")
                .inOrder();
        assertThat(lld2.toList())
                .containsExactly("first", "existing")
                .inOrder();
    }

    @Test
    public void addLastNonEmptyTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();
        lld1.addLast("existing");
        lld2.addFirst("existing");

        lld1.addLast("last");
        lld2.addLast("last");

        assertThat(lld1.size()).isEqualTo(2);
        assertThat(lld2.size()).isEqualTo(2);
        assertThat(lld1.toList())
                .containsExactly("existing", "last")
                .inOrder();
        assertThat(lld2.toList())
                .containsExactly("existing", "last")
                .inOrder();
    }

    /* Flags for isEmpty tests **/
    @Test
    public void isEmptyTrueTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.isEmpty()).isTrue();
    }

    @Test
    public void isEmptyFalseTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("existing");

        assertThat(lld1.isEmpty()).isFalse();
    }

    /* Flags for toList tests **/
    @Test
    public void toListEmptyTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.toList()).isEmpty();
    }

    @Test
    public void toListNonEmptyTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("existing");

        assertThat(lld1.toList()).containsExactly("existing");
    }

    /* Flags for size tests **/
    @Test
    public void sizeTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.size()).isEqualTo(0);

        lld1.addFirst("first");
        assertThat(lld1.size()).isEqualTo(1);

        lld1.addLast("last");
        assertThat(lld1.size()).isEqualTo(2);
    }

    @Test
    public void sizeAfterRemoveToEmptyTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("A");
        lld1.addLast("B");
        lld1.addLast("C");

        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();

        assertThat(lld1.size()).isEqualTo(0);
    }

    @Test
    public void sizeAfterRemoveFromEmptyTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.removeFirst();

        assertThat(lld1.size()).isEqualTo(0);
    }

    /* Flags for get tests **/
    @Test
    public void getValidTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("A");
        lld1.addLast("B");
        lld1.addLast("C");
        lld1.addLast("D");

        assertThat(lld1.get(0)).isEqualTo("A");
        assertThat(lld1.get(1)).isEqualTo("B");
        assertThat(lld1.get(2)).isEqualTo("C");
        assertThat(lld1.get(3)).isEqualTo("D");
    }

    @Test
    public void getOOBLargeTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        assertThat(lld1.get(3)).isNull();
        assertThat(lld1.get(10)).isNull();
        assertThat(lld1.get(100)).isNull();
    }

    @Test
    public void getOOBNegTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("A");
        lld1.addLast("B");

        assertThat(lld1.get(-1)).isNull();
        assertThat(lld1.get(-10)).isNull();
        assertThat(lld1.get(-100)).isNull();
    }

    @Test
    public void getRecursiveValidTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("A");
        lld1.addLast("B");
        lld1.addLast("C");
        lld1.addLast("D");

        assertThat(lld1.getRecursive(0)).isEqualTo("A");
        assertThat(lld1.getRecursive(1)).isEqualTo("B");
        assertThat(lld1.getRecursive(2)).isEqualTo("C");
        assertThat(lld1.getRecursive(3)).isEqualTo("D");
    }

    @Test
    public void getRecursiveOOBLargeTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        assertThat(lld1.getRecursive(3)).isNull();
        assertThat(lld1.getRecursive(10)).isNull();
        assertThat(lld1.getRecursive(100)).isNull();
    }

    @Test
    public void getRecursiveOOBNegTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("A");
        lld1.addLast("B");

        assertThat(lld1.getRecursive(-1)).isNull();
        assertThat(lld1.getRecursive(-10)).isNull();
        assertThat(lld1.getRecursive(-100)).isNull();
    }

    /* Flags for remove tests **/
    @Test
    public void removeFirstTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("A");
        lld1.addLast("B");
        lld1.addLast("C");

        assertThat(lld1.removeFirst()).isEqualTo("A");
        assertThat(lld1.toList()).containsExactly("B", "C").inOrder();
        assertThat(lld1.get(0)).isEqualTo("B");
    }

    @Test
    public void removeLastTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("A");
        lld1.addLast("B");
        lld1.addLast("C");

        assertThat(lld1.removeLast()).isEqualTo("C");
        assertThat(lld1.toList()).containsExactly("A", "B").inOrder();
        assertThat(lld1.get(0)).isEqualTo("A");
    }

    @Test
    public void removeFirstToEmptyTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);

        assertThat(lld1.removeFirst()).isEqualTo(1);
        assertThat(lld1.size()).isEqualTo(0);
        assertThat(lld1.toList()).isEmpty();
        assertThat(lld1.get(0)).isNull();
    }

    @Test
    public void removeLastToEmptyTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);

        assertThat(lld1.removeLast()).isEqualTo(1);
        assertThat(lld1.size()).isEqualTo(0);
        assertThat(lld1.toList()).isEmpty();
        assertThat(lld1.get(0)).isNull();
    }

    @Test
    public void removeFirstToOneTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);

        assertThat(lld1.removeFirst()).isEqualTo(1);
        assertThat(lld1.size()).isEqualTo(1);
        assertThat(lld1.toList()).containsExactly(2);
        assertThat(lld1.get(0)).isEqualTo(2);
    }

    @Test
    public void removeLastToOneTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);

        assertThat(lld1.removeLast()).isEqualTo(2);
        assertThat(lld1.size()).isEqualTo(1);
        assertThat(lld1.toList()).containsExactly(1);
        assertThat(lld1.get(0)).isEqualTo(1);
    }

    /* Flags for add after remove tests **/
    @Test
    public void addFirstAfterRemoveToEmptyTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);

        lld1.removeFirst();
        lld1.addFirst(2);
        lld1.addFirst(3);

        assertThat(lld1.size()).isEqualTo(2);
        assertThat(lld1.toList()).containsExactly(3, 2).inOrder();
    }

    @Test
    public void addLastAfterRemoveToEmptyTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);

        lld1.removeFirst();
        lld1.addLast(2);
        lld1.addLast(3);

        assertThat(lld1.size()).isEqualTo(2);
        assertThat(lld1.toList()).containsExactly(2, 3).inOrder();
    }
}