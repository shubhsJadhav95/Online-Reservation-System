import { useEffect, useState } from 'react'
import axios from 'axios'

type Train = { id:number, trainNumber:string, trainName:string, fromStation:string, toStation:string }

export default function Reservation(){
  const [trains,setTrains] = useState<Train[]>([])
  const [trainNumber,setTrainNumber] = useState('')
  const [journeyDate,setJourneyDate] = useState('')
  const [fromStation,setFromStation] = useState('')
  const [toStation,setToStation] = useState('')
  const [trainClass,setTrainClass] = useState('SLEEPER')
  const [message,setMessage] = useState('')

  useEffect(()=>{
    axios.get('http://localhost:8080/api/trains').then(r=>{
      setTrains(r.data)
    }).catch(()=>{})
  },[])

  const submit = async (e: React.FormEvent) => {
    e.preventDefault()
    const userId = localStorage.getItem('userId')
    if(!userId){ setMessage('Please login first'); return }
    try{
      const res = await axios.post('http://localhost:8080/api/reservations',{
        userId: Number(userId), trainNumber, journeyDate, fromStation, toStation, trainClass
      })
      setMessage(`Booked. PNR: ${res.data.pnr}`)
    }catch(err:any){
      setMessage('Booking failed')
    }
  }

  return (
    <form onSubmit={submit} style={{display:'grid',gap:8,maxWidth:420}}>
      <select value={trainNumber} onChange={e=>setTrainNumber(e.target.value)}>
        <option value=''>Select Train</option>
        {trains.map(t=> (
          <option key={t.id} value={t.trainNumber}>{t.trainNumber} - {t.trainName}</option>
        ))}
      </select>
      <input type='date' value={journeyDate} onChange={e=>setJourneyDate(e.target.value)} />
      <input placeholder='From' value={fromStation} onChange={e=>setFromStation(e.target.value)} />
      <input placeholder='To' value={toStation} onChange={e=>setToStation(e.target.value)} />
      <select value={trainClass} onChange={e=>setTrainClass(e.target.value)}>
        {['FIRST_AC','SECOND_AC','THIRD_AC','SLEEPER','CHAIR_CAR'].map(c=> <option key={c}>{c}</option>)}
      </select>
      <button type='submit'>Reserve</button>
      {message && <div>{message}</div>}
    </form>
  )
}


