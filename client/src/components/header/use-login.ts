import { api, setToken } from '@/plugins/api'
import { useState } from 'react'
import { Notify } from './use-notify'

export function useLogin (
  setNotify: React.Dispatch<React.SetStateAction<Notify>>
) {
  const [loginShow, setLoginShow] = useState(false)
  const [loginForm, setLoginForm] = useState({
    username: 'test',
    password: '123456'
  })
  const [loging, setLoging] = useState(false)

  const loginFormStyle = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    boxShadow: 24,
    border: 'none',
    outline: 'medium',
    p: 1
  }

  function login () {
    setLoging(true)
    api('login', loginForm)
      .then(({ str }) => {
        if (!str) {
          setLoging(false)
          setNotify({
            show: true,
            type: 'error',
            message: 'Password is incorrect!'
          })
          return
        }
        setNotify({
          show: true,
          type: 'success',
          message: 'Login successfully!'
        })
        setTimeout(() => setToken(str), 1500)
      })
      .catch(() => setLoging(false))
  }

  return {
    loginShow,
    loginForm,
    loging,
    loginFormStyle,

    setLoginShow,
    setLoginForm,
    setLoging,
    login
  }
}
