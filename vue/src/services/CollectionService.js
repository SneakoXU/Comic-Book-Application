import axios from 'axios';

export default{

    getCollections(){
        return axios.get('/collections/public');
    },

    getCollectionsPages(name, page){
        return axios.get(`/collections/${name}30/${page}`);
    },

    getPublicCollections(){
        return axios.get('/collections/public');
    },

    getLimitedCollections(limit){
        return axios.get(`/collections/public/${limit}`);
    },

    getCollectionsById(id){
        return axios.get(`/collections/${id}`);
    },

    getCollectionsByOwner(username){
        return axios.get(`/collections/owner/${username}`);
    },

    addComicToCollection(collectionId, comicId){
        return axios.post(`/collections/${collectionId}/add/${comicId}`);
    },

    removeComicFromCollection(collectionId, comicId){
        return axios.post(`/collections/${collectionId}/add/${comicId}`);
    },

    addCollection(collection){
        console.log(collection.isPublic);
        return axios.post('/collections/create', collection);   
    },

    getCollectionThumbnail(id) {
        return axios.get(`/collections/thumbnail/${id}`);
    },
    getUserById(id) {
        return axios.get(`//${id}`);
    },

    viewCollection(id){
        return axios.get(`/collections/${id}`)
    },

    deleteCollection(id){
        return axios.delete(`/collections/${id}`)
    }


}