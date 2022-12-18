
export interface PointCCoA {
    x: number;
    y: number;
}


export interface PositionCCoA {
    point: PointCCoA,
    angle: number
}

export interface PolygonCCoA {
    addPoint(newPoint: PointCCoA): void;
    getPoint(index: number):PointCCoA;
    getSize():number;
    getDeposeOn(vector: PointCCoA):PolygonCCoA;
    rotateRelative00(angle: number):PolygonCCoA;
}

export class PolygonCCoAClass implements PolygonCCoA {

    private points: Array<PointCCoA> = new Array<PointCCoA>();

    addPoint(newPoint: PointCCoA): void {
        this.points.push(newPoint);
    }

    getPoint(index: number):PointCCoA {
        return this.points[index];
        // return {x:1, y: 1};
    }
    getSize():number {
        return this.points.length;
    }

    getDeposeOn(vector: PointCCoA): PolygonCCoA {

        let newPolygon = new PolygonCCoAClass();

        this.points.forEach((point: PointCCoA) => {
            let deposedPoint: PointCCoA = {
                x: point.x + vector.x,
                y: point.y + vector.y
            };
            newPolygon.addPoint(deposedPoint);
        });

        return newPolygon;
    }

    rotateRelative00(angle: number): PolygonCCoA {

        // if (angle == 3.141592653589793 * 1.5) {
        //     console.log("is cool");
        // }


        const origin: PointCCoA = {x: 0, y: 0};

        const degreeM180 = -3.14159;
        const degree180 = 3.14159;

        let newPolygon = new PolygonCCoAClass();


        this.points.forEach((point: PointCCoA) => {

            const cosAngle = Math.cos(angle);
            const sinAngle = Math.sin(angle);


            const xDistanceOnProjection = point.x - origin.x;
            const yDistanceOnProjection = point.y - origin.y;

            let xRotate = cosAngle * xDistanceOnProjection - sinAngle * yDistanceOnProjection + origin.x;
            let yRotate = sinAngle * xDistanceOnProjection + cosAngle * yDistanceOnProjection + origin.y;


            let deposedPoint: PointCCoA = {
                x: xRotate,
                y: yRotate
            };

            newPolygon.addPoint(deposedPoint);
            // newPolygon.addPoint(origin);
        });

        return newPolygon;
    }



}