import { useEffect, useState } from "react";
import "./App.css";

function App() {
  const [data, setData] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    console.log("Fetching data from API...");
    fetch("http://localhost:8083/login") // Change to your correct API URL
      .then((res) => {
        if (!res.ok) {
          throw new Error(`HTTP error! Status: ${res.status}`);
        }
        return res.json();
      })
      .then((data) => setData(data))
      .catch((err) => setError(err.message));
  }, []);

  return (
    <div className="App">
      <h1>API Data</h1>

      {error && <p style={{ color: "red" }}>Error: {error}</p>}

      <ul>
        {Array.isArray(data) ? (
          data.map((item, index) => <li key={index}>{JSON.stringify(item)}</li>)
        ) : (
          <li>{JSON.stringify(data)}</li>
        )}
      </ul>
    </div>
  );
}

export default App;
