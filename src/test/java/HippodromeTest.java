import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

class HippodromeTest {

    @Mock
    List<Horse> horses = List.of(
            new Horse("Bucephalus", 2.4),
            new Horse("Ace of Spades", 2.5),
            new Horse("Zephyr", 2.6),
            new Horse("Blaze", 2.7),
            new Horse("Lobster", 2.8),
            new Horse("Pegasus", 2.9),
            new Horse("Cherry", 3));

    @Mock
    Hippodrome hippodrome = new Hippodrome(horses);

    @Test
    void testConstructorWithNullHorses() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void testConstructorWithNullHorsesMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void testConstructorWithEmptyHorses() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void testConstructorWithEmptyHorsesMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void testGetHorses() {

        // Проверяем, что метод getHorses возвращает те же лошади и в том же порядке
        List<Horse> returnedHorses = hippodrome.getHorses();
        assertIterableEquals(horses, returnedHorses);
    }

    @Test
    void testMove_Hippodrome() {

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        // Проверяем, что метод move вызван у всех лошадей
        for (Horse horse : horses) {
            verify(horse, times(1)).move();
        }
    }

    @Test
    void testGetWinner() {

        hippodrome.move();

        // Проверяем, что метод getWinner возвращает лошадь с наибольшим значением distance
        assertEquals("Cherry", hippodrome.getWinner().getName());
    }

    @Test
    void testConstructorWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.5));
    }

    @Test
    void testConstructorWithNullNameErrorMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.5));
        assertEquals("Name cannot be null.", exception.getMessage());
    }


    @Test
    void testConstructorWithBlankNameErrorMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("", 2.5));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void testConstructorWithNegativeSpeed() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", -2.5));
    }

    @Test
    void testConstructorWithNegativeSpeedErrorMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", -2.5));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void testConstructorWithNegativeDistance() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", 2.5, -10.0));
    }

    @Test
    void testConstructorWithNegativeDistanceErrorMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", 2.5, -10.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void testGetName() {
        assertEquals("Ace of Spades", horses.get(1).getName());
    }

    @Test
    void testGetSpeed() {
        assertEquals(2.5, horses.get(1).getSpeed(), 0.01);
    }

    @Test
    void testGetDistance() {
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4, 10),
                new Horse("Ace of Spades", 2.5, 5),
                new Horse("Cherry", 3,11));

        assertEquals(5.0, horses.get(1).getDistance(), 0.01);
    }

    @Test
    void testGetDistanceWithTwoParameterConstructor() {
        assertEquals(0.0, horses.get(1).getDistance(), 0.01);
    }

    @Test
    void testMove_Horse() {
        // Мокируем статический метод getRandomDouble
        try (MockedStatic<Horse> horseStatic = mockStatic(Horse.class)) {
            horseStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            horses.get(1).move();

            assertEquals(1.25, horses.get(1).getDistance(), 0.01);
        }
    }


    @Test
    @Timeout(22) // Установите максимальное время выполнения в секундах
    void testMethodExecutionTime() {

    }

    @Test
    @Disabled
    void testMethod() {

    }
}

