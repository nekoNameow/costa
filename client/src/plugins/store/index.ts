import { api, UserEntity, VehicleEntity } from '../api'

export const store = {
  vehicleList: [] as VehicleEntity[],
  vehicleMap: {} as Record<string, VehicleEntity>,
  user: {} as UserEntity,

  async updateVehicleList () {
    const list = await api('vehicleList')
    this.vehicleList = list
    this.vehicleMap = {}
    list.forEach(x => (this.vehicleMap[x.id] = x))
  },

  async updateUser () {
    this.user = (await api('checkLogin')) ?? {}
  }
}
