import bcryptjs from "bcryptjs";
import User from "../../modules/user/model/User.js";

export async function createInitialData() {
    await User.sync({ force: true });

    let password = await bcryptjs.hash('123456', 10);

    await User.create({
        name: "User Test",
        email: 'teste@gmail.com',
        password: password
    })

}