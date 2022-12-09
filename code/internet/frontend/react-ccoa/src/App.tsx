import React from 'react';
import logo from './logo.svg';
import './App.css';
import Canvas from "./Canvas";
import {ButtonCreateRoom} from "./CreateRoomButton";
import {ButtonSendOrders} from "./SendOrdersButton";

function App() {
  return (
    <div className="App">
      <Canvas />
        <ButtonCreateRoom />
        <ButtonSendOrders />
    </div>
  );
}

export default App;
