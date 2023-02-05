export const VehicleEntityObj = {
  id: '',
  name: '',
  msidn: '',
  engineStatus: '',
  fleet: '',
  brand: '',
  countryOfOperation: '',
  chassisNumber: '',
  cassisSeries: ''
}

export const ServiceEntityObj = {
  _id: '',
  serviceName: '',
  status: '',
  vehicleId: '',
  lastUpdate: ''
}

export const UserEntityObj = {
  _id: '',
  username: '',
  password: '',
  salt: ''
}

export type VehicleEntity = typeof VehicleEntityObj
export type ServiceEntity = typeof ServiceEntityObj
export type UserEntity = typeof UserEntityObj
