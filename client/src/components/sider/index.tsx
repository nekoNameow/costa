import { DirectionsCar, RvHookup } from '@mui/icons-material'
import { MenuList, MenuItem, ListItemIcon, ListItemText } from '@mui/material'
import { navigate, RoutePath, router } from '@/plugins/router'
import { useEffect, useState } from 'react'

function App () {
  const list: {
    icon: any
    label: string
    path: RoutePath
    group: string
  }[] = [
    {
      icon: DirectionsCar,
      label: 'Vehicles',
      path: '/vehicle/list',
      group: 'vehicle'
    },
    {
      icon: RvHookup,
      label: 'Services',
      path: '/service/list',
      group: 'service'
    }
  ]

  const [current, setCurrenct] = useState(router.state.location.pathname)
  useEffect(
    () =>
      router.subscribe(state => {
        setCurrenct(state.location.pathname)
      }),
    []
  )

  return (
    <MenuList className='w-[var(--sider-width)] h-[var(--container-height)] overflow-y-auto border-r border-r-gray-300 shadow-sm'>
      {list.map(item => (
        <MenuItem
          className='h-[50px]'
          key={item.label}
          onClick={() => navigate(item.path)}
          selected={current.includes(item.group)}
        >
          <ListItemIcon>
            <item.icon />
          </ListItemIcon>
          <ListItemText>{item.label}</ListItemText>
        </MenuItem>
      ))}
    </MenuList>
  )
}

export default App
