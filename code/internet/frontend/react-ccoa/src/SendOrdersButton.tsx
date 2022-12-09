import {useState} from "react";

export function ButtonSendOrders() {

    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);



    const jsonData = {"id": "textid"};

    // const jsonData = {
    //     "orders": [
    //         {
    //             "name": "alan",
    //             "age": 23,
    //             "username": "aturing"
    //         },
    //         {
    //             "name": "john",
    //             "age": 29,
    //             "username": "__john__"
    //         }
    //     ]
    // }



    function sendApplicationOrdersClick() {

        // Send data to the backend via POST
        fetch('http://localhost:8080/to_application_orders', {  // Enter your IP address here
            method: 'POST',

            headers: {
                'Content-Type': 'application/json'
            },
            mode: 'no-cors',
            body: JSON.stringify({    name: "Corrected post"  }) // body data type must match "Content-Type" header

        })
            .then(response => response.json())
            .then((usefulData) => {
                console.log(usefulData);
                setLoading(false);
                setData(usefulData);
            })
            .catch((e) => {
                console.error(`An error occurred: ${e}`);

                // getElbowFootprint();
            });

    }

    // function getElbowFootprint() {
    //     // Send data to the backend via POST
    //     fetch('http://localhost:8080/get_elbow_footprint', {  // Enter your IP address here
    //
    //         method: 'POST',
    //         mode: 'no-cors',
    //         body: JSON.stringify(jsonData) // body data type must match "Content-Type" header
    //
    //     })
    //         .then(response => response.json())
    //         .then((usefulData) => {
    //             console.log(usefulData);
    //             setLoading(false);
    //             setData(usefulData);
    //         })
    //         .catch((e) => {
    //             console.error(`An error occurred: ${e}`);
    //         });
    // }

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