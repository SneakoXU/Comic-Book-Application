import axios from 'axios';

export default{

    getCollections(){
        return axios.get('/collections');
    },

    addCollection(collection){
        return axios.post('/collections', collection)
    },

    viewCollection(id){
        return axios.get(`/collections/${id}`)
    },

    deleteCollection(id){
        return axios.delete(`/collections/${id}`)
    }


}