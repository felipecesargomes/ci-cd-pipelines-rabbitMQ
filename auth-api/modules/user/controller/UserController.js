import UserService from "../service/UserService.js";

class UserController {
    async getAccessToken(req, res) {
        console.log('Request Body:', req.body);  // Log para depuração
      
        try {
          const { email, password } = req.body;
      
          // Verifique se o corpo contém email e password
          if (!email || !password) {
            return res.status(400).json({ message: "Email or password not informed." });
          }
      
          let accessToken = await UserService.getAccessToken(email, password);
          
          // Caso o serviço retorne um token com sucesso, envie a resposta
          if (accessToken && accessToken.accessToken) {
            return res.status(200).json(accessToken);
          } else {
            return res.status(500).json({ message: "Failed to generate access token." });
          }
      
        } catch (err) {
          console.error('Error in getAccessToken:', err);  // Log de erro
          return res.status(500).json({
            message: err.message || "Internal server error",
          });
        }
      }
      
    async findByEmail(req, res) {
        let user = await UserService.findByEmail(req);
        return res.status(user.status).json(user);
    } 

}

export default new UserController();