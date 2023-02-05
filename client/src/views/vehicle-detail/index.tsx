import { useEffect, useState } from 'react'
import { getUrlParams, navigate } from '@/plugins/router'
import { api, ServiceEntity, VehicleEntity } from '@/plugins/api'
import { store } from '@/plugins/store'
import { ServiceTable } from '@/components'
import {
  Card,
  CardContent,
  CardHeader,
  Table,
  TableBody,
  TableCell,
  TableRow
} from '@mui/material'

function App () {
  const [detail, setDetail] = useState<VehicleEntity>()
  const [services, setServices] = useState<ServiceEntity[]>([])

  useEffect(() => {
    const id = getUrlParams('id')
    if (!id || !store.vehicleMap[id]) {
      navigate('/vehicle/list')
      return
    }
    setDetail(store.vehicleMap[id])
    api('vehicleServices', { id }).then(res => {
      setServices(res.filter(x => x.status === 'ACTIVE'))
    })
  }, [])

  const Detail = () => (
    <Card raised>
      <CardHeader title='Vehicle Detail' />
      <CardContent>
        <Table aria-label='vehicle detail'>
          <TableBody>
            <TableRow>
              <TableCell sx={{ fontWeight: 'bold' }}>Name</TableCell>
              <TableCell sx={{ fontWeight: 'bold' }}>Brand</TableCell>
              <TableCell sx={{ fontWeight: 'bold' }}>Engine Status</TableCell>
              <TableCell sx={{ fontWeight: 'bold' }}>Cassis Series</TableCell>
            </TableRow>
            <TableRow>
              <TableCell>{detail?.name ?? '-'}</TableCell>
              <TableCell>{detail?.brand ?? '-'}</TableCell>
              <TableCell>{detail?.engineStatus ?? '-'}</TableCell>
              <TableCell>{detail?.cassisSeries ?? '-'}</TableCell>
            </TableRow>
            <TableRow>
              <TableCell sx={{ fontWeight: 'bold' }}>Chassis Number</TableCell>
              <TableCell sx={{ fontWeight: 'bold' }}>
                Country Of Operation
              </TableCell>
              <TableCell sx={{ fontWeight: 'bold' }}>Fleet</TableCell>
              <TableCell sx={{ fontWeight: 'bold' }}>Msidn</TableCell>
            </TableRow>
            <TableRow>
              <TableCell>{detail?.chassisNumber ?? '-'}</TableCell>
              <TableCell>{detail?.countryOfOperation ?? '-'}</TableCell>
              <TableCell>{detail?.fleet ?? '-'}</TableCell>
              <TableCell>{detail?.msidn ?? '-'}</TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </CardContent>
    </Card>
  )

  const Services = () => (
    <Card raised className='mt-[20px]'>
      <CardHeader title='Vehicle Services' />
      <CardContent>
        <ServiceTable list={services} />
      </CardContent>
    </Card>
  )

  return (
    <div>
      <Detail />
      <Services />
    </div>
  )
}

export default App
