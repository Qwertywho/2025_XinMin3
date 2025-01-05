import React from "react";

const CoinChangeResult = ({ result }) => {
  if (!result || result.length === 0) {
    return null;
  }

  return (
    <div>
      <h2>Coin Change Result:</h2>
      <p>{result.join(", ")}</p>
    </div>
  );
};

export default CoinChangeResult;
