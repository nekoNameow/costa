import { ServiceEntityObj, UserEntityObj, VehicleEntityObj } from './entity'

export const APIObj = {
  vehicleList: {
    req: {},
    resp: [VehicleEntityObj],
    cache: true
  },
  vehicle: {
    req: {
      id: ''
    },
    resp: VehicleEntityObj,
    cache: true
  },
  vehicleServices: {
    req: {
      id: ''
    },
    resp: [ServiceEntityObj],
    cache: true
  },
  serviceList: {
    req: {},
    resp: [ServiceEntityObj],
    cache: true
  },
  login: {
    req: {
      username: '',
      password: ''
    },
    resp: {
      str: ''
    },
    cache: false
  },
  checkLogin: {
    req: {},
    resp: UserEntityObj,
    cache: false
  }
}

export type API = typeof APIObj

export type ApiKeys = keyof API
