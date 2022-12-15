import React, {MutableRefObject, useEffect, useRef} from "react";
import assert from "assert";




const imitationDataFromGetElbowFootprint = { //формируется PathMovingUniqueDataForRendering
    "id_moving_unique_object": "235662",
    "appearanceType": "non-uniform", //non-uniform is simple colorFill polygon
    "appearancePolygonForm": [ //(half_option_LINK_nEfBkQ, if appearanceType: non-uniform)
        {"x": "-13", "y": "-13"},
        {"x": "-13", "y": "13"},
        {"x": "13", "y": "13"},
        {"x": "13", "y": "-13"}
    ],
    timeSpaceCoordinates: [
        {"t": "0", "layer": "0", "x": "10", "y": "0", "angle": "0"},
        {"t": "1", "layer": "0", "x": "10", "y": "10", "angle": "0"},
        {"t": "2", "layer": "0", "x": "10", "y": "20", "angle": "0"}
    ]
}


interface SpacePosition {
    x: number;
    y: number;
}

interface VectorSpaceTimePosition {
    timeFrom: number;
    from: SpacePosition;
    timeTo: number;
    to: SpacePosition;
}

function getPositionFor(time: number): SpacePosition | null {
    const vectorSpaceTime: VectorSpaceTimePosition | null = getVectorMoveInSpaceTime(time);
    if (vectorSpaceTime == null) {
        return null;
    } else {
        return getApproximationPosition(
            vectorSpaceTime.from,
            vectorSpaceTime.timeFrom,
            vectorSpaceTime.to,
            vectorSpaceTime.timeTo,
            time
        );
    }
}

function getVectorMoveInSpaceTime(time: number): VectorSpaceTimePosition | null {

    if (imitationDataFromGetElbowFootprint.timeSpaceCoordinates.length < 2) {
        throw new Error("no exist diapasons");
    }

    const arrayTimeSpaceCoordinates = imitationDataFromGetElbowFootprint.timeSpaceCoordinates;
    for (let j = 0; j < arrayTimeSpaceCoordinates.length; j++) {

        const currentItem = arrayTimeSpaceCoordinates[j];
        const fromTime: number = +currentItem.t;

        const nextIndex = j + 1;
        const nextItem = arrayTimeSpaceCoordinates[nextIndex];
        const isRangesAreOver = !nextItem;


        if (isRangesAreOver) {
            break;
        }
        const toTime: number = +nextItem.t;

        if (fromTime <= time && time <= toTime) {
            return  {
                timeFrom: fromTime,
                from: {x: +currentItem.x, y: +currentItem.y},
                timeTo: toTime,
                to: {x: +nextItem.x, y: +nextItem.y}
            };
        }
    }

    return null;
}



function getApproximationPosition(
    position1: SpacePosition,
    time1: number,
    position2: SpacePosition,
    time2: number,
    timeApproximation: number
): SpacePosition {
    const xApproximation = getApproximationCoordinate(position1.x, time1, position2.x, time2, timeApproximation);
    const yApproximation = getApproximationCoordinate(position1.y, time1, position2.y, time2, timeApproximation);

    return {x: xApproximation, y: yApproximation};
}

function getApproximationCoordinate(
    coordination1: number,
    time1: number,
    coordination2: number,
    time2: number,
    timeApproximation: number
): number {
    const isReverseOrder: boolean = time1 > time2;
    if (isReverseOrder) {
        return getApproximationCoordinate(coordination2, time2, coordination1, time1, timeApproximation);
    } else {
        return getApproximationAscendingOrderOfTime(coordination1, time1, coordination2, time2, timeApproximation);
    }
}

function getApproximationAscendingOrderOfTime(
    position1: number,
    time1: number,
    position2: number,
    time2: number,
    timeApproximation: number
):number {
    // assert(time1 <= timeApproximation);
    // assert(timeApproximation <= time2);


    const percentage = percentageProximity(time1, time2, timeApproximation);
    const positionInterval = position2 - position1;

    const approximationNumber = position1 + (positionInterval * percentage);

    return approximationNumber;

}

function comparison(a:number, b:number):boolean {
    const differentPositive: number = Math.abs(a - b);
    const accuracy = 0.0001;
    return differentPositive < accuracy;
}

function percentageProximity(
    time1: number,
    time2: number,
    timeApproximation: number
): number {
    const timeInterval = time2 - time1;
    const isIntervalIs0 = comparison(timeInterval, 0);
    if (isIntervalIs0) {
        const inMiddle = 0.5;
        return inMiddle;
    }
    const percentProximity = (timeApproximation - time1) / timeInterval;
    return percentProximity;
}












































