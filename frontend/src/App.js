import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import Register from "./components/Register";

function App() {
    return (
        <div className="App">
            <Routes>
                <Route path="/" element={<Navigate to="/register" replace />} />
                <Route path="/register" element={<Register />} />
            </Routes>
        </div>
    );
}

export default App;
