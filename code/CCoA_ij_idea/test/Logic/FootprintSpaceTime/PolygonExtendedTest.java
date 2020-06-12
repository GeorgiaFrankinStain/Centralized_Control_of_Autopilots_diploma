package Logic.FootprintSpaceTime;

import org.junit.Test;

import static org.junit.Assert.*;




public class PolygonExtendedTest {

    @Test
    public void countPoints() {
        //==== <start> <assertTrue> ====================
        {
            PolygonExtended square = new PolygonExtendedClass();
            square.addPoint(new PointClass(10, 10));
            square.addPoint(new PointClass(10, 20));
            square.addPoint(new PointClass(20, 20));
            square.addPoint(new PointClass(20, 10));

            {
                int actual = square.getCountPoints();
                int expected = 4;
                assertEquals(expected, actual);
            }
        }
        {
            PolygonExtended square = new PolygonExtendedClass();
            square.addPoint(new PointClass(10, 10));

            {
                int actual = square.getCountPoints();
                int expected = 1;
                assertEquals(expected, actual);
            }
        }
        {
            PolygonExtended square = new PolygonExtendedClass();

            {
                int actual = square.getCountPoints();
                int expected = 0;
                assertEquals(expected, actual);
            }
        }
        //==== <end> <assertTrue> ====================
    }

    @Test
    public void getPoint() {
        {

            PolygonExtended square = new PolygonExtendedClass();
            Point[] arrayPoints = {
                    new PointClass(10, 10),
                    new PointClass(10, 20),
                    new PointClass(20, 20),
                    new PointClass(20, 10),
            };


            for (Point point : arrayPoints) {
                square.addPoint(point);
            }



            //==== <start> <assertTrue> ====================
            {
                for (int i = 0; i < arrayPoints.length; i++) {
                    Point actual = square.getPoint(i);
                    Point expected = arrayPoints[i];
                    assertEquals(expected, actual);
                }
            }
            //==== <end> <assertTrue> ====================
        }
    }

    @Test
    public void contains() {
        {
            PolygonExtended square = new PolygonExtendedClass();
            square.addPoint(new PointClass(10, 10));
            square.addPoint(new PointClass(10, 20));
            square.addPoint(new PointClass(20, 20));
            square.addPoint(new PointClass(20, 10));

            //==== <start> <assertTrue> ====================
            {
                boolean actual = square.contains(new PointClass(10, 10));
                assertTrue(actual);
            }
            {
                boolean actual = square.contains(new PointClass(10, 20));
                assertTrue(actual);
            }
            {
                boolean actual = square.contains(new PointClass(20, 20));
                assertTrue(actual);
            }
            {
                boolean actual = square.contains(new PointClass(20, 10));
                assertTrue(actual);
            }
            //==== <end> <assertTrue> ====================

            //==== <start> <assertFalse> ====================
            {
                boolean actual = square.contains(new PointClass(10.1, 10));
                assertFalse(actual);
            }
            //==== <end> <assertFalse> ====================
        }
    }

