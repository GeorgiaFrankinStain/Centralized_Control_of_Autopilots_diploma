import React, { useState, useRef } from 'react';

import { DragDropContext, Draggable, Droppable, DropResult } from "react-beautiful-dnd"
import './App.css';
import {coordinates_sprite_machines} from "./Canvas";
import {controllerAddABOrderMachine, controllerDeleteABOrderMachineAll} from "./SendOrdersButton";
import {PointCCoAClass} from "./Polygon";





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
    margin: `0 50px 15px 50px`,
    background: isDragging ? "#4a2975" : "white",
    color: isDragging ? "white" : "black",
    border: `1px solid black`,
    fontSize: `20px`,
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

interface IDragListProps {
    addABForOrderCallback:  React.Dispatch<React.SetStateAction<IDataForOrder[]>>,
    listOrders: IDataForOrder[]
}



export default React.memo(
    function ({addABForOrderCallback, listOrders}: IDragListProps) {

        const todoRef = useRef<IDataForOrder[]>([]);

        todoRef.current = listOrders;

        const onDragEnd = (result: DropResult) => {

            if (todoRef.current == null) {
                return;
            }

            const { source, destination } = result;
            if (!destination) {
                return;
            }

            const items = Array.from(listOrders)
            const [ newOrder ] = items.splice(source.index, 1);
            items.splice(destination.index, 0, newOrder);


            addABForOrderCallback(items);
            controllerDeleteABOrderMachineAll();
            todoRef.current.map(({startX, startY, endX, endY, skinNumber, startTime}) => {
                const start = new PointCCoAClass(startX, startY);
                const end = new PointCCoAClass(endX, endY);
                controllerAddABOrderMachine(start, 0, end, 0, startTime);
            });

        }
        return (
            <>
                <div className="App" style={{height: "1000px"}}>
                    <h1>Drag and Drop</h1>
                    <DragDropContext onDragEnd={onDragEnd}>
                        <Droppable droppableId="todo">
                            {(provided) => (
                                <div className="todo" {...provided.droppableProps} ref={provided.innerRef}>
                                    {todoRef.current.map(({ id, startTime, startX, startY, endX, endY, skinNumber }, index) => {
                                        const x = coordinates_sprite_machines[skinNumber].frame.x;
                                        const y = coordinates_sprite_machines[skinNumber].frame.y;
                                        const w = coordinates_sprite_machines[skinNumber].frame.w;
                                        const h = coordinates_sprite_machines[skinNumber].frame.h;
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
                                                                <td rowSpan={3}>
                                                                    <div style={{
                                                                        width: w + "px",
                                                                        height: h + "px",
                                                                        backgroundImage: "url(SetCars.png)",
                                                                        backgroundPosition: -x + "px " + -y + "px"
                                                                    }}></div>
                                                                </td>
                                                                <td>
                                                                    <div>start_time: {startTime}</div>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <div>
                                                                        start:
                                                                        x: {startX}
                                                                        y: {startY}
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <div>
                                                                        end:
                                                                        x: {endX}
                                                                        y: {endY}
                                                                    </div>
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

