import React, {Dispatch, SetStateAction, useState} from "react";
import './Home.css';
import SetOrders from "./parts/game/SetOrders";
import {ButtonSendOrders} from "./parts/game/SendOrdersButton";
import DrawingMotionPath from "./parts/game/DrawingMotionPath";
import {Bottom} from "../common/Bottom";

const Documentation: React.FC = () => {

    const [counterListABId, setCounterListABId] = useState<number>(0);

    return (
        <div className="container container-text">
            <p>Сайт сделан с использованием Java Spring, React (typescript), OAuth4, docker-compose</p>
            <p>Сайт имеет React фронтенд, а так же 2 бэкенда на Java Spring. 1-ый бэкэнд отвечает за работу с пользователями (пока что только регистрация и аутентификация через сторонние ресурсы). 2-ой бэкэнд отвечает за просчитывание траектории машинок (CCoA - Centralized Control of Autopilots). </p>
        </div>
    )
};

export default Documentation;
