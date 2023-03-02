import { PUBLIC_API_ROOT } from "$env/static/public";
import axios, { type AxiosRequestConfig } from "axios";

function buildApiPath(path: string): string {
    return `${PUBLIC_API_ROOT}/api/${path}`;
}

class ApiClient {
    private token: string | null = null;

    setToken(token: string) {
        this.token = token;
    }

    send(method: string, url: string, requestConfig: AxiosRequestConfig | null = null) {
        if (requestConfig === null) {
            requestConfig = {};
        }
        
        return axios({
            ...requestConfig,
            method,
            url: buildApiPath(url),
            headers: this.token ? {
                ...requestConfig.headers, 
                Authorization: `Basic ${this.token}`,
            } : requestConfig.headers,
        });
    }
    
    get(url: string, requestConfig: AxiosRequestConfig | null = null) {
        return this.send('get', url, requestConfig);
    }
    
    post(url: string, requestConfig: AxiosRequestConfig | null = null) {
        return this.send('post', url, requestConfig);
    }

    withToken(token: string) {
        return withToken(token);
    }
}

const defaultApiClient = new ApiClient();

function setToken(token: string) {
    defaultApiClient.setToken(token);
    return defaultApiClient;
}

function withToken(token: string) {
    const temporaryApiClient = new ApiClient();
    temporaryApiClient.setToken(token);
    return temporaryApiClient;
}

export {
    defaultApiClient as api,
    setToken,
    withToken,
};
