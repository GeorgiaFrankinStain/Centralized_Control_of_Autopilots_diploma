package com.alamutra.ccoa.PercistanceDataAccessObjects;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoAClass;
import com.alamutra.ccoa.PercistanceDataAccessObjects.Exception.NotEnoughDataException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class StringAdderMarksTest {

    //TODO right version

    @Test
    void addMarksIn_right() throws Exception {

        String jsonContent =
                "            {\n" +
                "                \"version\":1,\n" +
                "                \"orders\": [\n" +
                "                    {\n" +
                "                        \"parameters_moving\": {\n" +
                "                            \"polygon_form\": [\n" +
                "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
                "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
                "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
                "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
                "                            ],\n" +
                "                            \"type\": {\n" +
                "                                \"type_in_layer\":\"OBJECT\",\n" +
                "                                \"type_landscape_body\":\"ASPHALT\",\n" +
                "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                "                            },\n" +
                "                           \"speed\":10\n" +
                "                        },\n" +
                "                        \"start\": {\n" +
                "                            \"coordinate\": {\n" +
                "                            \t\"x\": 10, \"y\": 10\n" +
                "                            },\n" +
                "                            \"layer\":0,\n" +
                "                            \"angle\":0.0, \n" +
                "                            \"time\":0.0\n" +
                "                        },\n" +
                "                        \"end\": {\n" +
                "                            \"coordinate\": {\n" +
                "                            \t\"x\": 10, \"y\": 40\n" +
                "                            },\n" +
                "                            \"layer\":0,\n" +
                "                            \"angle\":0.0\n" +
                "                        },\n" +
                "                        \"standing\": \"false\",\n" +
                "                        \"standing_after\": \"false\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }";

        AdderMarks adderMarks = new StringAdderMarks(jsonContent);
    }

    @Test
    void addMarksIn_3PointsPolygonFrom() throws Exception {

        String jsonContent =
                "            {\n" +
                "                \"version\":1,\n" +
                "                \"orders\": [\n" +
                "                    {\n" +
                "                        \"parameters_moving\": {\n" +
                "                            \"polygon_form\": [\n" +
                "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
                "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
                "                                {\"x\":\"10\",\"y\":\"10\"}\n" +
//                "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
                "                            ],\n" +
                "                            \"type\": {\n" +
                "                                \"type_in_layer\":\"OBJECT\",\n" +
                "                                \"type_landscape_body\":\"ASPHALT\"\n" +
//                "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                "                            },\n" +
                "                           \"speed\":10\n" +
                "                        },\n" +
                "                        \"start\": {\n" +
                "                            \"coordinate\": {\n" +
                "                            \t\"x\": 10, \"y\": 10\n" +
                "                            },\n" +
                "                            \"layer\":0,\n" +
                "                            \"angle\":0.0, \n" +
                "                            \"time\":0.0\n" +
                "                        },\n" +
                "                        \"end\": {\n" +
                "                            \"coordinate\": {\n" +
                "                            \t\"x\": 10, \"y\": 40\n" +
                "                            },\n" +
                "                            \"layer\":0,\n" +
                "                            \"angle\":0.0\n" +
                "                        },\n" +
                "                        \"standing\": \"false\",\n" +
                "                        \"standing_after\": \"false\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }";

        AdderMarks adderMarks = new StringAdderMarks(jsonContent);
    }

    @Test
    void addMarksIn_2PointsPolygonFrom() throws Exception {

        String jsonContent =
                "            {\n" +
                "                \"version\":1,\n" +
                "                \"orders\": [\n" +
                "                    {\n" +
                "                        \"parameters_moving\": {\n" +
                "                            \"polygon_form\": [\n" +
                "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
                "                                {\"x\":\"-10\",\"y\":\"10\"}\n" +
//                "                                {\"x\":\"10\",\"y\":\"10\"}\n" +
//                "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
                "                            ],\n" +
                "                            \"type\": {\n" +
                "                                \"type_in_layer\":\"OBJECT\",\n" +
                "                                \"type_landscape_body\":\"ASPHALT\"\n" +
//                "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                "                            },\n" +
                "                           \"speed\":10\n" +
                "                        },\n" +
                "                        \"start\": {\n" +
                "                            \"coordinate\": {\n" +
                "                            \t\"x\": 10, \"y\": 10\n" +
                "                            },\n" +
                "                            \"layer\":0,\n" +
                "                            \"angle\":0.0, \n" +
                "                            \"time\":0.0\n" +
                "                        },\n" +
                "                        \"end\": {\n" +
                "                            \"coordinate\": {\n" +
                "                            \t\"x\": 10, \"y\": 40\n" +
                "                            },\n" +
                "                            \"layer\":0,\n" +
                "                            \"angle\":0.0\n" +
                "                        },\n" +
                "                        \"standing\": \"false\",\n" +
                "                        \"standing_after\": \"false\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }";


        String expectedErrorText = "polygon_form has minimum 3 points";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);


    }


    @Test
    void addMarksIn_notEnoughDataException_polygonPositiveArea() throws Exception {

        String jsonContent =
                "            {\n" +
                        "                \"version\":1,\n" +
                        "                \"orders\": [\n" +
                        "                    {\n" +
                        "                        \"parameters_moving\": {\n" +
                        "                            \"polygon_form\": [\n" +
                        "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
                        "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"-10\",\"y\":\"20\"},\n" +
                        "                                {\"x\":\"-10\",\"y\":\"30\"}\n" +
                        "                            ],\n" +
                        "                            \"type\": {\n" +
                        "                                \"type_in_layer\":\"OBJECT\",\n" +
                        "                                \"type_landscape_body\":\"ASPHALT\"\n" +
//                        "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                        "                            },\n" +
                        "                    \"speed\":10\n" +
                        "                        },\n" +
                        "                        \"start\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 10\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0, \n" +
                        "                            \"time\":0.0\n" +
                        "                        },\n" +
                        "                        \"end\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 40\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0\n" +
                        "                        },\n" +
                        "                        \"standing\": \"false\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";

        String expectedErrorText = "polygon_form has positive area (area > 0)";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);

    }

        @Test
    void addMarksIn_notEnoughDataException_version() throws Exception {

        String jsonContent =
                "            {\n" +
                //"                \"version\":1,\n" +
                "                \"orders\": [\n" +
                "                    {\n" +
                "                        \"parameters_moving\": {\n" +
                "                            \"polygon_form\": [\n" +
                "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
                "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
                "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
                "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
                "                            ],\n" +
                "                            \"type\": {\n" +
                "                                \"type_in_layer\":\"OBJECT\",\n" +
                "                                \"type_landscape_body\":\"ASPHALT\",\n" +
                "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                "                            },\n" +
                        "                    \"speed\":10\n" +
                "                        },\n" +
                "                        \"start\": {\n" +
                "                            \"coordinate\": {\n" +
                "                            \t\"x\": 10, \"y\": 10\n" +
                "                            },\n" +
                "                            \"layer\":0,\n" +
                "                            \"angle\":0.0, \n" +
                "                            \"time\":0.0\n" +
                "                        },\n" +
                "                        \"end\": {\n" +
                "                            \"coordinate\": {\n" +
                "                            \t\"x\": 10, \"y\": 40\n" +
                "                            },\n" +
                "                            \"layer\":0,\n" +
                "                            \"angle\":0.0\n" +
                "                        },\n" +
                "                        \"standing\": \"false\",\n" +
                "                        \"standing_after\": \"false\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }";

        String expectedErrorText = "version is missing";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }
    @Test
    void addMarksIn_notEnoughDataException_orders() throws Exception {

        String jsonContent =
                "            {\n" +
                "                \"version\":1\n" +
//                "                \"orders\": [\n" +
//                "                    {\n" +
//                "                        \"parameters_moving\": {\n" +
//                "                            \"polygon_form\": [\n" +
//                "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
//                "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
//                "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
//                "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
//                "                            ],\n" +
//                "                            \"type\": {\n" +
//                "                                \"type_in_layer\":\"OBJECT\",\n" +
//                "                                \"type_landscape_body\":\"ASPHALT\",\n" +
//                "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
//                "                            },\n" +
//                        "                    \"speed\":10\n" +
//                "                        },\n" +
//                "                        \"start\": {\n" +
//                "                            \"coordinate\": {\n" +
//                "                            \t\"x\": 10, \"y\": 10\n" +
//                "                            },\n" +
//                "                            \"layer\":0,\n" +
//                "                            \"angle\":0.0, \n" +
//                "                            \"time\":0.0\n" +
//                "                        },\n" +
//                "                        \"end\": {\n" +
//                "                            \"coordinate\": {\n" +
//                "                            \t\"x\": 10, \"y\": 40\n" +
//                "                            },\n" +
//                "                            \"layer\":0,\n" +
//                "                            \"angle\":0.0\n" +
//                "                        },\n" +
//                "                        \"standing\": \"false\",\n" +
//                "                        \"standing_after\": \"false\"\n" +
//                "                    }\n" +
//                "                ]\n" +
                "            }";

        String expectedErrorText = "orders is missing";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    void addMarksIn_notEnoughDataException_parametersMoving() throws Exception {

        String jsonContent =
                "            {\n" +
                        "                \"version\":1,\n" +
                        "                \"orders\": [\n" +
                        "                    {\n" +
//                        "                        \"parameters_moving\": {\n" +
//                        "                            \"polygon_form\": [\n" +
//                        "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
//                        "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
//                        "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
//                        "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
//                        "                            ],\n" +
//                        "                            \"type\": {\n" +
//                        "                                \"type_in_layer\":\"OBJECT\",\n" +
//                        "                                \"type_landscape_body\":\"ASPHALT\",\n" +
//                        "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
//                        "                            },\n" +
//                        "                           \"speed\":10\n" +
//                        "                        },\n" +
                        "                        \"start\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 10\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0, \n" +
                        "                            \"time\":0.0\n" +
                        "                        },\n" +
                        "                        \"end\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 40\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0\n" +
                        "                        },\n" +
                        "                        \"standing\": \"false\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";


        String expectedErrorText = "parameters_moving is missing";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }



    @Test
    void addMarksIn_notEnoughDataException_setFormTypeMachinesBodyPriority() throws Exception {

        String jsonContent =
                "            {\n" +
                        "                \"version\":1,\n" +
                        "                \"orders\": [\n" +
                        "                    {\n" +
                        "                        \"parameters_moving\": {\n" +
//                        "                            \"polygon_form\": [\n" +
//                        "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
//                        "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
//                        "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
//                        "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
//                        "                            ],\n" +
                        "                            \"type\": {\n" +
                        "                                \"type_in_layer\":\"OBJECT\",\n" +
                        "                                \"type_landscape_body\":\"ASPHALT\",\n" +
                        "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                        "                            },\n" +
                        "                           \"speed\":10\n" +
                        "                        },\n" +
                        "                        \"start\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 10\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0, \n" +
                        "                            \"time\":0.0\n" +
                        "                        },\n" +
                        "                        \"end\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 40\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0\n" +
                        "                        },\n" +
                        "                        \"standing\": \"false\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";



        String expectedErrorText = "";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    void addMarksIn_notEnoughDataException_setFormUnique() throws Exception {

        String jsonContent =
                "            {\n" +
                        "                \"version\":1,\n" +
                        "                \"orders\": [\n" +
                        "                    {\n" +
                        "                        \"parameters_moving\": {\n" +
                        "                            \"polygon_form\": [\n" +
                        "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
                        "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
                        "                            ],\n" +
                        "                            \"type\": {\n" +
                        "                                \"type_in_layer\":\"OBJECT\",\n" +
                        "                                \"type_landscape_body\":\"ASPHALT\"\n" +
                        //"                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                        "                            },\n" +
                        "                           \"speed\":10\n" +
                        "                        },\n" +
                        "                        \"start\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 10\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0, \n" +
                        "                            \"time\":0.0\n" +
                        "                        },\n" +
                        "                        \"end\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 40\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0\n" +
                        "                        },\n" +
                        "                        \"standing\": \"false\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";



        String expectedErrorText = "";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    void addMarksIn_notEnoughDataException_setFormNot() throws Exception {

        String jsonContent =
                "            {\n" +
                        "                \"version\":1,\n" +
                        "                \"orders\": [\n" +
                        "                    {\n" +
                        "                        \"parameters_moving\": {\n" +
//                        "                            \"polygon_form\": [\n" +
//                        "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
//                        "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
//                        "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
//                        "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
//                        "                            ],\n" +
                        "                            \"type\": {\n" +
                        "                                \"type_in_layer\":\"OBJECT\",\n" +
                        "                                \"type_landscape_body\":\"ASPHALT\"\n" +
                        //"                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                        "                            },\n" +
                        "                           \"speed\":10\n" +
                        "                        },\n" +
                        "                        \"start\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 10\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0, \n" +
                        "                            \"time\":0.0\n" +
                        "                        },\n" +
                        "                        \"end\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 40\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0\n" +
                        "                        },\n" +
                        "                        \"standing\": \"false\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";



        String expectedErrorText = "polygon_form or type_machines_body is missing";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    void addMarksIn_notEnoughDataException_setSpeedNot() throws Exception {

        String jsonContent =
                "            {\n" +
                        "                \"version\":1,\n" +
                        "                \"orders\": [\n" +
                        "                    {\n" +
                        "                        \"parameters_moving\": {\n" +
                        "                            \"polygon_form\": [\n" +
                        "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
                        "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
                        "                            ],\n" +
                        "                            \"type\": {\n" +
                        "                                \"type_in_layer\":\"OBJECT\",\n" +
                        "                                \"type_landscape_body\":\"ASPHALT\",\n" +
                        "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                        "                            }\n" +
                        //"                           \"speed\":10\n" +
                        "                        },\n" +
                        "                        \"start\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 10\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0, \n" +
                        "                            \"time\":0.0\n" +
                        "                        },\n" +
                        "                        \"end\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 40\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0\n" +
                        "                        },\n" +
                        "                        \"standing\": \"false\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";



        String expectedErrorText = "speed is missing";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    void addMarksIn_notEnoughDataException_setStartNot() throws Exception {

        String jsonContent =
                "            {\n" +
                        "                \"version\":1,\n" +
                        "                \"orders\": [\n" +
                        "                    {\n" +
                        "                        \"parameters_moving\": {\n" +
                        "                            \"polygon_form\": [\n" +
                        "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
                        "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
                        "                            ],\n" +
                        "                            \"type\": {\n" +
                        "                                \"type_in_layer\":\"OBJECT\",\n" +
                        "                                \"type_landscape_body\":\"ASPHALT\",\n" +
                        "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                        "                            },\n" +
                        "                           \"speed\":10\n" +
                        "                        },\n" +
//                        "                        \"start\": {\n" +
//                        "                            \"coordinate\": {\n" +
//                        "                            \t\"x\": 10, \"y\": 10\n" +
//                        "                            },\n" +
//                        "                            \"layer\":0,\n" +
//                        "                            \"angle\":0.0, \n" +
//                        "                            \"time\":0.0\n" +
//                        "                        },\n" +
                        "                        \"end\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 40\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0\n" +
                        "                        },\n" +
                        "                        \"standing\": \"false\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";



        String expectedErrorText = "start is missing";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    void addMarksIn_notEnoughDataException_setStartCoordinate() throws Exception {

        String jsonContent =
                "            {\n" +
                        "                \"version\":1,\n" +
                        "                \"orders\": [\n" +
                        "                    {\n" +
                        "                        \"parameters_moving\": {\n" +
                        "                            \"polygon_form\": [\n" +
                        "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
                        "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
                        "                            ],\n" +
                        "                            \"type\": {\n" +
                        "                                \"type_in_layer\":\"OBJECT\",\n" +
                        "                                \"type_landscape_body\":\"ASPHALT\",\n" +
                        "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                        "                            },\n" +
                        "                           \"speed\":10\n" +
                        "                        },\n" +
                        "                        \"start\": {\n" +
                        //"                            \"coordinate\": {\n" +
                        //"                            \t\"x\": 10, \"y\": 10\n" +
                        //"                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0, \n" +
                        "                            \"time\":0.0\n" +
                        "                        },\n" +
                        "                        \"end\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 40\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0\n" +
                        "                        },\n" +
                        "                        \"standing\": \"false\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";



        String expectedErrorText = "start coordinate is missing";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    void addMarksIn_notEnoughDataException_setStartTimeNot() throws Exception {

        String jsonContent =
                "            {\n" +
                        "                \"version\":1,\n" +
                        "                \"orders\": [\n" +
                        "                    {\n" +
                        "                        \"parameters_moving\": {\n" +
                        "                            \"polygon_form\": [\n" +
                        "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
                        "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
                        "                            ],\n" +
                        "                            \"type\": {\n" +
                        "                                \"type_in_layer\":\"OBJECT\",\n" +
                        "                                \"type_landscape_body\":\"ASPHALT\",\n" +
                        "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                        "                            },\n" +
                        "                           \"speed\":10\n" +
                        "                        },\n" +
                        "                        \"start\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 10\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0 \n" +
                        //"                            \"time\":0.0\n" +
                        "                        },\n" +
                        "                        \"end\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 40\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0\n" +
                        "                        },\n" +
                        "                        \"standing\": \"false\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";



        String expectedErrorText = "start time is missing";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    void addMarksIn_notEnoughDataException_setDynamicEnd() throws Exception {

        String jsonContent =
                "            {\n" +
                        "                \"version\":1,\n" +
                        "                \"orders\": [\n" +
                        "                    {\n" +
                        "                        \"parameters_moving\": {\n" +
                        "                            \"polygon_form\": [\n" +
                        "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
                        "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
                        "                            ],\n" +
                        "                            \"type\": {\n" +
                        "                                \"type_in_layer\":\"OBJECT\",\n" +
                        "                                \"type_landscape_body\":\"ASPHALT\",\n" +
                        "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                        "                            },\n" +
                        "                           \"speed\":10\n" +
                        "                        },\n" +
                        "                        \"start\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 10\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0,\n" +
                        "                            \"time\":0.0\n" +
                        "                        },\n" +
                        "                        \"end\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 40\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0\n" +
                        "                        },\n" +
//                        "                        \"standing\": \"false\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";



        String expectedErrorText = "";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    void addMarksIn_notEnoughDataException_setStaticEndMoveFalse() throws Exception {

        String jsonContent =
                "            {\n" +
                        "                \"version\":1,\n" +
                        "                \"orders\": [\n" +
                        "                    {\n" +
                        "                        \"parameters_moving\": {\n" +
                        "                            \"polygon_form\": [\n" +
                        "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
                        "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
                        "                            ],\n" +
                        "                            \"type\": {\n" +
                        "                                \"type_in_layer\":\"OBJECT\",\n" +
                        "                                \"type_landscape_body\":\"ASPHALT\",\n" +
                        "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                        "                            },\n" +
                        "                           \"speed\":10\n" +
                        "                        },\n" +
                        "                        \"start\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 10\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0,\n" +
                        "                            \"time\":0.0\n" +
                        "                        },\n" +
//                        "                        \"end\": {\n" +
//                        "                            \"coordinate\": {\n" +
//                        "                            \t\"x\": 10, \"y\": 40\n" +
//                        "                            },\n" +
//                        "                            \"layer\":0,\n" +
//                        "                            \"angle\":0.0\n" +
//                        "                        },\n" +
                        "                        \"standing\": \"false\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";



        String expectedErrorText = "end or standing is missing";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    void addMarksIn_notEnoughDataException_setStaticEndMove() throws Exception {

        String jsonContent =
                "            {\n" +
                        "                \"version\":1,\n" +
                        "                \"orders\": [\n" +
                        "                    {\n" +
                        "                        \"parameters_moving\": {\n" +
                        "                            \"polygon_form\": [\n" +
                        "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
                        "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
                        "                            ],\n" +
                        "                            \"type\": {\n" +
                        "                                \"type_in_layer\":\"OBJECT\",\n" +
                        "                                \"type_landscape_body\":\"ASPHALT\",\n" +
                        "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                        "                            },\n" +
                        "                           \"speed\":10\n" +
                        "                        },\n" +
                        "                        \"start\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 10\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0,\n" +
                        "                            \"time\":0.0\n" +
                        "                        },\n" +
//                        "                        \"end\": {\n" +
//                        "                            \"coordinate\": {\n" +
//                        "                            \t\"x\": 10, \"y\": 40\n" +
//                        "                            },\n" +
//                        "                            \"layer\":0,\n" +
//                        "                            \"angle\":0.0\n" +
//                        "                        },\n" +
                        "                        \"standing\": \"true\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";



        String expectedErrorText = "";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    void addMarksIn_notEnoughDataException_setDynamicEndMoveIgnore() throws Exception {

        String jsonContent =
                "            {\n" +
                        "                \"version\":1,\n" +
                        "                \"orders\": [\n" +
                        "                    {\n" +
                        "                        \"parameters_moving\": {\n" +
                        "                            \"polygon_form\": [\n" +
                        "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
                        "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
                        "                            ],\n" +
                        "                            \"type\": {\n" +
                        "                                \"type_in_layer\":\"OBJECT\",\n" +
                        "                                \"type_landscape_body\":\"ASPHALT\",\n" +
                        "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                        "                            },\n" +
                        "                           \"speed\":10\n" +
                        "                        },\n" +
                        "                        \"start\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 10\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0,\n" +
                        "                            \"time\":0.0\n" +
                        "                        },\n" +
                        "                        \"end\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 40\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0\n" +
                        "                        },\n" +
                        "                        \"standing\": \"true\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";



        String expectedErrorText = "";
        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    void addMarksIn_notEnoughDataException_setStandingAfterNot() throws Exception {

        String jsonContent =
                "            {\n" +
                        "                \"version\":1,\n" +
                        "                \"orders\": [\n" +
                        "                    {\n" +
                        "                        \"parameters_moving\": {\n" +
                        "                            \"polygon_form\": [\n" +
                        "                                {\"x\":\"-10\",\"y\":\"-10\"},\n" +
                        "                                {\"x\":\"-10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"10\"},\n" +
                        "                                {\"x\":\"10\",\"y\":\"-10\"}\n" +
                        "                            ],\n" +
                        "                            \"type\": {\n" +
                        "                                \"type_in_layer\":\"OBJECT\",\n" +
                        "                                \"type_landscape_body\":\"ASPHALT\",\n" +
                        "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                        "                            },\n" +
                        "                           \"speed\":10\n" +
                        "                        },\n" +
                        "                        \"start\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 10\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0,\n" +
                        "                            \"time\":0.0\n" +
                        "                        },\n" +
                        "                        \"end\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 10, \"y\": 40\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0\n" +
                        "                        },\n" +
                        "                        \"standing\": \"true\"\n" +
//                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";



        String expectedErrorText = "standing_after is missing";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }

    //TODO tests missing every items

    //TODO type every items

    //TODO add tests verification speed > 0
}