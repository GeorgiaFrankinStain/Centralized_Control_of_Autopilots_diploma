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

function getApproximationPosition(
    position1: number,
    time1: number,
    position2: number,
    time2: number,
    timeApproximation: number
): number {
    const isReverseOrder: boolean = time1 > time2;
    if (isReverseOrder) {
        return getApproximationPosition(position2, time2, position1, time1, timeApproximation);
    } else {
        return getApproximationAscendingOrderOfTime(position1, time1, position2, time2, timeApproximation);
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
    (progress: number) : void;
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

    const animate = (now: number) => {
        // calculate at what time fraction we are currently of whole time of animation
        let timeFraction = (now - firstFrameTime.current) / duration;
        if (timeFraction > 1) {
            timeFraction = 1;
        }

        if (timeFraction <= 1) {
            nextAnimationFrameHandler(timeFraction);

            // request next frame only in cases when we not reached 100% of duration
            if (timeFraction != 1) frame.current = requestAnimationFrame(animate);
        }
    };

    React.useEffect(() => {
        console.log(shouldAnimate);
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

    const reset = () => {
        brickRef.current.style.left = String(0);
    };

    const nextAnimationFrameHandler = (progress: number) => {
        console.log(progress);
        const brick = brickRef.current;
        if (brick) {
            const currentLeft = Number(brick.style.left.replace("px", "") || 0);

            if (progress < 1) {
                brick.style.left = `${1000 * progress}px`;
            } else {
                setShouldAnimate(false);
                brick.style.left = `1000px`;
            }
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