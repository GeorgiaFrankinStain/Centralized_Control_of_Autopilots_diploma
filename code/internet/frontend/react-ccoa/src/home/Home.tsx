import React, {Dispatch, SetStateAction, useState} from "react";
import './Home.css';
import {ToastContainer} from "react-toastify";
import SetOrders from "../SetOrders";
import {ButtonSendOrders} from "../SendOrdersButton";
import Canvas from "../Canvas";
import {API_BASE_URL_BACKEND_DATABASE} from "../index";

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
		<span>Данный сайт демонстрирует технические навыки Тимофеева Д.А.</span>
		<p>Сайт сделан с использованием Java Spring, React (typescript), OAuth2, docker-compose</p>
		<p>Над дизайном пока что не сильно заморачивался, времени нету.</p>
		<p>Сайт имеет React фронтенд, а так же 2 бэкенда на Java Spring. 1-ый бэкэнд отвечает за работу с пользователями (пока что только регистрация и аутентификация через сторонние ресурсы). 2-ой бэкэнд отвечает за просчитывание траектории машинок (CCoA - Centralized Control of Autopilots). Пока что имеется баг при передаче данных, из-за которых траектории машинок не могут пересекаться. При запуске на LibGDX на личном ПК машины умееют друг друга объезжать.</p>
		<p>С програмным кодом можно ознакомиться на моем github <a href="https://github.com/GeorgiaFrankinStain/Centralized_Control_of_Autopilots_diploma">github.com/GeorgiaFrankinStain/Centralized_Control_of_Autopilots_diploma</a> в директории /code/internet</p>
		<p>Как пользоваться сайтом: 1) в нижнем квадратике нужно кликнуть точку А (точка отправки) для машины, а потом клинуть B (точка прибытия для машинки), после чего автоматически будет переключен спрайт для 2-ой машинки. 2) Таким образом можно задать AB-курс для еще нескольких машинок. Но это не обязательно, можно указать курс только для 1-ой машинки. 3) Нажать кнопку "Send orders to backend calculated" и лицезреть процесс движения машинок по выданным маршнутам.</p>
    <p>;)</p>
                <p>{API_BASE_URL_BACKEND_DATABASE}</p>
            </div>
            <Canvas stateForForceUpdateCanvasInCode={counterListABId} />
            {/*<ButtonCreateRoom />*/}
            <ButtonSendOrders updateStateCanvasRenderingResultCallback={setCounterListABId} />
            <SetOrders />

        </div>
    )
};

export default Home;
