import express from "express";
import userRoutes from "./modules/user/routes/UserRoutes.js";
import CheckToken from "./config/auth/CheckToken.js";
import { createInitialData } from "./config/db/InitialData.js";

const app = express();

const env = process.env;
const PORT = env.PORT || 8080;

app.use(express.json());

// Criação dos dados iniciais
(async () => {
    await createInitialData();
})();

// Rota pública para verificar o status da API
app.get("/api/status", (req, res) => {
    return res.status(200).json({
        service: "Auth-Api",
        status: "up",
        httpStatus: 200,
    });
});

// Rotas protegidas com o middleware CheckToken
//app.use(CheckToken);
app.use(userRoutes);

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});