import {useState} from "react";
import {setDataElbow, SetElbowsJson} from "./Canvas"
import {PointCCoA} from "./Polygon";


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


interface ABOrder {
    start: PointCCoA;
    end: PointCCoA;
    angle: number;

}

export function controllerDeleteABOrderMachineAll() {
    sendJsonOrders.orders = [];
}

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


export function ButtonSendOrders() {

    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);



    // const sendJsonOrders = {"id": "textid"};



    function sendApplicationOrdersClick() {
        console.log("sendApplicationOrdersClick");
        console.log(sendJsonOrders);

        // Send data to the backend via POST
        fetch('http://localhost:8080/to_application_orders', {  // Enter your IP address here
            method: 'POST',

            headers: {
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            body: JSON.stringify(sendJsonOrders) // body data type must match "Content-Type" header

        })
            .then(response => response.json())
            .then((usefulData) => {
                console.log(usefulData);
                console.log("succeful send orders");
                setLoading(false);
                setData(usefulData);
                getElbowFootprint();
            })
            .catch((e) => {
                console.error(`An error occurred: ${e}`);
            });

    }

    function getElbowFootprint() {
        // Send data to the backend via POST
        fetch('http://localhost:8080/get_elbow_footprint', {  // Enter your IP address here

            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            body: "dWP5WK1gVqjHCVh4NvjS" // body data type must match "Content-Type" header

        })
            // .then(response => response.text())
            // .then(response => response.json())
            .then(response => response.text())
            .then((usefulData) => {
                console.log("userful_data");
                console.log(usefulData);
                setLoading(false);
                // setData(usefulData);


                setDataElbow({"jsonElbow": usefulData});
                //switch to mode view
                //run rendering
            })
            .catch((e) => {
                console.error(`An error occurred: ${e}`);
            });
    }

    return (
        <div onClick={sendApplicationOrdersClick} style={{
            textAlign: 'center',
            width: '100px',
            border: '1px solid gray',
            borderRadius: '5px'
        }}>
            Send orders to backend calculated
        </div>
    );

}

// export { ButtonCreateRoom };