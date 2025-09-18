import { useState } from 'react'
import axios from 'axios'
import { Link } from 'react-router-dom'

export default function Register(){
  const [username,setUsername] = useState('')
  const [email,setEmail] = useState('')
  const [password,setPassword] = useState('')
  const [loading,setLoading] = useState(false)
  const [message,setMessage] = useState<string>('')

  const submit = async (e: React.FormEvent) => {
    e.preventDefault()
    setLoading(true)
    setMessage('')
    try{
      const res = await axios.post('http://localhost:8080/api/auth/register',{username,email,password})
      setMessage(`Account created for ${res.data.username}. You can login now.`)
      setUsername(''); setEmail(''); setPassword('')
    }catch(err:any){
      setMessage(err?.response?.data?.message || 'Registration failed')
    }finally{ setLoading(false) }
  }

  return (
    <div style={{display:'grid',placeItems:'center',minHeight:'calc(100dvh - 60px)',padding:16}}>
      <form onSubmit={submit} style={{width:'100%',maxWidth:420,display:'grid',gap:12,background:'#fff',padding:20,borderRadius:12,boxShadow:'0 6px 30px rgba(0,0,0,0.08)'}}>
        <h2 style={{margin:0}}>Create account</h2>
        <input required placeholder='Username' value={username} onChange={e=>setUsername(e.target.value)} style={inputStyle}/>
        <input required type='email' placeholder='Email' value={email} onChange={e=>setEmail(e.target.value)} style={inputStyle}/>
        <input required placeholder='Password' type='password' value={password} onChange={e=>setPassword(e.target.value)} style={inputStyle}/>
        <button disabled={loading} type='submit' style={buttonStyle}>{loading? 'Creating...' : 'Create account'}</button>
        {message && <div style={{color:'#0a7',fontSize:14}}>{message}</div>}
        <div style={{fontSize:14}}>Already have an account? <Link to="/login">Login</Link></div>
      </form>
    </div>
  )
}

const inputStyle: React.CSSProperties = {
  padding:'10px 12px', border:'1px solid #ddd', borderRadius:8, fontSize:14
}

const buttonStyle: React.CSSProperties = {
  padding:'10px 12px', border:'0', borderRadius:8, background:'#2563eb', color:'#fff', fontWeight:600, cursor:'pointer'
}


