import React, {MutableRefObject, useEffect, useRef} from "react";
import assert from "assert";
import {PolygonCCoA, PolygonCCoAClass, PointCCoA, PositionCCoA} from "./Polygon";




const imitationDataFromGetElbowFootprint = { //формируется PathMovingUniqueDataForRendering
    "elbow_moving_objects": [
        {
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
                {"t": "1", "layer": "0", "x": "20", "y": "20", "angle": 3.141592653589793},
                {"t": "2", "layer": "0", "x": "50", "y": "50", "angle": "0"}
            ]
        },
        {
            "id_moving_unique_object": "34773",
            "appearanceType": "non-uniform", //non-uniform is simple colorFill polygon
            "appearancePolygonForm": [ //(half_option_LINK_nEfBkQ, if appearanceType: non-uniform)
                {"x": "-13", "y": "-13"},
                {"x": "-13", "y": "13"},
                {"x": "13", "y": "13"},
                {"x": "13", "y": "-13"}
            ],
            timeSpaceCoordinates: [
                {"t": "0", "layer": "0", "x": "55", "y": "0", "angle": "0"},
                {"t": "1", "layer": "0", "x": "70", "y": "20", "angle": 3.141592653589793},
                {"t": "2", "layer": "0", "x": "90", "y": "50", "angle": "0"}
            ]
        }
    ]
}



const imitationDataFromGetElbowFootprint2 = "{ \"elbow_moving_objects\": [ { \"id_moving_unique_object\": \"235662\", \"appearanceType\": \"non-uniform\", \"appearancePolygonForm\": [ {\"x\": \"-13\", \"y\": \"-13\"}, {\"x\": \"-13\", \"y\": \"13\"}, {\"x\": \"13\", \"y\": \"13\"}, {\"x\": \"13\", \"y\": \"-13\"} ], \"timeSpaceCoordinates\": [ {\"t\": \"0\", \"layer\": \"0\", \"x\": \"10\", \"y\": \"0\", \"angle\": \"0\"}, {\"t\": \"1\", \"layer\": \"0\", \"x\": \"20\", \"y\": \"20\", \"angle\": \"3.141592653589793\"}, {\"t\": \"2\", \"layer\": \"0\", \"x\": \"50\", \"y\": \"50\", \"angle\": \"0\"} ] }, { \"id_moving_unique_object\": \"34773\", \"appearanceType\": \"non-uniform\", \"appearancePolygonForm\": [ {\"x\": \"-13\", \"y\": \"-13\"}, {\"x\": \"-13\", \"y\": \"13\"}, {\"x\": \"13\", \"y\": \"13\"}, {\"x\": \"13\", \"y\": \"-13\"} ], \"timeSpaceCoordinates\": [ {\"t\": \"0\", \"layer\": \"0\", \"x\": \"55\", \"y\": \"0\", \"angle\": \"0\"}, {\"t\": \"1\", \"layer\": \"0\", \"x\": \"70\", \"y\": \"20\", \"angle\": \"3.141592653589793\"}, {\"t\": \"2\", \"layer\": \"0\", \"x\": \"90\", \"y\": \"50\", \"angle\": \"0\"} ] } ] }";





interface SetElbowsJson {
    elbow_moving_objects: ElbowJson[];
}

interface ElbowJson {
    id_moving_unique_object: string;
    appearanceType: string;
    appearancePolygonForm: PointCCoA[];
    timeSpaceCoordinates: TimeSpaceCoordinateJson[];
}

interface TimeSpaceCoordinateJson {
    t: number;
    layer: number;
    x: number;
    y: number;
    angle: number;
}




interface DataFootprintForRendering {
    id_machine: string;
    polygonInPosition: PolygonCCoA;
}





interface VectorSpaceTimePosition {
    timeFrom: number;
    from: PositionCCoA;
    timeTo: number;
    to: PositionCCoA;
}
















