import { clearToken } from '@/plugins/api'
import { store } from '@/plugins/store'
import { People } from '@mui/icons-material'
import {
  Alert,
  Button,
  Card,
  CardContent,
  CardHeader,
  Fade,
  Modal,
  Snackbar,
  TextField
} from '@mui/material'
import { useLogin } from './use-login'
import { useNotify } from './use-notify'

function App () {
  const user = store.user

  const { notify, setNotify } = useNotify()

  const {
    loginShow,
    loginForm,
    loging,
    loginFormStyle,
    setLoginShow,
    setLoginForm,
    login
  } = useLogin(setNotify)

  return (
    <div className='flex items-center justify-between h-[var(--header-height)] px-[20px] border-b border-b-gray-300 shadow-sm'>
      <div className='flex items-center'>
        <img
          className='w-[45px] h-[45px]'
          src={require('@/static/images/logo.png')}
          alt='logo'
        />
        <div className='ml-[10px] text-[18px] font-bold'>Volvo</div>
      </div>

      <div
        className={
          'flex items-center hover:text-primary transition-all cursor-pointer ' +
          (user.username ? 'text-primary' : 'text-gray-500')
        }
      >
        <People className='cursor-pointer' />
        <div className='ml-[5px]'>
          {user.username ? (
            <div onClick={() => clearToken()}>{user.username}</div>
          ) : (
            <div onClick={() => setLoginShow(true)}>Login</div>
          )}
        </div>
      </div>

      <Modal
        aria-labelledby='login-form'
        aria-describedby='login-form'
        open={loginShow}
        onClose={() => setLoginShow(false)}
        closeAfterTransition
      >
        <Fade in={loginShow}>
          <Card sx={loginFormStyle}>
            <CardHeader title='Login Form' />
            <CardContent className='pt-0'>
              <TextField
                fullWidth
                label='Username'
                variant='standard'
                value={loginForm.username}
                onChange={e =>
                  setLoginForm({ ...loginForm, username: e.target.value })
                }
              />
              <TextField
                fullWidth
                className='mt-[20px]'
                label='Password'
                variant='standard'
                type='password'
                value={loginForm.password}
                onChange={e =>
                  setLoginForm({ ...loginForm, password: e.target.value })
                }
              />
              <Button
                className='mt-[30px]'
                variant='contained'
                onClick={() => login()}
                disabled={loging}
              >
                {loging ? 'Submitting' : 'Login'}
              </Button>
            </CardContent>
          </Card>
        </Fade>
      </Modal>

      <Snackbar
        open={notify.show}
        autoHideDuration={6000}
        onClose={() => setNotify({ ...notify, show: false })}
      >
        <Alert
          onClose={() => setNotify({ ...notify, show: false })}
          severity={notify.type}
          sx={{ width: '100%' }}
        >
          {notify.message}
        </Alert>
      </Snackbar>
    </div>
  )
}

export default App
