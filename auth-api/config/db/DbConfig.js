import Sequelize from "sequelize";

const sequelize = new Sequelize("auth-db", "postgres", "root", {
  host: "localhost",
  dialect: "postgres",
  quoteIdentifiers: false,
  pool: {
    max: 5, // Número máximo de conexões
    min: 0, // Número mínimo de conexões
    acquire: 30000, // Tempo máximo de espera por uma conexão (em ms)
    idle: 10000, // Tempo máximo que uma conexão pode ficar ociosa (em ms)
  },
  define: {
    syncOnAssociation: true,
    timestamps: false,
    underscored: true,
    underscoredAll: true,
    freezeTableName: true,
  },
  logging: console.log, // Exibe logs detalhados de queries no console
});

sequelize
  .authenticate()
  .then(() => {
    console.log("Connection has been established successfully.");
  })
  .catch((err) => {
    console.error("Unable to connect to the database:", err);
  });

export default sequelize;
