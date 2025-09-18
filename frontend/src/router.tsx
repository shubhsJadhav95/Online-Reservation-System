import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import Home from './pages/Home'
import Login from './pages/Login'
import Register from './pages/Register'
import Reservation from './pages/Reservation'
import Cancellation from './pages/Cancellation'

const RequireAuth = ({ children }: { children: JSX.Element }) => {
  const isAuthed = !!localStorage.getItem('userId')
  return isAuthed ? children : <Navigate to='/' replace />
}

export default function AppRouter() {
  return (
    <BrowserRouter>
      <main className="container fade-in" style={{ paddingTop: 12 }}>
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/register' element={<Register />} />
          <Route path='/reserve' element={<RequireAuth><Reservation /></RequireAuth>} />
          <Route path='/cancel' element={<RequireAuth><Cancellation /></RequireAuth>} />
        </Routes>
      </main>
    </BrowserRouter>
  )
}


