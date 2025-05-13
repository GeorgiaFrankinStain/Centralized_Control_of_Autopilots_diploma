import React, {useState} from "react";
import {IDataForOrder} from "./DragList";



interface IClearAllButtonProps {
    setOrderMachines:  React.Dispatch<React.SetStateAction<IDataForOrder[]>>,
    updateStateCanvasRenderingResultCallback:  React.Dispatch<React.SetStateAction<number>>
}


export function ButtonClearAll({updateStateCanvasRenderingResultCallback, setOrderMachines}: IClearAllButtonProps) {

    function sendApplicationOrdersClick() {
        updateStateCanvasRenderingResultCallback(Math.random());
        setOrderMachines([]);
    }

    return (
        <div className={"button"} onClick={sendApplicationOrdersClick} style={{
            width: '100px',
            border: '1px solid gray',
            borderRadius: '5px'
        }}>
           Очистить всё
        </div>
    );

}
