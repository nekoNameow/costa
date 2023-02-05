import { Navigate, createBrowserRouter } from 'react-router-dom'
import { arrayObject } from '@jh-zjz/utils/common'

import VehicleList from '@/views/vehicle-list'
import VehicleDetail from '@/views/vehicle-detail'
import ServiceList from '@/views/service-list'

export type RoutePath =
  | '/'
  | '/vehicle/list'
  | '/vehicle/detail'
  | '/service/list'

const routes: {
  path: RoutePath
  element: JSX.Element
  meta?: {
    title?: string
    breads?: RoutePath[]
  }
}[] = [
  {
    path: '/',
    element: <Navigate to='/vehicle/list' replace />,
    meta: {
      title: 'Home',
      breads: []
    }
  },
  {
    path: '/vehicle/list',
    element: <VehicleList />,
    meta: {
      title: 'Vehicle List',
      breads: ['/']
    }
  },
  {
    path: '/vehicle/detail',
    element: <VehicleDetail />,
    meta: {
      title: 'Vehicle Detail',
      breads: ['/', '/vehicle/list']
    }
  },
  {
    path: '/service/list',
    element: <ServiceList />,
    meta: {
      title: 'Service List',
      breads: ['/']
    }
  }
]

export const routeMap: Record<RoutePath, typeof routes[number]> = arrayObject(
  routes,
  'path'
)

export const router = createBrowserRouter(routes)

export function navigate (url: RoutePath, params?: string) {
  router.navigate(url + (params ?? ''))
}

export function getUrlParams (key: string) {
  const arr = router.state.location.search.substring(1).split('&')
  const data = arr.map(x => x.split('=')).find(x => x[0] === key)
  return data?.[1] ?? undefined
}
