import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';
import Canvas from "./Canvas";
import {ButtonCreateRoom} from "./CreateRoomButton";
import {ButtonSendOrders} from "./SendOrdersButton";
import CanvasSetOrders from "./SetOrdersCanvas";
import DragList from "./DragList";
import SetOrders from "./SetOrders";

function App() {

  const [counterListABId, setCounterListABId] = useState<number>(0);

  return (
    <div className="App">
      <Canvas stateForForceUpdateCanvasInCode={counterListABId} />
      {/*<ButtonCreateRoom />*/}
      <ButtonSendOrders updateStateCanvasRenderingResultCallback={setCounterListABId} />
      <SetOrders />
    </div>
  );
}

export default App;
