import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  getUserById(id)
  {
    return axios.get(`/user/${id}`)
  },

  getIdByUsername(username)
  {
    return axios.get(`/user/get/id/${username}`)
  },

  getUsers(name, page)
  {
    return axios.get(`/user/${name}30/${page}`)
  },

  getFriends()
  {
    return axios.get(`/user/friends`)
  },

  addFriend(id)
  {
    return axios.post(`/user/friends/add/${id}`)
  },

  removeFriend(id)
  {
    return axios.post(`/user/friends/remove/${id}`)
  },

  getIncoming()
  {
    return axios.get('/user/friends/request/incoming')
  },

  getOutgoing()
  {
    return axios.get('/user/friends/request/outgoing')
  },

  denyIncoming(id)
  {
    return axios.post(`/user/friends/request/deny/${id}`)
  },

  acceptIncoming(id)
  {
    return axios.post(`/user/friends/request/accept/${id}`)
  },

  cancelOutgoing(id)
  {
    return axios.post(`/user/friends/request/cancel/${id}`)
  },

  acknowledgeOutgoing(id)
  {
    return axios.post(`/user/friends/request/acknowledge/${id}`)
  },
  

  

}