    @Test
    public void enteringPoint() {
        {
            PolygonExtended square = new PolygonExtendedClass();
            square.addPoint(new PointClass(10, 10));
            square.addPoint(new PointClass(10, 20));
            square.addPoint(new PointClass(20, 20));
            square.addPoint(new PointClass(20, 10));

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
            triangle.addPoint(new PointClass(10, 10));
            triangle.addPoint(new PointClass(20, 10));
            triangle.addPoint(new PointClass(15, 20));

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
            squareAroundOrigin.addPoint(new PointClass(-10, -10));
            squareAroundOrigin.addPoint(new PointClass(10, -10));
            squareAroundOrigin.addPoint(new PointClass(10, 10));
            squareAroundOrigin.addPoint(new PointClass(-10, 10));

            //==== assertTrue ====================
            {
                boolean actual = squareAroundOrigin.enteringPoint(new PointClass(0, 0));
                assertTrue(actual);
            }
        }


        {
            PolygonExtended narrowSquare = new PolygonExtendedClass();
            narrowSquare.addPoint(new PointClass(14, 0));
            narrowSquare.addPoint(new PointClass(16, 0));
            narrowSquare.addPoint(new PointClass(16, 30));
            narrowSquare.addPoint(new PointClass(14, 30));

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
            glassesPolygon.addPoint(new PointClass(0, 15));
            glassesPolygon.addPoint(new PointClass(15, 15));
            glassesPolygon.addPoint(new PointClass(55, 56));
            glassesPolygon.addPoint(new PointClass(52, 15));
            glassesPolygon.addPoint(new PointClass(85, 15));
            glassesPolygon.addPoint(new PointClass(96, 21));
            glassesPolygon.addPoint(new PointClass(100, 85));
            glassesPolygon.addPoint(new PointClass(85, 85));
            glassesPolygon.addPoint(new PointClass(80, 73));
            glassesPolygon.addPoint(new PointClass(71, 53));
            glassesPolygon.addPoint(new PointClass(15, 85));
            glassesPolygon.addPoint(new PointClass(0, 85));

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
            square.addPoint(new PointClass(10, 10));
            square.addPoint(new PointClass(10, 20));
            square.addPoint(new PointClass(20, 20));
            square.addPoint(new PointClass(20, 10));


            //==== <start> <assertTrue> ====================
            {
                PolygonExtended squarePlus5 = new PolygonExtendedClass();
                squarePlus5.addPoint(new PointClass(15, 15));
                squarePlus5.addPoint(new PointClass(15, 25));
                squarePlus5.addPoint(new PointClass(25, 25));
                squarePlus5.addPoint(new PointClass(25, 15));


                assertTrue(square.intersectionPolygon(squarePlus5));
            }

            {
                PolygonExtended squarePlus10 = new PolygonExtendedClass();
                squarePlus10.addPoint(new PointClass(20, 20));
                squarePlus10.addPoint(new PointClass(20, 30));
                squarePlus10.addPoint(new PointClass(30, 30));
                squarePlus10.addPoint(new PointClass(30, 20));


                assertTrue(square.intersectionPolygon(squarePlus10));
            }


            {//cut of line, cling to each other
                PolygonExtended squareBig = new PolygonExtendedClass();
                squareBig.addPoint(new PointClass(15, 0));
                squareBig.addPoint(new PointClass(15, 30));
                squareBig.addPoint(new PointClass(30, 30));
                squareBig.addPoint(new PointClass(30, 0));


                assertTrue(square.intersectionPolygon(squareBig));
            }

            {//cut of line, cling to each other
                PolygonExtended squareSmall = new PolygonExtendedClass();
                squareSmall.addPoint(new PointClass(14, 0));
                squareSmall.addPoint(new PointClass(16, 0));
                squareSmall.addPoint(new PointClass(16, 30));
                squareSmall.addPoint(new PointClass(14, 30));


                assertTrue(square.intersectionPolygon(squareSmall));
            }

            //==== <end> <assertTrue> ====================


            //==== <start> <assertFalse> ====================
            {
                PolygonExtended squarePlus15 = new PolygonExtendedClass();
                squarePlus15.addPoint(new PointClass(25, 25));
                squarePlus15.addPoint(new PointClass(25, 35));
                squarePlus15.addPoint(new PointClass(35, 35));
                squarePlus15.addPoint(new PointClass(35, 25));


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
            square.addPoint(new PointClass(10, 10));
            square.addPoint(new PointClass(10, 20));
            square.addPoint(new PointClass(20, 20));
            square.addPoint(new PointClass(20, 10));

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

    @Test
    public void getCenterAverage() {
        {
            PolygonExtended polygonExtended = new PolygonExtendedClass();
            polygonExtended.addPoint(new PointClass(0, 0));
            polygonExtended.addPoint(new PointClass(100, 0));
            polygonExtended.addPoint(new PointClass(100, 100));
            polygonExtended.addPoint(new PointClass(0, 100));

            Point actualAverageCenter = polygonExtended.getCenterAverage();
            Point expectedAverageCenter = new PointClass(50, 50);

            assertEquals(actualAverageCenter, expectedAverageCenter);
        }
        {
            PolygonExtended polygonExtended = new PolygonExtendedClass();
            polygonExtended.addPoint(new PointClass(0, 0));
            polygonExtended.addPoint(new PointClass(-100, 0));
            polygonExtended.addPoint(new PointClass(-100, -100));
            polygonExtended.addPoint(new PointClass(0, -100));

            Point actualAverageCenter = polygonExtended.getCenterAverage();
            Point expectedAverageCenter = new PointClass(-50, -50);

            assertEquals(actualAverageCenter, expectedAverageCenter);
        }
        {
            PolygonExtended polygonExtended = new PolygonExtendedClass();
            polygonExtended.addPoint(new PointClass(0, 0));
            polygonExtended.addPoint(new PointClass(0, 0));
            polygonExtended.addPoint(new PointClass(0, 0));
            polygonExtended.addPoint(new PointClass(0, 0));

            Point actualAverageCenter = polygonExtended.getCenterAverage();
            Point expectedAverageCenter = new PointClass(0, 0);

            assertEquals(actualAverageCenter, expectedAverageCenter);
        }
    }

    @Test
    public void getFormatDoubleArray() {
        {
            PolygonExtended polygonExtended = new PolygonExtendedClass();
            polygonExtended.addPoint(new PointClass(0, 0));
            polygonExtended.addPoint(new PointClass(10, 0));
            polygonExtended.addPoint(new PointClass(10, 10));
            polygonExtended.addPoint(new PointClass(0, 10));

            Double[] actualArray = polygonExtended.getFormatDoubleArray();
            Double[] expectedArray = {0.0, 0.0, 10.0, 0.0, 10.0, 10.0, 0.0, 10.0};

            assertArrayEquals(expectedArray, actualArray);
        }
    }

    @Test
    public void rotateRelative() {
        {
            PolygonExtended actualRectangle = new PolygonExtendedClass();
            actualRectangle.addPoint(new PointClass(20, 20));
            actualRectangle.addPoint(new PointClass(-20, 20));
            actualRectangle.addPoint(new PointClass(-20, -20));
            actualRectangle.addPoint(new PointClass(20, -20));


            actualRectangle.rotateRelative(
                    new PointClass(0, 0),
                    Math.PI / 2
            );



            PolygonExtended expectedRectangle = new PolygonExtendedClass();
            expectedRectangle.addPoint(new PointClass(-20, 20));
            expectedRectangle.addPoint(new PointClass(-20, -20));
            expectedRectangle.addPoint(new PointClass(20, -20));
            expectedRectangle.addPoint(new PointClass(20, 20));

            assertEquals(expectedRectangle, actualRectangle);
        }
    }

    @Test
    public void deposeOn() {
        {
            PolygonExtended actualRectangle = new PolygonExtendedClass();
            actualRectangle.addPoint(new PointClass(20, 20));
            actualRectangle.addPoint(new PointClass(-20, 20));
            actualRectangle.addPoint(new PointClass(-20, -20));
            actualRectangle.addPoint(new PointClass(20, -20));

            Point vector = new PointClass(20, 20);

            actualRectangle.deposeOn(vector);

            PolygonExtended expectedRectangle = new PolygonExtendedClass();
            expectedRectangle.addPoint(new PointClass(40, 40));
            expectedRectangle.addPoint(new PointClass(0, 40));
            expectedRectangle.addPoint(new PointClass(0, 0));
            expectedRectangle.addPoint(new PointClass(40, 0));

            assertEquals(expectedRectangle, actualRectangle);
        }
    }
}