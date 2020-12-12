import axios from 'axios';

export default{

    getCollections(){
        return axios.get('/collections/public');
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