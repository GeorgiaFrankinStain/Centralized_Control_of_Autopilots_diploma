import React from 'react';
import logo from './logo.svg';
import './App.css';
import Canvas from "./Canvas";
import {ButtonCreateRoom} from "./CreateRoomButton";

function App() {
  return (
    <div className="App">
      <Canvas />
        <ButtonCreateRoom />
    </div>
  );
}

export default App;
