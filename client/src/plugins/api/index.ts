import nProgress from 'nprogress'
import { ApolloClient, InMemoryCache, gql } from '@apollo/client'
import { API, ApiKeys, APIObj } from './types'
import { getKeys } from '@jh-zjz/utils/common'

const BASE_URL = 'http://localhost:8081/graphql'
const client = new ApolloClient({
  cache: new InMemoryCache(),
  uri: BASE_URL,
  headers: {
    token: getToken()
  }
})

export function api<T extends ApiKeys> (
  key: T,
  params?: API[T]['req'],
  fields?: Array<
    API[T]['resp'] extends Array<object>
      ? keyof API[T]['resp'][number]
      : keyof API[T]['resp']
  >
): Promise<API[T]['resp']> {
  nProgress.start()
  return new Promise((resolve, reject) => {
    const resp = APIObj[key].resp
    fields = fields ?? getKeys(Array.isArray(resp) ? resp[0] ?? {} : resp)
    const paramKey = getKeys(params ?? {})

    let query = '{\n' + key
    if (params && paramKey.length) {
      const paramStr = paramKey.map(x => `${x}: "${params[x]}"`)
      query += `(${paramStr})`
    }
    query += '{\n'
    query += fields.join('\n') + '\n'
    query += '}\n'
    query += '}'

    client
      .query({
        query: gql(query),
        fetchPolicy: APIObj[key].cache ? 'cache-first' : 'network-only'
      })
      .then(({ data, error, errors }) => {
        if (errors?.length) {
          reject(errors[0].extensions)
        } else if (error) {
          reject(error)
        } else {
          resolve(data?.[key] ?? null)
        }
      })
      .finally(nProgress.done)
  })
}

export function getToken () {
  return localStorage.getItem('TOKEN') ?? ''
}

export function setToken (token: string) {
  localStorage.setItem('TOKEN', token)
  window.location.reload()
}

export function clearToken () {
  localStorage.removeItem('TOKEN')
  window.location.reload()
}

export * from './types'
