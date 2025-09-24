import {customApiCall} from '../service';
export async function getData() {
  return customApiCall('/api/getData', 'GET', {
    withCredentials: true,
  });
}
