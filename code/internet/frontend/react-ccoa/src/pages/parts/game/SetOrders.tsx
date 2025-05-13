import React, {useRef, useState} from "react";
import OrderMachinesDragList, {
    IDataForOrder,
    IDataForOrderWithoutId,
    IOrderMachinesGetState
} from "./DragList";
import SetOrdersCanvas from "./SetOrdersCanvas";
import {PointCCoAClass} from "../../../Polygon";
import {controllerAddABOrderMachine} from "./SendOrdersButton";


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



export function SetOrders ({setOrderMachines, orderMachines}: IOrderMachinesGetState) {

    const [counterListABId, setCounterListABId] = useState<number>(0);


    const countRef = useRef(0);
    countRef.current = counterListABId;

    const orderMachinesRef = useRef<IDataForOrder[]>([]);
    orderMachinesRef.current = orderMachines;






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

        const newOrder = [...orderMachinesRef.current];
        newOrder.splice(
            newOrder.length,
            0,
            newDataForOrderWithId
        );


        const start = new PointCCoAClass(newDataForOrderWithId.startX, newDataForOrderWithId.startY);
        const end = new PointCCoAClass(newDataForOrderWithId.endX, newDataForOrderWithId.endY);
        controllerAddABOrderMachine(start, 0, end, 0, 0);


        setOrderMachines(newOrder);
        orderMachinesRef.current = newOrder;

    }



    return (
        <>
            <SetOrdersCanvas addABForOrderCallback={addABForOrderCallback} />
        </>
    );

}

export default SetOrders;