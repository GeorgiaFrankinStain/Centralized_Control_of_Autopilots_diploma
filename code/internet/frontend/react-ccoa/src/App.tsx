import React from 'react';
import logo from './logo.svg';
import './App.css';
import Canvas from "./Canvas";
import {ButtonCreateRoom} from "./CreateRoomButton";
import {ButtonSendOrders} from "./SendOrdersButton";
import CanvasSetOrders from "./SetOrdersCanvas";
import DragList from "./DragList";
import SetOrders from "./SetOrders";

function App() {
  return (
    <div className="App">
      <Canvas />
      <ButtonCreateRoom />
      <ButtonSendOrders />
      <SetOrders />
    </div>
  );
}

export default App;
