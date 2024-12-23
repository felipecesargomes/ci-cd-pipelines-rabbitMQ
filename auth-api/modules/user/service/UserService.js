import userRepository from "../repository/UserRepository.js";
import * as httpStatus from "../../../config/constants/HttpStatus.js";
import UserException from "../userException/UserException.js";
import UserRepository from "../repository/UserRepository.js";
import bcryptjs from 'bcryptjs';
import jwt from "jsonwebtoken";
import * as secrets from "../../../config/constants/Secrets.js";

class UserService {
  async findByEmail(req) {
    try {
      const { email } = req.params;
      const { authUser } = req;
      this.validateRequestData(email);
      let user = await userRepository.findByEmail(email);
      this.validateUserNotFound(user);
      this.validateAuthenticatedUser(user, authUser)
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
        message: err.message,
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
    if (!user) {
      throw new UserException(httpStatus.BAD_REQUEST, "User not found.");
    }
  }

  validateAuthenticatedUser(user, authUser) {
    if(!authUser || (user.id !== authUser.id)) {
      throw new UserException(httpStatus.FORBIDDEN, "User not authorized.");
    }
  }

  async getAccessToken(email, password) {
    try {
      const user = await UserRepository.findByEmail(email);
  
      if (!user) {
        throw new Error("User not found.");
      }
  
      await this.validatePassword(password, user.password);  // Verifique a senha
  
      const authUser = { id: user.id, name: user.name, email: user.email };
      const accessToken = jwt.sign({ authUser }, secrets.API_SECRET, { expiresIn: "1d" });
  
      return {
        status: httpStatus.SUCCESS,
        accessToken: accessToken,
      };
    } catch (err) {
      console.error('Error in getAccessToken:', err);  // Log de erro
      throw err;  // Lança o erro para ser capturado no controlador
    }
  }
  
  
  validateAccessTokenData(email, password) {
    if (!email || !password) {
      throw new UserException(
        httpStatus.UNAUTHORIZED,
        "Email or password not informed."
      );
    }
  }

  async validatePassword(password, hasPassword) {
    if (!(await bcryptjs.compare(password, hasPassword))) {
      throw new UserException(httpStatus.UNAUTHORIZED, "Invalid password.");
    }
  }
}

export default new UserService();
