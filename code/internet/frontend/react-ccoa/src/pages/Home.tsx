import React, {Dispatch, SetStateAction, useState} from "react";
import './Home.css';
import SetOrders from "./parts/game/SetOrders";
import {ButtonSendOrders} from "./parts/game/SendOrdersButton";
import DrawingMotionPath from "./parts/game/DrawingMotionPath";
import {Bottom} from "../common/Bottom";
import OrderMachinesDragList, {IDataForOrder} from "./parts/game/DragList";
import {ButtonClearAll} from "./parts/game/ButtonClearAll";


const Home: React.FC = () => {

    const [kostilForRerender, setKostilForRerender] = useState<number>(0);

    const [orderMachines, setOrderMachines] = useState<IDataForOrder[]>([]);


    return (
        <div className={"line-break-container"}>

            <div className={"line-break-container"} style={{height: "fit-content"}}>
                <SetOrders orderMachines={orderMachines} setOrderMachines={setOrderMachines} />
                <div style={{
                    display: "table",
                    margin: 0,
                    padding: 0,
                    boxSizing: "border-box"
                }}>
                    <div style={{display: "table-cell", verticalAlign: "middle", padding: "0px 5px 0px 5px"}}>
                        <ButtonSendOrders updateStateCanvasRenderingResultCallback={setKostilForRerender} />
                        <ButtonClearAll setOrderMachines={setOrderMachines} updateStateCanvasRenderingResultCallback={setKostilForRerender} />
                    </div>
                </div>
                <DrawingMotionPath stateForForceUpdateCanvasInCode={kostilForRerender} />
            </div>

            <OrderMachinesDragList orderMachines={orderMachines} setOrderMachines={setOrderMachines} />
        </div>
    )
};

export default Home;
