import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Button,
  Link
} from '@mui/material'
import { navigate } from '@/plugins/router'
import { store } from '@/plugins/store'

function App () {
  const list = store.vehicleList

  function toDetail (id: string) {
    navigate('/vehicle/detail', '?id=' + id)
  }

  return (
    <Table aria-label='vehicle list'>
      <TableHead>
        <TableRow>
          <TableCell sx={{ fontWeight: 'bold' }}>ID</TableCell>
          <TableCell sx={{ fontWeight: 'bold' }}>Name</TableCell>
          <TableCell sx={{ fontWeight: 'bold' }}>Brand</TableCell>
          <TableCell sx={{ fontWeight: 'bold' }}>Engine Status</TableCell>
          <TableCell sx={{ fontWeight: 'bold' }}>Operation</TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {list.map(row => (
          <TableRow key={row.id}>
            <TableCell>
              <Link onClick={() => toDetail(row.id)} className='cursor-pointer'>
                {row.id ?? '-'}
              </Link>
            </TableCell>
            <TableCell>{row.name ?? '-'}</TableCell>
            <TableCell>{row.brand ?? '-'}</TableCell>
            <TableCell>{row.engineStatus ?? '-'}</TableCell>
            <TableCell>
              <Button onClick={() => toDetail(row.id)} className='normal-case'>
                View
              </Button>
            </TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  )
}

export default App
