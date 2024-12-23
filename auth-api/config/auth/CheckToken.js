import jwt from "jsonwebtoken";
import { promisify } from "util";
import httpStatus from "http-status";


import AuthException from "./AcessTokenException.js";

import * as secrets from "../constants/Secrets.js";

const bearer = "Bearer ";

export default async (req, res, next) => {
  try {
    const { authorization } = req.headers;
    console.log("Authorization Header:", authorization);

    if (!authorization) {
      throw new AuthException(
        httpStatus.UNAUTHORIZED,
        "Access token not found."
      );
    }

    let accessToken = authorization;
    if (accessToken.startsWith(bearer)) {
      accessToken = authorization.split(" ")[1];
    }
    console.log("Access Token:", accessToken);

    const decoded = jwt.decode(accessToken);
    console.log("Decoded Token:", decoded);

    const verified = await jwt.verify(accessToken, secrets.API_SECRET);
    console.log("Verified Token:", verified);

    req.authUser = verified.authUser;
    return next();
  } catch (err) {
    console.error("Error Verifying Token:", err.message);
    const status = err.status ? err.status : httpStatus.INTERNAL_SERVER_ERROR;
    return res.status(status).json({
      status,
      message: err.message,
    });
  }
};
