import React, {useRef, useState} from "react";
import DragList, {IDataForOrder, IDataForOrderWithoutId} from "./DragList";
import SetOrdersCanvas from "./SetOrdersCanvas";


const getItemStyle = (isDragging: boolean, draggableStyle: any) => ({
    padding: 10,
    margin: `0 50px 15px 50px`,
    background: isDragging ? "#4a2975" : "white",
    color: isDragging ? "white" : "black",
    border: `1px solid black`,
    fontSize: `20px`,
    borderRadius: `5px`,

    ...draggableStyle
})

const SetOrders: React.FC = () => {

    const [counterListABId, setCounterListABId] = useState<number>(0);
    const [swing, setSwing] = useState<boolean>(true);
    const swingRef = useRef(true);
    swingRef.current = swing;

    const [listDataForOrders, setListDataForOrders] = useState<IDataForOrder[]>([]);

    const countRef = useRef(0);
    countRef.current = counterListABId;

    const listOrdersRef = useRef<IDataForOrder[]>([]);
    listOrdersRef.current = listDataForOrders;




    const addABForOrderCallback = (
        newDataForOrder: IDataForOrderWithoutId
    ) => {
        setCounterListABId(countRef.current + 1);
        const newDataForOrderWithId: IDataForOrder = {
            id: countRef.current + "",
            startTime: 0,
            startX: newDataForOrder.startX,
            startY: newDataForOrder.startY,
            endX: newDataForOrder.endX,
            endY: newDataForOrder.endY,
            skinNumber: newDataForOrder.skinNumber
        }

        const newState = [...listOrdersRef.current];
        newState.splice(
            newState.length,
            0,
            newDataForOrderWithId
        );

        setListDataForOrders(newState);
        listOrdersRef.current = newState;

    }
/*
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
        fetch('http://localhost:8080/create_room', {  // Enter your IP address here
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

    }*/



    return (
        <>
            <button onClick={() => setCounterListABId(13)} >button {counterListABId}</button>
            <SetOrdersCanvas addABForOrderCallback={addABForOrderCallback} />
            <DragList listOrders={listDataForOrders} addABForOrderCallback={setListDataForOrders} />
        </>
    );

}

export default SetOrders;