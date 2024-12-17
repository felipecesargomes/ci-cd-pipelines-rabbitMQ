import express from "express";
import * as db from "./config/db/initialData";

const app = express();

const env = process.env;
const PORT = env.PORT || 8080;

db.createInitialData();

app.get("/api/status", (req, res) => {
  return res.status(200).json({
    service: "Auth-Api",
    status: "up",
    httpStatus: 200,
  });
});

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