interface StorageElbowsJsonDTO {
    setJsonString(stringJson: string): void;
    setJson(setElbows: SetElbowsJson): void;
    getDataMultiFootprintsForRendering(time: number): DataFootprintForRendering[];
}

interface ElbowMovingObject {
    getIdMovingUniqueObject(): string;

    getDataFootprintForRendering(time: number): DataFootprintForRendering | null;
}

class StorageElbowJsonDTOClass implements StorageElbowsJsonDTO {

    private elbowsJsonDTO: Array<ElbowMovingObject> = new Array<ElbowMovingObject>();

    setJsonString(stringJson: string): void {
        let setElbows: SetElbowsJson = JSON.parse(imitationDataFromGetElbowFootprint2) as SetElbowsJson;
        this.setJson(setElbows);
    }
    setJson(setElbows: SetElbowsJson): void {
        for (let i = 0; i < setElbows.elbow_moving_objects.length; i++) {
            let newElbowMovingObject = new ElbowMovingObjectClass(setElbows.elbow_moving_objects[i]);
            this.elbowsJsonDTO.push(newElbowMovingObject)
        }
    }

    getDataMultiFootprintsForRendering(time: number): DataFootprintForRendering[] {
        let dataMultiFootprintsForRendering: Array<DataFootprintForRendering> = new Array<DataFootprintForRendering>();

        for (let i = 0; i < this.elbowsJsonDTO.length; i++) {
            let elbow = this.elbowsJsonDTO[i];
            if (elbow) {
                let dataFootprintForRendering = elbow.getDataFootprintForRendering(time);
                if (dataFootprintForRendering) {
                    dataMultiFootprintsForRendering.push(dataFootprintForRendering);
                }
            }
        }

        return dataMultiFootprintsForRendering;
    }

}


class ElbowMovingObjectClass implements ElbowMovingObject {
    private readonly appearanceType: string = "";
    private readonly id: string = "";
    private polygonForm: PolygonCCoA = new PolygonCCoAClass();
    private readonly timeSpaceCoordinates: Array<TimeSpaceCoordinateJson> = new Array<TimeSpaceCoordinateJson>();


    constructor(elbowJson: ElbowJson) {
        this.appearanceType = elbowJson.appearanceType;
        this.id = elbowJson.id_moving_unique_object;
        this.timeSpaceCoordinates = elbowJson.timeSpaceCoordinates;
        this.polygonForm.addPoints(elbowJson.appearancePolygonForm);
    }

    getIdMovingUniqueObject(): string {
        return this.id;
    }

    getDataFootprintForRendering(time: number): DataFootprintForRendering | null {

        if (this.appearanceType == "non-uniform") {
            const position = this.getPositionFor(time);


            let movedPolygon = null;
            if (position) {
                movedPolygon = this.polygonForm.rotateRelative00(position.angle);
                movedPolygon = movedPolygon.getDeposeOn(position.point);
                return {id_machine: this.id, polygonInPosition: movedPolygon};
            }
        }
        return null;
    }





    private getPositionFor(time: number): PositionCCoA | null {
        const vectorSpaceTime: VectorSpaceTimePosition | null = this.getVectorMoveInSpaceTime(time);
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

    private getVectorMoveInSpaceTime(time: number): VectorSpaceTimePosition | null {

        const arrayTimeSpaceCoordinates = this.timeSpaceCoordinates;
        if (arrayTimeSpaceCoordinates.length < 2) {
            throw new Error("no exist diapasons");

        }
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
                    from: {point: {x: +currentItem.x, y: +currentItem.y}, angle: +currentItem.angle},
                    timeTo: toTime,
                    to: {point: {x: +nextItem.x, y: +nextItem.y}, angle: +nextItem.angle}
                };
            }
        }

        return null;
    }


}











