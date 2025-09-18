import { useNavigate, Link } from "react-router-dom";
import { Mail, Lock, Train } from "lucide-react";

export default function Login() {
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();
    navigate("/home");
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-r from-blue-500 via-indigo-500 to-purple-600 px-4">
      <div className="bg-white shadow-xl rounded-2xl p-8 w-full max-w-md">
        {/* Header */}
        <div className="text-center mb-6">
          <Train className="mx-auto text-indigo-600" size={50} />
          <h1 className="text-2xl font-bold text-gray-800 mt-2">
            Welcome Back 🚆
          </h1>
          <p className="text-gray-500 text-sm">Login to continue your journey</p>
        </div>

        {/* Login Form */}
        <form onSubmit={handleLogin} className="space-y-4">
          {/* Email */}
          <div className="flex items-center border rounded-lg px-3 py-2 focus-within:ring-2 focus-within:ring-indigo-400">
            <Mail className="text-gray-400 mr-2" size={20} />
            <input
              type="email"
              placeholder="Enter your email"
              className="flex-1 outline-none text-gray-700"
              required
            />
          </div>

          {/* Password */}
          <div className="flex items-center border rounded-lg px-3 py-2 focus-within:ring-2 focus-within:ring-indigo-400">
            <Lock className="text-gray-400 mr-2" size={20} />
            <input
              type="password"
              placeholder="Enter your password"
              className="flex-1 outline-none text-gray-700"
              required
            />
          </div>

          {/* Submit Button */}
          <button
            type="submit"
            className="w-full bg-indigo-600 text-white py-2 rounded-lg font-semibold hover:bg-indigo-700 transition shadow-md"
          >
            Login
          </button>
        </form>

        {/* Footer Links */}
        <p className="text-sm text-center text-gray-600 mt-4">
          Don’t have an account?{" "}
          <Link to="/signup" className="text-indigo-600 font-semibold hover:underline">
            Create one
          </Link>
        </p>
      </div>
    </div>
  );
}
