import { useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

export default function Login() {
  const [email, setEmail] = useState("");   // NEW
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState<string>("");
  const navigate = useNavigate();

  const submit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setMessage("");
    try {
      const res = await axios.post("http://localhost:8080/api/auth/login", {
        email,      // NEW
        username,
        password,
      });

      setMessage(res.data);

      // Navigate after login
      setTimeout(() => navigate("/dashboard"), 1000);
    } catch (err: any) {
      setMessage(err?.response?.data || "Login failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-[calc(100dvh-60px)] px-4">
      <form
        onSubmit={submit}
        className="w-full max-w-md bg-white p-6 rounded-2xl shadow-lg space-y-4"
      >
        <h2 className="text-2xl font-semibold text-gray-800 text-center">
          Login
        </h2>

        {/* Email input */}
        <input
          required
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="w-full px-3 py-2 border border-gray-300 rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
        />

        {/* Username input */}
        <input
          required
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="w-full px-3 py-2 border border-gray-300 rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
        />

        {/* Password input */}
        <input
          required
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="w-full px-3 py-2 border border-gray-300 rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
        />

        {/* Submit button */}
        <button
          disabled={loading}
          type="submit"
          className="w-full py-2 px-3 bg-blue-600 hover:bg-blue-700 text-white font-semibold rounded-lg transition disabled:opacity-70"
        >
          {loading ? "Logging in..." : "Login"}
        </button>

        {/* Message */}
        {message && (
          <div
            className={`text-sm text-center ${
              message.toLowerCase().includes("successful")
                ? "text-green-600"
                : "text-red-600"
            }`}
          >
            {message}
          </div>
        )}

        {/* Register link */}
        <div className="text-sm text-center text-gray-600">
          Don’t have an account?{" "}
          <Link to="/register" className="text-blue-600 hover:underline">
            Register
          </Link>
        </div>
      </form>
    </div>
  );
}
