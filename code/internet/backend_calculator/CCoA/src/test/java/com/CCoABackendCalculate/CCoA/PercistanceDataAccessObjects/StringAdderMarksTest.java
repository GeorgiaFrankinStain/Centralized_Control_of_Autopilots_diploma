package com.CCoABackendCalculate.CCoA.PercistanceDataAccessObjects;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.AStar.AStarSpaceTimePlanarGraphClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.AlhorithmFastFindPath;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.FabricNetworkNodes.FabricHexagonNodes;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.FabricNetworkNodes.FabricNetworkNodes;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTimeClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoAClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayer;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayerClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.*;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.TypeMachinesBody;
import com.CCoABackendCalculate.CCoA.PercistanceDataAccessObjects.Exception.NotEnoughDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class StringAdderMarksTest {

    //TODO right version

    @Test
    void constructor_right() throws Exception {

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
    void constructor_3PointsPolygonFrom() throws Exception {

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
    void constructor_2PointsPolygonFrom() throws Exception {

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
    void constructor_notEnoughDataException_polygonPositiveArea() throws Exception {

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
    void constructor_notEnoughDataException_version() throws Exception {

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
    void constructor_notEnoughDataException_orders() throws Exception {

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
    void constructor_notEnoughDataException_parametersMoving() throws Exception {

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
    void constructor_notEnoughDataException_setFormTypeMachinesBodyPriority() throws Exception {

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
    void constructor_notEnoughDataException_setFormUnique() throws Exception {

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
    void constructor_notEnoughDataException_setFormNot() throws Exception {

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
    void constructor_notEnoughDataException_setSpeedNot() throws Exception {

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
    void constructor_notEnoughDataException_setStartNot() throws Exception {

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
    void constructor_notEnoughDataException_setStartCoordinate() throws Exception {

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
    void constructor_notEnoughDataException_setStartTimeNot() throws Exception {

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
    void constructor_notEnoughDataException_setDynamicEnd() throws Exception {

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
    void constructor_notEnoughDataException_setStaticEndMoveFalse() throws Exception {

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
    void constructor_notEnoughDataException_setStaticEndMove() throws Exception {

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
    void constructor_notEnoughDataException_setStandingAfterNot() throws Exception {

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

    @Test
    void addMarksIn_speedIsNegative() throws Exception {

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
                        "                           \"speed\":-10\n" +
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
                        "                        \"standing\": \"false\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";



        String expectedErrorText = "speed is negative";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }


        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    void addMarksIn_speedZeroForDynamicMovingObject() throws Exception {

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
                        "                           \"speed\":0\n" +
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
                        "                        \"standing\": \"false\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";



        String expectedErrorText = "speed is 0 for dynamic moving object";

        String actualErrorText = "";
        try {
            AdderMarks adderMarks = new StringAdderMarks(jsonContent);
        } catch (NotEnoughDataException e) {
            actualErrorText = e.getMessage();
        }

        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    void addMarksIn_speedZeroForStaticObject() throws Exception {

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
//                        "                                \"type_landscape_body\":\"ASPHALT\",\n" +
                        "                                \"type_machines_body\":\"TEST_SQUARE_20\"\n" +
                        "                            },\n" +
                        "                           \"speed\":0\n" +
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
    void addMarksIn_createStandingWall() throws Exception {

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
//                        "                                \"type_landscape_body\":\"ASPHALT\"\n" +
                        "                                \"type_machines_body\":\"WALL_CAR\"\n" +
                        "                            }\n" +
//                        "                           \"speed\":10\n" +
                        "                        },\n" +
                        "                        \"start\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 120, \"y\": 160\n" +
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



        AdderMarks adderMarks = new StringAdderMarks(jsonContent);

        FootprintsSpaceTime jsonCreateFootprintsSpaceTime = new FootprintsSpaceTimeClass();
        adderMarks.addMarksIn(jsonCreateFootprintsSpaceTime);

        FootprintsSpaceTime codeCreateFootprintSpaceTime = createWall();

        assertTrue(codeCreateFootprintSpaceTime.equalsWithoutUniqueId(jsonCreateFootprintsSpaceTime));
    }

    private FootprintsSpaceTime createWall() throws СrashIntoAnImpassableObjectException {
        IndexLayer indexLayer = new IndexLayerClass(0);
        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique wall = fabricParametersMovingUnique.getMoving(TypeMachinesBody.WALL_CAR);
        PathCCoA wallPath = new PathCCoAClass();
        wallPath.addPoint(new PointCCoAClass(120, 160));

        wall.mark(onlyFootprintsSpaceTime, wallPath, 0.0, indexLayer);
        return onlyFootprintsSpaceTime;
    }

    //FIXME add tests if set standing true, the set should be not set

    @Test
    void addMarksIn_sameResultWithCommand() throws Exception {
        FootprintsSpaceTime fromCommandFootprintSpaceTime = createFootprintSpaceTimeTwoMachines();
        FootprintsSpaceTime fromJsonFootprintSpaceTime = createFootprintSpaceTimeTwoMachinesFromJson();


        assertTrue(fromCommandFootprintSpaceTime.equalsWithoutUniqueId(fromJsonFootprintSpaceTime));
    }

    private FootprintsSpaceTime createFootprintSpaceTimeTwoMachinesFromJson() throws Exception {
        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();


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
                        "                            \t\"x\": 0, \"y\": 131\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0,\n" +
                        "                            \"time\":0.0\n" +
                        "                        },\n" +
                        "                        \"end\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 0, \"y\": 300\n" +
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
        adderMarks.addMarksIn(onlyFootprintsSpaceTime);


        String jsonContent2 =
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
                        "                            \t\"x\": 30, \"y\": 131\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0,\n" +
                        "                            \"time\":0.0\n" +
                        "                        },\n" +
                        "                        \"end\": {\n" +
                        "                            \"coordinate\": {\n" +
                        "                            \t\"x\": 70, \"y\": 300\n" +
                        "                            },\n" +
                        "                            \"layer\":0,\n" +
                        "                            \"angle\":0.0\n" +
                        "                        },\n" +
                        "                        \"standing\": \"false\",\n" +
                        "                        \"standing_after\": \"false\"\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }";




        AdderMarks adderMarks2 = new StringAdderMarks(jsonContent2);
        adderMarks2.addMarksIn(onlyFootprintsSpaceTime);

        return onlyFootprintsSpaceTime;
    }
    private FootprintsSpaceTime createFootprintSpaceTimeTwoMachines() throws СrashIntoAnImpassableObjectException {


        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(); //create FootprintsSpaceTime (Landscape) //PUNKT_1

        IndexLayer indexLayer = new IndexLayerClass(0);

        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique parametersMovingUnique = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20);
        double degree60 = 1.0472;
        FabricNetworkNodes fabricNetworkNodes = new FabricHexagonNodes(degree60 / 9, parametersMovingUnique);
        AlhorithmFastFindPath fastFinderPath = new AStarSpaceTimePlanarGraphClass(fabricNetworkNodes, onlyFootprintsSpaceTime);


        {
            PointCCoA from = new PointCCoAClass(0, 131);
            PointCCoA to = new PointCCoAClass(0, 300);


            double timeAdding = 0.0;
            PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMovingUnique, timeAdding);
            try {
                parametersMovingUnique.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
            } catch (СrashIntoAnImpassableObjectException ex) {
            }
        }
        {
            ParametersMovingUnique localNewCar = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20);
            PointCCoA from = new PointCCoAClass(30, 131);
            PointCCoA to = new PointCCoAClass(70, 300);


            double timeAdding = 0.0;
            PathCCoA actualPath = fastFinderPath.getPath(from, to, localNewCar, timeAdding);
            try {
                localNewCar.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer); //FIXME bag sequense time adding
            } catch (СrashIntoAnImpassableObjectException ex) {
            }
        }

        return onlyFootprintsSpaceTime;
    }

    //TODO add tests verification speed > 0

    //FIXME create function for standing before time
}