function getApproximationPosition(
    position1: PositionCCoA,
    time1: number,
    position2: PositionCCoA,
    time2: number,
    timeApproximation: number
): PositionCCoA {
    const xApproximation = getApproximationCoordinate(position1.point.x, time1, position2.point.x, time2, timeApproximation);
    const yApproximation = getApproximationCoordinate(position1.point.y, time1, position2.point.y, time2, timeApproximation);
    const angleApproximation = getApproximationAngle(position1.angle, time1, position2.angle, time2, timeApproximation);

    return {point: {x: xApproximation, y: yApproximation}, angle: angleApproximation};
}


















function getApproximationAngle(
    angle1: number,
    time1: number,
    angle2: number,
    time2: number,
    timeApproximation: number
): number {
    if (angle1 <= angle2) {
        return approximationAngleAscendingOrderOfAngle(angle1, time1, angle2, time2, timeApproximation);
    } else {
        return approximationAngleAscendingOrderOfAngle(angle2, time2, angle1, time1, timeApproximation);
    }
}

function approximationAngleAscendingOrderOfAngle(
    angle1: number,
    time1: number,
    angle2: number,
    time2: number,
    timeApproximation: number
): number {
    const coefficientApproximation = percentageProximityFromAngle1ToFoundAngle(time1, time2, timeApproximation);

    const mediumAngle = getMediumAngle(angle1, angle2);

    const angularDistanceToMediumAngle = mediumAngle - angle1;
    const rotationVectorThatConvertsFirstAngleToSecond = angularDistanceToMediumAngle * 2;

    const angularVectorThatConvertsFirstAngleToFoundApproximationAngle =
        rotationVectorThatConvertsFirstAngleToSecond * coefficientApproximation;

    const approximationAngle = angle1 + angularVectorThatConvertsFirstAngleToFoundApproximationAngle;

    return approximationAngle;
}


function percentageProximityFromAngle1ToFoundAngle(
    time1: number,
    time2: number,
    timeProximity: number
): number {
    if (time1 <= time2) {
        return percentageProximity(time1, time2, timeProximity);
    } else {
        return 1 - percentageProximity(time2, time1, timeProximity);
    }
}

function getMediumAngle(a: number, b: number): number {
    const halfRound = Math.PI;
    const fullRound = halfRound * 2;

    a = a % fullRound;
    b = b % fullRound;

    let sum = a + b;

    if (fullRound < sum && sum < fullRound * 1.5) {
        sum = sum % halfRound;
    }
    return sum / 2;
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

    let storageElbows: StorageElbowsJsonDTO = new StorageElbowJsonDTOClass();
    storageElbows.setJsonString(imitationDataFromGetElbowFootprint2);

    const reset = () => {
        brickRef.current.style.left = String(0);
    };

    const nextAnimationFrameHandler = (nowTime: number) => {

        const brick = brickRef.current;
        const canvas = document.getElementById('myChart') as HTMLCanvasElement;

        if (!canvas) {
            return;
        }

        canvas.width = 400;
        canvas.height = 400;
        const context = canvas.getContext('2d');


        if (!context) {
            return;
        }


        context.clearRect(0, 0, canvas.width, canvas.height);

        let dataForRendering = storageElbows.getDataMultiFootprintsForRendering(nowTime / 1000);
        for (let i = 0; i < dataForRendering.length; i++) {

            let movedPolygon: PolygonCCoA = dataForRendering[i].polygonInPosition;


            if (movedPolygon) {
                context.beginPath();

                for (let index = 0; index < movedPolygon.getSize(); index++) {
                    let polygonCoordinate = movedPolygon.getPoint(index);

                    if (index == 0) {
                        context.moveTo(Number(polygonCoordinate.x), Number(polygonCoordinate.y));
                    } else {
                        context.lineTo(Number(polygonCoordinate.x), Number(polygonCoordinate.y));
                    }
                }

                context.closePath();
                context.fillStyle = 'green';
                context.fill();
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

            <canvas  id="myChart"  />;
        </>
    );
};

export default Canvas;