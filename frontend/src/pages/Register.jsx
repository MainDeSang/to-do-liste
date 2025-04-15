import React, { useState } from "react";

const Register = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");

    const handleSubmit = async (event) => {
        event.preventDefault();

        const userData = {
            username,
            password,
            email,
        };

        try {
            const response = await fetch("http://localhost:8080/api/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(userData),
            });

            if (response.ok) {
                alert("Registrierung erfolgreich!");
            } else {
                alert("Fehler bei der Registrierung.");
            }
        } catch (error) {
            console.error("Fehler bei der Verbindung zum Server:", error);
            alert("Fehler bei der Verbindung zum Server.");
        }
    };

    return (
        <div>
            <h2>Registrieren</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Benutzername:</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Passwort:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Registrieren</button>
            </form>
        </div>
    );
};

export default Register;
