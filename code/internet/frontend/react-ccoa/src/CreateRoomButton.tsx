import {useState} from "react";
import {API_BASE_URL} from "./index";

export function ButtonCreateRoom() {

    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);



    const jsonData = {
        "users": [
            {
                "name": "alan",
                "age": 23,
                "username": "aturing"
            },
            {
                "name": "john",
                "age": 29,
                "username": "__john__"
            }
        ]
    }

    function handleClick() {

        // Send data to the backend via POST
        fetch(API_BASE_URL + ':8080/create_room', {  // Enter your IP address here

            method: 'POST',
            mode: 'cors',
            body: JSON.stringify(jsonData) // body data type must match "Content-Type" header

        })
            .then(response => response.json())
            .then((usefulData) => {
                console.log(usefulData);
                setLoading(false);
                setData(usefulData);
            })
            .catch((e) => {
                console.error(`An error occurred: ${e}`)
            });

    }

    return (
        <div onClick={handleClick} style={{
            textAlign: 'center',
            width: '100px',
            border: '1px solid gray',
            borderRadius: '5px'
        }}>
            Create room
        </div>
    );

}

// export { ButtonCreateRoom };