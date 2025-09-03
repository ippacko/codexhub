import React from 'react'
import { createRoot } from 'react-dom/client'
import { useState } from 'react'

function App(){
  const [token, setToken] = useState<string | null>(null)
  const [books, setBooks] = useState<any[]>([])

  async function login(){
    const res = await fetch('/auth/login', {
      method: 'POST',
      headers: {'Content-Type':'application/json'},
      body: JSON.stringify({email:'admin@codexhub.local', password:'admin123'})
    })
    const data = await res.json()
    setToken(data.token)
  }

  async function loadBooks(){
    const res = await fetch('/catalog/books?page=0&size=10', {
      headers: token ? {Authorization: `Bearer ${token}`} : {}
    })
    const data = await res.json()
    setBooks(data.content || [])
  }

  return (
    <div style={{fontFamily: 'system-ui', padding: 24}}>
      <h1>CodexHub Admin</h1>
      {!token ? <button onClick={login}>Login as admin</button> :
        <>
          <p>Token acquired</p>
          <button onClick={loadBooks}>Load books</button>
          <ul>
            {books.map((b:any) => <li key={b.id}>{b.title}</li>)}
          </ul>
        </>
      }
    </div>
  )
}

createRoot(document.getElementById('root')!).render(<App/>)
