import { jwtDecode } from 'jwt-decode'

interface JwtPayload {
  exp: number
}

export function isAuthenticated(): boolean {
  const token = localStorage.getItem('jwt')
  if (!token) {
    return false
  }

  try {
    const { exp } = jwtDecode<JwtPayload>(token)
    if (Date.now()/1000 >= exp) {
      localStorage.removeItem('jwt')
      return false
    }
    return true
  } catch (error) {
    localStorage.removeItem('jwt')
    return false
  }
}
