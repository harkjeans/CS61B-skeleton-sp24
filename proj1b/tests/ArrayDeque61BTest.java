import deque.LinkedListDeque61B;
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
    }

    @Test
    public void addLastFromEmptyTest() {
    }

    @Test
    public void addFirstNonEmptyTest() {
    }

    @Test
    public void addLastNonEmptyTest() {
    }

    @Test
    public void addFirstTriggerResizeTest() {

    }

    @Test
    public void addLastTriggerResizeTest() {

    }

    /* Flags for add after remove tests **/
    @Test
    public void addFirstAfterRemoveToEmptyTest() {

    }

    @Test
    public void addLastAfterRemoveToEmptyTest() {

    }

    /* Flags for remove tests **/
    @Test
    public void removeFirstTest() {
    }

    @Test
    public void removeLastTest() {
    }

    @Test
    public void removeFirstToEmptyTest() {
    }

    @Test
    public void removeLastToEmptyTest() {
    }

    @Test
    public void removeFirstToOneTest() {
    }

    @Test
    public void removeLastToOneTest() {
    }

    @Test
    public void removeFirstTriggerResizeTest() {

    }

    @Test
    public void removeLastTriggerResizeTest() {

    }

    /* Flags for get tests **/
    @Test
    public void getValidTest() {
    }

    @Test
    public void getOOBLargeTest() {
    }

    @Test
    public void getOOBNegTest() {
    }

    @Test
    public void getRecursiveValidTest() {
    }

    @Test
    public void getRecursiveOOBLargeTest() {
    }

    @Test
    public void getRecursiveOOBNegTest() {
    }

    /* Flags for isEmpty tests **/
    @Test
    public void isEmptyTrueTest() {
    }

    @Test
    public void isEmptyFalseTest() {
    }

    /* Flags for size tests **/
    @Test
    public void sizeTestBasic() {
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
    }

    @Test
    public void toListNonEmptyTest() {
    }

    /* Flags for advanced resize tests **/
    @Test
    public void resizeUpAndDownTest() {

    }
}
