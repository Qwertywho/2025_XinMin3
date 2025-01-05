import React, { useState } from "react";

const CoinChangeForm = ({ onSubmit }) => {
  const [targetAmount, setTargetAmount] = useState("");
  const [coinDenominations, setCoinDenominations] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    const coins = coinDenominations.split(",").map(Number);
    onSubmit(targetAmount, coins);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>
          Target Amount:
          <input
            type="number"
            value={targetAmount}
            onChange={(e) => setTargetAmount(e.target.value)}
          />
        </label>
      </div>
      <div>
        <label>
          Coin Denominations (comma-separated):
          <input
            type="text"
            value={coinDenominations}
            onChange={(e) => setCoinDenominations(e.target.value)}
          />
        </label>
      </div>
      <button type="submit">Calculate Change</button>
    </form>
  );
};

export default CoinChangeForm;
