package edu.uoc.trip.model.cells;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
class MovableTest {

    private final Class<Movable> ownClass = Movable.class;

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Interface definition")
    void checkInterfaceSanitfy() {
        //Interface declaration
        assertTrue(ownClass.isInterface());
        int modifiers = ownClass.getModifiers();
        assertTrue(Modifier.isPublic(modifiers));
        assertFalse(Modifier.isStatic(modifiers));
        assertFalse(Modifier.isFinal(modifiers));
        assertEquals("edu.uoc.trip.model.cells",ownClass.getPackageName());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    void checkFieldsSanity() {
        assertEquals(0, ownClass.getDeclaredFields().length);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        assertEquals(1,ownClass.getDeclaredMethods().length);

        assertTrue(Arrays.stream(ownClass.getDeclaredMethods()).noneMatch(Method::isDefault));
    }
}
