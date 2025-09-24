import React, { useState } from "react";
import { ValidateLogin } from "./actions";
import PasswordInput from "./EyeIcon";

type LoginMode = "userName" | "email";

type LoginPayload =
  | { userName: string; password: string }
  | { email: string; password: string };

type ApiSuccess = { ok: true; message?: string };
type ApiFailure = { ok: false; message?: string; field?: "userName" | "email" | "password" };
type ApiResponse = ApiSuccess | ApiFailure;

export default function LoginForm() {
  const [loginMode, setLoginMode] = useState<LoginMode>("userName");

  // Consolidated errors; keys match field names or _global
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [loginMessage, setLoginMessage] = useState("");

  const clearFieldError = (field: string) =>
    setErrors((prev) => {
      if (!prev[field]) return prev;
      const next = { ...prev };
      delete next[field];
      return next;
    });

  const handleLogin = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setLoginMessage("");
    setErrors({});

    const form = e.currentTarget;
    const formData = new FormData(form);

    const userName = String(formData.get("userName") || "").trim();
    const email = String(formData.get("email") || "").trim();
    const password = String(formData.get("password") || "");

    // Client-side validation
    const nextErrors: Record<string, string> = {};
    const loginValue = loginMode === "userName" ? userName : email;

    if (!loginValue) nextErrors[loginMode] = `Please enter ${loginMode}.`;
    if (!password) nextErrors.password = "Password cannot be empty.";

    if (Object.keys(nextErrors).length) {
      setErrors(nextErrors);
      return;
    }

    // Prepare payload based on mode
    const payload: LoginPayload =
      loginMode === "userName"
        ? { userName, password }
        : { email, password };

    try {
      const response = (await ValidateLogin(payload)) as ApiResponse;

      if (response && "ok" in response && response.ok) {
        setLoginMessage(response.message || "Login successful");
        localStorage.setItem("isLoggedIn", "true");
        window.location.href = "/dashboard";
        return;
      }

      // Failure handling
      const message =
        (response && "message" in response && response.message) || "Login failed";
      setLoginMessage("Login failed");

      // Heuristic mapping to field-specific errors if server hints at it
      const lower = message.toLowerCase();
      const mapped: Record<string, string> = {};
      if (lower.includes("password")) {
        mapped.password = "Incorrect password.";
      } else if (response && "field" in response && response.field) {
        mapped[response.field] = message;
      } else if (loginMode === "userName") {
        mapped.userName = message || "User not found.";
      } else {
        mapped.email = message || "User not found.";
      }
      setErrors(mapped);
    } catch (err) {
      setLoginMessage("Login failed. Please try again later.");
      setErrors({ _global: "Unexpected error. Please try again." });
    }
  };

  return (
    <>
      <h2 className="text-2xl font-bold text-center text-stone-950">Login</h2>

      {/* Toggle Button */}
      <div className="flex justify-center mb-4" role="tablist" aria-label="Login mode">
        <button
          type="button"
          role="tab"
          aria-selected={loginMode === "userName"}
          className={`px-4 py-2 rounded-l-md border ${
            loginMode === "userName" ? "bg-blue-600 text-white" : "bg-gray-200"
          }`}
          onClick={() => {
            setLoginMode("userName");
            // clear opposite field errors
            setErrors((e) => {
              const n = { ...e };
              delete n.email;
              return n;
            });
          }}
        >
          Username
        </button>
        <button
          type="button"
          role="tab"
          aria-selected={loginMode === "email"}
          className={`px-4 py-2 rounded-r-md border ${
            loginMode === "email" ? "bg-blue-600 text-white" : "bg-gray-200"
          }`}
          onClick={() => {
            setLoginMode("email");
            setErrors((e) => {
              const n = { ...e };
              delete n.userName;
              return n;
            });
          }}
        >
          Email
        </button>
      </div>

      <form className="space-y-4" onSubmit={handleLogin} noValidate>
        {/* Conditional Input */}
        {loginMode === "userName" ? (
          <div>
            <label htmlFor="userName" className="block text-sm font-medium text-gray-700">
              Username
            </label>
            <input
              type="text"
              id="userName"
              name="userName"
              required
              onFocus={() => clearFieldError("userName")}
              className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm 
                    focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm text-stone-950"
              aria-invalid={!!errors.userName}
              aria-describedby={errors.userName ? "userName-error" : undefined}
            />
            {errors.userName && (
              <p id="userName-error" className="text-sm text-red-600">
                {errors.userName}
              </p>
            )}
          </div>
        ) : (
          <div>
            <label htmlFor="email" className="block text-sm font-medium text-gray-700">
              Email
            </label>
            <input
              type="email"
              id="email"
              name="email"
              required
              onFocus={() => clearFieldError("email")}
              className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm 
                    focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm text-stone-950"
              aria-invalid={!!errors.email}
              aria-describedby={errors.email ? "email-error" : undefined}
            />
            {errors.email && (
              <p id="email-error" className="text-sm text-red-600">
                {errors.email}
              </p>
            )}
          </div>
        )}

        {/* Password Input */}
        <div className="relative">
          <label htmlFor="password" className="block text-sm font-medium text-gray-700">
            Password
          </label>
          {/* Assuming PasswordInput renders <input id="password" name="password" ... /> and accepts error props */}
          <PasswordInput
            passwordError={!!errors.password}
            setPasswordError={(flag: boolean) => {
              if (!flag) clearFieldError("password");
              else setErrors((e) => ({ ...e, password: e.password || "Invalid password" }));
            }}
            setPasswordErrorMessage={(msg: string) =>
              setErrors((e) => ({ ...e, password: msg }))
            }
          />
          {errors.password && (
            <p id="password-error" className="mt-1 text-sm text-red-600">
              {errors.password || "Incorrect password."}
            </p>
          )}
        </div>

        {errors._global && (
          <p className="text-sm text-red-600" role="alert">
            {errors._global}
          </p>
        )}

        <button
          type="submit"
          className="w-full py-2 px-4 bg-blue-600 text-white font-semibold rounded-md 
                hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
        >
          Login
        </button>

        {loginMessage && (
          <p className="text-sm mt-2 text-gray-700" role="status">
            {loginMessage}
          </p>
        )}
      </form>
    </>
  );
}
