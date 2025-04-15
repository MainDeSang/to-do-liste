import React from "react";
import katzenbild from "../assets/katze.jpeg"; // Pfad anpassen, falls anders

const Katzenbild = () => {
    return (
        <div style={{ textAlign: "center" }}>
            <h2>🐾 Willkommen zurück!</h2>
            <span><img
                src={katzenbild}
                alt="Süße Katze"
                style={{ width: "400px", borderRadius: "12px", marginTop: "20px" }}
            /></span>
        </div>
    );
};

export default Katzenbild;