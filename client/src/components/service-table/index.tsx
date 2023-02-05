import { ServiceEntity } from '@/plugins/api'
import { Table, TableBody, TableCell, TableHead, TableRow } from '@mui/material'
import { forwardRef } from 'react'

export default forwardRef<
  null,
  {
    list: ServiceEntity[]
  }
>((props, ref) => {
  const { list } = props

  return (
    <Table>
      <TableHead>
        <TableRow>
          <TableCell sx={{ fontWeight: 'bold' }}>Service Name</TableCell>
          <TableCell sx={{ fontWeight: 'bold' }}>Status</TableCell>
          <TableCell sx={{ fontWeight: 'bold' }}>Last Update</TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {list.map(x => (
          <TableRow key={x._id}>
            <TableCell>{x.serviceName}</TableCell>
            <TableCell>{x.status}</TableCell>
            <TableCell>{x.lastUpdate}</TableCell>
          </TableRow>
        ))}
        {!list.length ? (
          <TableRow>
            <TableCell className='text-gray-400' align='center' colSpan={3}>
              No Service
            </TableCell>
          </TableRow>
        ) : null}
      </TableBody>
    </Table>
  )
})
