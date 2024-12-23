class AuthException extends Error {
    constructor(status, message) {
        super(message); // Chama o construtor da classe pai (Error)
        this.status = status; // Agora é seguro acessar `this`
        this.name = this.constructor.name; // Define o nome da exceção
        Error.captureStackTrace(this, this.constructor); // Captura o stack trace
    }
}

export default AuthException;
