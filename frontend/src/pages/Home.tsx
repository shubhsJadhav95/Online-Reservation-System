import { useEffect, useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { FaTicketAlt, FaTimesCircle } from 'react-icons/fa'
import axios from 'axios'

export default function Home(){
  const navigate = useNavigate()
  const [authed, setAuthed] = useState(!!localStorage.getItem('userId'))
  const [username, setUsername] = useState('')
  const [loginForm, setLoginForm] = useState({ username:'', password:'' })
  const [loading,setLoading] = useState(false)
  const [message,setMessage] = useState('')

  useEffect(()=>{
    setAuthed(!!localStorage.getItem('userId'))
  },[])

  const onLogin = async (e: React.FormEvent) => {
    e.preventDefault()
    setLoading(true); setMessage('')
    try{
      const res = await axios.post('http://localhost:8080/api/auth/login', loginForm)
      localStorage.setItem('userId', String(res.data.id))
      setUsername(res.data.username)
      setAuthed(true)
    }catch(err:any){
      setMessage(err?.response?.data?.message || 'Login failed')
    }finally{ setLoading(false) }
  }

  const onLogout = () => {
    localStorage.removeItem('userId')
    setAuthed(false)
  }

  if(!authed){
    return (
      <div className="stack" style={{display:'grid',placeItems:'center',minHeight:'calc(100dvh - 80px)'}}>
        <form onSubmit={onLogin} className="card slide-up" style={{width:'100%',maxWidth:420,display:'grid',gap:12,padding:20}}>
          <h2 style={{margin:0}}>Login</h2>
          <input required placeholder='Username' value={loginForm.username} onChange={e=>setLoginForm(f=>({...f, username:e.target.value}))} style={inputStyle}/>
          <input required type='password' placeholder='Password' value={loginForm.password} onChange={e=>setLoginForm(f=>({...f, password:e.target.value}))} style={inputStyle}/>
          <button disabled={loading} type='submit' style={buttonStyle}>{loading? 'Signing in...' : 'Login'}</button>
          {message && <div style={{color:'#d00',fontSize:14}}>{message}</div>}
          <div style={{fontSize:14}}>No account? <Link to="/register">Create one</Link></div>
        </form>
      </div>
    )
  }

  const Card = ({ to, title, desc, Icon, color }: { to:string, title:string, desc:string, Icon:any, color:string }) => (
    <Link to={to} className="slide-up" style={{
      display:'grid', gap:6, padding:16, border:'1px solid #eee', borderRadius:12,
      textDecoration:'none', color:'var(--text)', background:'#fff', boxShadow:'var(--shadow)'
    }}>
      <div style={{display:'flex', alignItems:'center', gap:10, fontWeight:700}}>
        <Icon color={color}/><span>{title}</span>
      </div>
      <div style={{fontSize:14, color:'var(--muted)'}}>{desc}</div>
    </Link>
  )

  return (
    <div className="stack">
      <div className="fade-in" style={{display:'flex', alignItems:'center', justifyContent:'space-between'}}>
        <h2 style={{margin:'8px 0'}}>Welcome {username || 'Passenger'} 🚆</h2>
        <button onClick={onLogout} style={{...buttonStyle, background:'#334155'}}>Logout</button>
      </div>
      <div className="grid-auto">
        <Card to='/reserve' title='Make Reservation' desc='Book tickets with passenger details' Icon={FaTicketAlt} color='#2563eb' />
        <Card to='/cancel' title='Cancel Reservation' desc='Cancel using PNR' Icon={FaTimesCircle} color='#dc2626' />
      </div>
    </div>
  )
}

const inputStyle: React.CSSProperties = { padding:'10px 12px', border:'1px solid #ddd', borderRadius:8, fontSize:14 }
const buttonStyle: React.CSSProperties = { padding:'10px 12px', border:'0', borderRadius:8, background:'#2563eb', color:'#fff', fontWeight:600, cursor:'pointer' }


