import { navigate, routeMap, RoutePath, router } from '@/plugins/router'
import { Breadcrumbs, Link, Typography } from '@mui/material'
import { useEffect, useState } from 'react'

function App () {
  const [list, setList] = useState<typeof routeMap[RoutePath][]>([])

  useEffect(() => {
    handle(router.state.location.pathname as RoutePath)
    router.subscribe(item => handle(item.location.pathname as RoutePath))
  }, [])

  function handle (path: RoutePath) {
    const route = routeMap[path]
    if (!route || !route.meta?.breads?.length) {
      return
    }
    const _ = route.meta.breads.map(x => routeMap[x])
    _.push(route)
    setList(_)
  }

  return (
    <Breadcrumbs
      aria-label='breadcrumb'
      className='mb-[20px] pb-[15px] border-b border-dashed border-b-gray-200 text-[14px]'
    >
      {list.map((x, i) =>
        i === list.length - 1 ? (
          <Typography color='text.primary' fontSize='14px' key={x.path}>
            {x.meta?.title ?? '-'}
          </Typography>
        ) : (
          <Link
            underline='hover'
            color='inherit'
            className='cursor-pointer'
            key={x.path}
            onClick={() => navigate(x.path)}
          >
            {x.meta?.title ?? '-'}
          </Link>
        )
      )}
    </Breadcrumbs>
  )
}

export default App
