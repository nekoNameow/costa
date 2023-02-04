import ReactDOM from 'react-dom/client'
import { RouterProvider } from 'react-router-dom'
import { VBreadcrumbs, VHeader, VSider } from '@/components'
import { store } from './plugins/store'
import { router } from '@/plugins/router'

import './static/css/index.css'

async function start () {
  await store.updateVehicleList()

  const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
  )
  root.render(
    <div>
      <VHeader />

      <div className='flex'>
        <VSider />

        <div className='w-[var(--container-width)] h-[var(--container-height)] overflow-y-auto p-[20px]'>
          <VBreadcrumbs />
          <RouterProvider router={router} />
        </div>
      </div>
    </div>
  )
}

start()
