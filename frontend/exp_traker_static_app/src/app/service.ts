type HttpMethod = 'GET' | 'POST' | 'PUT' | 'DELETE';

interface ApiOptions {
  body?: Record<string, any>;
  authToken?: string | null;    // for bearer token flows
  withCredentials?: boolean;    // for cookie/session flows
}

export function customAuthApiCall(
  url: string,
  method: HttpMethod,
  optionsInput: ApiOptions = {}
): Promise<any> {
  const { body, authToken, withCredentials } = optionsInput;
console.log("customAuthApiCall called with:", {url, method, body, authToken, withCredentials});
  const domain = process.env.REACT_APP_API_DOMAIN || 'http://localhost:8082';
  const fullUrl = `${domain}${url}`;

  const headers: HeadersInit = {
    'Content-Type': 'application/json',
  };

  const fetchOptions: RequestInit = {
    method,
    headers,
    credentials: withCredentials ? 'include' : 'omit',
  };

  if (body) {
    fetchOptions.body = JSON.stringify(body);
  }

  return fetch(fullUrl, fetchOptions).then(async (response) => {
    const contentType = response.headers.get('content-type') || '';
    const payload = contentType.includes('application/json')
      ? await response.json().catch(() => null)
      : await response.text().catch(() => '');

    if (!response.ok) {
      const err = new Error(
        `HTTP ${response.status} ${response.statusText} - ${typeof payload === 'string' ? payload : JSON.stringify(payload)}`
      );
      throw err;
      console.log(response);
    }
    return payload;
  });
}

export function customApiCall(
  url: string,
  method: HttpMethod,
  optionsInput: ApiOptions = {}
): Promise<any> {
  const { body } = optionsInput;
  const domain = process.env.REACT_APP_API_DOMAIN || 'http://localhost:8080';
  const fullUrl = `${domain}${url}`;

  const headers: HeadersInit = {
    'Content-Type': 'application/json',
  };

  const fetchOptions: RequestInit = {
    method,
    headers,
    credentials: 'include',
  };

  if (body) {
    fetchOptions.body = JSON.stringify(body);
  }

  return fetch(fullUrl, fetchOptions).then(async (response) => {
    const contentType = response.headers.get('content-type') || '';
    const payload = contentType.includes('application/json')
      ? await response.json().catch(() => null)
      : await response.text().catch(() => '');

    if (!response.ok) {
      const err = new Error(
        `HTTP ${response.status} ${response.statusText} - ${typeof payload === 'string' ? payload : JSON.stringify(payload)}`
      );
      throw err;
    }
    return payload;
  });
}
