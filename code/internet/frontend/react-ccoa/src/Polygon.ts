
export interface PointCCoA {
    x: number;
    y: number;
    getDistanceTo(point: PointCCoA): number;
    getAngleRotateRelative(origin: PointCCoA): number;
}
export class PointCCoAClass implements PointCCoA {
    x: number;
    y: number;


    constructor(x: number, y: number) {
        this.x = x;
        this.y = y;
    }

    getDistanceTo(point: PointCCoA): number {
        const vector = this.getVector(point);
        return vector.getLengthVector();
    }

    getAngleRotateRelative(origin: PointCCoA): number {
        const vector = this.getVector(origin);
        return Math.atan2(-vector.y, -vector.x); //I don't understand why it works that way
    }

    private getVector(point: PointCCoA) {
        return new PointCCoAClass(this.x - point.x, this.y - point.y);
    }

    private getLengthVector() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }
}


export interface PositionCCoA {
    point: PointCCoA,
    angle: number
}

export interface PolygonCCoA {
    addPoint(newPoint: PointCCoA): void;
    addPoints(pointsPolygonForm: Array<PointCCoA>): void;
    getPoint(index: number):PointCCoA;
    getSize():number;
    getDeposeOn(vector: PointCCoA):PolygonCCoA;
    rotateRelative00(angle: number):PolygonCCoA;
}

export class PolygonCCoAClass implements PolygonCCoA {

    private points: Array<PointCCoA> = new Array<PointCCoA>();


    addPoints(pointsPolygonForm: Array<PointCCoA>): void {
        for (let j = 0; j < pointsPolygonForm.length; j++) {
            this.addPoint(pointsPolygonForm[j]);
        }
    }

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
            let deposedPoint: PointCCoA = new PointCCoAClass(
                point.x + vector.x,
                point.y + vector.y
            );
            newPolygon.addPoint(deposedPoint);
        });

        return newPolygon;
    }

    rotateRelative00(angle: number): PolygonCCoA {

        // if (angle == 3.141592653589793 * 1.5) {
        //     console.log("is cool");
        // }


        const origin: PointCCoA = new PointCCoAClass(0, 0);

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


            let deposedPoint: PointCCoA = new PointCCoAClass(
                xRotate,
                yRotate
            );

            newPolygon.addPoint(deposedPoint);
            // newPolygon.addPoint(origin);
        });

        return newPolygon;
    }



}