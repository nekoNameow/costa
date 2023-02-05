import { useState } from 'react'

export type Notify = {
  show: boolean
  type: 'success' | 'error' | 'info' | 'warning'
  message: string
}

export function useNotify () {
  const [notify, setNotify] = useState<Notify>({
    show: false,
    type: 'success',
    message: ''
  })

  return {
    notify,

    setNotify
  }
}
