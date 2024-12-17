import bcryptjs from "bcryptjs";
import User from "../../modules/user/model/User.js";

export async function createInitialData() {
    try {
        // Sincroniza a tabela (sem recriar, evita perda de dados)
        await User.sync();

        // Verifica se o usuário já existe
        const userExists = await User.findOne({ where: { email: "teste@gmail.com" } });

        if (!userExists) {
            const password = await bcryptjs.hash("123456", 10);

            // Cria o usuário inicial
            await User.create({
                name: "User Test",
                email: "teste@gmail.com",
                password: password,
            });

            console.log("Initial data created: User Test");
        } else {
            console.log("Initial data already exists.");
        }
    } catch (err) {
        console.error("Error creating initial data:", err);
    }
}
