import React, { useState, useRef } from 'react';

import { DragDropContext, Draggable, Droppable, DropResult } from "react-beautiful-dnd"
import '../../../App.css';
import {coordinates_sprite_machines} from "./DrawingMotionPath";
import {controllerAddABOrderMachine, controllerDeleteABOrderMachineAll} from "./SendOrdersButton";
import {PointCCoAClass} from "../../../Polygon";
import SpriteCars from '../../../SetCars.png';





const listABForOrder = [
    {
        id: "1",
        startTime: 0,
        startX: 1,
        startY: 0,
        endX: 40,
        endY: 0,
        skinNumber: 3
    },
    {
        id: "2",
        startTime: 0,
        startX: 1,
        startY: 22,
        endX: 40,
        endY: 22,
        skinNumber: 4
    },
    {
        id: "3",
        startTime: 0,
        startX: 1,
        startY: 44,
        endX: 40,
        endY: 44,
        skinNumber: 5
    },
    {
        id: "4",
        startTime: 0,
        startX: 1,
        startY: 66,
        endX: 40,
        endY: 66,
        skinNumber: 6
    }
]



const getItemStyle = (isDragging: boolean, draggableStyle: any) => ({
    padding: 10,
    margin: `10px 10px 10px 10px`,
    background: isDragging ? "#4a2975" : "white",
    color: isDragging ? "white" : "black",
    border: `1px solid black`,
    fontSize: `13px`,
    borderRadius: `5px`,

    ...draggableStyle
})

export interface IDataForOrder {
    id: string,
    startTime: number,
    startX: number,
    startY: number,
    endX: number,
    endY: number,
    skinNumber: number
}
export interface IDataForOrderWithoutId {
    startX: number,
    startY: number,
    endX: number,
    endY: number,
    skinNumber: number
}


export interface IOrderMachinesGetState {
    setOrderMachines:  React.Dispatch<React.SetStateAction<IDataForOrder[]>>,
    orderMachines: IDataForOrder[]
}



export default React.memo(
    function ({setOrderMachines, orderMachines}: IOrderMachinesGetState) {

        const todoRef = useRef<IDataForOrder[]>([]);

        todoRef.current = orderMachines;

        const onDragEnd = (result: DropResult) => {

            if (todoRef.current == null) {
                return;
            }

            const { source, destination } = result;
            if (!destination) {
                return;
            }

            const items = Array.from(orderMachines)
            const [ newOrder ] = items.splice(source.index, 1);
            items.splice(destination.index, 0, newOrder);


            setOrderMachines(items);
            controllerDeleteABOrderMachineAll();
            todoRef.current.map(({startX, startY, endX, endY, skinNumber, startTime}) => {
                const start = new PointCCoAClass(startX, startY);
                const end = new PointCCoAClass(endX, endY);
                controllerAddABOrderMachine(start, 0, end, 0, startTime);
            });

        }
        return (
            <>
                <div style={{
                    width: "240px",
                    borderRadius: `5px`,
                    border: "1px solid black",
                    height: "fit-content",
                    minHeight: "500px",
                    marginLeft: "15px"
                }}>
                    <DragDropContext onDragEnd={onDragEnd}>
                        <Droppable droppableId="todo">
                            {(provided) => (
                                <div className="todo" {...provided.droppableProps} ref={provided.innerRef}>
                                    {todoRef.current.map(({ id, startTime, startX, startY, endX, endY, skinNumber }, index) => {
                                        const x = coordinates_sprite_machines[skinNumber].frame.x;
                                        const y = coordinates_sprite_machines[skinNumber].frame.y;
                                        const w = coordinates_sprite_machines[skinNumber].frame.w;
                                        const h = coordinates_sprite_machines[skinNumber].frame.h;
                                        const scale = 0.25;

                                        return (
                                            <Draggable key={id} draggableId={id} index={index}>
                                                {(provided, snapshot) => (
                                                    <div
                                                        ref={provided.innerRef}
                                                        {...provided.draggableProps}
                                                        {...provided.dragHandleProps}
                                                        style={getItemStyle(snapshot.isDragging, provided.draggableProps.style)}
                                                    >
                                                        <table>
                                                            <tr>
                                                                <td rowSpan={3} style={{width: "50px"}}>
                                                                    <div style={{
                                                                        width: (w * scale) + "px",
                                                                        height: (h * scale) + "px",
                                                                        backgroundImage: `url(${SpriteCars})`,
                                                                        backgroundSize: (1074 * scale) + "px " + (1010 * scale) + "px",
                                                                        backgroundPosition: -(x * scale) + "px " + -(y * scale) + "px"
                                                                    }}></div>
                                                                </td>
                                                                <td>
                                                                    <div>{startTime} | ({startX}; {startY}) | ({endX}; {endY})</div>
                                                                </td>

                                                            </tr>
                                                        </table>
                                                    </div>
                                                )}
                                            </Draggable>
                                        )
                                    })}
                                </div>
                            )}
                        </Droppable>
                    </DragDropContext>
                </div>
            </>

        );

    },
    (prevProps, nextProps) => {
        return false;
    }
);

