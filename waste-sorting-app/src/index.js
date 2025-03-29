import React from "react";
import ReactDOM from "react-dom/client";  // Correct import for React 18
import WasteSortingApp from "./WasteSortingApp";

// Create a root using createRoot for React 18 and mount the app
const root = ReactDOM.createRoot(document.getElementById("root"));
if (process.env.NODE_ENV === "development") {
  root.render(
    <React.StrictMode>
      <WasteSortingApp />
    </React.StrictMode>
  );
} else {
  root.render(<WasteSortingApp />);
}
