// config.ts
export interface DynamicConfig {
    REACT_APP_BASE_URL: string;
    REACT_APP_BACKEND_CALCULATOR_URL: string;
    REACT_APP_BACKEND_DATABASE_URL: string;
    environment: "DEV" | "TST" | "AKC" | "PROD";
}

export const defaultConfig: DynamicConfig = {
    REACT_APP_BASE_URL: "https://localhost:3000",
    REACT_APP_BACKEND_CALCULATOR_URL: "https://localhost:8080",
    REACT_APP_BACKEND_DATABASE_URL: "https://localhost:8081",
    environment: "DEV"
};

class GlobalConfig {
    config: DynamicConfig = defaultConfig;
}

export const globalConfig = new GlobalConfig();

export const globalConfigUrl = "config.json";