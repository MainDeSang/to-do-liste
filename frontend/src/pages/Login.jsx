import React, { useState } from "react";
import Katzenbild from "../components/Katzenbild";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loggedIn, setLoggedIn] = useState(false);

    const handleSubmit = async (event) => {
        event.preventDefault();

        const loginData = {
            username,
            password,
        };

        try {
            const response = await fetch("http://localhost:8080/api/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(loginData),
            });

            if (response.ok) {
                setLoggedIn(true);
            } else {
                alert("Login fehlgeschlagen!");
            }
        } catch (error) {
            console.error("Fehler beim Login:", error);
        }
    };

    if (loggedIn) {
        return <Katzenbild />;
    }


    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Benutzername:
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </label>
                <br />
                <label>
                    Passwort:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </label>
                <br />
                <button type="submit">Einloggen</button>
            </form>
        </div>
    );
}

export default Login;
