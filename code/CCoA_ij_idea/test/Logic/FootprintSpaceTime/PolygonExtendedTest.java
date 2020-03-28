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

            //==== <start> <assertTrue> ====================
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

            //==== <end> <assertTrue> ====================


            //==== <start> <assertFalse> ====================
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

            //==== <end> <assertFalse> ====================
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


        {
            PolygonExtended narrowSquare = new PolygonExtendedClass();
            narrowSquare.setPoint(new PointClass(14, 0));
            narrowSquare.setPoint(new PointClass(16, 0));
            narrowSquare.setPoint(new PointClass(16, 30));
            narrowSquare.setPoint(new PointClass(14, 30));

            //==== <start> <assertFalse> ====================

            {
                boolean actual = narrowSquare.enteringPoint(new PointClass(-10, -10));
                assertFalse(actual);
            }

            {
                Point point = new PointClass(20, 10);
                boolean actual = narrowSquare.enteringPoint(point);


//                LocalToolRenderingPolygon localToolRenderingPolygon =
//                        new LocalToolRenderingPolygon(narrowSquare, point);
//                localToolRenderingPolygon.rendering();


                assertFalse(actual);
            }


            {
                boolean actual = narrowSquare.enteringPoint(new PointClass(10, 20));
                assertFalse(actual);
            }


            //==== <end> <assertFalse> ====================
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

    @Test
    public void intersectionPolygon() {
        {
            PolygonExtended square = new PolygonExtendedClass();
            square.setPoint(new PointClass(10, 10));
            square.setPoint(new PointClass(10, 20));
            square.setPoint(new PointClass(20, 20));
            square.setPoint(new PointClass(20, 10));


            //==== <start> <assertTrue> ====================
            {
                PolygonExtended squarePlus5 = new PolygonExtendedClass();
                squarePlus5.setPoint(new PointClass(15, 15));
                squarePlus5.setPoint(new PointClass(15, 25));
                squarePlus5.setPoint(new PointClass(25, 25));
                squarePlus5.setPoint(new PointClass(25, 15));


                assertTrue(square.intersectionPolygon(squarePlus5));
            }

            {
                PolygonExtended squarePlus10 = new PolygonExtendedClass();
                squarePlus10.setPoint(new PointClass(20, 20));
                squarePlus10.setPoint(new PointClass(20, 30));
                squarePlus10.setPoint(new PointClass(30, 30));
                squarePlus10.setPoint(new PointClass(30, 20));


                assertTrue(square.intersectionPolygon(squarePlus10));
            }


            {//cut of line, cling to each other
                PolygonExtended squareBig = new PolygonExtendedClass();
                squareBig.setPoint(new PointClass(15, 0));
                squareBig.setPoint(new PointClass(15, 30));
                squareBig.setPoint(new PointClass(30, 30));
                squareBig.setPoint(new PointClass(30, 0));


                assertTrue(square.intersectionPolygon(squareBig));
            }

            {//cut of line, cling to each other
                PolygonExtended squareSmall = new PolygonExtendedClass();
                squareSmall.setPoint(new PointClass(14, 0));
                squareSmall.setPoint(new PointClass(16, 0));
                squareSmall.setPoint(new PointClass(16, 30));
                squareSmall.setPoint(new PointClass(14, 30));


                assertTrue(square.intersectionPolygon(squareSmall));
            }

            //==== <end> <assertTrue> ====================


            //==== <start> <assertFalse> ====================
            {
                PolygonExtended squarePlus15 = new PolygonExtendedClass();
                squarePlus15.setPoint(new PointClass(25, 25));
                squarePlus15.setPoint(new PointClass(25, 35));
                squarePlus15.setPoint(new PointClass(35, 35));
                squarePlus15.setPoint(new PointClass(35, 25));


                assertFalse(square.intersectionPolygon(squarePlus15));
            }
            //==== <end> <assertFalse> ====================
        }
    }

    /**
     * Snatch from (https://martin-thoma.com/how-to-check-if-two-line-segments-intersect/) (marking "Testcase T...")
     */
    @Test
    public void intersecionLines() {
        PolygonExtended polygonExtended = new PolygonExtendedClass();
        //==== <start> <assertTrue> ====================
        {
            Point startLine1 = new PointClass(0, -4);
            Point endLine1 = new PointClass(0, 4);

            {
                assertTrue(polygonExtended.intersectionLines(
                        startLine1,
                        endLine1,
                        startLine1,
                        endLine1
                ));
            }


            {
                Point startLine2 = new PointClass(-4, 0); //Stanch Testcase T1
                Point endLine2 = new PointClass(4, 0);

                assertTrue(polygonExtended.intersectionLines(
                        startLine1,
                        endLine1,
                        startLine2,
                        endLine2
                ));
            }
            {
                Point startLine2 = new PointClass(4, 0); //Stanch Testcase T1 inversion
                Point endLine2 = new PointClass(-4, 0); //FIXME CODESTYLE

                assertTrue(polygonExtended.intersectionLines(
                        startLine1,
                        endLine1,
                        startLine2,
                        endLine2
                ));
            }
        }


        {
            Point startLine1 = new PointClass(0, 0);
            Point endLine1 = new PointClass(10, 10);

            {
                Point startLine2 = new PointClass(2, 2); //Stanch Testcase T2
                Point endLine2 = new PointClass(16, 4);

                assertTrue(polygonExtended.intersectionLines(
                        startLine1,
                        endLine1,
                        startLine2,
                        endLine2
                ));
            }


            {
                Point startLine2 = new PointClass(2, 2); //Stanch Testcase T2
                Point endLine2 = new PointClass(16, 4);

                assertTrue(polygonExtended.intersectionLines(
                        startLine1,
                        endLine1,
                        startLine2,
                        endLine2
                ));
            }


            {
                Point endLine2 = new PointClass(2, 2); //Stanch Testcase T2 inversion
                Point startLine2 = new PointClass(16, 4);

                assertTrue(polygonExtended.intersectionLines(
                        startLine1,
                        endLine1,
                        startLine2,
                        endLine2
                ));
            }
        }

        {
            Point startLine1 = new PointClass(0, 0);
            Point endLine1 = new PointClass(4, 4);

            {
                Point startLine2 = new PointClass(0, 4);
                Point endLine2 = new PointClass(4, 0);

                assertTrue(polygonExtended.intersectionLines(
                        startLine1,
                        endLine1,
                        startLine2,
                        endLine2
                ));
            }
        }


        {
            Point startLine1 = new PointClass(1, 1);
            Point endLine1 = new PointClass(5, 5);

            {
                Point startLine2 = new PointClass(1, 5);
                Point endLine2 = new PointClass(5, 1);

                assertTrue(polygonExtended.intersectionLines(
                        startLine1,
                        endLine1,
                        startLine2,
                        endLine2
                ));
            }
        }


        //==== <end> <assertTrue> ====================
        //==== <start> <assertFalse> ====================




        {
            Point startLine1 = new PointClass(1, 1);
            Point endLine1 = new PointClass(5, 5);

            {
                Point startLine2 = new PointClass(100, 100);
                Point endLine2 = new PointClass(200, 200);

                assertFalse(polygonExtended.intersectionLines(
                        startLine1,
                        endLine1,
                        startLine2,
                        endLine2
                ));
            }
        }
        //==== <end> <assertFalse> ====================
    }

    @Test
    public void intersecionLine() {
        {
            PolygonExtended square = new PolygonExtendedClass();
            square.setPoint(new PointClass(10, 10));
            square.setPoint(new PointClass(10, 20));
            square.setPoint(new PointClass(20, 20));
            square.setPoint(new PointClass(20, 10));

            //==== <start> <assertTrue> ====================
            {
                Point startLine = new PointClass(0, 0);
                Point endLine = new PointClass(40, 40);

                assertTrue(square.intersecionLine(startLine, endLine));
            }
            //==== <end> <assertTrue> ====================

            //==== <start> <assertFalse> ====================
            {
                Point startLine = new PointClass(0, -4);
                Point endLine = new PointClass(0, 4);

                assertFalse(square.intersecionLine(startLine, endLine));
            }
            //==== <end> <assertFalse> ====================
        }
    }
}