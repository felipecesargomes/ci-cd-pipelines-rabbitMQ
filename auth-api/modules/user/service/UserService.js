import userRepository from "../repository/UserRepository.js";
import * as httpStatus from "../../../config/constants/httpStatus.js";
import UserException from "../userException/UserException.js";

class UserService {
  async findByEmail(req) {
    try {
      const { email } = req.params;
      this.validateRequestData(email);
      let user = await userRepository.findByEmail(email);
      this.validateUserNotFound(user);
      if (!user) {
      }
      return {
        status: httpStatus.SUCCESS,
        message: {
          id: user.id,
          name: user.name,
          email: user.email,
        },
      };
    } catch (err) {
      return {
        status: err.status ? err.status : httpStatus.INTERNAL_SERVER_ERROR,
        message: err.status,
      };
    }
  }

  validateRequestData(email) {
    if (!email) {
      throw new UserException(
        httpStatus.BAD_REQUEST,
        "User email was not informed."
      );
    }
  }

  validateUserNotFound(user) {
    if(!user) {
      throw new UserException(httpStatus.BAD_REQUEST, "User not found.");
    }
  }
}

export default new UserService();
