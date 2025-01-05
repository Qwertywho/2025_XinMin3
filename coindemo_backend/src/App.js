import React, { useState } from "react";
import CoinChangeForm from "./CoinChangeForm";
import CoinChangeResult from "./CoinChangeResult";
import axios from "axios";

function App() {
  const [result, setResult] = useState([]);

  const handleCalculateChange = async (targetAmount, coinDenominations) => {
    try {
      const response = await axios.post(
        "http://backend:8080/api/coins/minCoins",
        {
          targetAmount: parseFloat(targetAmount),
          coinDenominations: coinDenominations,
        }
      );
      setResult(response.data); //update result with response
    } catch (error) {
      console.error("Error fetching data:", error);
      setResult([]); //reset result on error
    }
  };

  return (
    <div className="App">
      <h1>Coin Change Calculator</h1>
      <CoinChangeForm onSubmit={handleCalculateChange} />
      <CoinChangeResult result={result} />
    </div>
  );
}
export default App;