interface NextAnimationFrameHandler {
    (nowTime: number) : void;
}

type SetParameters = {

    nextAnimationFrameHandler: NextAnimationFrameHandler;
    // we still want to have "infinite" animations in some cases
    duration: number;
    shouldAnimate: boolean;
}

//
// const useAnimationFrame = ({
//                                nextAnimationFrameHandler: NextAnimationFrameHandler,
//                                // we still want to have "infinite" animations in some cases
//                                duration = Number.POSITIVE_INFINITY,
//                                shouldAnimate = true
//                            }) => {


const useAnimationFrame = ({
                               nextAnimationFrameHandler,
                               // we still want to have "infinite" animations in some cases
                               duration = Number.POSITIVE_INFINITY,
                               shouldAnimate = true
                           }: SetParameters) => {
    const frame = React.useRef(0);
    // keep track of when animation is started
    const firstFrameTime = React.useRef(performance.now());
    const start = Date.now();

    const animate = (now: number) => {
        // calculate at what time fraction we are currently of whole time of animation
        const timeFromStartAnimation = Date.now() - start;
        nextAnimationFrameHandler(timeFromStartAnimation);
        requestAnimationFrame(animate);
    };

    React.useEffect(() => {
        // console.log(shouldAnimate);
        if (shouldAnimate) {
            firstFrameTime.current = performance.now();
            frame.current = requestAnimationFrame(animate);
        } else {
            cancelAnimationFrame(frame.current);
        }

        return () => cancelAnimationFrame(frame.current);
    }, [shouldAnimate]);
};




const Canvas = () => {
    const brickRef = React.useRef()  as MutableRefObject<HTMLDivElement>;
    const [shouldAnimate, setShouldAnimate] = React.useState(false);




    // const canvasRef = useRef<HTMLCanvasElement | null>(null);
    const canvasRef = useRef()   as MutableRefObject<HTMLDivElement>;
    let timeCurrent = 0;







    const reset = () => {
        brickRef.current.style.left = String(0);
    };

    const nextAnimationFrameHandler = (nowTime: number) => {
        const brick = brickRef.current;
        // if (brick) {
        //     const currentLeft = Number(brick.style.left.replace("px", "") || 0);
        //
        //     if (nowTime < 1) {
        //         brick.style.left = `${1000 * nowTime}px`;
        //     } else {
        //         setShouldAnimate(false);
        //         brick.style.left = `1000px`;
        //     }
        // }

        // const canvas = canvasRef.current;
        // const canvas: HTMLCanvasElement = canvasRef.current;
        const canvas = document.getElementById('myChart') as HTMLCanvasElement;
        // const canvas: HTMLCanvasElement = document.getElementById('myChart');

        if (canvas) {

            // canvas.width = 400;


            // canvas.height = 400;
            const context = canvas.getContext('2d');




            let x = 50;

            if (!context) {

                return;
            }
            context.clearRect(0, 0, canvas.width, canvas.height);

            context.fillStyle = 'blue';
            context.fillRect(0, 0, 100, 100);




            if (imitationDataFromGetElbowFootprint.appearanceType == "non-uniform") {
                console.log(getPositionFor(nowTime / 1000));
                context.fillStyle = 'green';

                context.beginPath();
                let i = 0;
                imitationDataFromGetElbowFootprint.appearancePolygonForm.forEach(pointForm => {
                    if (i == 0) {
                        context.moveTo(Number(pointForm.x), Number(pointForm.y));
                    } else {
                        context.lineTo(Number(pointForm.x), Number(pointForm.y));
                    }
                    i++;

                });

                context.closePath();

                context.fill();


            }
            // context.fillStyle = 'yellow';
            // context.fillRect(0,0, canvas.width, canvas.height);
            //
            // context.beginPath();
            // context.moveTo(0, 0);
            // context.lineTo(100, 50);
            // context.lineTo(50, 100);
            // context.lineTo(0, 90);
            // context.closePath();
            // context.fill();

            // context.fillStyle = 'green';
            // context.fillRect(x = x + 15, 50, 300, 200);

            // context.fill();
        }
    };

    useAnimationFrame({
        nextAnimationFrameHandler,
        shouldAnimate,
        duration: 1000
    });

    return (
        <>
            <button onClick={() => reset()}>Reset!</button>
            <main className="path">
                <div
                    className="brick"
                    ref={brickRef}
                    onClick={() => setShouldAnimate(true)}
                >
                    Click me!
                </div>
            </main>

            <canvas  id="myChart"  />;
        </>
    );
};



// ReactDOM.render(<Counter />, document.getElementById('app'))


//
// const Canvas = () => {
//
//
//     return <canvas />;
// };


export default Canvas;