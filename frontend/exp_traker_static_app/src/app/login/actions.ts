import { log } from "console";
import {customAuthApiCall} from "../service";

export const ValidateLogin = async (body: any) => {
  return customAuthApiCall('/api/auth/login', 'POST', {
    body,
    withCredentials: true, // important for cookies/session
  });
};

export const RegisterUser = async (body: any) => {
  return customAuthApiCall('/api/auth/register', 'POST', {
    body,
    withCredentials: true,
  });
};
export const LogoutUser = async () => {
  localStorage.removeItem("isLoggedIn");
  customAuthApiCall('/api/auth/logout', 'POST', {
    body:{logout:true},
    withCredentials: true,
  }).then(() => {
    window.location.href = "/login";
  }); 
};
