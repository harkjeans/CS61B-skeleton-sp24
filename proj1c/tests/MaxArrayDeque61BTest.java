import org.junit.jupiter.api.*;

import java.util.Comparator;
import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
    }

    @Test
    public void testMaxWithCustomComparator() {
        Comparator<String> lengthComparator = (s1, s2) -> s1.length() - s2.length();

        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<String>(Comparator.naturalOrder());
        mad.addLast("apple");
        mad.addLast("banana");
        mad.addLast("mango");

        String res1 = mad.max();
        String res2 = mad.max(lengthComparator);

        assertThat(res1).isEqualTo("mango");
        assertThat(res2).isEqualTo("banana");
    }

    @Test
    public void testMaxWithEdgeCases() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<Integer>(Comparator.naturalOrder());
        assertThat(mad.max()).isNull();

        mad.addLast(41);
        assertThat(mad.max()).isEqualTo(41);

        mad.addLast(41);
        assertThat(mad.toList()).containsExactly(41, 41);
        assertThat(mad.max()).isEqualTo(41);
    }
}
