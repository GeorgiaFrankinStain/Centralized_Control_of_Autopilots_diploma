package Logic.FootprintSpaceTime;

import org.junit.Test;

import static org.junit.Assert.*;



//FIXME сделать тут класс рядом, который будет отрисовывать полигоны и проверяемую точку (чтобы было понятней, что не работает


public class PolygonExtendedTest {

    @Test
    public void countPoints() {
        //FIXME FORMALITY
    }

    @Test
    public void getPoint() {
        //FIXME FORMALITY
    }

    @Test
    public void contains() {
        //FIXME FORMALITY
    }

    @Test
    public void enteringPoint() {
        {
            PolygonExtended square = new PolygonExtendedClass();
            square.setPoint(new PointClass(10, 10));
            square.setPoint(new PointClass(10, 20));
            square.setPoint(new PointClass(20, 20));
            square.setPoint(new PointClass(20, 10));

            //==== assertTrue ====================
            {
                boolean actual = square.enteringPoint(new PointClass(15, 15));
                assertTrue(actual);
            }


            {
                boolean actual = square.enteringPoint(new PointClass(10, 20));
                assertTrue(actual);
            }
            {
                boolean actual = square.enteringPoint(new PointClass(20, 10));
                assertTrue(actual);
            }
            {
                boolean actual = square.enteringPoint(new PointClass(20, 20));
                assertTrue(actual);
            }
            {
                boolean actual = square.enteringPoint(new PointClass(10, 10));
                assertTrue(actual);
            }


//            { //points lying on the line included int the polygon? //FIXME FORMALITY
//                boolean actual = square.enteringPoint(new PointClass(10, 11));
//                assertTrue(actual);
//            }


            //==== assertFalse ====================
            {
                boolean actual = square.enteringPoint(new PointClass(15, 25));
                assertFalse(actual);
            }
            {
                boolean actual = square.enteringPoint(new PointClass(-15, -15));
                assertFalse(actual);
            }
            {
                boolean actual = square.enteringPoint(new PointClass(-15, 15));
                assertFalse(actual);
            }
            {
                boolean actual = square.enteringPoint(new PointClass(15, -15));
                assertFalse(actual);
            }
            {
                boolean actual = square.enteringPoint(new PointClass(0, 0));
                assertFalse(actual);
            }
        }


        {
            PolygonExtended triangle = new PolygonExtendedClass();
            triangle.setPoint(new PointClass(10, 10));
            triangle.setPoint(new PointClass(20, 10));
            triangle.setPoint(new PointClass(15, 20));

            //==== assertTrue ====================
            {
                boolean actual = triangle.enteringPoint(new PointClass(10, 10));
                assertTrue(actual);
            }
            {
                boolean actual = triangle.enteringPoint(new PointClass(20, 10));
                assertTrue(actual);
            }
            {
                boolean actual = triangle.enteringPoint(new PointClass(15, 20));
                assertTrue(actual);
            }


            {
                boolean actual = triangle.enteringPoint(new PointClass(15, 13));
                assertTrue(actual);
            }

            //==== assertFalse ====================
            {
                boolean actual = triangle.enteringPoint(new PointClass(20, 16));
                assertFalse(actual);
            }
            {
                boolean actual = triangle.enteringPoint(new PointClass(10, 16));
                assertFalse(actual);
            }
            {
                boolean actual = triangle.enteringPoint(new PointClass(15, 7));
                assertFalse(actual);
            }
        }
        {
            PolygonExtended squareAroundOrigin = new PolygonExtendedClass();
            squareAroundOrigin.setPoint(new PointClass(-10, -10));
            squareAroundOrigin.setPoint(new PointClass(10, -10));
            squareAroundOrigin.setPoint(new PointClass(10, 10));
            squareAroundOrigin.setPoint(new PointClass(-10, 10));

            //==== assertTrue ====================
            {
                boolean actual = squareAroundOrigin.enteringPoint(new PointClass(0, 0));
                assertTrue(actual);
            }
        }
        /*

        {
            PolygonExtended glassesPolygon = new PolygonExtendedClass();
            glassesPolygon.setPoint(new PointClass(0, 15));
            glassesPolygon.setPoint(new PointClass(15, 15));
            glassesPolygon.setPoint(new PointClass(55, 56));
            glassesPolygon.setPoint(new PointClass(52, 15));
            glassesPolygon.setPoint(new PointClass(85, 15));
            glassesPolygon.setPoint(new PointClass(96, 21));
            glassesPolygon.setPoint(new PointClass(100, 85));
            glassesPolygon.setPoint(new PointClass(85, 85));
            glassesPolygon.setPoint(new PointClass(80, 73));
            glassesPolygon.setPoint(new PointClass(71, 53));
            glassesPolygon.setPoint(new PointClass(15, 85));
            glassesPolygon.setPoint(new PointClass(0, 85));

            //==== assertTrue ====================
            {
                boolean actual = glassesPolygon.enteringPoint(new PointClass(19, 46));
                assertTrue(actual);
            }

            {
                boolean actual = glassesPolygon.enteringPoint(new PointClass(79, 32));
                assertTrue(actual); //FIXME TEST_FAILED
            }

            //FIXME добавить тестирование полигона "полумесяц" и лабиринт для алгоритма зональной декстры

            {
                boolean actual = glassesPolygon.enteringPoint(new PointClass(58, 22));
                assertTrue(actual);
            }


            //==== assertFalse ====================
            {
                boolean actual = glassesPolygon.enteringPoint(new PointClass(22, 6));
                assertFalse(actual);
            }

            {
                boolean actual = glassesPolygon.enteringPoint(new PointClass(59, 82));
                assertFalse(actual);
            }
        }

*/

    }
}