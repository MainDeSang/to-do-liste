import React from "react";
import { Link } from "react-router-dom";

const Home = () => {
    return (
        <div style={{ textAlign: "center", marginTop: "4rem" }}>
            <h1>Willkommen bei der ToDo-App</h1>
            <p>Bitte wähle eine Option:</p>
            <div style={{ display: "flex", justifyContent: "center", gap: "1rem", marginTop: "2rem" }}>
                <Link to="/register">
                    <button>Registrieren</button>
                </Link>
                <Link to="/login">
                    <button>Einloggen</button>
                </Link>
            </div>
        </div>
    );
};

export default Home;
