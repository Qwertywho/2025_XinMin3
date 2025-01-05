import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

const http = require("http");

// Create an HTTP server
const server = http.createServer((req, res) => {
  res.writeHead(200, { "Content-Type": "text/plain" });
  res.end("Hello World\n");
});

// Function to handle graceful shutdown
function shutdown(signal) {
  return (err) => {
    console.log(`${signal} received.`);
    if (err) console.error(err.stack || err);
    // Allow some time for cleanup before exiting
    setTimeout(() => {
      console.log("Exiting...");
      process.exit(err ? 1 : 0);
    }, 5000).unref(); // Unref allows the event loop to continue running
  };
}

// Listen for termination signals
process.on("SIGTERM", shutdown("SIGTERM"));
process.on("SIGINT", shutdown("SIGINT"));

// Start the server
const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
  console.log(`Server listening on port ${PORT}`);
});

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
