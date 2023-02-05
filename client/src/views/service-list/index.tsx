import { useEffect, useState } from 'react'
import { arrayObject, getKeys } from '@jh-zjz/utils/common'
import { api, ServiceEntity } from '@/plugins/api'
import { ServiceTable } from '@/components'
import { FormControl, InputLabel, MenuItem, Select } from '@mui/material'

function App () {
  const [list, setList] = useState<ServiceEntity[]>([])
  const [status, setStatus] = useState('ALL')
  const [statusList, setStatusList] = useState<string[]>(['ALL'])

  useEffect(() => {
    api('serviceList').then(res => {
      setList(res)

      const _ = getKeys(arrayObject(res, 'status'))
      _.unshift('ALL')
      setStatusList(_)
    })
  }, [])

  return (
    <div>
      <div className='mb-[20px]'>
        <FormControl variant='standard' className='w-[150px]'>
          <InputLabel id='demo-simple-select-label'>Status</InputLabel>
          <Select
            value={status}
            label='Age'
            onChange={e => setStatus(e.target.value)}
          >
            {statusList.map(x => (
              <MenuItem value={x} key={x}>
                {x}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
      </div>

      <ServiceTable
        list={list.filter(x => status === 'ALL' || x.status === status)}
      />
    </div>
  )
}

export default App
