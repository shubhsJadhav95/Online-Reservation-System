import { Link, useLocation } from 'react-router-dom'
import { FaTrain, FaHome, FaSignInAlt, FaUserPlus, FaTicketAlt, FaTimesCircle, FaSignOutAlt } from 'react-icons/fa'

export default function NavBar(){
  const { pathname } = useLocation()
  const NavLink = ({ to, label, Icon }: { to:string, label:string, Icon:any }) => (
    <Link to={to} style={{
      display:'flex', alignItems:'center', gap:8, padding:'8px 12px', borderRadius:8,
      textDecoration:'none', color: pathname===to ? '#0b5' : 'var(--text)', background: pathname===to ? 'rgba(11,170,100,0.12)' : 'transparent'
    }}>
      <Icon/>{label}
    </Link>
  )

  return (
    <header className="fade-in" style={{position:'sticky', top:0, zIndex:10, background:'#fff', borderBottom:'1px solid #eee'}}>
      <nav className="container" style={{ padding:12, display:'flex', gap:12, flexWrap:'wrap', alignItems:'center', justifyContent:'space-between'}}>
        <div className="pulse" style={{display:'flex', alignItems:'center', gap:10, color:'var(--primary)', fontWeight:800, fontSize:18}}>
          <FaTrain/> RailReserve
        </div>
        <div style={{display:'flex', gap:8, flexWrap:'wrap'}}>
          <NavLink to='/' label='Home' Icon={FaHome} />
          <NavLink to='/login' label='Login' Icon={FaSignInAlt} />
          <NavLink to='/register' label='Create Account' Icon={FaUserPlus} />
          <NavLink to='/reserve' label='Reserve' Icon={FaTicketAlt} />
          <NavLink to='/cancel' label='Cancel' Icon={FaTimesCircle} />
          <NavLink to='/logout' label='Logout' Icon={FaSignOutAlt} />
        </div>
      </nav>
    </header>
  )
}


