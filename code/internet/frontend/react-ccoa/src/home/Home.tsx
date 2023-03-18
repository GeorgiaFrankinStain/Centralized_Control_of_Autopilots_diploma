import React, {Dispatch, SetStateAction, useState} from "react";
import './Home.css';
import {ToastContainer} from "react-toastify";
import SetOrders from "../SetOrders";
import {ButtonSendOrders} from "../SendOrdersButton";
import Canvas from "../Canvas";

/*type HomeProps = {
    counterListABId: number;
    setCounterListABId: Dispatch<SetStateAction<number>>;
};*/

const Home: React.FC = () => {

    const [counterListABId, setCounterListABId] = useState<number>(0);

    return (
        <div className="home-container">
            <div className="container">
                <div className="graf-bg-container">
                    <div className="graf-layout">
                        <div className="graf-circle"></div>
                        <div className="graf-circle"></div>
                        <div className="graf-circle"></div>
                        <div className="graf-circle"></div>
                        <div className="graf-circle"></div>
                        <div className="graf-circle"></div>
                        <div className="graf-circle"></div>
                        <div className="graf-circle"></div>
                        <div className="graf-circle"></div>
                        <div className="graf-circle"></div>
                        <div className="graf-circle"></div>
                    </div>
                </div>
                <h1 className="home-title">Spring Boot React OAuth2 Social Login Demo</h1>
            </div>
            <Canvas stateForForceUpdateCanvasInCode={counterListABId} />
            {/*<ButtonCreateRoom />*/}
            <ButtonSendOrders updateStateCanvasRenderingResultCallback={setCounterListABId} />
            <SetOrders />

        </div>
    )
};

export default Home;