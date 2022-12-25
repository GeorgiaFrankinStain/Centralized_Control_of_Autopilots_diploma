import {useState} from "react";

export function ButtonSendOrders() {

    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);



    // const jsonData = {"id": "textid"};

    const jsonData = {
        "version": 1,
        "id_room": "VAsIOqbh7JIoSEGuZvAN", //option
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
                        "x": 10, "y": 40
                    },
                    "layer":0,
                    "angle":0.0
                },
                "standing": "false",
                "standing_after": "false"
            }
        ]
    }



    function sendApplicationOrdersClick() {
        console.log("sendApplicationOrdersClick");

        // Send data to the backend via POST
        fetch('http://localhost:8080/to_application_orders', {  // Enter your IP address here
            method: 'POST',

            headers: {
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            body: JSON.stringify(jsonData) // body data type must match "Content-Type" header

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
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            body: JSON.stringify(jsonData) // body data type must match "Content-Type" header

        })
            .then(response => response.json())
            .then((usefulData) => {
                console.log("userful_data");
                console.log(usefulData);
                setLoading(false);
                setData(usefulData);
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