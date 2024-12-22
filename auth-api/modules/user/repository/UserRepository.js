import User from "../model/User.js";

class UserRepository {
  async findAll() {
    return await User.findAll();
  }

  async findById(id) {
    try {
      return await User.findById(id);
    } catch(err) {
        console.log(err.message);
        return null;
    }
  }

  async findByEmail(email) {
    try {
      return await User.findOne({
        where: {
          email: email,
        },
      });
    } catch(err) {
        console.log(err.message);
        return null;
    }
  }
}

export default new UserRepository();
