import React, {useState} from "react";
import {setDataElbow, SetElbowsJson} from "./DrawingMotionPath"
import {PointCCoA} from "../../../Polygon";
import {API_BACKEND_CALCULATOR} from "../../../index";


let sendJsonOrders = {
    "version": 1,
    "id_room": "dWP5WK1gVqjHCVh4NvjS", //option
    "orders": [
        {
            "parameters_moving": {
                "polygon_form": [
                    {"x":"-10","y":"-10"},
                    {"x":"-10","y":"10"},
                    {"x":"10","y":"10"},
                    {"x":"10","y":"-10"}
                ],
                "type": {
                    "type_in_layer":"OBJECT",
                    "type_landscape_body":"ASPHALT",
                    "type_machines_body":"TEST_SQUARE_20"
                },
                "speed":10
            },
            "start": {
                "coordinate": {
                    "x": 10, "y": 10
                },
                "layer":0,
                "angle":0.0,
                "time":0.0
            },
            "end": {
                "coordinate": {
                    "x": 40, "y": 50
                },
                "layer":0,
                "angle":0.0
            },
            "standing": "false",
            "standing_after": "false"
        }
    ]
}

controllerDeleteABOrderMachineAll(); //I'm too lazy to set a new interface, so I manually create an array, and then delete it


interface ABOrder {
    start: PointCCoA;
    end: PointCCoA;
    angle: number;

}

export function controllerDeleteABOrderMachineAll() {
    sendJsonOrders.orders = [];
}

const jsonData = {}

export function controllerAddABOrderMachine(
    start: PointCCoA,
    angleStart: number,
    end: PointCCoA,
    angleEnd: number,
    time: number
) {
    let order = JSON.parse("{}");
    order['parameters_moving'] = {
        "polygon_form": [
            {"x":"-10","y":"-10"},
            {"x":"-10","y":"10"},
            {"x":"10","y":"10"},
            {"x":"10","y":"-10"}
        ],
        "type": {
            "type_in_layer":"OBJECT",
            "type_landscape_body":"ASPHALT",
            "type_machines_body":"TEST_SQUARE_20"
        },
        "speed":10
    };
    order["start"] = {
        "coordinate" : {"x": start.x, "y": start.y},
        "layer": 0,
        "angle": angleStart,
        "time": time
    };
    order["end"] = {
        "coordinate" : {"x": end.x, "y": end.y},
        "layer": 0,
        "angle": angleEnd
    };
    order["standing"] = false;
    order["standing_after"] = false;
    sendJsonOrders.orders.push(order);
    // sendJsonOrders.orders.splice(positionInArray, 0, order);
}


interface ISetOrdersProps {
    updateStateCanvasRenderingResultCallback:  React.Dispatch<React.SetStateAction<number>>
}


export function ButtonSendOrders({updateStateCanvasRenderingResultCallback}: ISetOrdersProps) {

    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);




    function sendApplicationOrdersClick() {

        console.log("before use create_room variable");
        console.log(API_BACKEND_CALCULATOR);
        // Send data to the backend via POST
        fetch(API_BACKEND_CALCULATOR + '/create_room', {  // Enter your IP address here
            method: 'POST',
            mode: 'cors',
            body: JSON.stringify(jsonData) // body data type must match "Content-Type" header
        })
            .then(response => response.json())
            .then((usefulData) => {
                setLoading(false);
                // setData(usefulData);
                sendJsonOrders.id_room = usefulData.id;










                // Send data to the backend via POST
                fetch(API_BACKEND_CALCULATOR + '/to_application_orders', {  // Enter your IP address here
                    method: 'POST',

                    headers: {
                        'Content-Type': 'application/json'
                    },
                    mode: 'cors',
                    body: JSON.stringify(sendJsonOrders) // body data type must match "Content-Type" header

                })
                    .then(response => response.json())
                    .then((usefulData) => {
                        setLoading(false);
                        setData(usefulData);
                        getElbowFootprint();
                    })
                    .catch((e) => {
                        console.error(`An error occurred: ${e}`);
                    });





            })
            .catch((e) => {
                console.error(`An error occurred: ${e}`)
            });







    }

    function getElbowFootprint() {
        // Send data to the backend via POST
        fetch(API_BACKEND_CALCULATOR + '/get_elbow_footprint', {  // Enter your IP address here

            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            body: sendJsonOrders.id_room
            // body: "dWP5WK1gVqjHCVh4NvjS" // body data type must match "Content-Type" header //FIXME why it work?

        })
            // .then(response => response.text())
            // .then(response => response.json())
            .then(response => response.text())
            .then((usefulData) => {
                setLoading(false);
                // setData(usefulData);


                setDataElbow({"jsonElbow": usefulData});
                //switch to mode view
                //run rendering
                updateStateCanvasRenderingResultCallback(Math.random());
            })
            .catch((e) => {
                console.error(`An error occurred: ${e}`);
            });
    }

    return (
        <div className={'button'} onClick={sendApplicationOrdersClick} style={{
            textAlign: 'center',
            width: '125px',
            marginBottom: "5px",
            border: '1px solid gray',
            borderRadius: '25px'
        }}>
            Расчитать траектории движения
        </div>
    );

}
