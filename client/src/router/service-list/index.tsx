import Button from '@mui/material/Button'
import { useNavigate } from 'react-router-dom'

function App () {
  const navigate = useNavigate()

  return (
    <div className='App'>
      <Button
        variant='contained'
        onClick={() => {
          navigate('/')
        }}
      >
        Hello world333
      </Button>
    </div>
  )
}

export default App
