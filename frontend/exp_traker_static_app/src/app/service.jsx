export default function customeApiCall(url, method, body) {
  const domain = process.env.REACT_APP_API_DOMAIN || 'http://localhost:8081';
  url = `${domain}${url}`;
  const headers = {
    'Content-Type': 'application/json',
  };

  const options = {
    method: method,
    headers: headers,
  };
L
  if (body) {
    options.body = JSON.stringify(body);
  }

  return fetch(url, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      return response.json();
    })
    .catch((error) => {
      console.error('Error:', error);
      throw error;
    });
}