import { createInitialData } from "./config/db/initialData.js";

(async () => {
    await createInitialData();
})();