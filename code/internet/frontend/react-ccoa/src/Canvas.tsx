import {useEffect, useRef} from "react";

const Canvas = () => {
    const canvasRef = useRef<HTMLCanvasElement | null>(null);

    useEffect(() => {
        const canvas = canvasRef.current;

        if (!canvas) {
            return;
        }

        canvas.width = 400;
        canvas.height = 400;

        const context = canvas.getContext('2d');

        if (!context) {
            return;
        }
        let x = 50;
        context.fillStyle = 'blue';
        context.fillRect(0, 0, 100, 100);
        setInterval(function() {


            context.fillStyle = 'white';
            context.fillRect(0,0, canvas.width, canvas.height);


            context.beginPath();
            context.beginPath();
            context.moveTo(0, 0);
            context.lineTo(100, 50);
            context.lineTo(50, 100);
            context.lineTo(0, 90);
            context.closePath();
            context.fill();

            context.fillStyle = 'red';
            context.fillRect(x = x + 15, 50, 300, 200);

            context.fill();

        }, 105)
    }, []);

    return <canvas ref={canvasRef} />;
};

export default Canvas;