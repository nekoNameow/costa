import ReactDOM from 'react-dom/client'
import { RouterProvider } from 'react-router-dom'
import { router } from '@/router'
import { VHeader, VSider } from '@/components'

import './static/css/index.css'

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement)
root.render(
  <div>
    <VHeader />

    <div className='flex'>
      <VSider />

      <div className='w-[var(--container-width)] h-[var(--container-height)] overflow-y-auto p-[20px]'>
        <RouterProvider router={router} />
      </div>
    </div>
  </div>
)
