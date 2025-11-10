import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

    /* Flags for add tests **/
    @Test
    public void addFirstFromEmptyTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        ad1.addFirst(1);

        assertThat(ad1.toList()).containsExactly(1);
    }

    @Test
    public void addLastFromEmptyTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        ad1.addLast(1);

        assertThat(ad1.toList()).containsExactly(1);
    }

    @Test
    public void addFirstNonEmptyTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);

        ad1.addFirst(2);

        assertThat(ad1.toList()).containsExactly(2, 1).inOrder();
        assertThat(ad1.size()).isEqualTo(2);
        assertThat(ad1.isEmpty()).isFalse();
    }

    @Test
    public void addLastNonEmptyTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(1);

        ad1.addLast(2);

        assertThat(ad1.toList()).containsExactly(1, 2).inOrder();
        assertThat(ad1.size()).isEqualTo(2);
        assertThat(ad1.isEmpty()).isFalse();
    }

    @Test
    public void addFirstTriggerResizeTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addFirst(4);
        ad1.addFirst(5);
        ad1.addFirst(6);
        ad1.addFirst(7);
        ad1.addFirst(8);
        assertThat(ad1.size()).isEqualTo(8);
        assertThat(ad1.toList()).containsExactly(8, 7, 6, 5, 4, 3, 2, 1).inOrder();

        ad1.addFirst(9);
        assertThat(ad1.size()).isEqualTo(9);
        assertThat(ad1.isEmpty()).isFalse();
        assertThat(ad1.toList()).containsExactly(9, 8, 7, 6, 5, 4, 3, 2, 1).inOrder();
        ad1.addFirst(0);
        assertThat(ad1.size()).isEqualTo(10);
        assertThat(ad1.isEmpty()).isFalse();
        assertThat(ad1.toList()).containsExactly(0, 9, 8, 7, 6, 5, 4, 3, 2, 1).inOrder();
    }

    @Test
    public void addLastTriggerResizeTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addFirst(4);
        ad1.addFirst(5);
        ad1.addFirst(6);
        ad1.addFirst(7);
        ad1.addFirst(8);
        assertThat(ad1.size()).isEqualTo(8);
        assertThat(ad1.toList()).containsExactly(8, 7, 6, 5, 4, 3, 2, 1).inOrder();

        ad1.addLast(9);
        assertThat(ad1.size()).isEqualTo(9);
        assertThat(ad1.isEmpty()).isFalse();
        assertThat(ad1.toList()).containsExactly(8, 7, 6, 5, 4, 3, 2, 1, 9).inOrder();
        ad1.addLast(0);
        assertThat(ad1.size()).isEqualTo(10);
        assertThat(ad1.isEmpty()).isFalse();
        assertThat(ad1.toList()).containsExactly(8, 7, 6, 5, 4, 3, 2, 1, 9, 0).inOrder();
    }

    /* Flags for add after remove tests **/
    @Test
    public void addFirstAfterRemoveToEmptyTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(1);
        ad1.removeFirst();
        assertThat(ad1.toList()).isEmpty();

        ad1.addFirst(2);

        assertThat(ad1.toList()).containsExactly(2);
        assertThat(ad1.size()).isEqualTo(1);
    }

    @Test
    public void addLastAfterRemoveToEmptyTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(1);
        ad1.removeFirst();
        assertThat(ad1.toList()).isEmpty();

        ad1.addLast(2);

        assertThat(ad1.toList()).containsExactly(2);
        assertThat(ad1.size()).isEqualTo(1);
    }

    /* Flags for remove tests **/
    @Test
    public void removeFirstTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addLast("A");
        ad1.addLast("B");
        ad1.addLast("C");

        String firstItem = ad1.removeFirst();

        assertThat(firstItem).isEqualTo("A");
        assertThat(ad1.toList()).containsExactly("B", "C").inOrder();
        assertThat(ad1.size()).isEqualTo(2);
    }

    @Test
    public void removeLastTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addLast("A");
        ad1.addLast("B");
        ad1.addLast("C");

        String lastItem = ad1.removeLast();

        assertThat(lastItem).isEqualTo("C");
        assertThat(ad1.toList()).containsExactly("A", "B").inOrder();
        assertThat(ad1.size()).isEqualTo(2);
    }

    @Test
    public void removeFirstToEmptyTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(1);

        Integer firstItem = ad1.removeFirst();

        assertThat(firstItem).isEqualTo(1);
        assertThat(ad1.toList()).isEmpty();
        assertThat(ad1.size()).isEqualTo(0);
    }

    @Test
    public void removeLastToEmptyTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(1);

        Integer lastItem = ad1.removeLast();

        assertThat(lastItem).isEqualTo(1);
        assertThat(ad1.toList()).isEmpty();
        assertThat(ad1.size()).isEqualTo(0);
    }

    @Test
    public void removeFirstToOneTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(1);
        ad1.addLast(2);

        Integer firstItem = ad1.removeFirst();

        assertThat(firstItem).isEqualTo(1);
        assertThat(ad1.toList()).containsExactly(2);
        assertThat(ad1.size()).isEqualTo(1);
    }

    @Test
    public void removeLastToOneTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(1);
        ad1.addLast(2);

        Integer lastItem = ad1.removeLast();

        assertThat(lastItem).isEqualTo(2);
        assertThat(ad1.toList()).containsExactly(1);
        assertThat(ad1.size()).isEqualTo(1);
    }

    @Test
    public void removeFirstTriggerResizeTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addLast("C");
        ad1.addLast("S");
        ad1.addLast("6");
        ad1.addLast("1");
        ad1.addLast("B");
        ad1.addLast("G");
        ad1.addLast("O");
        ad1.addLast("O");
        ad1.addLast("D");

        String firstItem = ad1.removeFirst();

        assertThat(firstItem).isEqualTo("C");
        assertThat(ad1.size()).isEqualTo(8);
        assertThat(ad1.toList())
                .containsExactly("S", "6", "1", "B", "G", "O", "O", "D")
                .inOrder();
    }

    @Test
    public void removeLastTriggerResizeTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addLast("C");
        ad1.addLast("S");
        ad1.addLast("6");
        ad1.addLast("1");
        ad1.addLast("B");
        ad1.addLast("G");
        ad1.addLast("O");
        ad1.addLast("O");
        ad1.addLast("D");

        String lastItem = ad1.removeLast();

        assertThat(lastItem).isEqualTo("D");
        assertThat(ad1.size()).isEqualTo(8);
        assertThat(ad1.toList())
                .containsExactly("C", "S", "6", "1", "B", "G", "O", "O")
                .inOrder();
    }

    /* Flags for get tests **/
    @Test
    public void getValidTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        assertThat(ad1.get(0)).isNull();
        ad1.addLast("A");
        ad1.addLast("B");
        assertThat(ad1.get(0)).isEqualTo("A");
        assertThat(ad1.get(1)).isEqualTo("B");
    }

    @Test
    public void getOOBLargeTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addLast("A");
        ad1.addLast("B");

        assertThat(ad1.get(2)).isNull();
        assertThat(ad1.get(10)).isNull();
        assertThat(ad1.get(100)).isNull();
    }

    @Test
    public void getOOBNegTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addLast("A");
        ad1.addLast("B");

        assertThat(ad1.get(-1)).isNull();
        assertThat(ad1.get(-10)).isNull();
    }

    @Test
    public void getTriggerResizeTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addLast("C");
        ad1.addLast("S");
        ad1.addLast("6");
        ad1.addLast("1");
        ad1.addLast("B");
        ad1.addLast("G");
        ad1.addLast("O");
        ad1.addLast("O");
        ad1.addLast("D");

        assertThat(ad1.get(0)).isEqualTo("C");
        assertThat(ad1.get(1)).isEqualTo("S");
        assertThat(ad1.get(8)).isEqualTo("D");
        assertThat(ad1.get(-1)).isNull();
        assertThat(ad1.get(9)).isNull();
    }

    /* Flags for isEmpty tests **/
    @Test
    public void isEmptyTrueTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        assertThat(ad1.isEmpty()).isTrue();
    }

    @Test
    public void isEmptyFalseTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);

        assertThat(ad1.isEmpty()).isFalse();
    }

    /* Flags for size tests **/
    @Test
    public void sizeTestBasic() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);

        assertThat(ad1.size()).isEqualTo(1);
    }

    @Test
    public void sizeAfterRemoveToEmptyTest() {
    }

    @Test
    public void sizeAfterRemoveFromEmptyTest() {
    }

    /* Flags for toList tests **/
    @Test
    public void toListEmptyTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        assertThat(ad1.toList()).isEmpty();
    }

    @Test
    public void toListNonEmptyTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        ad1.addFirst("A");
        assertThat(ad1.toList()).containsExactly("A");
        ad1.addFirst("B");
        assertThat(ad1.toList()).containsExactly("B", "A").inOrder();
        ad1.addFirst("C");
        assertThat(ad1.toList()).containsExactly("C", "B", "A").inOrder();
    }

    /* Flags for advanced resize tests **/
    @Test
    public void resizeUpAndDownTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        // fill the deque to trigger resize up
        for (int i = 0; i < 9; i++) {
            ad1.addLast(i);
        }
        assertThat(ad1.size()).isEqualTo(9);
        for (int i = 0; i < 9; i++) {
            assertThat(ad1.get(i)).isEqualTo(i);
        }

        // continue adding to ensure resize worked correctly
        for (int i = 9; i < 20; i++) {
            ad1.addLast(i);
        }
        assertThat(ad1.size()).isEqualTo(20);

        // remove elements to trigger resize down (capacity >= 16 && size < capacity / 4)
        // current_capacity == 32, current_size == 20
        for (int i = 0; i < 13; i++) {
            ad1.removeFirst();
        }
        assertThat(ad1.size()).isEqualTo(7);
        assertThat(ad1.toList())
                .containsExactly(13, 14, 15, 16, 17, 18 ,19)
                .inOrder();

        // current_capacity == 16, current_size == 7
        for (int i = 0; i < 4; i++) {
            ad1.removeLast();
        }
        assertThat(ad1.size()).isEqualTo(3);
        assertThat(ad1.toList())
                .containsExactly(13, 14, 15)
                .inOrder();
    }
}
