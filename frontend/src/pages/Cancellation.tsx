import { useState } from 'react'
import axios from 'axios'

export default function Cancellation(){
  const [pnr,setPnr] = useState('')
  const [info,setInfo] = useState<any>(null)
  const [message,setMessage] = useState('')

  const fetchInfo = async () => {
    try{
      const res = await axios.get(`http://localhost:8080/api/reservations/${pnr}`)
      setInfo(res.data)
      setMessage('')
    }catch{
      setInfo(null)
      setMessage('PNR not found')
    }
  }

  const cancel = async () => {
    try{
      const res = await axios.post(`http://localhost:8080/api/reservations/${pnr}/cancel`)
      setInfo(res.data)
      setMessage('Cancelled')
    }catch{
      setMessage('Cancellation failed')
    }
  }

  return (
    <div style={{display:'grid',gap:8,maxWidth:420}}>
      <input placeholder='Enter PNR' value={pnr} onChange={e=>setPnr(e.target.value)} />
      <div style={{display:'flex',gap:8}}>
        <button onClick={fetchInfo}>Fetch</button>
        <button onClick={cancel}>Cancel</button>
      </div>
      {message && <div>{message}</div>}
      {info && (
        <pre style={{background:'#f5f5f5',padding:8}}>{JSON.stringify(info,null,2)}</pre>
      )}
    </div>
  )
}


