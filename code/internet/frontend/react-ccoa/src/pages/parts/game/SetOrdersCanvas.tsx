import React, {MutableRefObject, useEffect, useRef, useState} from "react";
import {coordinates_sprite_machines, globalScale} from "./DrawingMotionPath";
import {PointCCoAClass} from "../../../Polygon";
import {IDataForOrderWithoutId} from "./DragList";



// canvas.addEventListener("mousedown", this.pressEventHandler);
//add position in fixed positions list
//end draw then move
// canvas.addEventListener("mousemove", this.dragEventHandler); //draw potential position car
// canvas.addEventListener("mouseup", this.releaseEventHandler);
//nothing
// canvas.addEventListener("mouseout", this.cancelEventHandler);







const angleUser = 0;

// this.indexFrameMachine = (this.indexFrameMachine + 1) % coordinates_sprite_machines.length;




interface ClickCar {
    x: number,
    y: number,
    angle: number,
    numberSkin: number
}




interface ISetOrdersCanvasProps {
    addABForOrderCallback: (newDataForOrder: IDataForOrderWithoutId) => void
}


const CanvasSetOrders: React.FC<ISetOrdersCanvasProps> = ({addABForOrderCallback}) => {
    const brickRef = React.useRef()  as MutableRefObject<HTMLDivElement>;
    const refCanvas = React.useRef(null);


    class DrawingApp {
        private canvas: HTMLCanvasElement | undefined;
        private context: CanvasRenderingContext2D | undefined;
        private paint: boolean = false;


        private clickCars: ClickCar[] = [];
        private indexFrameMachine: number = 3; //because on the server the sequential numbering of the id starts with 3

        constructor(/*context: CanvasRenderingContext2D, canvas: HTMLCanvasElement*/) {
            let canvasTry = refCanvas.current as HTMLCanvasElement | null;

            if (!canvasTry) {
                return;
            }
            let canvas = canvasTry as HTMLCanvasElement;
            let context = canvas.getContext("2d") as CanvasRenderingContext2D;


            this.canvas = canvas;
            this.context = context;

            this.redraw();
            this.createUserEvents();
        }


        private createUserEvents() {
            let canvas = this.canvas;


            if (!canvas) {
                return;
            }

            canvas.addEventListener("mousedown", this.pressEventHandler);
            canvas.addEventListener("mousemove", this.dragEventHandler);
            canvas.addEventListener("mouseup", this.releaseEventHandler);
            canvas.addEventListener("mouseout", this.cancelEventHandler);

            canvas.addEventListener("touchstart", this.pressEventHandler);
            canvas.addEventListener("touchmove", this.dragEventHandler);
            canvas.addEventListener("touchend", this.releaseEventHandler);
            canvas.addEventListener("touchcancel", this.cancelEventHandler);

        }

        private redraw() {


            let context = this.context;

            if (!context) {
                return;
            }
            for (let i = 0; i < this.clickCars.length; ++i) {
                this.drawCar(
                    this.clickCars[i].x,
                    this.clickCars[i].y,
                    this.clickCars[i].angle,
                    this.clickCars[i].numberSkin
                );
            }
        }






        private drawCar(x: number, y: number, angle: number, numberSkin: number) {
            const sideSize = 20; //TEST_SQUARE_20

            const halfScaleSideSquare = sideSize * globalScale / 2;

            const image = document.getElementById('set_cars') as HTMLImageElement;


            if (!this.context) {
                return;
            }


            this.context.save();
            this.context.translate(
                x + halfScaleSideSquare,
                y + halfScaleSideSquare
            );
            this.context.rotate(angle);
            this.context.translate(
                - x - halfScaleSideSquare,
                - y - halfScaleSideSquare
            );
            this.context.drawImage(
                image,
                coordinates_sprite_machines[numberSkin].frame.x,
                coordinates_sprite_machines[numberSkin].frame.y,
                coordinates_sprite_machines[numberSkin].frame.w,
                coordinates_sprite_machines[numberSkin].frame.h,
                x,
                y,
                sideSize  * globalScale,
                sideSize  * globalScale
            );

            this.context.restore();

        }

        private addClick(x: number, y: number, angle: number, numberSkin: number) {
            this.clickCars.push({x, y, angle, numberSkin});
        }

        private resetMachines() {
            if (!this.context) {
                return;
            }
            if (!this.canvas) {
                return;
            }
            this.clearCanvas();
            this.clickCars = [];
        }

        public clearEventHandler = () => {
            this.resetMachines();
        }

        private releaseEventHandler = () => {
            this.paint = false;
            this.redraw();
        }

        private cancelEventHandler = () => {
            this.paint = false;
        }

        private isStartSet: boolean = false;
        private previousStart = new PointCCoAClass(0, 0);


        private pressEventHandler = (e: MouseEvent | TouchEvent) => {
            let mouseX = (e as TouchEvent).changedTouches ?
                (e as TouchEvent).changedTouches[0].pageX :
                (e as MouseEvent).pageX;
            let mouseY = (e as TouchEvent).changedTouches ?
                (e as TouchEvent).changedTouches[0].pageY :
                (e as MouseEvent).pageY;


            if (!this.context) {
                return;
            }
            if (!this.canvas) {
                return;
            }

            mouseX -= this.canvas.offsetLeft;
            mouseY -= this.canvas.offsetTop;

            let isNeedUpIndexFrameMachine = false;
            if (this.isStartSet) {
                addABForOrderCallback({
                    startX: this.previousStart.x,
                    startY: this.previousStart.y,
                    endX: mouseX,
                    endY: mouseY,
                    skinNumber: this.indexFrameMachine
                });
                isNeedUpIndexFrameMachine = true;

                this.isStartSet = false;
            } else {
                this.previousStart = new PointCCoAClass(mouseX, mouseY);
                this.isStartSet = true;
            }

            this.paint = true;
            this.addClick(mouseX, mouseY, angleUser, this.indexFrameMachine);
            if (isNeedUpIndexFrameMachine) {
                this.indexFrameMachine++;
            }
            this.redraw();
        }

        private dragEventHandler = (e: MouseEvent | TouchEvent) => {
            let mouseX = (e as TouchEvent).changedTouches ?
                (e as TouchEvent).changedTouches[0].pageX :
                (e as MouseEvent).pageX;
            let mouseY = (e as TouchEvent).changedTouches ?
                (e as TouchEvent).changedTouches[0].pageY :
                (e as MouseEvent).pageY;



            if (!this.context) {
                return;
            }
            if (!this.canvas) {
                return;
            }

            mouseX -= this.canvas.offsetLeft;
            mouseY -= this.canvas.offsetTop;

            this.clearCanvas();

            this.drawCar(mouseX, mouseY, angleUser, this.indexFrameMachine);

            this.redraw();

            e.preventDefault();
        }
        private clearCanvas() {
            if (!this.context) {
                return;
            }

            if (!this.canvas) {
                return;
            }
            this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
        }
    }


    function deleteOrder() {

    }
    function addOrder() {

    }





    function changeViewDeleteOrder() {

    }

    function changeViewAddOrder() {

    }


    useEffect(() => {
        let classVar = new DrawingApp(/*context, canvas*/);
    }, []);


    const reset = () => {
        brickRef.current.style.left = String(0);
    }


    return (
        <>
            <div>
                <canvas  id="setOrdersCanvas" width={"625px"} height={"625px"} ref={refCanvas} />
            </div>
            {/*<div*/}
            {/*    id="clear"*/}
            {/*    onClick={() => classVar.clearEventHandler()}*/}
            {/*>clear</div>*/}
            <img id="set_cars" className="display-none" src={require("../../../SetCars.png")} />
        </>
    );
}

export default CanvasSetOrders;
