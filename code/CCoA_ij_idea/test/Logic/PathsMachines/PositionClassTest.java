package Logic.PathsMachines;

import Logic.FootprintSpaceTime.PointClass;
import Logic.Position;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class PositionClassTest {

    private double mediumAngleExtractionFromPrivate(double angle1, double angle2) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Position anyPosition = new PositionClass(new PointClass(0, 0), 0);

        Method method = PositionClass.class.getDeclaredMethod("mediumAngle", double.class, double.class);
        method.setAccessible(true);
        double actual = (double) method.invoke(anyPosition, angle1, angle2);

        return actual;
    }

    @Test
    void privateTest_mediumAngle_two90DegreeAngles() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(1.54, mediumAngleExtractionFromPrivate(1.54, 1.54));
    }

    @Test
    void privateTest_mediumAngle_two0DegreeAngles() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(0, mediumAngleExtractionFromPrivate(0, 0));
    }

    @Test
    void privateTest_mediumAngle_from0To180Degree() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(Math.PI / 2, mediumAngleExtractionFromPrivate(Math.PI, 0));
    }

    @Test
    void privateTest_mediumAngle_from270To0Degree() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(Math.PI * -0.25, mediumAngleExtractionFromPrivate(Math.PI * -0.5, 0));
    }

    @Test
    void privateTest_mediumAngle_two90To270DegreeAngles() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(Math.PI, mediumAngleExtractionFromPrivate(Math.PI / 2, Math.PI * 1.5));
    }

    @Test
    void privateTest_mediumAngle_two135To225DegreeAngles() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(Math.PI, mediumAngleExtractionFromPrivate(Math.PI * 0.75, Math.PI * 1.25));
    }
    @Test
    void privateTest_mediumAngle_two135To45DegreeAngles() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(Math.PI / 2, mediumAngleExtractionFromPrivate(Math.PI * 0.75, Math.PI * 0.25));
    }
}