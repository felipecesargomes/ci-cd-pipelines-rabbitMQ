import bcryptjs from "bcryptjs";
import User from "../../modules/user/model/User.js";

export async function createInitialData() {
    try {
        // Sincroniza a tabela (sem recriar, evita perda de dados)
        await User.sync();

        // Verifica se o usuário "teste@gmail.com" já existe
        const userTestExists = await User.findOne({ where: { email: "teste@gmail.com" } });
        if (!userTestExists) {
            const password = await bcryptjs.hash("123456", 10);
            await User.create({
                name: "User Test",
                email: "teste@gmail.com",
                password: password,
            });
            console.log("User 'teste@gmail.com' created.");
        } else {
            console.log("User 'teste@gmail.com' already exists.");
        }

        // Verifica se o usuário "felipe@gmail.com" já existe
        const userFelipeExists = await User.findOne({ where: { email: "felipe@gmail.com" } });
        if (!userFelipeExists) {
            const password = await bcryptjs.hash("123456", 10);
            await User.create({
                name: "felipe",
                email: "felipe@gmail.com",
                password: password,
            });
            console.log("User 'felipe@gmail.com' created.");
        } else {
            console.log("User 'felipe@gmail.com' already exists.");
        }

    } catch (err) {
        console.error("Error creating initial data:", err);
    }
}
