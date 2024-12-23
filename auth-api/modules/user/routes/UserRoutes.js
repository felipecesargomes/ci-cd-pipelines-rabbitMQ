import { Router } from "express";
import UserController from "../controller/UserController.js";
import CheckToken from "../../../config/auth/CheckToken.js";

const router = Router();

// Rotas públicas (sem proteção)
router.get("/api/status", (req, res) => {
  res.status(200).json({ status: "API is running!" });
});
router.post("/api/user/auth", UserController.getAccessToken);

// Aplicar o middleware CheckToken a partir daqui
router.use(CheckToken);

// Rotas protegidas
router.get("/api/user/check-token", (req, res) => {
  res.status(200).json({ message: "Token is valid!" });
});
router.get("/api/user/email/:email", UserController.findByEmail);

export default router;