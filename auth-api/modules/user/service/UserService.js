import userRepository from "../repository/UserRepository.js";
import * as httpStatus from "../../config/constants/httpStatus.js";

class UserService {
  async findByEmail(req) {
    try {
      const { email } = req.params;
      this.validateRequestData(email);
      let user = userRepository.findByEmail(email);
      if(!user) {

      }
      return {
        status: httpStatus.SUCCESS,
        message: {
            id: user.id,
            name: user.name,
            email: user.email
        }
      }
    } catch (err) {
      return {
        status: err.status ? err.status : httpStatus.INTERNAL_SERVER_ERROR,
        message: err.status,
      };
    }
  }

  validateRequestData(email) {
    if (!email) {
      throw new Error("User email was not informed.");
    }
  }
}

export default new UserService();