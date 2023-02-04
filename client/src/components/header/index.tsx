import { People } from '@mui/icons-material'

function App () {
  return (
    <div className='flex items-center justify-between h-[var(--header-height)] px-[20px] border-b border-b-gray-300 shadow-sm'>
      <div className='flex items-center'>
        <img
          className='w-[45px] h-[45px]'
          src={require('@/static/images/logo.png')}
          alt='logo'
        />
        <div className='ml-[10px] text-[18px] font-bold'>Volvo</div>
      </div>

      <People className='cursor-pointer' />
    </div>
  )
}

export default App
