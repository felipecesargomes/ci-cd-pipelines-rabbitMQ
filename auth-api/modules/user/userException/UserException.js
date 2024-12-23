class UserException extends Error {
    constructor(status, message) {
        super(message); // Chama o construtor da classe pai (Error)
        this.status = status;
        this.message = message;
        this.name = this.constructor.name;
        Error.captureStackTrace(this, this.constructor);
    }
}

export default UserException;