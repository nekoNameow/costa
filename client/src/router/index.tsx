import { Navigate, createBrowserRouter } from 'react-router-dom'

import VehicleList from './vehicle-list'
import VehicleDetail from './vehicle-detail'
import ServiceList from './service-list'

export const router = createBrowserRouter([
  {
    path: '/',
    element: <Navigate to='/vehicle/list' replace />
  },
  {
    path: '/vehicle/list',
    element: <VehicleList />
  },
  {
    path: '/vehicle/detail',
    element: <VehicleDetail />
  },
  {
    path: '/service/list',
    element: <ServiceList />
  }
])

// export const routes = (
//   <BrowserRouter>
//     <Routes>
//       <Route path='/' element={<Navigate to='/vehicle/list' replace />} />
//       <Route path='/vehicle/list' element={<VehicleList />} />
//       <Route path='/vehicle/detail' element={<VehicleDetail />} />
//       <Route path='/service/list' element={<ServiceList />} />
//     </Routes>
//   </BrowserRouter>
// )
